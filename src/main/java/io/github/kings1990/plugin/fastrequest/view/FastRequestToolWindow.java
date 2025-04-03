/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
 *
 */

package io.github.kings1990.plugin.fastrequest.view;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.RevealFileAction;
import com.intellij.json.JsonFileType;
import com.intellij.json.JsonLanguage;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.*;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.progress.util.ColorProgressBar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.*;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.*;
import com.intellij.ui.components.ActionLink;
import com.intellij.ui.dualView.TreeTableView;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.util.PsiNavigateUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.AbstractTableCellEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.action.GotoFastRequestAction;
import io.github.kings1990.plugin.fastrequest.action.OpenConfigAction;
import io.github.kings1990.plugin.fastrequest.action.ToolbarSendAndDownloadRequestAction;
import io.github.kings1990.plugin.fastrequest.action.ToolbarSendRequestAction;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestCollectionComponent;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.config.FastRequestCurrentProjectConfigComponent;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.model.*;
import io.github.kings1990.plugin.fastrequest.service.GeneratorUrlService;
import io.github.kings1990.plugin.fastrequest.util.*;
import io.github.kings1990.plugin.fastrequest.view.component.*;
import io.github.kings1990.plugin.fastrequest.view.inner.HeaderGroupView;
import io.github.kings1990.plugin.fastrequest.view.inner.SupportView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * FastRequest工具窗口
 *
 * @author Kings
 * @date 2021/06/02
 * @see SimpleToolWindowPanel
 */
public class FastRequestToolWindow extends SimpleToolWindowPanel {
    private static final Logger LOGGER = Logger.getInstance(FastRequestToolWindow.class);
    public static final int JSON_TABLE_COLUMN_NAME_WIDTH = 200;
    public static final int JSON_TABLE_COLUMN_TYPE_WIDTH = 80;
    public static final String NO_ENV = "<No Env>";
    public static final String NO_PROJECT = "<No Project>";
    public static final int MAX_DATA_LENGTH = 5 * 1024 * 1024;

    private final Project myProject;
    private JPanel panel;
    private JComboBox<String> envComboBox;
    private JComboBox<String> projectComboBox;
    private JTextField urlTextField;
    private JComboBox<String> methodTypeComboBox;
    private JTextArea urlParamsTextArea;
    private JPanel jsonParamsTextArea;
    private JTextArea urlEncodedTextArea;
    private JTabbedPane urlEncodedTabbedPane;
    private JTabbedPane urlParamsTabbedPane;
    private JPanel urlParamsTablePanel;
    private JPanel urlEncodedTablePanel;
    private JScrollPane urlParamsTextPanel;
    private JScrollPane urlEncodedTextPanel;
    private JPanel pathParamsPanel;
    private JPanel headerPanel;
    private JTabbedPane tabbedPane;
    private JTabbedPane responseTabbedPanel;
    private JScrollPane responseBodyScrollPanel;
    private JScrollPane responseInfoScrollPanel;
    private JComboBox<Integer> responseStatusComboBox;
    private JPanel responseInfoPanel;
    private JTabbedPane multipartTabbedPane;
    private JPanel multipartTablePanel;
    private JPanel jsonResponsePanel;
    private JButton manageConfigButton;
    private JPanel titlePanel;
    private JLabel warnLabel1;
    private JLabel warnLabel2;
    private JTabbedPane bodyTabbedPane;
    private JProgressBar requestProgressBar;
    private JPanel prettyJsonEditorPanel;
    private JPanel responseTextAreaPanel;


    private MyLanguageTextField prettyJsonLanguageTextField;
    private MyLanguageTextField jsonParamsLanguageTextField;

    private JBTable urlParamsTable;
    private JBTable urlEncodedTable;
    private JBTable multipartTable;
    private JBTable pathParamsTable;

    private CheckBoxHeader urlParamsCheckBoxHeader;
    private CheckBoxHeader urlEncodedCheckBoxHeader;
    private CheckBoxHeader multipartCheckBoxHeader;
    private CheckBoxHeader pathParamsCheckBoxHeader;


    private JBTable responseInfoTable;
    private TreeTableView responseTable;
    private JBTable headerTable;
    private List<DataMapping> headerParamsKeyValueList;
    private List<ParamKeyValue> responseInfoParamsKeyValueList = new ArrayList<>();
    private List<ParamKeyValue> pathParamsKeyValueList = new ArrayList<>();
    private List<ParamKeyValue> urlParamsKeyValueList = new ArrayList<>();
    private List<ParamKeyValue> urlEncodedKeyValueList = new ArrayList<>();
    private List<ParamKeyValue> multipartKeyValueList = new ArrayList<>();
    private LinkedHashMap<String, Object> bodyParamMap;
    private AtomicBoolean urlEncodedParamChangeFlag;
    private AtomicBoolean urlParamsChangeFlag;
    private static final Map<Object, Icon> TYPE_ICONS = ImmutableMap.<Object, Icon>builder()
            .put(TypeUtil.Type.Object.name(), PluginIcons.ICON_OBJECT)
            .put(TypeUtil.Type.Array.name(), PluginIcons.ICON_ARRAY)
            .put(TypeUtil.Type.String.name(), PluginIcons.ICON_STRING)
            .put(TypeUtil.Type.Number.name(), PluginIcons.ICON_NUMBER)
            .put(TypeUtil.Type.Boolean.name(), PluginIcons.ICON_BOOLEAN)
            .put(TypeUtil.Type.File.name(), PluginIcons.ICON_FILE)
            .build();
    private ComboBox<String> typeJComboBox;
    private ComboBox<String> normalTypeJComboBox;

    public boolean sendButtonFlag = true;


    private JTextField getKeyTextField(String text) {
        JTextField jTextField = new JTextField(text);
        jTextField.setText(text);
        return jTextField;
    }

    private ComboBox getTypeComboBox(String type) {
        ComboBox<String> typeJComboBox = new ComboBox<>();
        typeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        typeJComboBox.addItem(TypeUtil.Type.Number.name());
        typeJComboBox.addItem(TypeUtil.Type.Array.name());
        typeJComboBox.addItem(TypeUtil.Type.String.name());
        typeJComboBox.addItem(TypeUtil.Type.Object.name());
        typeJComboBox.addItem(TypeUtil.Type.Boolean.name());
        if (type != null) {
            typeJComboBox.setSelectedItem(type);
        }
        return typeJComboBox;
    }

    private ComboBox getRootTypeComboBox(String type) {
        ComboBox<String> typeJComboBox = new ComboBox<>();
        typeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        typeJComboBox.addItem(TypeUtil.Type.Array.name());
        typeJComboBox.addItem(TypeUtil.Type.Object.name());
        typeJComboBox.setSelectedItem(type);
        return typeJComboBox;
    }

    private ComboBox getNormalTypeComboBox(String type) {
        ComboBox<String> typeJComboBox = new ComboBox<>();
        typeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        typeJComboBox.addItem(TypeUtil.Type.Number.name());
        typeJComboBox.addItem(TypeUtil.Type.String.name());
        typeJComboBox.addItem(TypeUtil.Type.Boolean.name());
        typeJComboBox.setSelectedItem(type);
        return typeJComboBox;
    }

    private ComboBox getNormalTypeAndFileComboBox(String type) {
        ComboBox<String> typeJComboBox = new ComboBox<>();
        typeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        typeJComboBox.addItem(TypeUtil.Type.Number.name());
        typeJComboBox.addItem(TypeUtil.Type.String.name());
        typeJComboBox.addItem(TypeUtil.Type.Boolean.name());
        typeJComboBox.addItem(TypeUtil.Type.File.name());
        typeJComboBox.setSelectedItem(type);
        return typeJComboBox;
    }

    private void createUIComponents() {
        typeJComboBox = new ComboBox<>();
        typeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        typeJComboBox.addItem(TypeUtil.Type.Number.name());
        typeJComboBox.addItem(TypeUtil.Type.Array.name());
        typeJComboBox.addItem(TypeUtil.Type.String.name());
        typeJComboBox.addItem(TypeUtil.Type.Object.name());
        typeJComboBox.addItem(TypeUtil.Type.Boolean.name());

        normalTypeJComboBox = new ComboBox<>();
        normalTypeJComboBox.setRenderer(new IconListRenderer(TYPE_ICONS));
        normalTypeJComboBox.addItem(TypeUtil.Type.Number.name());
        normalTypeJComboBox.addItem(TypeUtil.Type.String.name());
        normalTypeJComboBox.addItem(TypeUtil.Type.Boolean.name());

        urlEncodedParamChangeFlag = new AtomicBoolean(true);
        urlParamsChangeFlag = new AtomicBoolean(true);

        renderingHeaderTablePanel();
        renderingUrlParamsTablePanel();
        renderingUrlEncodedPanel();
        renderingMultipartPanel();
        renderingPathParamsPanel();
        renderingResponseInfoPanel();
        renderingJsonResponsePanel();


        ActionLink managerConfigLink = new ActionLink("config", e -> {
            ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Restful Fast Request");
        });
        managerConfigLink.setExternalLinkIcon();
        manageConfigButton = managerConfigLink;
        prettyJsonEditorPanel = new MyLanguageTextField(myProject, JsonLanguage.INSTANCE, JsonFileType.INSTANCE);
        responseTextAreaPanel = new MyLanguageTextField(myProject, PlainTextLanguage.INSTANCE, PlainTextFileType.INSTANCE);
        jsonParamsTextArea = new MyLanguageTextField(myProject, JsonLanguage.INSTANCE, JsonFileType.INSTANCE);
        //设置高度固定搜索框
        prettyJsonEditorPanel.setMinimumSize(new Dimension(-1, 120));
        prettyJsonEditorPanel.setPreferredSize(new Dimension(-1, 120));
        prettyJsonEditorPanel.setMaximumSize(new Dimension(-1, 1000));
        responseTextAreaPanel.setMinimumSize(new Dimension(-1, 120));
        responseTextAreaPanel.setPreferredSize(new Dimension(-1, 120));
        responseTextAreaPanel.setMaximumSize(new Dimension(-1, 1000));

        jsonParamsTextArea.setMinimumSize(new Dimension(-1, 120));
        jsonParamsTextArea.setPreferredSize(new Dimension(-1, 120));
        jsonParamsTextArea.setMaximumSize(new Dimension(-1, 1000));

        //2020.3before
//        manageConfigButton = new JButton();
//        manageConfigButton.addActionListener(e->{
//            ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Fast Request");
//        });
    }

    private void $$$setupUI$$$() {
        createUIComponents();
    }

    public FastRequestToolWindow(ToolWindow toolWindow, Project project) {
        super(true, false);
        this.myProject = project;
        this.$$$setupUI$$$();

        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new OpenConfigAction());
        GotoFastRequestAction gotoFastRequestAction = (GotoFastRequestAction) ActionManager.getInstance().getAction("fastRequest.gotoFastRequest");
        group.add(gotoFastRequestAction);
        group.addSeparator("  |  ");
        ToolbarSendRequestAction toolbarSendRequestAction = (ToolbarSendRequestAction) ActionManager.getInstance().getAction("fastRequest.sendAction");
        ToolbarSendAndDownloadRequestAction sendAndDownloadRequestAction = (ToolbarSendAndDownloadRequestAction) ActionManager.getInstance().getAction("fastRequest.sendDownloadAction");

// todo idea暂时有bug
//        DefaultActionGroup sendGroup = DefaultActionGroup.createPopupGroupWithEmptyText();
//        sendGroup.addAll(Lists.newArrayList(toolbarSendRequestAction, sendAndDownloadRequestAction));
//        ActionGroup splitSendGroup = new SplitButtonAction(sendGroup);
//        group.add(splitSendGroup);

