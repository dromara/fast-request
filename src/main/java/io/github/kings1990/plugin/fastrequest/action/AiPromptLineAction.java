package io.github.kings1990.plugin.fastrequest.action;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.AiPromptConfig;
import io.github.kings1990.plugin.fastrequest.model.AiSetting;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.util.RfrNotificationUtil;
import io.github.kings1990.plugin.fastrequest.util.ToolWindowUtil;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AiPromptLineAction extends AnAction {
    private static final Logger LOGGER = Logger.getInstance(AiPromptLineAction.class);
    private boolean enabled = true;
    private String title;
    private Integer index;

    public AiPromptLineAction(String title, String description, Integer index) {
        super(title, description, null);
        this.title = title;
        this.index = index;
    }

    /**
     *
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        String selectedText = getSelectedText(e);
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        List<AiPromptConfig> aiPromptList = config.getAiPromptList();
        if (CollectionUtils.isEmpty(aiPromptList)) {
            return;
        }
        AiPromptConfig aiPromptConfig = aiPromptList.get(index);
        if (aiPromptConfig == null) {
            return;
        }
        String value = aiPromptConfig.getPrompt();
        if (StringUtils.isBlank(value)) {
            NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Copy success", MessageType.INFO).notify(project);
            RfrNotificationUtil.buildError(MyResourceBundleUtil.getKey("PromptCanNotBeEmpty")).notify(project);
            return;
        }
        String finalPrompt = value.replaceAll("\\$\\{SELECTION}", selectedText);

        AiSetting aiSetting = config.getAiSetting();

        if (aiSetting == null || StringUtils.isBlank(aiSetting.getApiLink()) || StringUtils.isBlank(aiSetting.getToken())) {
            RfrNotificationUtil.buildError(MyResourceBundleUtil.getKey("PleaseSetAIConfig")).notify(project);
            return;
        }

        String originText = e.getData(CommonDataKeys.EDITOR).getDocument().getText();


        String summary = aiPromptConfig.getSummary();
        String preQuery = aiPromptConfig.getPreQuery();
        String preAnswer = aiPromptConfig.getPreAnswer();
        ThreadUtil.execAsync(() -> {
            try {
                if (aiSetting.getType() == 2) {
                    sendRequestAndReplaceCodegeeX(project, e.getData(CommonDataKeys.EDITOR), finalPrompt, summary, preQuery, preAnswer, aiSetting);
                } else {
                    sendRequestAndReplaceOpenAi(project, e.getData(CommonDataKeys.EDITOR), finalPrompt, summary, preQuery, preAnswer, aiSetting);
                }

            } catch (Exception exception) {
                LOGGER.error(exception);
            }
        });

    }

    public void sendRequestAndReplaceCodegeeX(Project project, Editor editor, String finalPrompt, String summary, String preQuery, String preAnswer, AiSetting aiSetting) {
        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(project);
        fastRequestToolWindow.runOtherStart();
        enabled = false;

        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
        
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("prompt", finalPrompt);
        if(StringUtils.isNotBlank(preAnswer)){
            messageMap.put("history", Lists.newArrayList(
                    ImmutableMap.<String, Object>builder().put("query", summary).put("answer", "ok").build(),
                    ImmutableMap.<String, Object>builder().put("query", preQuery).put("answer", preAnswer).build()
            ));
        }
        String jsonBody = JSON.toJSONString(messageMap);
        RequestBody requestBody = RequestBody.create(jsonBody, jsonMediaType);

        // 定义see接口
        Request request = new Request.Builder().url(aiSetting.getApiLink())
                .header("Code-Token", aiSetting.getToken())
                .header("Content-Type", "application/json")
                .post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        // 实例化EventSource，注册EventSource监听器

        final boolean[] taskFlag = {true};
        Task.Backgroundable task = new Task.Backgroundable(project, "AI writing...") {

            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                for (int i = 0; i < 1200; ++i) {
                    if (indicator.isCanceled()) {
                        taskFlag[0] = false;
                        fastRequestToolWindow.runOtherStop();
                        break;
                    }
                    if (!taskFlag[0]) {
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        };

        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {

            private String data;
            boolean firstFlag = true;

            @Override
            public void onOpen(EventSource eventSource, Response response) {
            }

            @Override
            public void onEvent(EventSource eventSource, String id, String type, String replaceText) {
                try {
                    if (!taskFlag[0]) {
                        eventSource.cancel();
                        okHttpClient.dispatcher().executorService().shutdown();
                        fastRequestToolWindow.runOtherStop();
                        enabled = true;
                        return;
                    }

                    ApplicationManager.getApplication().invokeLater(() -> {
                        WriteCommandAction.runWriteCommandAction(project, () -> {
                            SelectionModel selectionModel = editor.getSelectionModel();
                            int startOffset = selectionModel.getSelectionStart();
                            int endOffset = selectionModel.getSelectionEnd();
                            CaretModel caretModel = editor.getCaretModel();

                            if (firstFlag) {
                                editor.getDocument().deleteString(startOffset, endOffset);
                                editor.getDocument().insertString(startOffset, replaceText);
                                caretModel.moveToOffset(startOffset + replaceText.length());
                                firstFlag = false;
                            } else {
                                String appendString = replaceText.replace(data, "");
                                editor.getDocument().insertString(editor.getCaretModel().getOffset(), appendString);
                                caretModel.moveToOffset(editor.getCaretModel().getOffset() + appendString.length());
                            }
                            data = replaceText;
                        });
                    });

                    Thread.sleep(100);
                    WriteCommandAction.runWriteCommandAction(
                            project,
                            () -> {
                                PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
                                CodeStyleManager.getInstance(project).reformat(psiFile);
                            }
                    );

                } catch (Exception ee) {
                    LOGGER.error(ee.getMessage());
                    fastRequestToolWindow.runOtherStop();
                    enabled = true;
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                eventSource.cancel();
                okHttpClient.dispatcher().executorService().shutdown();
                fastRequestToolWindow.runOtherStop();
                enabled = true;
                taskFlag[0] = false;
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {

                try {
                    String msg = response.body().string();
                    if (StringUtils.isNotBlank(msg)) {
                        RfrNotificationUtil.buildError(msg).notify(project);
                    }
                } catch (IOException e) {

                }
                fastRequestToolWindow.runOtherStop();
                enabled = true;
                taskFlag[0] = false;
            }
        });
        realEventSource.connect(okHttpClient);//真正开始请求的一步
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }

    public void sendRequestAndReplaceOpenAi(Project project, Editor editor, String finalPrompt, String summary, String preQuery, String preAnswer, AiSetting aiSetting) {
        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(project);
        fastRequestToolWindow.runOtherStart();
        enabled = false;

        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

        List<Map<String,String>> messages = Lists.newArrayList(
                ImmutableMap.<String, String>builder().put("role", "system").put("content", summary).build()
        );
        if (StringUtils.isNotBlank(preQuery)) {
            messages.add(ImmutableMap.<String, String>builder().put("role", "user").put("content", preQuery).build());
            if(StringUtils.isNotBlank(preAnswer)){
                messages.add(ImmutableMap.<String, String>builder().put("role", "assistant").put("content", preAnswer).build());
            }
        }
        messages.add(ImmutableMap.<String, String>builder().put("role", "user").put("content", finalPrompt).build());
        
        ImmutableMap<String, Object> paramMap = ImmutableMap.<String, Object>builder().put("model", aiSetting.getModel()).put("stream", true)
                .put("messages", messages)
                .build();
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(paramMap), jsonMediaType);

        // 定义see接口
        Request request = new Request.Builder().url(aiSetting.getApiLink())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + aiSetting.getToken())
                .post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        // 实例化EventSource，注册EventSource监听器

        final boolean[] taskFlag = {true};
        Task.Backgroundable task = new Task.Backgroundable(project, "AI writing...") {

            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                for (int i = 0; i < 1200; ++i) {
                    if (indicator.isCanceled()) {
                        taskFlag[0] = false;
                        fastRequestToolWindow.runOtherStop();
                        break;
                    }
                    if (!taskFlag[0]) {
                        break;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        };

        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {

            boolean firstFlag = true;

            @Override
            public void onOpen(EventSource eventSource, Response response) {
            }

            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                try {
                    if (!taskFlag[0]) {
                        eventSource.cancel();
                        okHttpClient.dispatcher().executorService().shutdown();
                        fastRequestToolWindow.runOtherStop();
                        enabled = true;
                        return;
                    }
                    String content;
                    try {
                        JSONObject jsonObject = JSON.parseObject(data);

                        // 提取 delta 对象
                        JSONArray choicesArray = jsonObject.getJSONArray("choices");
                        JSONObject deltaObject = choicesArray.getJSONObject(0).getJSONObject("delta");

                        // 提取 content 字段的值
                        content = deltaObject.getString("content");
                        if (content == null) {
                            return;
                        }
                    } catch (Exception e) {
                        return;
                    }


                    ApplicationManager.getApplication().invokeLater(() -> {
                        WriteCommandAction.runWriteCommandAction(project, () -> {
                            SelectionModel selectionModel = editor.getSelectionModel();
                            int startOffset = selectionModel.getSelectionStart();
                            int endOffset = selectionModel.getSelectionEnd();
                            CaretModel caretModel = editor.getCaretModel();

                            if (firstFlag) {
                                editor.getDocument().deleteString(startOffset, endOffset);
                                editor.getDocument().insertString(startOffset, content);
                                caretModel.moveToOffset(startOffset + content.length());
                                firstFlag = false;
                            } else {
                                editor.getDocument().insertString(editor.getCaretModel().getOffset(), content);
                                caretModel.moveToOffset(editor.getCaretModel().getOffset() + content.length());
                            }
                        });
                    });

                    Thread.sleep(100);
                    WriteCommandAction.runWriteCommandAction(
                            project,
                            () -> {
                                PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
                                CodeStyleManager.getInstance(project).reformat(psiFile);
                            }
                    );

                } catch (Exception ee) {
                    LOGGER.error(ee.getMessage());
                    fastRequestToolWindow.runOtherStop();
                    enabled = true;
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                eventSource.cancel();
                okHttpClient.dispatcher().executorService().shutdown();
                fastRequestToolWindow.runOtherStop();
                enabled = true;
                taskFlag[0] = false;
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {

                try {
                    String msg = response.body().string();
                    if (StringUtils.isNotBlank(msg)) {
                        RfrNotificationUtil.buildError(msg).notify(project);
                    }
                } catch (IOException e) {

                }
                fastRequestToolWindow.runOtherStop();
                enabled = true;
                taskFlag[0] = false;
            }
        });
        realEventSource.connect(okHttpClient);//真正开始请求的一步
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }

    public void sendRequestAndReplace(Project project, Editor editor, String finalPrompt, AiSetting aiSetting) {
        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(project);
        fastRequestToolWindow.runOtherStart();
        enabled = false;
        //https://ningmengguorou.top:94/v1/chat/completions
        HttpRequest request = HttpUtil.createPost(aiSetting.getApiLink());
        request.setReadTimeout(30000);
        request.setConnectionTimeout(30000);
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + aiSetting.getToken());
        ImmutableMap<String, Object> paramMap = ImmutableMap.<String, Object>builder().put("model", aiSetting.getModel()).put("messages", Lists.newArrayList(ImmutableMap.<String, Object>builder().put("role", "user").put("content", finalPrompt).build())).build();
        request.body(JSON.toJSONString(paramMap));
        HttpResponse response = request.execute();

        if (response.isOk()) {
            String body = response.body();
            try {
                String replaceText = ((JSONObject) JSONObject.parseObject(body).getJSONArray("choices").get(0)).getJSONObject("message").getString("content");

                ApplicationManager.getApplication().invokeLater(() -> {
                    WriteCommandAction.runWriteCommandAction(project, () -> {
                        SelectionModel selectionModel = editor.getSelectionModel();

                        int startOffset = selectionModel.getSelectionStart();
                        int endOffset = selectionModel.getSelectionEnd();
                        editor.getDocument().replaceString(startOffset, endOffset, replaceText);
                        fastRequestToolWindow.runOtherStop();
                        enabled = true;
                    });
                });

                Thread.sleep(100);
                WriteCommandAction.runWriteCommandAction(
                        project,
                        () -> {
                            PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
                            CodeStyleManager.getInstance(project).reformat(psiFile);
                        }
                );

            } catch (Exception ee) {
                LOGGER.error(ee.getMessage());
                fastRequestToolWindow.runOtherStop();
                enabled = true;
            }
        } else {
            RfrNotificationUtil.buildError(response.body()).notify(project);
            fastRequestToolWindow.runOtherStop();
            enabled = true;
            LOGGER.error(response.body());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(enabled);
    }

    private String getSelectedText(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();

        int startOffset = selectionModel.getSelectionStart();
        int endOffset = selectionModel.getSelectionEnd();

        return editor.getDocument().getText(new TextRange(startOffset, endOffset));
    }
}
