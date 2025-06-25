package io.github.kings1990.plugin.fastrequest.view.inner;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.panel.ComponentPanelBuilder;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UI;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.AiSetting;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.util.RfrNotificationUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class AiSettingView extends DialogWrapper {
    private static final Logger LOGGER = Logger.getInstance(AiSettingView.class);
    private final Project myProject;

    private JTextField apiLinkTextField;
    private JPasswordField tokenTextField;
    private JTextField modelTextField;
    private Integer type = 1;

    public AiSettingView(Project project) {
        super(project, false);
        this.myProject = project;
        init();
        setSize(800, 200);
        setTitle(MyResourceBundleUtil.getKey("AIManagement"));
    }

    public AiSetting getValue() {
        return new AiSetting(type, apiLinkTextField.getText(), String.valueOf(tokenTextField.getPassword()), modelTextField.getText());
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        AiSetting aiSetting = config.getAiSetting();
        type = aiSetting.getType();

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton openaiRadio = new JRadioButton("OpenAI");
        JRadioButton zhipuAiRadio;
        zhipuAiRadio = new JRadioButton("CodeGeeX(Free)");
        JRadioButton deepseekRadio = new JRadioButton("Deepseek");
        JRadioButton customOpenaiRadio = new JRadioButton("Custom OpenAi");
        openaiRadio.setSelected(type == 1);
        zhipuAiRadio.setSelected(type == 2);
        customOpenaiRadio.setSelected(type == 3);
        deepseekRadio.setSelected(type == 4);
        buttonGroup.add(openaiRadio);
        buttonGroup.add(zhipuAiRadio);
        buttonGroup.add(deepseekRadio);
        buttonGroup.add(customOpenaiRadio);
        JPanel buttonGroupPanel = new JPanel();
        buttonGroupPanel.setLayout(new GridLayout(1, 4));
        buttonGroupPanel.add(UI.PanelFactory.panel(openaiRadio).moveCommentRight().createPanel());
        buttonGroupPanel.add(UI.PanelFactory.panel(zhipuAiRadio).moveCommentRight().createPanel());
        buttonGroupPanel.add(UI.PanelFactory.panel(deepseekRadio).moveCommentRight().createPanel());
        buttonGroupPanel.add(UI.PanelFactory.panel(customOpenaiRadio).moveCommentRight().createPanel());

        apiLinkTextField = new JTextField(aiSetting.getApiLink());
        apiLinkTextField.setEditable(aiSetting.getType() == 3);
        tokenTextField = new JPasswordField(aiSetting.getToken());
        modelTextField = new JTextField(aiSetting.getModel());

        tokenTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                update();
            }

            private void update() {
                aiSetting.setToken(String.valueOf(tokenTextField.getPassword()));
            }
        });


        ChangeListener radioChangeListener = changeEvent -> {
            if (openaiRadio.isSelected()) {
//                aiSetting.setType(1);
                type = 1;
//                aiSetting.setApiLink("https://api.openai.com/v1/chat/completions");
//                aiSetting.setModel("gpt-3.5-turbo");
                apiLinkTextField.setText("https://api.openai.com/v1/chat/completions");
                modelTextField.setText("gpt-3.5-turbo");
                apiLinkTextField.setEditable(false);
            } else if (zhipuAiRadio.isSelected()) {
//                aiSetting.setType(2);
                type = 2;
//                aiSetting.setApiLink("https://codegeex.cn/prod/code/chatCodeSseV2/chat");
//                aiSetting.setModel("codegeex-pro");
                apiLinkTextField.setText("https://codegeex.cn/prod/code/chatCodeSseV2/chat");
                modelTextField.setText("codegeex-pro");
                apiLinkTextField.setEditable(false);
            } else if (deepseekRadio.isSelected()) {
//                aiSetting.setType(2);
                type = 4;
                apiLinkTextField.setText("https://api.deepseek.com/chat/completions");
                modelTextField.setText("deepseek-chat");
                apiLinkTextField.setEditable(false);
            } else {
//                aiSetting.setType(3);
                type = 3;
//                aiSetting.setApiLink("");
//                aiSetting.setModel("");
                apiLinkTextField.setText("");
                modelTextField.setText("");
                apiLinkTextField.setEditable(true);
            }
        };
        openaiRadio.addChangeListener(radioChangeListener);
        zhipuAiRadio.addChangeListener(radioChangeListener);
        deepseekRadio.addChangeListener(radioChangeListener);
        customOpenaiRadio.addChangeListener(radioChangeListener);

        JButton browserButton = new JButton("Get api key");
        browserButton.setMaximumSize(new Dimension(80, -1));
        browserButton.setPreferredSize(new Dimension(80, -1));
        browserButton.addActionListener(e -> {
            if (zhipuAiRadio.isSelected()) {
                String sessionId = UUID.randomUUID().toString();
                String authUrl = String.format("https://codegeex.cn/auth?sessionId=%s&machineId=%s&device=Intellij_IDEA_FastRequest", sessionId, UUID.randomUUID());
                BrowserUtil.browse(authUrl);
                confirmSignIn(sessionId);
            } else if (openaiRadio.isSelected()) {
                BrowserUtil.browse("https://platform.openai.com/api-keys");
            } else if(deepseekRadio.isSelected()){
                BrowserUtil.browse("https://platform.deepseek.com/api_keys");
            }
        });

        ComponentPanelBuilder urlLinkPanel = UI.PanelFactory.panel(JBUI.Panels.simplePanel()
                .addToCenter(apiLinkTextField)
                .addToRight(browserButton));

        return UI.PanelFactory.grid().add(UI.PanelFactory.panel((buttonGroupPanel)))
                .add(urlLinkPanel.withLabel("Api link"))
                .add(UI.PanelFactory.panel(tokenTextField).withLabel("Api key"))
                .add(UI.PanelFactory.panel(modelTextField).withLabel("Model").withComment(MyResourceBundleUtil.getKey("AiApiDesc")))
                .createPanel();
    }


    private @Nullable void confirmSignIn(@NotNull String sessionId) {
        Task.Backgroundable task = new Task.Backgroundable(myProject, "Login to CodeGeeX...") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                boolean signedIn = false;

                indicator.setText("Login...");
                for (int i = 0; i < 1200; ++i) {
                    if (indicator.isCanceled()) {
                        break;
                    }
                    HttpRequest request = HttpUtil.createGet("https://codegeex.cn/prod/code/oauth/getUserInfo").header("code-token", sessionId);
                    request.setConnectionTimeout(60000);
                    request.setReadTimeout(60000);

                    try {
                        HttpResponse response = request.execute();
                        if (response == null) {
                            TimeUnit.SECONDS.sleep(1L);
                        } else {
                            if (JSONObject.parseObject(response.body()).getBoolean("success")) {
                                signedIn = true;
                                break;
                            }
                            TimeUnit.SECONDS.sleep(1L);
                        }
                    } catch (Exception exception) {
                        LOGGER.error(exception);
                        break;
                    }
                }
                if (signedIn) {
                    tokenTextField.setText(sessionId);
                    RfrNotificationUtil.buildInfo("Get api key success").notify(myProject);
                }
            }
        };
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }
}