        group.add(toolbarSendRequestAction);
        group.add(sendAndDownloadRequestAction);
        group.add(new SaveRequestAction());
        group.add(new RetryAction());
        group.add(new CopyCurlAction());
        group.addSeparator("  |  ");
        group.add(new DocAction());
        group.add(new WhatsNewAction());
        group.add(new CoffeeMeAction());
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLWINDOW_CONTENT, group, true);
        actionToolbar.setTargetComponent(panel);
        JComponent toolbarComponent = actionToolbar.getComponent();
        Border border = IdeBorderFactory.createBorder(SideBorder.BOTTOM);
        actionToolbar.getComponent().setBorder(border);
        setToolbar(toolbarComponent);


        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        FastRequestCurrentProjectConfiguration projectConfig = FastRequestCurrentProjectConfigComponent.getInstance(project).getState();
        assert projectConfig != null;

        warnLabel1.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        manageConfigButton.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        warnLabel2.setVisible(StringUtils.isBlank(getActiveDomain()));


        //method 颜色渲染
        methodTypeComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    JLabel l = (JLabel) c;
                    l.setBackground(buildMethodColor(value.toString()));
                }
                return c;
            }
        });

        methodTypeComboBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object selectedItem = methodTypeComboBox.getSelectedItem();
                if (selectedItem != null) {
                    methodTypeComboBox.setBackground(buildMethodColor(selectedItem.toString()));
                }
            }
        });
        methodTypeComboBox.setBackground(MyColor.blue);

        //responseStatus ComboBox
        List<Integer> values = new ArrayList<>(Constant.HttpStatusDesc.STATUS_MAP.keySet());
        CollectionComboBoxModel<Integer> responseStatusComboBoxModel = new CollectionComboBoxModel<>(values);
        responseStatusComboBox.setModel(responseStatusComboBoxModel);

        String activeEnv = getActiveEnv();
        String activeProject = getActiveProject();

        //env下拉列表
        ArrayList<String> envListClone = Lists.newArrayList(NO_ENV);
        envListClone.addAll(JSONObject.parseObject(JSONObject.toJSONString(config.getEnvList()), ArrayList.class));
        envListClone.add("Add Env");
        CollectionComboBoxModel<String> envModel = new CollectionComboBoxModel<>(envListClone);
        envComboBox.setModel(envModel);

        envComboBox.setRenderer(new ColoredListCellRenderer<>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends String> list, String value, int index, boolean selected, boolean hasFocus) {
                if (value != null) {
                    append(value);
                    if ("Add Env".equals(value)) {
                        setIcon(AllIcons.General.Add);
                    } else if (NO_ENV.equals(value)) {
                        setIcon(AllIcons.General.BalloonError);
                    } else {
                        setIcon(AllIcons.Nodes.Enum);
                    }
                }
            }
        });

        envComboBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object selectEnv = envComboBox.getSelectedItem();
                if (selectEnv == null) {
                    return;
                }
                if ("Add Env".equals(envComboBox.getSelectedItem())) {
                    int idx = config.getEnvList().indexOf(activeEnv);
                    envComboBox.setSelectedIndex(Math.max(0, idx + 1));
                    envComboBox.hidePopup();
                    ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Restful Fast Request");
                    return;
                }

                String env = selectEnv.toString();
                List<String> envList = config.getEnvList();
                if (!envList.contains(env)) {
                    //配置删除了当前的env则默认选中第一个env
                    if (envList.isEmpty()) {
                        //env被删除完了 补全域名开关自动关闭
                        config.setEnableEnv(null);
                        projectConfig.setEnableEnv(null);
                        config.setDomain(StringUtils.EMPTY);
                        projectConfig.setDomain(StringUtils.EMPTY);
                        envModel.setSelectedItem(NO_ENV);
                    } else {
                        if (NO_ENV.equals(env)) {
                            config.setEnableEnv(null);
                            projectConfig.setEnableEnv(null);
                            envModel.setSelectedItem(NO_ENV);
                        } else {
                            config.setEnableEnv(envList.get(0));
                            projectConfig.setEnableEnv(envList.get(0));
                            envModel.setSelectedItem(activeEnv);
                        }
                    }
                } else {
                    config.setEnableEnv(env);
                    projectConfig.setEnableEnv(env);
                }
                switchHeaderParam();
                //根据当前的env和project设置url
                setDomain(config);
            }
        });
        envModel.setSelectedItem(StringUtils.isBlank(activeEnv) ? NO_ENV : activeEnv);

        //project下拉列表
        ArrayList<String> projectListClone = Lists.newArrayList(NO_PROJECT);
        projectListClone.addAll(JSONObject.parseObject(JSONObject.toJSONString(config.getProjectList()), ArrayList.class));
        projectListClone.add("Add Project");
        CollectionComboBoxModel<String> projectModel = new CollectionComboBoxModel<>(projectListClone);
        projectComboBox.setModel(projectModel);
        projectComboBox.setRenderer(new ColoredListCellRenderer<>() {
            @Override
            protected void customizeCellRenderer(@NotNull JList<? extends String> list, String value, int index, boolean selected, boolean hasFocus) {
                if (value != null) {
                    append(value);
                    if ("Add Project".equals(value)) {
                        setIcon(AllIcons.General.Add);
                    } else if (NO_PROJECT.equals(value)) {
                        setIcon(AllIcons.General.BalloonError);
                    } else {
                        setIcon(AllIcons.Nodes.Property);
                    }
                }
            }
        });


        projectComboBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object selectProject = projectComboBox.getSelectedItem();
                if (selectProject == null) {
                    return;
                }
                if ("Add Project".equals(projectComboBox.getSelectedItem())) {
                    int idx = config.getProjectList().indexOf(activeProject);
                    projectComboBox.setSelectedIndex(Math.max(0, idx + 1));
                    projectComboBox.hidePopup();
                    ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Restful Fast Request");
                    return;
                }
                String projectSelect = selectProject.toString();
                List<String> projectList = config.getProjectList();
                if (!projectList.contains(projectSelect)) {
                    //配置删除了当前的env则默认选中第一个env
                    if (projectList.isEmpty()) {
                        //project被删除完了 补全域名开关自动关闭
                        config.setEnableProject(null);
                        projectConfig.setEnableProject(null);
                        config.setDomain(StringUtils.EMPTY);
                        projectConfig.setDomain(null);
                        projectModel.setSelectedItem(NO_PROJECT);
                    } else {
                        if (NO_PROJECT.equals(projectSelect)) {
                            config.setEnableProject(null);
                            projectConfig.setEnableProject(null);
                            projectModel.setSelectedItem(NO_PROJECT);
                        } else {
                            config.setEnableProject(projectList.get(0));
                            projectConfig.setEnableProject(projectList.get(0));
                            projectModel.setSelectedItem(activeProject);
                        }
                    }
                } else {
                    config.setEnableProject(projectSelect);
                    projectConfig.setEnableProject(projectSelect);
                }
                switchHeaderParam();
                //根据当前的env和project设置url
                setDomain(config);
            }
        });
        projectModel.setSelectedItem(StringUtils.isBlank(activeProject) ? NO_PROJECT : activeProject);


        //更新域名
        config.getParamGroup().setOriginUrl("");
        setDomain(config);

        //动态更新text中的内容
        urlEncodedTabbedPane.addChangeListener(changeEvent -> {
            if (urlEncodedTabbedPane.getSelectedIndex() == 0) {
                String paramStr = conventDataToString(urlEncodedKeyValueList);
                String currentUrlParamText = urlEncodedTextArea.getText();
                if (!paramStr.equals(currentUrlParamText)) {
                    List<ParamKeyValue> currentUrlParamsKeyValueList = new ArrayList<>();
                    if (StringUtils.isNoneBlank(currentUrlParamText)) {
                        String[] split = currentUrlParamText.split("&");
                        if (split.length > 0) {
                            for (String s : split) {
                                String[] kvArray = s.split("=");
                                if (kvArray.length <= 2) {
                                    String value = kvArray.length < 2 ? "" : kvArray[1].replace("\n", "");
                                    ParamKeyValue paramKeyValue = new ParamKeyValue(kvArray[0], value, 2, TypeUtil.calcTypeByStringValue(value));
                                    currentUrlParamsKeyValueList.add(paramKeyValue);
                                }
                            }
                        }
                    }
                    urlEncodedKeyValueList = currentUrlParamsKeyValueList;
                    //refreshTable(urlEncodedTable);
                    urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
                    resizeTable(urlEncodedTable);
                    setCheckBoxHeader(urlEncodedTable, urlEncodedCheckBoxHeader);
                }
            }
        });

        //动态更新text中的内容
        urlParamsTabbedPane.addChangeListener(changeEvent -> {
            //table change 引发重新构建text
            if (urlParamsTabbedPane.getSelectedIndex() == 0) {
                String paramStr = conventDataToString(urlParamsKeyValueList);
                String currentUrlParamText = urlParamsTextArea.getText();
                if (!paramStr.equals(currentUrlParamText)) {
                    List<ParamKeyValue> currentUrlParamsKeyValueList = new ArrayList<>();
                    if (StringUtils.isNoneBlank(currentUrlParamText)) {
                        String[] split = currentUrlParamText.split("&");
                        for (String s : split) {
                            String[] kvArray = s.split("=");
                            if (kvArray.length <= 2) {
                                String value = kvArray.length < 2 ? "" : kvArray[1].replace("\n", "");
                                ParamKeyValue paramKeyValue = new ParamKeyValue(kvArray[0], value, 2, TypeUtil.calcTypeByStringValue(value));
                                currentUrlParamsKeyValueList.add(paramKeyValue);
                            }
                        }
                    }
                    urlParamsKeyValueList = currentUrlParamsKeyValueList;
                    //refreshTable(urlParamsTable);
                    urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
                    resizeTable(urlParamsTable);
                    setCheckBoxHeader(urlParamsTable, urlParamsCheckBoxHeader);
                }
            }
        });

        //copy param
//        jsonParamsTextArea.addMouseListener(copyMouseAdapter(jsonParamsTextArea));
//        urlEncodedTextArea.addMouseListener(copyMouseAdapter(urlEncodedTextArea));
//        urlParamsTextArea.addMouseListener(copyMouseAdapter(urlParamsTextArea));
//        urlTextField.addMouseListener(copyMouseAdapterField(urlTextField));
//        headerParamsKeyValueList = config.getHeaderList();
        calcHeaderList();

//        sendRequestEvent();
        //send request
        //2秒内不允许狂点
        requestProgressBar.setIndeterminate(true);
        requestProgressBar.setVisible(false);
    }

    private void changeUrlParamsText() {
        String paramStr = conventDataToString(urlParamsKeyValueList);
        urlParamsTextArea.setText(paramStr);
        urlEncodedParamChangeFlag.set(false);
    }

    private void changeUrlEncodedParamsText() {
        String paramStr = conventDataToString(urlEncodedKeyValueList);
        urlEncodedTextArea.setText(paramStr);
        urlEncodedParamChangeFlag.set(false);
    }

    private String getCurlDataAndCopy() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        String methodType = (String) methodTypeComboBox.getSelectedItem();
        String domain = getActiveDomain();
        String sendUrl = urlTextField.getText();
        List<DataMapping> headerList = headerParamsKeyValueList;
        String urlParam = urlParamsTextArea.getText();
        String jsonParam = ((LanguageTextField) jsonParamsTextArea).getText();
        String urlEncodedParam = urlEncodedTextArea.getText();

        if (StringUtils.isEmpty(sendUrl)) {
            Messages.showMessageDialog("Url not exist", "Error", Messages.getInformationIcon());
            return "";
        }
        String url = domain + sendUrl;
        if (StringUtils.isNotEmpty(urlParam)) {
            String urlParamDeal = urlParam.lines().collect(Collectors.joining(""));
            url = url + "?" + urlParamDeal;
        }

        StringBuilder sb = new StringBuilder("curl -X ");
        sb.append("\"").append(methodType).append("\" ");
        sb.append("\"").append(url).append("\" \\\n");
        for (DataMapping header : headerList) {
            sb.append("-H '").append(header.getType()).append(": ").append(header.getValue()).append("' \\\n");
        }
        config.getGlobalHeaderList().stream().filter(DataMapping::getEnabled).forEach(globalHeader->
            sb.append("-H '").append(globalHeader.getType()).append(": ").append(globalHeader.getValue()).append("' \\\n")
        );
        if (StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)) {
            sb.append("-H '").append("Content-Type: application/json").append("' \\\n");
        }

        if (StringUtils.isNotEmpty(urlEncodedParam)) {
            String urlEncodedParamDeal = urlEncodedParam.lines().collect(Collectors.joining(""));
            sb.append("-d '").append(urlEncodedParamDeal).append("' \\\n");
        }
        if (StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)) {
            String jsonParamDeal = jsonParam.lines().collect(Collectors.joining(""));
            sb.append("-d '").append(jsonParamDeal).append("' \\\n");
        }

        for (ParamKeyValue paramKeyValue : multipartKeyValueList) {
            if (!TypeUtil.Type.File.name().equals(paramKeyValue.getType())) {
                sb.append("-F \"").append(paramKeyValue.getKey()).append("=").append(paramKeyValue.getValue().toString()).append("\" \\\n");
            } else {
                sb.append("-F \"").append(paramKeyValue.getKey()).append("=").append("\" \\\n");
            }
        }
        String result = sb.toString();
        ToolUtil.setClipboardString(result);
        return result;
    }

    public boolean getSendButtonFlag() {
        return sendButtonFlag;
    }

    public void sendRequestEvent(boolean fileMode) {
        if (!sendButtonFlag) {
            return;
        }
        sendButtonFlag = false;
        try {
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            assert config != null;
            NameGroup defaultNameGroup = new NameGroup(StringUtils.EMPTY, new ArrayList<>());
            HostGroup defaultHostGroup = new HostGroup(StringUtils.EMPTY, StringUtils.EMPTY);
            String domain = config.getDataList().stream().filter(n -> n.getName().equals(projectComboBox.getSelectedItem())).findFirst().orElse(defaultNameGroup)
                    .getHostGroup().stream().filter(h -> h.getEnv().equals(envComboBox.getSelectedItem())).findFirst().orElse(defaultHostGroup).getUrl();

            String sendUrl = urlTextField.getText();
            if (StringUtils.isBlank(domain) || !UrlUtil.isURL(domain + sendUrl)) {
                ApplicationManager.getApplication().invokeLater(() -> {
                    ((MyLanguageTextField) responseTextAreaPanel).setText("Correct url required");
                });
                tabbedPane.setSelectedIndex(4);
                responseTabbedPanel.setSelectedIndex(2);
                sendButtonFlag = true;
                ((MyLanguageTextField) prettyJsonEditorPanel).setText("");
                return;
            }
            String methodType = (String) methodTypeComboBox.getSelectedItem();
            HttpRequest request = HttpUtil.createRequest(Method.valueOf(methodType), domain + sendUrl);
            request.setMaxRedirectCount(10);
            headerParamsKeyValueList = headerParamsKeyValueList == null ? new ArrayList<>() : headerParamsKeyValueList;
            List<DataMapping> globalHeaderList = config.getGlobalHeaderList();
            globalHeaderList = globalHeaderList == null ? new ArrayList<>() : globalHeaderList;
            Map<String, List<String>> globalHeaderMap = globalHeaderList.stream().filter(DataMapping::getEnabled).collect(Collectors.toMap(DataMapping::getType, p -> Lists.newArrayList(p.getValue()), (existing, replacement) -> existing));
            Map<String, List<String>> headerMap = headerParamsKeyValueList.stream().filter(DataMapping::getEnabled).collect(Collectors.toMap(DataMapping::getType, p -> Lists.newArrayList(p.getValue()), (existing, replacement) -> existing));
            globalHeaderMap.putAll(headerMap);
            request.header(globalHeaderMap);
            Map<String, Object> multipartFormParam = multipartKeyValueList.stream().filter(ParamKeyValue::getEnabled)
                    .collect(HashMap::new, (m, v) -> {
                        Object value = v.getValue();
                        String key = v.getKey();
                        if (TypeUtil.Type.File.name().equals(v.getType())) {
                            if (value != null && !StringUtils.isBlank(value.toString())) {
                                m.put(key, new File(value.toString()));
                            } else {
                                m.put(key, null);
                            }
                        } else {
                            m.put(key, value);
                        }
                    }, HashMap::putAll);

            Map<String, Object> urlParam = urlParamsKeyValueList.stream().filter(ParamKeyValue::getEnabled).collect(Collectors.toMap(ParamKeyValue::getKey, ParamKeyValue::getValue, (existing, replacement) -> existing));
            String jsonParam = ((LanguageTextField) jsonParamsTextArea).getText();
            Map<String, Object> formMap = new HashMap<>();
            urlEncodedKeyValueList.stream().filter(ParamKeyValue::getEnabled).forEach(q -> {
                formMap.put(q.getKey(),q.getValue());
            });

            boolean formFlag = true;
            //json优先
            if (!formMap.isEmpty()) {
                request.form(formMap);
                formFlag = false;
            }
            if (StringUtils.isNotEmpty(jsonParam)) {
                request.body(jsonParam);
                formFlag = false;
            }

            if (!urlParam.isEmpty()) {
                String queryParam = UrlQuery.of(urlParam).build(StandardCharsets.UTF_8);
                request.setUrl(request.getUrl() + "?" + queryParam);
            }
            if (!multipartFormParam.isEmpty() && formFlag) {
                request.form(multipartFormParam);
            }
            FileSaverDialog fd = null;
            if (fileMode) {
                fd = FileChooserFactory.getInstance().createSaveFileDialog(new FileSaverDescriptor("Save As", ""), myProject);
            }
            FileSaverDialog finalFd = fd;
            requestProgressBar.setVisible(true);
            requestProgressBar.setForeground(ColorProgressBar.GREEN);
            Future<?> future = ThreadUtil.execAsync(() -> {
                try {
                    long start = System.currentTimeMillis();
                    HttpResponse response = request.execute();
                    long end = System.currentTimeMillis();

                    ApplicationManager.getApplication().invokeLater(() -> {
                        tabbedPane.setSelectedIndex(4);
                        String duration = String.valueOf(end - start);
                        requestProgressBar.setVisible(false);
                        int status = response.getStatus();
                        //download file
                        if (fileMode && status >= 200 && status < 300) {
                            ((MyLanguageTextField) prettyJsonEditorPanel).setText("");
                            ((MyLanguageTextField) responseTextAreaPanel).setText("");
                            Task.Backgroundable task = new Task.Backgroundable(myProject, "Saving file...") {
                                @Override
                                public void run(@NotNull ProgressIndicator indicator) {
                                    ApplicationManager.getApplication().invokeLater(() -> {
                                        sendButtonFlag = false;
                                        try {
                                            File f = new File(myProject.getBasePath());
                                            File finalFile = response.completeFileNameFromHeader(f);
                                            response.writeBody(finalFile);
                                            VirtualFileWrapper fileWrapper = finalFd.save(finalFile.getName());
                                            if (fileWrapper != null) {
                                                File file = fileWrapper.getFile();
                                                FileUtil.move(finalFile, file, true);
                                                NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Success", MessageType.INFO)
                                                        .addAction(new GotoFile(file))
                                                        .notify(myProject);
                                            }
                                            finalFile.delete();
                                        } catch (Exception ignored) {
                                        }
                                        sendButtonFlag = true;
                                    });
                                }
                            };
                            ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
                        }
                        if (!fileMode) {
                            String body = response.body();
                            int bodyLength = StrUtil.byteLength(body, StandardCharsets.UTF_8);
                            if (bodyLength > MAX_DATA_LENGTH) {
                                ((MyLanguageTextField) responseTextAreaPanel).setText(body);
                                ((MyLanguageTextField) prettyJsonEditorPanel).setText(body);
                                refreshResponseTable("");
                            } else {
                                if (JSONUtil.isJson(body)) {
                                    responseTabbedPanel.setSelectedIndex(1);
                                    ((MyLanguageTextField) prettyJsonEditorPanel).setText(body.isBlank() ? "" : body);
                                    ((MyLanguageTextField) responseTextAreaPanel).setText(body);
                                    refreshResponseTable(body);
                                } else {
                                    responseTabbedPanel.setSelectedIndex(2);
                                    String subBody = body.substring(0, Math.min(body.length(), 32768));
                                    if (body.length() > 32768) {
                                        subBody += "\n\ntext too large only show 32768 characters\n.............";
                                    }
                                    String finalSubBody = subBody;
                                    ((MyLanguageTextField) prettyJsonEditorPanel).setText(finalSubBody);
                                    ((MyLanguageTextField) responseTextAreaPanel).setText(subBody);
                                    refreshResponseTable("");
                                }
                            }
                        }
                        responseInfoParamsKeyValueList = Lists.newArrayList(
                                new ParamKeyValue("Url", request.getUrl(), 2, TypeUtil.Type.String.name()),
                                new ParamKeyValue("Cost", duration + " ms", 2, TypeUtil.Type.String.name()),
                                new ParamKeyValue("Response status", status + " " + Constant.HttpStatusDesc.STATUS_MAP.get(status)),
                                new ParamKeyValue("Date", DateUtil.formatDateTime(new Date()))
                        );
                        //refreshTable(responseInfoTable);
                        responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));
                        responseInfoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                        responseInfoTable.getColumnModel().getColumn(0).setMaxWidth(150);
                        responseStatusComboBox.setSelectedItem(status);
                        responseStatusComboBox.setBackground((status >= 200 && status < 300) ? MyColor.green : MyColor.red);
                    }, ModalityState.NON_MODAL);
                } catch (Exception ee) {
                    sendButtonFlag = true;
                    requestProgressBar.setVisible(false);
                    tabbedPane.setSelectedIndex(4);
                    responseTabbedPanel.setSelectedIndex(2);
                    responseStatusComboBox.setSelectedItem(0);
                    String errorMsg = ee.getMessage();
                    ApplicationManager.getApplication().invokeLater(() -> {
                        ((MyLanguageTextField) responseTextAreaPanel).setText(errorMsg);
                        ((MyLanguageTextField) prettyJsonEditorPanel).setText("");
                    });
                    responseStatusComboBox.setBackground(MyColor.red);
                    responseInfoParamsKeyValueList = Lists.newArrayList(
                            new ParamKeyValue("Url", request.getUrl(), 2, TypeUtil.Type.String.name()),
                            new ParamKeyValue("Error", errorMsg)
                    );
                    //refreshTable(responseInfoTable);
                    responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));
                    responseInfoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                    responseInfoTable.getColumnModel().getColumn(0).setMaxWidth(150);
                    CustomNode root = new CustomNode("Root", "");
                    ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
                }
                sendButtonFlag = true;
            });
        } catch (Exception exception) {
            sendButtonFlag = true;
            requestProgressBar.setVisible(false);
            String errorMsg = exception.getMessage();
            ApplicationManager.getApplication().invokeLater(() -> {
                ((MyLanguageTextField) responseTextAreaPanel).setText(errorMsg);
                ((MyLanguageTextField) prettyJsonEditorPanel).setText("");
            });
            responseStatusComboBox.setSelectedItem(0);
            responseStatusComboBox.setBackground(MyColor.red);
            responseInfoParamsKeyValueList = Lists.newArrayList(
                    new ParamKeyValue("Error", errorMsg)
            );
            //refreshTable(responseInfoTable);
            responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));
            responseInfoTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            responseInfoTable.getColumnModel().getColumn(0).setMaxWidth(150);
            CustomNode root = new CustomNode("Root", "");
            ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
            tabbedPane.setSelectedIndex(4);
            responseTabbedPanel.setSelectedIndex(2);
        }

    }


    private void refreshTable(JBTable table) {
        SwingUtilities.invokeLater(table::updateUI);
    }

    /**
     * text鼠标右键拷贝至粘贴板
     *
     * @param textarea 文本区域
     * @return {@link MouseAdapter }
     * @author Kings
     * @date 2021/06/07
     */
    private MouseAdapter copyMouseAdapter(JTextArea textarea) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = textarea.getText();
                if (SwingUtilities.isRightMouseButton(e) && StringUtils.isNotEmpty(text)) {
                    ToolUtil.setClipboardString(text);
                }
            }
        };
    }

    private MouseAdapter copyMouseAdapterField(JTextField textField) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = textField.getText();
                if (SwingUtilities.isRightMouseButton(e) && StringUtils.isNotEmpty(text)) {
                    ToolUtil.setClipboardString(text);
                }
            }
        };
    }

    /**
     * 根据配置设置domain
     *
     * @param config 配置
     * @author Kings
     * @date 2021/05/23
     */
    private void setDomain(FastRequestConfiguration config) {
        warnLabel1.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        manageConfigButton.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());

        FastRequestCurrentProjectConfiguration projectConfig = FastRequestCurrentProjectConfigComponent.getInstance(myProject).getState();
        assert projectConfig != null;

        String activeEnv = getActiveEnv();
        String activeProject = getActiveProject();
        if (StringUtils.isEmpty(activeEnv)) {
            config.setDomain(StringUtils.EMPTY);
            projectConfig.setDomain(StringUtils.EMPTY);
            warnLabel2.setVisible(true);
            return;
        }
        if (StringUtils.isEmpty(activeProject)) {
            config.setDomain(StringUtils.EMPTY);
            projectConfig.setDomain(StringUtils.EMPTY);
            warnLabel2.setVisible(true);
            return;
        }
        NameGroup defaultNameGroup = new NameGroup(StringUtils.EMPTY, new ArrayList<>());
        HostGroup defaultHostGroup = new HostGroup(StringUtils.EMPTY, StringUtils.EMPTY);
        String domain = config.getDataList().stream().filter(n -> activeProject.equals(n.getName())).findFirst().orElse(defaultNameGroup)
                .getHostGroup().stream().filter(h -> activeEnv.equals(h.getEnv())).findFirst().orElse(defaultHostGroup).getUrl();
        config.setDomain(domain);
        projectConfig.setDomain(domain);
        changeUrl();


    }


    /**
     * message事件:修改env和project动态修改ToolWindow中的内容
     *
     * @author Kings
     * @date 2021/06/02
     */
    public void changeEnvAndProject() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;

        String activeEnv = getActiveEnv();
        String activeProject = getActiveProject();

        warnLabel1.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        manageConfigButton.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        warnLabel2.setVisible(StringUtils.isBlank(getActiveDomain()));


        ArrayList<String> projectListClone = Lists.newArrayList(NO_PROJECT);
        projectListClone.addAll(JSONObject.parseObject(JSONObject.toJSONString(config.getProjectList()), ArrayList.class));
        projectListClone.add("Add Project");
        CollectionComboBoxModel<String> projectModel = new CollectionComboBoxModel<>(projectListClone);
        projectComboBox.setModel(projectModel);


        ArrayList<String> envListClone = Lists.newArrayList(NO_ENV);
        envListClone.addAll(JSONObject.parseObject(JSONObject.toJSONString(config.getEnvList()), ArrayList.class));
        envListClone.add("Add Env");
        CollectionComboBoxModel<String> envModel = new CollectionComboBoxModel<>(envListClone);
        envComboBox.setModel(envModel);

        int idxProject = StringUtils.isBlank(activeProject) ? -1 : config.getProjectList().indexOf(activeProject);
        int idxEnv = StringUtils.isBlank(activeEnv) ? -1 : config.getEnvList().indexOf(activeEnv);
        projectComboBox.setSelectedIndex(Math.max(0, idxProject + 1));
        envComboBox.setSelectedIndex(Math.max(0, idxEnv + 1));
        setDomain(config);
    }

    private JBColor buildMethodColor(String method) {
        JBColor jbColor = JBColor.darkGray;
        if ("POST".equals(method)) {
            jbColor = MyColor.green;
        } else if ("DELETE".equals(method)) {
            jbColor = MyColor.red;
        } else if ("PUT".equals(method)) {
            jbColor = MyColor.yellow;
        } else if ("GET".equals(method)) {
            jbColor = MyColor.blue;
        } else if ("PATCH".equals(method)) {
            jbColor = MyColor.purple;
        }
        return jbColor;
    }

    public void refreshByCollection(CollectionConfiguration.CollectionDetail detail) {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        multipartKeyValueList = new ArrayList<>();
        urlParamsTextArea.setText("");
        ((LanguageTextField) jsonParamsTextArea).setText("");
        urlEncodedTextArea.setText("");
        ParamGroupCollection paramGroup = detail.getParamGroup();
        assert config != null;
        ParamGroup paramGroupConfig = config.getParamGroup();
        paramGroupConfig.setOriginUrl(paramGroup.getOriginUrl());
        paramGroupConfig.setClassName(paramGroup.getClassName());
        paramGroupConfig.setMethod(paramGroup.getMethod());
        paramGroupConfig.setMethodDescription(detail.getName());
        String pathParamsKeyValueListJson = paramGroup.getPathParamsKeyValueListJson();
        String urlParamsKeyValueListJson = paramGroup.getUrlParamsKeyValueListJson();
        String urlParamsKeyValueListText = paramGroup.getUrlParamsKeyValueListText();
        String bodyKeyValueListJson = paramGroup.getBodyKeyValueListJson();
        String urlEncodedKeyValueListJson = paramGroup.getUrlEncodedKeyValueListJson();
        String urlEncodedKeyValueListText = paramGroup.getUrlEncodedKeyValueListText();
        String multipartKeyValueListJson = paramGroup.getMultipartKeyValueListJson();
        String domain = detail.getDomain();
        String url = paramGroup.getUrl();

        pathParamsKeyValueList = JSON.parseObject(pathParamsKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });
        urlParamsKeyValueList = JSON.parseObject(urlParamsKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });
        urlEncodedKeyValueList = JSON.parseObject(urlEncodedKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });
        multipartKeyValueList = JSON.parseObject(multipartKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });

        String methodType = paramGroup.getMethodType();

        methodTypeComboBox.setBackground(buildMethodColor(methodType));
        projectComboBox.setSelectedItem(detail.getEnableProject());
        //method
        methodTypeComboBox.setSelectedItem(methodType);

        //headers默认取最新的
        calcHeaderList();

        if ("GET".equals(methodType)) {
            urlParamsTextArea.setText(urlParamsKeyValueListText);
            if (pathParamsKeyValueList.isEmpty()) {
                tabbedPane.setSelectedIndex(2);
            } else {
                tabbedPane.setSelectedIndex(1);
            }
            urlParamsTabbedPane.setSelectedIndex(0);
            //get请求urlencoded param参数为空
            urlEncodedKeyValueList = new ArrayList<>();
            urlEncodedTextArea.setText("");
            ((LanguageTextField) jsonParamsTextArea).setText("");
        } else {
            //body param
            if (!bodyKeyValueListJson.isBlank()) {
                //json
                ((LanguageTextField) jsonParamsTextArea).setText(bodyKeyValueListJson);
                tabbedPane.setSelectedIndex(3);
                bodyTabbedPane.setSelectedIndex(0);
                urlEncodedTextArea.setText("");
                urlEncodedKeyValueList = new ArrayList<>();
            } else {
                boolean isMultipart = multipartKeyValueList.stream().anyMatch(q -> TypeUtil.Type.File.name().equals(q.getType()));
                if (isMultipart) {
                    tabbedPane.setSelectedIndex(3);
                    bodyTabbedPane.setSelectedIndex(2);
                    urlEncodedTextArea.setText("");
                    urlEncodedKeyValueList = new ArrayList<>();
                } else {
                    //urlencoded
                    urlEncodedTextArea.setText(urlEncodedKeyValueListText);
                    tabbedPane.setSelectedIndex(3);
                    bodyTabbedPane.setSelectedIndex(1);
                }
                //json设置为空
                ((LanguageTextField) jsonParamsTextArea).setText("");
                //如果是非get请求则request Param为空转到url Encoded参数下
                urlParamsKeyValueList = new ArrayList<>();
                urlParamsTextArea.setText("");
            }
        }

        //刷新table
        pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
        resizeTable(pathParamsTable);
        setCheckBoxHeader(pathParamsTable, pathParamsCheckBoxHeader);

        urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
        resizeTable(urlParamsTable);
        setCheckBoxHeader(urlParamsTable, urlParamsCheckBoxHeader);

        urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
        resizeTable(urlEncodedTable);
        setCheckBoxHeader(urlEncodedTable, urlEncodedCheckBoxHeader);

        multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
        resizeTable(multipartTable);
        setCheckBoxHeader(multipartTable, multipartCheckBoxHeader);

        urlTextField.setText(url);
    }

    private void setCheckBoxHeader(JTable table, CheckBoxHeader header) {
        TableColumn checkBoxColumn = table.getColumnModel().getColumn(0);
        checkBoxColumn.setHeaderRenderer(header);
    }

    /**
     * message事件:action操作生成数据,修改ToolWindow中的内容
     *
     * @author Kings
     * @date 2021/06/02
     */
    public void refresh(boolean regenerate) {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        CollectionConfiguration collectionConfiguration = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert collectionConfiguration != null;

        ParamGroup paramGroup = config.getParamGroup();
        String mid = "id_" + paramGroup.getClassName() + "." + paramGroup.getMethod();
        CollectionConfiguration.CollectionDetail detail = filterById(mid, collectionConfiguration.getDetail());
        if (detail != null && !regenerate) {
            refreshByCollection(detail);
            return;
        }

        //reset value
        multipartKeyValueList = new ArrayList<>();
        urlParamsTextArea.setText("");
        ((LanguageTextField) jsonParamsTextArea).setText("");
        urlEncodedTextArea.setText("");


        LinkedHashMap<String, Object> pathParamMap = paramGroup.getPathParamMap();
        LinkedHashMap<String, Object> requestParamMap = paramGroup.getRequestParamMap();
        bodyParamMap = paramGroup.getBodyParamMap();
        String methodType = paramGroup.getMethodType();

        methodTypeComboBox.setBackground(buildMethodColor(methodType));

        //method
        methodTypeComboBox.setSelectedItem(methodType);

        //request param
        String requestParamStr = conventDataToString(conventMapToList(requestParamMap));

        //默认urlParam是允许的即使是post json形式
        urlParamsKeyValueList = conventMapToList(requestParamMap);
        urlParamsTextArea.setText(requestParamStr);
        pathParamsKeyValueList = conventMapToList(pathParamMap);
        calcHeaderList();

        if ("GET".equals(methodType)) {
            if (pathParamsKeyValueList.isEmpty()) {
                tabbedPane.setSelectedIndex(2);
                urlParamsTabbedPane.setSelectedIndex(0);
            } else {
                tabbedPane.setSelectedIndex(1);
            }
            //get请求urlencoded param参数为空
            urlEncodedKeyValueList = new ArrayList<>();
            urlEncodedTextArea.setText("");
            ((LanguageTextField) jsonParamsTextArea).setText("");
        } else {
            //body param(form和body只能存在其一)
            if (!bodyParamMap.isEmpty()) {
                //json
                tabbedPane.setSelectedIndex(3);
                bodyTabbedPane.setSelectedIndex(0);
                ((LanguageTextField) jsonParamsTextArea).setText(bodyParamMapToJson());
                //body去除form参数
                urlEncodedTextArea.setText("");
                urlEncodedKeyValueList = new ArrayList<>();
            } else {
                urlEncodedKeyValueList = conventMapToList(requestParamMap);
                boolean isMultipart = urlEncodedKeyValueList.stream().anyMatch(q -> TypeUtil.Type.File.name().equals(q.getType()));
                if (isMultipart) {
                    tabbedPane.setSelectedIndex(3);
                    bodyTabbedPane.setSelectedIndex(2);
                    multipartKeyValueList = new ArrayList<>(urlEncodedKeyValueList);
                    urlEncodedTextArea.setText("");
                    urlEncodedKeyValueList = new ArrayList<>();
                } else {
                    //urlencoded
                    urlEncodedTextArea.setText(requestParamStr);
                    tabbedPane.setSelectedIndex(3);
                    bodyTabbedPane.setSelectedIndex(1);
                    urlEncodedTabbedPane.setSelectedIndex(0);
                    urlEncodedKeyValueList = conventMapToList(requestParamMap);
                }
                //json设置为空(form去除body参数)
                ((LanguageTextField) jsonParamsTextArea).setText("");
                //如果是非get请求则request Param为空转到url Encoded参数下
                urlParamsKeyValueList = new ArrayList<>();
                urlParamsTextArea.setText("");
            }
        }
        //刷新table
        pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
        resizeTable(pathParamsTable);
        setCheckBoxHeader(pathParamsTable, pathParamsCheckBoxHeader);

        urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
        resizeTable(urlParamsTable);
        setCheckBoxHeader(urlParamsTable, urlParamsCheckBoxHeader);

        urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
        resizeTable(urlEncodedTable);
        setCheckBoxHeader(urlEncodedTable, urlEncodedCheckBoxHeader);

        multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
        resizeTable(multipartTable);
        setCheckBoxHeader(multipartTable, multipartCheckBoxHeader);

        setDomain(config);
    }

    public void resizeTable(JBTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth((int) Math.round(table.getWidth() * 0.3));
        table.getColumnModel().getColumn(3).setPreferredWidth((int) Math.round(table.getWidth() * 0.55));
    }


    private void changeUrl() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        ParamGroup paramGroup = config.getParamGroup();
        String originUrl = paramGroup.getOriginUrl();
        String url = buildPathParamUrl(originUrl);
        url = ((url.startsWith("/") || "".equals(url)) ? "" : "/") + url;
        urlTextField.setText(url);
        paramGroup.setUrl(url);
        warnLabel2.setVisible(StringUtils.isBlank(getActiveDomain()));
    }

    private String buildPathParamUrl(String url) {
        List<String> paramNameList = UrlUtil.paramPathParam(url);
        if (paramNameList.isEmpty()) {
            return url;
        }
        for (ParamKeyValue paramKeyValue : pathParamsKeyValueList) {
            if (paramKeyValue.getEnabled()) {
                String paramName = paramKeyValue.getKey();
                String paramNameWithSymbol = "{" + paramName + "}";
                url = url.replace(paramNameWithSymbol, paramKeyValue.getValue().toString());
            }
        }
        return url;
    }

    private void renderingHeaderTablePanel() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        headerTable = createHeaderTable();
        headerTable.getEmptyText().setText("No header params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(headerTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(anActionButton -> {
                    if (headerParamsKeyValueList == null) {
                        headerParamsKeyValueList = new ArrayList<>();
                    }
                    int selectedRow = headerTable.getSelectedRow();
                    selectedRow = Math.min(selectedRow, headerParamsKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        headerParamsKeyValueList.add(new DataMapping("", ""));
                    } else {
                        headerParamsKeyValueList.add(selectedRow + 1, new DataMapping("", ""));
                    }
                    //refreshTable(headerTable);
                    headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("", "Header Name", "Header Value")), headerParamsKeyValueList));
                    headerTable.getColumnModel().getColumn(0).setMaxWidth(30);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = headerTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes = Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q -> q > headerParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(headerParamsKeyValueList::remove);
            headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("", "Header Name", "Header Value")), headerParamsKeyValueList));
            headerTable.getColumnModel().getColumn(0).setMaxWidth(30);
            saveAndChangeHeader();
            switchHeaderParam();
//            refreshTable(headerTable);
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        toolbarDecorator.addExtraAction(new ToolbarDecorator.ElementActionButton(MyResourceBundleUtil.getKey("header.group.manage"), AllIcons.Actions.ListChanges) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                int idx = -1;
                List<HeaderGroup> headerGroupList = config.getHeaderGroupList();
                HeaderGroup currentHeaderGroup = headerGroupList.stream().filter(q -> getActiveProject().equals(q.getProjectName())).findFirst().orElse(null);
                if (currentHeaderGroup != null) {
                    idx = headerGroupList.indexOf(currentHeaderGroup);
                }
                HeaderGroupView dialog = new HeaderGroupView(myProject, currentHeaderGroup, getActiveProject(), getActiveEnv(), config.getEnvList());
                if (dialog.showAndGet()) {
                    HeaderGroup viewHeaderGroup = dialog.changeAndGet();
                    if (idx == -1) {
                        headerGroupList.add(viewHeaderGroup);
                    } else {
                        headerGroupList.set(idx, viewHeaderGroup);
                    }
                    switchHeaderParam();
                }
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
        headerPanel = toolbarDecorator.createPanel();
    }

    private void renderingResponseInfoPanel() {
        responseInfoTable = createResponseInfoTable();
        responseInfoTable.getEmptyText().setText("No info");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(responseInfoTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(null);
        toolbarDecorator.setRemoveAction(null);
        responseInfoPanel = toolbarDecorator.createPanel();
    }

    private void renderingJsonResponsePanel() {
        responseTable = createJsonResponseTable();
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(responseTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddActionName("Add to Headers").setAddAction(anActionButton -> {
                    int selectedRow = responseTable.getSelectedRow();
                    CustomNode node = (CustomNode) ((ListTreeTableModelOnColumns) responseTable.getTableModel()).getRowValue(selectedRow);
                    String key = node.getKey();
                    Object value = node.getValue();
                    DataMapping dataMapping = headerParamsKeyValueList.stream().filter(q -> q.getType().equals(key)).findFirst().orElse(null);
                    if (dataMapping == null) {
                        DataMapping addOne = new DataMapping(key, value.toString());
                        headerParamsKeyValueList.add(addOne);
                    } else {
                        dataMapping.setValue(value.toString());
                    }
                    FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
                    assert config != null;
                    config.setHeaderList(headerParamsKeyValueList);
                    saveAndChangeHeader();
                    //refreshTable(headerTable);
                    headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("", "Header Name", "Header Value")), headerParamsKeyValueList));
                    tabbedPane.setSelectedIndex(0);
                    headerTable.getColumnModel().getColumn(0).setMaxWidth(30);
                }
        );
        toolbarDecorator.setRemoveAction(null);

        toolbarDecorator.setAddActionUpdater(e -> {
            int selectedRow = responseTable.getSelectedRow();
            ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) responseTable.getTableModel();
            CustomNode node = (CustomNode) myModel.getRowValue(selectedRow);
            return node != null && node.isLeaf() && selectedRow != 0;
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        jsonResponsePanel = toolbarDecorator.createPanel();
    }

    /**
     * 渲染UrlParams table面板
     *
     * @author Kings
     * @date 2021/06/02
     */
    private void renderingUrlParamsTablePanel() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        ParamGroup paramGroup = config.getParamGroup();
        String methodType = paramGroup.getMethodType();
        if (!"GET".equals(methodType)) {
            urlParamsKeyValueList = new ArrayList<>();
        }
        urlParamsTable = createUrlParamsKeyValueTable();
        urlParamsTable.getEmptyText().setText("No params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(urlParamsTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = urlParamsTable.getSelectedRow();
                    selectedRow = urlParamsKeyValueList.isEmpty() ? -1 : selectedRow;
                    selectedRow = Math.min(selectedRow, urlParamsKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        urlParamsKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        urlParamsKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    refreshTable(urlParamsTable);
                    //urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
                    resizeTable(urlParamsTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = urlParamsTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes = Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q -> q > urlParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(urlParamsKeyValueList::remove);
            refreshTable(urlParamsTable);
            //urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
            resizeTable(urlParamsTable);
            changeUrlParamsText();
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        urlParamsTablePanel = toolbarDecorator.createPanel();
    }

    public void refreshResponseTable(String body) {
        CustomNode root = new CustomNode("Root", "");
        if (StringUtils.isBlank(body)) {
            ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
            return;
        }

        if (body.startsWith("{")) {
            convertJsonObjectToNode(root, JSONObject.parseObject(body));
            ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
        } else {
            convertJsonArrayToNode("index ", JSONObject.parseArray(body), root);
            ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
        }

        expandAll(responseTable.getTree(), new TreePath(root), true);
        responseTable.updateUI();
    }

    private void renderingPathParamsPanel() {
        pathParamsTable = createPathParamKeyValueTable();
        pathParamsTable.getEmptyText().setText("No params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(pathParamsTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = pathParamsTable.getSelectedRow();
                    selectedRow = Math.min(selectedRow, pathParamsKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        pathParamsKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        pathParamsKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    refreshTable(pathParamsTable);
                    //pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
                    resizeTable(pathParamsTable);
                    changeUrl();
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = pathParamsTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes = Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q -> q > pathParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(pathParamsKeyValueList::remove);
            refreshTable(pathParamsTable);
            //pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
            resizeTable(pathParamsTable);
            changeUrl();
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        pathParamsPanel = toolbarDecorator.createPanel();
    }

    /**
     * 渲染UrlEncoded table面板
     *
     * @author Kings
     * @date 2021/06/02f
     */
    private void renderingUrlEncodedPanel() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        ParamGroup paramGroup = config.getParamGroup();
        LinkedHashMap<String, Object> bodyParamMap = paramGroup.getBodyParamMap();
        String methodType = paramGroup.getMethodType();
        if (!"GET".equals(methodType)) {
            //body param
            Object bodyParam = bodyParamMap.values().stream().findFirst().orElse("");
            if ("".equals(bodyParam)) {
                //json形式 urlencoded 值为空
                urlEncodedKeyValueList = new ArrayList<>();
            }
        } else {
            //get urlencoded 值为空
            urlEncodedKeyValueList = new ArrayList<>();
        }
        urlEncodedTable = createUrlEncodedKeyValueTable();
        urlEncodedTable.getEmptyText().setText("No params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(urlEncodedTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = urlEncodedTable.getSelectedRow();
                    selectedRow = Math.min(selectedRow, urlEncodedKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        urlEncodedKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        urlEncodedKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    refreshTable(urlEncodedTable);
                    //urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
                    resizeTable(urlEncodedTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = urlEncodedTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes = Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q -> q > urlEncodedKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(urlEncodedKeyValueList::remove);
            refreshTable(urlEncodedTable);
            //urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
            resizeTable(urlEncodedTable);
            changeUrlEncodedParamsText();
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        urlEncodedTablePanel = toolbarDecorator.createPanel();
    }

    public void renderingMultipartPanel() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        ParamGroup paramGroup = config.getParamGroup();
        LinkedHashMap<String, Object> bodyParamMap = paramGroup.getBodyParamMap();
        String methodType = paramGroup.getMethodType();
        if (!"GET".equals(methodType)) {
            //body param
            Object bodyParam = bodyParamMap.values().stream().findFirst().orElse("");
            if ("".equals(bodyParam)) {
                //json形式 urlencoded 值为空
                multipartKeyValueList = new ArrayList<>();
            }
        } else {
            //get urlencoded 值为空
            multipartKeyValueList = new ArrayList<>();
        }
        multipartTable = createMultipartKeyValueTable();
        multipartTable.getEmptyText().setText("No params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(multipartTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = multipartTable.getSelectedRow();
                    selectedRow = Math.min(selectedRow, multipartKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        multipartKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        multipartKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    refreshTable(multipartTable);
                    //multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
                    resizeTable(multipartTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = multipartTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes = Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q -> q > multipartKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(multipartKeyValueList::remove);
            refreshTable(multipartTable);
            //multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
            resizeTable(multipartTable);
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        multipartTablePanel = toolbarDecorator.createPanel();
    }

    /**
     * LinkedHashMap数据转ParamKeyValue集合
     *
     * @param paramLinkedMap 参数与地图
     * @return {@link List<ParamKeyValue> }
     * @author Kings
     * @date 2021/06/02
     */
    private List<ParamKeyValue> conventMapToList(LinkedHashMap<String, ?> paramLinkedMap) {
        List<ParamKeyValue> paramKeyValueList = new ArrayList<>();
        paramLinkedMap.forEach((key, value) -> {
            ParamKeyValue paramKeyValue = (ParamKeyValue) value;
            if (paramKeyValue.getCustomFlag() == 1) {
                KV<String, ParamKeyValue> data = (KV<String, ParamKeyValue>) paramKeyValue.getValue();
                //kv转成普通类型
                List<ParamKeyValue> list = new ArrayList<>();
                convertToParamKeyValueList("", data, list);
                paramKeyValueList.addAll(list);
            } else {
                paramKeyValueList.add(paramKeyValue);
            }
        });
        return paramKeyValueList;
    }

    private List<ParamKeyValue> conventPathParamsToList(LinkedHashMap<String, Object> paramLinkedMap) {
        List<ParamKeyValue> paramKeyValueList = new ArrayList<>();
        paramLinkedMap.forEach((key, value) -> paramKeyValueList.add((ParamKeyValue) value));
        return paramKeyValueList;
    }

    /**
     * requestParam urlEncodedParam 转 text
     * 每个参数换行处理
     *
     * @param paramKeyValueList 参数键值列表
     * @return {@link String }
     * @author Kings
     * @date 2021/06/02
     */
    private String conventDataToString(List<ParamKeyValue> paramKeyValueList) {
        StringBuilder sb = new StringBuilder();
        paramKeyValueList.forEach(paramKeyValue -> {
            Object value = paramKeyValue.getValue();
            value = paramKeyValue.getEnabled() ? value : "";
            if (paramKeyValue.getCustomFlag() == 2) {
                //基本类型映射  key=value
                sb.append(paramKeyValue.getKey()).append("=").append(value).append("\n&");
            } else {
                //对象 直接拼上value
                sb.append(value).append("\n&");
            }
        });
        return StringUtils.removeEnd(sb.toString(), "\n&");
    }


    private ColumnInfo<Object, Object>[] getPathColumnInfo() {
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[4];
        List<String> titleList = Lists.newArrayList("", "Type", "Key", "Value");
        for (int i = 0; i < titleList.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(titleList.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };

            columnArray[i] = envColumn;
        }
        return columnArray;
    }

    /**
     * table列信息
     *
     * @return {@link ColumnInfo<Object, Object>[] }
     * @author Kings
     * @date 2021/06/02
     */
    private ColumnInfo<Object, Object>[] getColumnInfo() {
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[2];
        List<String> titleList = Lists.newArrayList("Key", "Value");
        for (int i = 0; i < titleList.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(titleList.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };

            columnArray[i] = envColumn;
        }
        return columnArray;
    }


    private TreeTableView createJsonResponseTable() {
        //初始化为空
        CustomNode root = new CustomNode("Root", "");
        convertToNode(true, root, new LinkedHashMap<>());
        ColumnInfo[] columnInfo = new ColumnInfo[]{
                new TreeColumnInfo("Name") {
                    @Override
                    public int getWidth(JTable table) {
                        return JSON_TABLE_COLUMN_NAME_WIDTH;
                    }

                },   // <-- This is important!
                new ColumnInfo("Value") {
                    @Nullable
                    @Override
                    public Object valueOf(Object o) {
                        if (o instanceof CustomNode) {
                            return ((CustomNode) o).getValue();
                        } else return o;
                    }
                }
        };

        ListTreeTableModelOnColumns model = new ListTreeTableModelOnColumns(root, columnInfo);
        TreeTableView table = new TreeTableView(model) {
            @Override
            public void setTreeCellRenderer(TreeCellRenderer renderer) {
                super.setTreeCellRenderer(new Renderer());
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (row != 0 && column == 1) {
                    return new MyWrapCellRenderer();
                }
                return super.getCellRenderer(row, column);
            }


            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (row != 0 && column == 1) {
                    return new MyWrapCellEditor();
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CustomNode node = (CustomNode) myModel.getRowValue(row);
                if (column == 0) {
                    return node.getKey();
                } else {
                    return node.getValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CustomNode node = (CustomNode) myModel.getRowValue(row);
                return row != 0 && column == 1 && (!TypeUtil.Type.Object.name().equals(node.getType()) && !TypeUtil.Type.Array.name().equals(node.getType()));
            }

        };
        table.setRootVisible(true);
        table.setVisible(true);
        return table;
    }

    /**
     * 解析数据,异常返回默认值
     *
     * @param type 类型
     * @return {@link Object }
     * @author Kings
     * @date 2021/06/09
     */
    private Object convertCellData(Object toBeConvert, String type) {
        Object defaultValue = null;
        try {

            switch (type) {
                case "Number":
                    defaultValue = 1;
                    break;
                case "Boolean":
                    defaultValue = true;
                    break;
                default:
                    defaultValue = "";
                    break;
            }
            if (TypeUtil.Type.Number.name().equals(type)) {
                return Integer.parseInt(toBeConvert.toString());
            } else if (TypeUtil.Type.Boolean.name().equals(type)) {
                return Boolean.parseBoolean(toBeConvert.toString());
            } else {
                return String.valueOf(toBeConvert);
            }
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private void jsonTableNodeToJson(CustomNode firstNode, JSONObject jsonObject) {
        Iterator<TreeNode> treeNodeIterator = firstNode.children().asIterator();
        while (treeNodeIterator.hasNext()) {
            CustomNode node = (CustomNode) treeNodeIterator.next();
            String key = node.getKey();
            String type = node.getType();
            Object value = node.getValue();
            if (TypeUtil.Type.Object.name().equals(type)) {
                if (node.getChildCount() == 0) {
                    continue;
                }
                if (key.contains("index ")) {
                    JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                    jsonTableNodeToJson(node, jsonObjectChild);
                    jsonObject.putAll(jsonObjectChild);
                } else {
                    JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                    jsonTableNodeToJson(node, jsonObjectChild);
                    jsonObject.put(key, jsonObjectChild);
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                if (node.getChildCount() == 0) {
                    continue;
                }
                JSONArray jsonArrayChild = new JSONArray();
                jsonTableNodeToJsonArray(jsonArrayChild, node);
                jsonObject.put(key, jsonArrayChild);
            } else {
                jsonObject.put(key, convertCellData(value, type));
            }
        }
    }

    private void jsonTableNodeToJsonArray(JSONArray jsonArrayChild, CustomNode nodeHasChild) {
        Iterator<TreeNode> treeNodeIterator = nodeHasChild.children().asIterator();
        while (treeNodeIterator.hasNext()) {
            CustomNode node = (CustomNode) treeNodeIterator.next();
            String key = node.getKey();
            String type = node.getType();
            Object value = node.getValue();
            if (TypeUtil.Type.Object.name().equals(type)) {
                if (node.getChildCount() == 0) {
                    continue;
                }
                JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                jsonTableNodeToJson(node, jsonObjectChild);
                jsonArrayChild.add(jsonObjectChild);
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                if (node.getChildCount() == 0) {
                    continue;
                }

                JSONArray jsonArrayChildChild = new JSONArray();
                jsonTableNodeToJsonArray(jsonArrayChildChild, node);
                jsonArrayChild.add(jsonArrayChildChild);
            } else {
                jsonArrayChild.add(convertCellData(value, type));
            }
        }
    }


    private String bodyParamMapToJson() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        convertToMap(bodyParamMap, map, false);
        return JSON.toJSONString(map.get(map.keySet().stream().findFirst().orElse("")), true);
    }


    class Renderer extends ColoredTreeCellRenderer {
        @Override
        public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            //解决TreeTable key加上>
            CustomNode node = (CustomNode) value;
            append(node.getKey());
            setToolTipText(node.getComment());
        }
    }

    class IconListRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;
        private Map<Object, Icon> icons = null;

        public IconListRenderer(Map<Object, Icon> icons) {
            this.icons = icons;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Icon icon = icons.get(value);
            JLabel picture = new JLabel("", icon, JLabel.LEFT);
            if (index != -1) {
                //下拉才显示值
                picture.setText(value.toString());
            }
            picture.setHorizontalAlignment(JLabel.LEFT);
            return picture;
        }


    }

    private static void expandAll(JTree tree, TreePath parent, boolean expand) {
        // Traverse children
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }

        // Expansion or collapse must be done bottom-up
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

    private ColumnInfo[] jsonColumnInfo() {
        ColumnInfo value = new ColumnInfo("Value") {
            @Nullable
            @Override
            public Object valueOf(Object o) {
                if (o instanceof CustomNode) {
                    return ((CustomNode) o).getValue();
                } else return o;
            }
        };
        ColumnInfo type = new ColumnInfo("Type") {
            @Nullable
            @Override
            public Object valueOf(Object o) {
                if (o instanceof CustomNode) {
                    return ((CustomNode) o).getType();
                } else return o;
            }

            @Override
            public int getWidth(JTable table) {
                return JSON_TABLE_COLUMN_TYPE_WIDTH;
            }
        };

        ColumnInfo[] columnInfo = new ColumnInfo[]{
                new TreeColumnInfo("Name") {

                    @Override
                    public int getWidth(JTable table) {
                        return JSON_TABLE_COLUMN_NAME_WIDTH;
                    }

                },   // <-- This is important!
                type,
                value
        };
        return columnInfo;
    }

    private CustomNode convertJsonObjectToNode(CustomNode node, JSONObject jsonObject) {
        LinkedHashMap<String, Object> linkedHashMap = JSON.parseObject(JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue), new TypeReference<>() {
        });
        Set<String> keys = linkedHashMap.keySet();
        keys.forEach(key -> {
//            node.setKey(key);
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                JSONObject valueJsonObject = (JSONObject) value;
                CustomNode customNode = new CustomNode(key, null, TypeUtil.Type.Object.name());
                node.add(convertJsonObjectToNode(customNode, valueJsonObject));
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                if (jsonArray.size() == 0) {
                    return;
                }
                CustomNode nodeArray = new CustomNode(key, "", TypeUtil.Type.Array.name());
                convertJsonArrayToNode("index ", jsonArray, nodeArray);
                node.add(nodeArray);
            } else {
                node.add(new CustomNode(key, value == null ? "null" : value));
            }
        });
        return node;
    }

    private void convertJsonArrayToNode(String key, JSONArray jsonArray, CustomNode node) {
        AtomicInteger idx = new AtomicInteger();
        jsonArray.forEach(json -> {
            CustomNode nodeArray = new CustomNode(key + (idx.get()), null);
            if (json instanceof JSONObject) {
                JSONObject valueJsonObject = (JSONObject) json;
                nodeArray.setType(TypeUtil.Type.Object.name());
                node.add(convertJsonObjectToNode(nodeArray, valueJsonObject));
            } else if (json instanceof JSONArray) {
                JSONArray tmpJsonArray = (JSONArray) json;
                if (tmpJsonArray.size() == 0) {
                    return;
                }
                CustomNode nodeArrayIn = new CustomNode(key, "");
                convertJsonArrayToNode("index ", tmpJsonArray, nodeArrayIn);
                nodeArray.setType(TypeUtil.Type.Array.name());
                nodeArray.add(nodeArrayIn);
                node.add(nodeArray);
            } else {
                node.add(new CustomNode(key + (idx.get()), json));
            }
            idx.getAndIncrement();
        });
    }

    /**
     * json数据转化为map用于text展示(递归遍历)
     *
     * @param data   数据
     * @param result 结果
     * @return
     * @author Kings
     * @date 2021/06/07
     */
    private void convertToMap(LinkedHashMap<String, Object> data, LinkedHashMap<String, Object> result, boolean isRoot) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            ParamKeyValue value = (ParamKeyValue) entry.getValue();
            String type = value.getType();
            Object dataValue = value.getValue();
            if (TypeUtil.Type.Object.name().equals(type)) {
                LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                LinkedHashMap<String, Object> kv = (LinkedHashMap<String, Object>) dataValue;
                if (kv != null) {
                    if (isRoot) {
                        LinkedHashMap<String, Object> rootMap = new LinkedHashMap<>();
                        convertToMap(kv, rootMap, false);
                        result.putAll(rootMap);
                    } else {
                        convertToMap(kv, objectLinkedHashMap, false);
                        result.put(key, objectLinkedHashMap);
                    }
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                if (dataValue instanceof KV) {
                    List<Object> list = new ArrayList<>();
                    LinkedHashMap<String, Object> arrayMap = new LinkedHashMap<>();
                    convertToMap((LinkedHashMap<String, Object>) dataValue, arrayMap, false);
                    list.add(arrayMap);
                    result.put(key, list);
                } else {
                    ArrayList<ParamKeyValue> dataList = (ArrayList<ParamKeyValue>) dataValue;
                    if (dataList.size() == 0) {
                        continue;
                    }
                    LinkedHashMap<String, Object> arrayMap = new LinkedHashMap<>();
                    List<Object> list = convertArrayToMap(dataList, arrayMap);
                    result.put(key, list);
                }
            } else {
                result.put(key, dataValue);
            }
        }
    }

    /**
     * json数据 list转化为map用于text展示(递归遍历)
     *
     * @param data   数据
     * @param result 结果
     * @return
     * @author Kings
     * @date 2021/06/07
     */
    private List<Object> convertArrayToMap(ArrayList<?> data, LinkedHashMap<String, Object> result) {
        List<Object> list = new ArrayList<>();
        for (Object o : data) {
            if (o instanceof ParamKeyValue) {
                list.add(((ParamKeyValue) o).getValue());
            } else {
                KV<String, ParamKeyValue> kv = (KV<String, ParamKeyValue>) o;
                kv.keySet().forEach(k -> {
                    ParamKeyValue paramKeyValue = kv.get(k);
                    String type = paramKeyValue.getType();
                    Object value = paramKeyValue.getValue();
                    if (TypeUtil.Type.Object.name().equals(type)) {
                        LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                        LinkedHashMap<String, Object> kvValue = (KV<String, Object>) value;
                        if (kvValue != null) {
                            convertToMap(kvValue, objectLinkedHashMap, false);
                            result.put(k, objectLinkedHashMap);
                        }
                    } else if (TypeUtil.Type.Array.name().equals(type)) {
                        ArrayList<KV<String, ParamKeyValue>> dataList = (ArrayList<KV<String, ParamKeyValue>>) value;
                        if (dataList.size() != 0) {
                            LinkedHashMap<String, Object> arrayDataList = new LinkedHashMap<>();
                            List<Object> l = convertArrayToMap(dataList, arrayDataList);
                            result.put(k, l);
                        }
                    } else {
                        result.put(k, value);
                    }
                });
                list.add(result);
            }
        }
        return list;
    }


    private void convertToParamKeyValueList(String prefixKey, KV<String, ParamKeyValue> data, List<ParamKeyValue> list) {
        for (Map.Entry<String, ParamKeyValue> entry : data.entrySet()) {
            String key = entry.getKey();
            ParamKeyValue value = entry.getValue();
            String type = value.getType();
            Object dataValue = value.getValue();
            String comment = value.getComment();
            if (TypeUtil.Type.Object.name().equals(type)) {
                List<ParamKeyValue> childObject = new ArrayList<>();
                convertToParamKeyValueList(prefixKey + key + ".", (KV<String, ParamKeyValue>) dataValue, childObject);
                list.addAll(childObject);
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                ArrayList childList = (ArrayList) value.getValue();
                if (list.size() == 0) {
                    continue;
                }
                convertArrayToParamKeyValueList(prefixKey + key, childList, list);
            } else {
                list.add(new ParamKeyValue(prefixKey + key, dataValue, 2, type, comment));
            }
        }
    }

    private void convertArrayToParamKeyValueList(String key, ArrayList childList, List<ParamKeyValue> list) {
        for (int i = 0; i < childList.size(); i++) {
            String arrayKey = key + "[" + i + "]";
            Object o = childList.get(i);
            if (o instanceof ParamKeyValue) {
                //非对象进入
                ParamKeyValue paramKeyValue = (ParamKeyValue) o;
                paramKeyValue.setKey(key + "[0]");
                list.add(paramKeyValue);
            } else {
                KV<String, ParamKeyValue> kv = (KV<String, ParamKeyValue>) o;
                kv.forEach((k, v) -> {
                    ParamKeyValue value = kv.get(k);
                    Object dataValue = value.getValue();
                    String type = value.getType();
                    String comment = value.getComment();
                    if (TypeUtil.Type.Object.name().equals(type)) {
                        convertToParamKeyValueList(arrayKey + ".", (KV<String, ParamKeyValue>) dataValue, list);
                    } else if (TypeUtil.Type.Array.name().equals(type)) {
                        ArrayList<KV<String, ParamKeyValue>> childArrayList = (ArrayList<KV<String, ParamKeyValue>>) value.getValue();
                        if (childArrayList.size() != 0) {
                            convertArrayToParamKeyValueList(key + "." + arrayKey, childArrayList, list);
                        }
                    } else {
                        list.add(new ParamKeyValue(arrayKey + "." + k, dataValue, 2, type, comment));
                    }
                });
            }
        }
    }


    /**
     * json数据转树节点
     *
     * @param node 节点
     * @param data 数据
     * @return {@link CustomNode }
     * @author Kings
     * @date 2021/06/07
     */
    private CustomNode convertToNode(boolean isRoot, CustomNode node, LinkedHashMap<String, Object> data) {
        Set<String> keys = data.keySet();
        keys.forEach(key -> {
//            node.setKey(key);
            ParamKeyValue value = (ParamKeyValue) data.get(key);
            String type = value.getType();
            String comment = value.getComment();
            if (TypeUtil.Type.Object.name().equals(type)) {
                KV valueJsonObject = (KV) value.getValue();
                if (valueJsonObject == null) {
                    CustomNode nodeObject = new CustomNode(key, null, TypeUtil.Type.Object.name(), comment);
                    node.add(nodeObject);
                    return;
                }
                if (isRoot) {
                    convertToNode(false, node, valueJsonObject);
                } else {
                    CustomNode customNode = new CustomNode(key, null, type, comment);
                    node.add(convertToNode(false, customNode, valueJsonObject));
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                Object valueChild = value.getValue();
                if (valueChild instanceof KV) {
                    CustomNode addNode;
                    if (isRoot) {
                        addNode = new CustomNode("index 0", null, TypeUtil.Type.Object.name());
                    } else {
                        addNode = node;
                    }

                    KV k = (KV) valueChild;
                    Object o = k.entrySet().stream().findFirst().get();
                    if (o instanceof ArrayList) {
                        KV<String, ArrayList<ParamKeyValue>> listKV = k;
                        for (Map.Entry<String, ArrayList<ParamKeyValue>> entry : listKV.entrySet()) {
                            ArrayList<ParamKeyValue> basicTypeValue = entry.getValue();
                            for (ParamKeyValue paramKeyValue : basicTypeValue) {
                                CustomNode customNode = new CustomNode("", paramKeyValue.getValue(), paramKeyValue.getType(), comment);
                                addNode.add(customNode);
                            }
                        }
                    } else {
                        //参数直接传BeanName []
                        for (Map.Entry<String, ParamKeyValue> entry : ((KV<String, ParamKeyValue>) k).entrySet()) {
                            ParamKeyValue paramKeyValue = entry.getValue();
                            String childType = paramKeyValue.getType();
                            String childKey = paramKeyValue.getKey();
                            Object childValue = paramKeyValue.getValue();
                            String childComment = paramKeyValue.getComment();
                            if (TypeUtil.Type.Object.name().equals(childType)) {
                                CustomNode customNode = new CustomNode(childKey, null, childType, childComment);
                                addNode.add(convertToNode(false, customNode, (KV) childValue));
                            } else if (TypeUtil.Type.Array.name().equals(childType)) {
                                convertArrayToNode(false, childKey, childComment, (ArrayList) childValue, addNode);
                            } else {
                                CustomNode customNode = new CustomNode(childKey, childValue, childType, childComment);
                                addNode.add(customNode);
                            }
                        }
                    }
                    if (isRoot) {
                        node.add(addNode);
                    }
                } else {
                    ArrayList list = (ArrayList) valueChild;
                    if (list.size() == 0) {
                        CustomNode nodeArray = new CustomNode(key, null, TypeUtil.Type.Array.name(), comment);
                        node.add(nodeArray);
                        return;
                    }

                    convertArrayToNode(isRoot, key, comment, list, node);
                }
            } else {
                node.add(new CustomNode(key, value.getValue(), type, comment));
            }
        });
        return node;
    }


    /**
     * json数据中list转树节点
     *
     * @param key      关键
     * @param dataList 数据列表
     * @param node     节点
     * @author Kings
     * @date 2021/06/07
     */
    private void convertArrayToNode(boolean isRoot, String key, String comment, ArrayList dataList, CustomNode node) {
        CustomNode addNode;
        if (isRoot) {
            addNode = node;
        } else {
            addNode = new CustomNode(key, null, TypeUtil.Type.Array.name(), comment);
        }
        for (int j = 0; j < dataList.size(); j++) {
            Object o = dataList.get(j);
            if (o instanceof ParamKeyValue) {
                //非对象进入
                ParamKeyValue paramKeyValue = (ParamKeyValue) o;
                CustomNode nodeArrayIndex = new CustomNode("index " + j, paramKeyValue.getValue(), paramKeyValue.getType());
                addNode.add(nodeArrayIndex);
            } else {
                //对象进入
                KV<String, ParamKeyValue> kv = (KV<String, ParamKeyValue>) dataList.get(j);
                CustomNode nodeArrayIndex = new CustomNode("index " + j, null, TypeUtil.Type.Object.name());
                kv.entrySet().forEach(inKv -> {
                    String inKey = inKv.getKey();
                    ParamKeyValue value = kv.get(inKey);
                    String type = value.getType();
                    String commentChild = value.getComment();
                    if (TypeUtil.Type.Object.name().equals(type)) {
                        KV valueKvObject = (KV) value.getValue();
                        if (valueKvObject == null) {
                            return;
                        }
                        CustomNode customNode = new CustomNode(inKey, null, type, commentChild);
                        nodeArrayIndex.add(convertToNode(false, customNode, valueKvObject));
                    } else if (TypeUtil.Type.Array.name().equals(type)) {
                        ArrayList<KV<String, ParamKeyValue>> list = (ArrayList<KV<String, ParamKeyValue>>) value.getValue();
                        if (list.size() == 0) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            convertArrayToNode(false, inKey, commentChild, list, nodeArrayIndex);
                        }
                    } else {
                        nodeArrayIndex.add(new CustomNode(inKey, value.getValue(), type, commentChild));
                    }
                });
                addNode.add(nodeArrayIndex);
            }
        }
        if (!isRoot) {
            node.add(addNode);
        }

    }

    /**
     * 自定义节点 json树节点
     *
     * @author Kings
     * @date 2021/06/07
     * @see DefaultMutableTreeNode
     */
    private class CustomNode extends DefaultMutableTreeNode {
        private String key;
        private Object value;
        private String type;
        private String comment;

        public CustomNode() {
        }

        public CustomNode(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public CustomNode(String key, Object value, String type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public CustomNode(String key, Object value, String type, String comment) {
            this.key = key;
            this.value = value;
            this.type = type;
            this.comment = comment;
        }


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    /**
     * 创建urlEncoded table
     *
     * @return {@link JBTable }
     * @author Kings
     * @date 2021/06/02
     */
    private JBTable createPathParamKeyValueTable() {
        ColumnInfo<Object, Object>[] columns = getPathColumnInfo();
        if (pathParamsKeyValueList == null) {
            pathParamsKeyValueList = new ArrayList<>();
        }
        ListTableModel<ParamKeyValue> model = new ListTableModel<>(columns, pathParamsKeyValueList);
        JBTable table = new JBTable(model) {

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("", enable));
                }
                if (column == 1) {
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    boolean enabled = paramKeyValue.getEnabled();
                    return new JCheckBox("", enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                }
                return super.prepareRenderer(renderer, row, column);
            }


            @Override
            public Object getValueAt(int row, int column) {
                if (pathParamsKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ParamKeyValue keyValue = pathParamsKeyValueList.get(row);
                if (keyValue == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return keyValue.getEnabled();
                } else if (column == 1) {
                    return keyValue.getType();
                } else if (column == 2) {
                    return keyValue.getKey();
                } else {
                    return keyValue.getValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    boolean changeFlag = false;
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    paramKeyValue.setEnabled(Boolean.parseBoolean(value));
                    if (!paramKeyValue.getValue().equals(value)) {
                        changeFlag = true;
                    }
                    if (changeFlag) {
                        changeUrl();
                    }
                }
                if (column == 1) {
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 2) {
                    boolean changeFlag = false;
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        changeFlag = true;
                    }
                    paramKeyValue.setKey(aValue.toString());
                    if (changeFlag) {
                        changeUrl();
                    }
                }
                if (column == 3) {
                    boolean changeFlag = false;
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    if (!paramKeyValue.getValue().equals(value)) {
                        changeFlag = true;
                    }
                    paramKeyValue.setValue(value);
                    if (changeFlag) {
                        changeUrl();
                    }
                }

            }
        };
        table.setVisible(true);
        TableColumn checkBoxColumn = table.getColumnModel().getColumn(0);
        checkBoxColumn.setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        pathParamsCheckBoxHeader = new CheckBoxHeader(new MyParamCheckItemListener(table));
        return table;
    }

    /**
     * 创建urlEncoded table
     *
     * @return {@link JBTable }
     * @author Kings
     * @date 2021/06/02
     */
    public JBTable createUrlEncodedKeyValueTable() {
        ColumnInfo<Object, Object>[] columns = getPathColumnInfo();
        if (urlEncodedKeyValueList == null) {
            urlEncodedKeyValueList = new ArrayList<>();
        }
        ListTableModel<ParamKeyValue> model = new ListTableModel<>(columns, urlEncodedKeyValueList);
        JBTable table = new JBTable(model) {

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("", enable));
                } else if (column == 1) {
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    boolean enabled = paramKeyValue.getEnabled();
                    return new JCheckBox("", enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                } else if (column == 2) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row, column).toString());
                    textField.setToolTipText(paramKeyValue.getComment());
                    textField.setOpaque(false);
                    return textField;
                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (urlEncodedKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ParamKeyValue keyValue = urlEncodedKeyValueList.get(row);
                if (keyValue == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return keyValue.getEnabled();
                } else if (column == 1) {
                    return keyValue.getType();
                } else if (column == 2) {
                    return keyValue.getKey();
                } else {
                    return keyValue.getValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setEnabled(Boolean.parseBoolean(aValue.toString()));
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                } else if (column == 2) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlEncodedParamChangeFlag.set(true);
                    }
                } else if (column == 3) {
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlEncodedParamChangeFlag.set(true);
                    }
                    paramKeyValue.setValue(value);
                }
                changeUrlEncodedParamsText();
            }
        };
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.setVisible(true);
        urlEncodedCheckBoxHeader = new CheckBoxHeader(new MyParamCheckItemListener(table));
        return table;
    }

    class FileChooseCellEditor extends AbstractTableCellEditor {
        private TextFieldWithBrowseButton textFieldWithBrowseButton;

        public FileChooseCellEditor(TextFieldWithBrowseButton textFieldWithBrowseButton) {
            this.textFieldWithBrowseButton = textFieldWithBrowseButton;
        }

        @Override
        public Object getCellEditorValue() {
            return textFieldWithBrowseButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
            return textFieldWithBrowseButton;
        }
    }

    public JBTable createMultipartKeyValueTable() {
        ColumnInfo<Object, Object>[] columns = getPathColumnInfo();
        if (multipartKeyValueList == null) {
            multipartKeyValueList = new ArrayList<>();
        }
        ListTableModel<ParamKeyValue> model = new ListTableModel<>(columns, multipartKeyValueList);
        JBTable table = new JBTable(model) {

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("", enable));
                }
                if (column == 1) {
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeAndFileComboBox(type));
                }
                if (column == 3) {
                    String type = (String) getValueAt(row, 1);
                    String value = (String) getValueAt(row, 2);
                    if (TypeUtil.Type.File.name().equals(type)) {
                        VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFileDescriptor(), myProject, LocalFileSystem.getInstance().findFileByIoFile(new File(value)));
                        String path = virtualFile == null ? value : virtualFile.getCanonicalPath();
                        TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton(new JTextField(path));
                        return new FileChooseCellEditor(textFieldWithBrowseButton);
                    }
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    boolean enabled = paramKeyValue.getEnabled();
                    return new JCheckBox("", enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeAndFileComboBox(type);
                } else if (column == 2) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row, column).toString());
                    textField.setToolTipText(paramKeyValue.getComment());
                    textField.setOpaque(false);
                    return textField;
                } else if (column == 3) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    if (TypeUtil.Type.File.name().equals(type)) {
                        return new TextFieldWithBrowseButton(new JTextField(paramKeyValue.getValue().toString()));
                    } else {
                        return super.prepareRenderer(renderer, row, column);
                    }

                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (multipartKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ParamKeyValue keyValue = multipartKeyValueList.get(row);
                if (keyValue == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return keyValue.getEnabled();
                } else if (column == 1) {
                    return keyValue.getType();
                } else if (column == 2) {
                    return keyValue.getKey();
                } else {
                    return keyValue.getValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    paramKeyValue.setEnabled(Boolean.parseBoolean(aValue.toString()));
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                } else if (column == 2) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                } else if (column == 3) {
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    if (TypeUtil.Type.File.name().equals(paramKeyValue.getType())) {
                        paramKeyValue.setValue(((TextFieldWithBrowseButton) aValue).getText());
                    } else {
                        paramKeyValue.setValue(value);
                    }

                }
            }
        };
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.setVisible(true);
        multipartCheckBoxHeader = new CheckBoxHeader(new MyParamCheckItemListener(table));
        return table;
    }

    private ColumnInfo<Object, Object>[] getColumns(List<String> titleList) {
        ColumnInfo<Object, Object>[] columns = new ColumnInfo[titleList.size()];
        for (int i = 0; i < titleList.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(titleList.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };

            columns[i] = envColumn;
        }
        return columns;
    }


    private JBTable createHeaderTable() {
        ColumnInfo<Object, Object>[] columns = getColumns(Lists.newArrayList("", "Header Name", "Header Value"));
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        calcHeaderList();
        if (headerParamsKeyValueList == null) {
            headerParamsKeyValueList = new ArrayList<>();
        }
        ListTableModel<DataMapping> model = new ListTableModel<>(columns, headerParamsKeyValueList);
        JBTable table = new JBTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0 && !headerParamsKeyValueList.isEmpty()) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    boolean enabled = dataMapping.getEnabled();
                    return new JCheckBox("", enabled);
                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0 && !headerParamsKeyValueList.isEmpty()) {
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("", enable));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (headerParamsKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = headerParamsKeyValueList.get(row);
                if (dataMapping == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return dataMapping.getEnabled();
                } else if (column == 1) {
                    return dataMapping.getType();
                } else {
                    return dataMapping.getValue();
                }
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    dataMapping.setEnabled(Boolean.parseBoolean(aValue.toString()));
                } else if (column == 1) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    dataMapping.setType(aValue.toString());
                } else if (column == 2) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    dataMapping.setValue(aValue.toString());
                }
                saveAndChangeHeader();
//                config.setHeaderList(headerParamsKeyValueList);
            }
        };
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.setVisible(true);
        return table;
    }

    private JBTable createResponseInfoTable() {
        ColumnInfo<Object, Object>[] columns = getColumns(Lists.newArrayList("Name", "Value"));
        if (responseInfoParamsKeyValueList == null) {
            responseInfoParamsKeyValueList = new ArrayList<>();
        }
        ListTableModel<ParamKeyValue> model = new ListTableModel<>(columns, responseInfoParamsKeyValueList);
        JBTable table = new JBTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //默认只允许修改value不允许修改key
                return true;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (responseInfoParamsKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ParamKeyValue keyValue = responseInfoParamsKeyValueList.get(row);
                if (keyValue == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return keyValue.getKey();
                } else {
                    return keyValue.getValue();
                }
            }
        };

        table.setVisible(true);
        return table;
    }

    /**
     * 创建urlParams table
     *
     * @return {@link JBTable }
     * @author Kings
     * @date 2021/06/02
     */
    public JBTable createUrlParamsKeyValueTable() {
        ColumnInfo<Object, Object>[] columns = getPathColumnInfo();
        if (urlParamsKeyValueList == null) {
            urlParamsKeyValueList = new ArrayList<>();
        }
        ListTableModel<ParamKeyValue> model = new ListTableModel<>(columns, urlParamsKeyValueList);
        JBTable table = new JBTable(model) {
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("", enable));
                } else if (column == 1) {
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    boolean enabled = paramKeyValue.getEnabled();
                    return new JCheckBox("", enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                } else if (column == 2) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row, column).toString());
                    textField.setToolTipText(paramKeyValue.getComment());
                    textField.setOpaque(false);
                    return textField;
                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (urlParamsKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                ParamKeyValue keyValue = urlParamsKeyValueList.get(row);
                if (keyValue == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return keyValue.getEnabled();
                } else if (column == 1) {
                    return keyValue.getType();
                } else if (column == 2) {
                    return keyValue.getKey();
                } else {
                    return keyValue.getValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    paramKeyValue.setEnabled(Boolean.parseBoolean(aValue.toString()));
                }
                if (column == 1) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 2) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlParamsChangeFlag.set(true);
                    }
                }
                if (column == 3) {
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlParamsChangeFlag.set(true);
                    }
                    paramKeyValue.setValue(value);
                }
                changeUrlParamsText();
            }

        };
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.setVisible(true);
        urlParamsCheckBoxHeader = new CheckBoxHeader(new MyParamCheckItemListener(table));
        return table;
    }

    /*****getter setter*****/

    public JComponent getContent() {
        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }


    private CollectionConfiguration.CollectionDetail filterById(String id, CollectionConfiguration.CollectionDetail detail) {
        if (detail.getId().equals(id)) {
            return detail;
        }
        for (CollectionConfiguration.CollectionDetail d : detail.getChildList()) {
            CollectionConfiguration.CollectionDetail filterResult = filterById(id, d);
            if (filterResult != null) {
                return filterResult;
            }
        }
        return null;
    }

    private CollectionConfiguration.CollectionDetail filterClassGroupByName(String name, CollectionConfiguration.CollectionDetail detail) {
        if (detail.getName().equals(name) && detail.getType() == 1) {
            return detail;
        }
        for (CollectionConfiguration.CollectionDetail d : detail.getChildList()) {
            CollectionConfiguration.CollectionDetail filterResult = filterClassGroupByName(name, d);
            if (filterResult != null) {
                return filterResult;
            }
        }
        return null;
    }

    private final class SaveRequestAction extends AnAction {
        public SaveRequestAction() {
            super(MyResourceBundleUtil.getKey("SaveRequest"), MyResourceBundleUtil.getKey("SaveRequest"), AllIcons.Actions.MenuSaveall);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            assert config != null;
            ParamGroup paramGroup = config.getParamGroup();
            if (getActiveDomain().isBlank()) {
                Messages.showMessageDialog(MyResourceBundleUtil.getKey("msg_currentDomain_null"), "Error", Messages.getInformationIcon());
                return;
            }
            if (urlTextField.getText().isBlank()) {
                Messages.showMessageDialog(MyResourceBundleUtil.getKey("msg_UrlNull"), "Error", Messages.getInformationIcon());
                return;
            }

            CollectionConfiguration collectionConfiguration = FastRequestCollectionComponent.getInstance(myProject).getState();
            assert collectionConfiguration != null;

            CollectionConfiguration.CollectionDetail collectionDetail;
            String id = "id_" + paramGroup.getClassName() + "." + paramGroup.getMethod();
            collectionDetail = filterById(id, collectionConfiguration.getDetail());
            boolean insertFlag = collectionDetail == null;
            if (insertFlag) {
                //插入
                collectionDetail = new CollectionConfiguration.CollectionDetail();
                String mid = "id_" + paramGroup.getClassName() + "." + paramGroup.getMethod();
                collectionDetail.setId(mid);
            }
            ParamGroupCollection paramGroupCollection = new ParamGroupCollection();
            collectionDetail.setEnableEnv(getActiveEnv());
            collectionDetail.setEnableProject(getActiveProject());
            collectionDetail.setDomain(getActiveDomain());
            collectionDetail.setName(StringUtils.isBlank(paramGroup.getMethodDescription()) ? "New Request" : paramGroup.getMethodDescription());
            collectionDetail.setType(2);
            paramGroupCollection.setOriginUrl(paramGroup.getOriginUrl());
            paramGroupCollection.setUrl(urlTextField.getText());
            paramGroupCollection.setMethodType((String) methodTypeComboBox.getSelectedItem());
            paramGroupCollection.setMethodDescription(StringUtils.isBlank(paramGroup.getMethodDescription()) ? "New Request" : paramGroup.getMethodDescription());
            paramGroupCollection.setClassName(paramGroup.getClassName());
            paramGroupCollection.setMethod(paramGroup.getMethod());
            paramGroupCollection.setPathParamsKeyValueListJson(JSON.toJSONString(pathParamsKeyValueList));
            paramGroupCollection.setUrlParamsKeyValueListJson(JSON.toJSONString(urlParamsKeyValueList));
            paramGroupCollection.setUrlParamsKeyValueListText(urlParamsTextArea.getText());
            paramGroupCollection.setUrlEncodedKeyValueListJson(JSON.toJSONString(urlEncodedKeyValueList));
            paramGroupCollection.setUrlEncodedKeyValueListText(urlEncodedTextArea.getText());
            paramGroupCollection.setBodyKeyValueListJson(((LanguageTextField) jsonParamsTextArea).getText());
            paramGroupCollection.setMultipartKeyValueListJson(JSON.toJSONString(multipartKeyValueList));
            collectionDetail.setParamGroup(paramGroupCollection);
            collectionDetail.setHeaderList(headerParamsKeyValueList);

            String apiClassName = paramGroup.getClassName().substring(paramGroup.getClassName().lastIndexOf(".") + 1);
            CollectionConfiguration.CollectionDetail classNameGroup = filterClassGroupByName(apiClassName, collectionConfiguration.getDetail());

            if (insertFlag) {
                String module = paramGroup.getModule();
                CollectionConfiguration.CollectionDetail root = collectionConfiguration.getDetail();
                List<CollectionConfiguration.CollectionDetail> rootChildren = root.getChildList();
                CollectionConfiguration.CollectionDetail defaultGroup = rootChildren.get(0);
                CollectionConfiguration.CollectionDetail group;
                if (module == null) {
                    group = defaultGroup;
                } else {
                    group = rootChildren.stream().filter(q -> module.equals(q.getName())).findFirst().orElse(null);
                    if(group == null){
                        group = new CollectionConfiguration.CollectionDetail();
                        group.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                        group.setName(module);
                        group.setType(1);
                        rootChildren.add(group);
                    }
                }


                //classGroup
                if(classNameGroup == null){
                    CollectionConfiguration.CollectionDetail groupDetail = new CollectionConfiguration.CollectionDetail();
                    groupDetail.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                    groupDetail.setName(apiClassName);
                    groupDetail.setType(1);
                    groupDetail.setChildList(Lists.newArrayList(collectionDetail));
                    List<CollectionConfiguration.CollectionDetail> childList = group.getChildList();
                    childList.add(groupDetail);
                    group.setChildList(childList);
                } else {
                    classNameGroup.getChildList().add(collectionDetail);
                }
            }

            //send message to change param
            MessageBus messageBus = myProject.getMessageBus();
            messageBus.connect();
            ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.ADD_REQUEST_TOPIC);
            configChangeNotifier.configChanged(true, myProject.getName());
            //兼容性处理code
            NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Success", MessageType.INFO).notify(myProject);
            // 2020.3 before
            //new NotificationGroup("toolWindowNotificationGroup", NotificationDisplayType.TOOL_WINDOW, true).createNotification("Success", NotificationType.INFORMATION).notify(myProject);
        }
    }

    private final class CopyCurlAction extends AnAction {
        public CopyCurlAction() {
            super("Copy as CURL", "Copy as cURL", PluginIcons.ICON_CURL);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            String curlData = getCurlDataAndCopy();
            //兼容性处理code
            if (StringUtils.isNoneBlank(curlData)) {
                NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Copy success", MessageType.INFO).notify(myProject);
            }
            // 2020.3 before
            //new NotificationGroup("toolWindowNotificationGroup", NotificationDisplayType.TOOL_WINDOW, true).createNotification("Success", NotificationType.INFORMATION).notify(myProject);
        }
    }

    private static final class DocAction extends AnAction {
        public DocAction() {
            super(MyResourceBundleUtil.getKey("StarDocument"), MyResourceBundleUtil.getKey("StarDocument"), PluginIcons.ICON_DOC);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
                        dp.browse(URI.create(Constant.CN_DOC_DOMAIN));
                    } else {
                        dp.browse(URI.create(Constant.EN_DOC_DOMAIN));
                    }
                }
            } catch (Exception e) {
                LOGGER.error("open url fail:%s", e, Constant.EN_DOC_DOMAIN);
            }
        }
    }

    private static final class WhatsNewAction extends AnAction {
        public WhatsNewAction() {
            super(MyResourceBundleUtil.getKey("whatsnew"), MyResourceBundleUtil.getKey("whatsnew"), PluginIcons.NOTIFICATIONS_NEW);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
                        dp.browse(URI.create(String.format("%s%s", Constant.CN_DOC_DOMAIN, "/guide/history")));
                    } else {
                        dp.browse(URI.create(String.format("%s%s", Constant.EN_DOC_DOMAIN, "guide/history")));
                    }
                }
            } catch (Exception e) {
                LOGGER.error("open url fail:%s", e, String.format("%s%s", Constant.EN_DOC_DOMAIN, "guide/history"));
            }
        }
    }


//    public class ToolbarSendRequestAction extends DumbAwareAction {
//
//        public ToolbarSendRequestAction() {
//            super(() -> "Send", PluginIcons.ICON_SEND);
//        }
//
//
//        @Override
//        public void actionPerformed(@NotNull AnActionEvent e) {
//            sendRequestEvent(e,false);
//        }
//
//        @Override
//        public void update(@NotNull AnActionEvent e) {
//            e.getPresentation().setEnabled(sendButtonFlag);
//        }
//
//        @Override
//        public @Nullable @NlsActions.ActionText String getTemplateText() {
//            return "Fast Request Send";
//        }
//
//        @Override
//        protected void setShortcutSet(@NotNull ShortcutSet shortcutSet) {
//            CustomShortcutSet altPlus = new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
//            super.setShortcutSet(altPlus);
//        }
//
//
//    }
//
//    public class ToolbarSendAndDownloadRequestAction extends DumbAwareAction {
//
//        public ToolbarSendAndDownloadRequestAction() {
//            super(() -> "Send and Download", PluginIcons.ICON_SEND_DOWNLOAD);
//        }
//
//        @Override
//        public void actionPerformed(@NotNull AnActionEvent e) {
//            sendRequestEvent(e,true);
//        }
//
//        @Override
//        public void update(@NotNull AnActionEvent e) {
//            e.getPresentation().setEnabled(sendButtonFlag);
//        }
//
//        @Override
//        protected void setShortcutSet(@NotNull ShortcutSet shortcutSet) {
//            CustomShortcutSet altMinue = new CustomShortcutSet(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
//            super.setShortcutSet(altMinue);
//        }
//
//    }


    private static final class CoffeeMeAction extends AnAction {
        public CoffeeMeAction() {
            super(MyResourceBundleUtil.getKey("CoffeeMe"), MyResourceBundleUtil.getKey("CoffeeMe"), AllIcons.Ide.Gift);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            SupportView supportView = new SupportView();
            supportView.show();
        }
    }

    private class RetryAction extends AnAction {
        public RetryAction() {
            super(MyResourceBundleUtil.getKey("regenerate"), MyResourceBundleUtil.getKey("regenerate"), AllIcons.Actions.Redo);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            assert config != null;
            ParamGroup paramGroup = config.getParamGroup();
            String className = paramGroup.getClassName();
            String methodName = paramGroup.getMethod();
            if (StringUtils.isBlank(className)) {
                NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("You should generate first", MessageType.ERROR)
                        .addAction(new NotificationAction("Document") {
                            @Override
                            public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
                                Desktop dp = Desktop.getDesktop();
                                if (dp.isSupported(Desktop.Action.BROWSE)) {
                                    try {
                                        if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
                                            dp.browse(URI.create(Constant.CN_DOC_DOMAIN + "/guide/feature/#%E9%87%8D%E6%96%B0%E7%94%9F%E5%AD%98%E8%AF%B7%E6%B1%82"));
                                        } else {
                                            dp.browse(URI.create(String.format("%s/guide/feature/#regenetate", Constant.EN_DOC_DOMAIN)));
                                        }
                                    } catch (IOException ex) {
                                        LOGGER.error("open url fail:%s/guide/feature/#regenetate", ex, Constant.EN_DOC_DOMAIN);
                                    }
                                }
                            }
                        })
                        .notify(myProject);
                return;
            }
            PsiClass psiClass = JavaPsiFacade.getInstance(myProject).findClass(className, GlobalSearchScope.projectScope(myProject));
            if (psiClass != null) {
                PsiElement[] elementArray = psiClass.findMethodsByName(methodName, true);
                if (elementArray.length > 0) {
                    PsiMethod psiMethod = (PsiMethod) elementArray[0];
                    PsiNavigateUtil.navigate(psiMethod);
                    GeneratorUrlService generatorUrlService = ApplicationManager.getApplication().getService(GeneratorUrlService.class);
                    generatorUrlService.generate(psiMethod);
                    refresh(true);
                }
            }
        }
    }

    private class GotoFile extends AnAction {
        private File file;

        public GotoFile(File file) {
            super(file.getName());
            this.file = file;
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            RevealFileAction.openFile(file);
        }
    }

    private void calcHeaderList() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        List<HeaderGroup> headerGroupList = config.getHeaderGroupList();
        if (headerGroupList == null || headerGroupList.isEmpty()) {
            headerParamsKeyValueList = new ArrayList<>();
            return;
        }
        String enableEnv = getActiveEnv();
        String enableProject = getActiveProject();
        if (StringUtils.isBlank(enableEnv) || StringUtils.isBlank(enableProject)) {
            headerParamsKeyValueList = new ArrayList<>();
            return;
        }
        HeaderGroup headerGroup = headerGroupList.stream().filter(q -> enableProject.equals(q.getProjectName())).findFirst().orElse(null);
        if (headerGroup == null) {
            headerParamsKeyValueList = new ArrayList<>();
            return;
        }
        Map<String, LinkedHashMap<String, String>> envMap = headerGroup.getEnvMap();
        if (envMap == null || envMap.isEmpty()) {
            headerParamsKeyValueList = new ArrayList<>();
            return;
        }
        List<DataMapping> headerList = new ArrayList<>();
        LinkedHashMap<String, String> headerKeyValues = envMap.get(enableEnv);
        if (headerKeyValues == null || headerKeyValues.isEmpty()) {
            headerParamsKeyValueList = new ArrayList<>();
            return;
        }
        for (Map.Entry<String, String> entry : headerKeyValues.entrySet()) {
            headerList.add(new DataMapping(entry.getKey(), entry.getValue(), true));
        }
        headerParamsKeyValueList = headerList;
    }

    private void switchHeaderParam() {
        calcHeaderList();
        refreshHeader();
    }

    private void refreshHeader() {
        headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("", "Header Name", "Header Value")), headerParamsKeyValueList));
        headerTable.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    private String getActiveEnv() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;

        FastRequestCurrentProjectConfiguration projectConfig = FastRequestCurrentProjectConfigComponent.getInstance(myProject).getState();
        assert projectConfig != null;

        String projectEnableEnv = projectConfig.getEnableEnv();
        String globalEnableEnv = config.getEnableEnv();
        return StringUtils.isNoneBlank(projectEnableEnv) ? projectEnableEnv : globalEnableEnv;
    }

    private String getActiveDomain() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;

        FastRequestCurrentProjectConfiguration projectConfig = FastRequestCurrentProjectConfigComponent.getInstance(myProject).getState();
        assert projectConfig != null;

        String projectEnableDomain = projectConfig.getDomain();
        String globalEnableDomain = config.getDomain();
        return StringUtils.isNoneBlank(projectEnableDomain) ? projectEnableDomain : globalEnableDomain;
    }

    private String getActiveProject() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;

        FastRequestCurrentProjectConfiguration projectConfig = FastRequestCurrentProjectConfigComponent.getInstance(myProject).getState();
        assert projectConfig != null;

        String projectEnableProject = projectConfig.getEnableProject();
        String globalEnableProject = config.getEnableProject();
        return StringUtils.isNoneBlank(projectEnableProject) ? projectEnableProject : globalEnableProject;
    }

    private void saveAndChangeHeader() {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        String enableEnv = getActiveEnv();
        String enableProject = getActiveProject();
        if (StringUtils.isBlank(enableEnv) || StringUtils.isBlank(enableProject)) {
            return;
        }

        List<HeaderGroup> headerGroupList = config.getHeaderGroupList();
        HeaderGroup headerGroup;
        LinkedHashMap<String, String> keyValueMap = headerParamsKeyValueList.stream().filter(DataMapping::getEnabled)
                .collect(Collectors.toMap(DataMapping::getType, DataMapping::getValue, (oldOne, newOne) -> newOne, LinkedHashMap::new));
        if (headerGroupList == null || headerGroupList.isEmpty()) {
            headerGroupList = new ArrayList<>();
            LinkedHashMap<String, LinkedHashMap<String, String>> envMap = Maps.newLinkedHashMap();
            envMap.put(enableEnv, keyValueMap);
            headerGroupList.add(new HeaderGroup(enableProject, envMap));
            config.setHeaderGroupList(headerGroupList);
            return;
        }
        if ((headerGroup = headerGroupList.stream().filter(q -> enableProject.equals(q.getProjectName())).findFirst().orElse(null)) == null) {
            LinkedHashMap<String, LinkedHashMap<String, String>> envMap = Maps.newLinkedHashMap();
            envMap.put(enableEnv, keyValueMap);
            headerGroupList.add(new HeaderGroup(enableProject, envMap));
            config.setHeaderGroupList(headerGroupList);
            return;
        }
        Map<String, LinkedHashMap<String, String>> envMap = headerGroup.getEnvMap();
        if (envMap == null || envMap.isEmpty()) {
            envMap = new LinkedHashMap<>();
            envMap.put(enableEnv, keyValueMap);
            headerGroup.setEnvMap(envMap);
            return;
        }
        envMap.put(enableEnv, keyValueMap);
        headerGroup.setEnvMap(envMap);
    }

    public List<DataMapping> getHeaderParamsKeyValueList() {
        return headerParamsKeyValueList;
    }
}
