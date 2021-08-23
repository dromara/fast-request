package io.github.kings1990.plugin.fastrequest.view;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.*;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
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
import io.github.kings1990.plugin.fastrequest.action.OpenConfigAction;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestCollectionComponent;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.model.*;
import io.github.kings1990.plugin.fastrequest.service.GeneratorUrlService;
import io.github.kings1990.plugin.fastrequest.util.*;
import io.github.kings1990.plugin.fastrequest.view.inner.SupportView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
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
    private static final Logger LOGGER = Logger.getInstance(CommonConfigView.class);
    public static final int JSON_TABLE_COLUMN_NAME_WIDTH = 200;
    public static final int JSON_TABLE_COLUMN_TYPE_WIDTH = 80;

    private final GeneratorUrlService generatorUrlService = ServiceManager.getService(GeneratorUrlService.class);
    private final Project myProject;
    private JPanel panel;
    private JComboBox<String> envComboBox;
    private JComboBox<String> projectComboBox;
    private JCheckBox enableProjectCheckBox;
    private JTextField urlTextField;
    private JComboBox<String> methodTypeComboBox;
    private JTextArea urlParamsTextArea;
    private JTextArea jsonParamsTextArea;
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
    private JButton sendButton;
    private JTabbedPane responseTabbedPanel;
    private JScrollPane responseBodyScrollPanel;
    private JScrollPane responseInfoScrollPanel;
    private JComboBox<Integer> responseStatusComboBox;
    private JTextArea responseTextArea;
    private JPanel responseInfoPanel;
    private JTabbedPane multipartTabbedPane;
    private JPanel multipartTablePanel;
    private JPanel jsonResponsePanel;
    private JButton manageConfigButton;
    private JScrollPane prettyJsonPanle;
    private JTextArea prettyResponseTextArea;
    private JPanel titlePanel;
    private JLabel warnLabel1;
    private JLabel warnLabel2;
    private JTabbedPane bodyTabbedPane;
    private JScrollPane responseBodyScrollPane;

    private JBTable urlParamsTable;
    private JBTable urlEncodedTable;
    private JBTable multipartTable;
    private JBTable pathParamsTable;
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

        sendButton = new JButton("Send Request",PluginIcons.ICON_SEND);
        ActionLink managerConfigLink = new ActionLink("config", e -> {
            ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Fast Request");
        });
        managerConfigLink.setExternalLinkIcon();
        manageConfigButton = managerConfigLink;

        //2020.3before
//        manageConfigButton = new JButton();
//        manageConfigButton.addActionListener(e->{
//            ShowSettingsUtil.getInstance().showSettingsDialog(myProject, "Fast Request");
//        });
    }

    public FastRequestToolWindow(ToolWindow toolWindow,Project project) {
        super(true, false);
        this.myProject = project;
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new OpenConfigAction());
        group.addSeparator("  |  ");
        group.add(new SaveRequestAction());
        group.add(new RetryAction());
        group.add(new CopyCurlAction());
        group.addSeparator("  |  ");
        group.add(new DocAction());
        group.add(new CoffeeMeAction());
        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLWINDOW_CONTENT, group, true);
        actionToolbar.setTargetComponent(panel);
        JComponent toolbarComponent = actionToolbar.getComponent();
        Border border = IdeBorderFactory.createBorder(SideBorder.BOTTOM);
        actionToolbar.getComponent().setBorder(border);
        setToolbar(toolbarComponent);

        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;

        warnLabel1.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        manageConfigButton.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        warnLabel2.setVisible(StringUtils.isBlank(config.getDomain()));


        //env下拉列表
        CollectionComboBoxModel<String> envModel = new CollectionComboBoxModel<>(config.getEnvList());
        envComboBox.setModel(envModel);

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


        envComboBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object selectEnv = envComboBox.getSelectedItem();
                if (selectEnv == null) {
                    return;
                }
                String env = selectEnv.toString();
                List<String> envList = config.getEnvList();
                if (!envList.contains(env)) {
                    //配置删除了当前的env则默认选中第一个env
                    if (envList.isEmpty()) {
                        //env被删除完了 补全域名开关自动关闭
                        config.setEnableEnv(null);
                        config.setDomain(StringUtils.EMPTY);
                        enableProjectCheckBox.setSelected(false);
                    } else {
                        config.setEnableEnv(envList.get(0));
                    }
                    envModel.setSelectedItem(config.getEnableEnv());
                } else {
                    config.setEnableEnv(env);
                }
                //根据当前的env和project设置url
                setDomain(config);
            }
        });
        envModel.setSelectedItem(config.getEnableEnv());

        //project下拉列表
        CollectionComboBoxModel<String> projectModel = new CollectionComboBoxModel<>(config.getProjectList());
        projectComboBox.setModel(projectModel);
        projectComboBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object selectProject = projectComboBox.getSelectedItem();
                if (selectProject == null) {
                    return;
                }
                String projectSelect = selectProject.toString();
                List<String> projectList = config.getProjectList();
                if (!projectList.contains(projectSelect)) {
                    //配置删除了当前的env则默认选中第一个env
                    if (projectList.isEmpty()) {
                        //project被删除完了 补全域名开关自动关闭
                        config.setEnableProject(null);
                        config.setDomain(StringUtils.EMPTY);
                        enableProjectCheckBox.setSelected(false);
                    } else {
                        config.setEnableProject(projectList.get(0));
                    }
                    projectModel.setSelectedItem(config.getEnableProject());
                } else {
                    config.setEnableProject(projectSelect);
                }
                //根据当前的env和project设置url
                setDomain(config);
            }
        });
        projectModel.setSelectedItem(config.getEnableProject());


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
                    if(StringUtils.isNoneBlank(currentUrlParamText)) {
                        String[] split = currentUrlParamText.split("&");
                        if(split.length > 0) {
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
                    if(StringUtils.isNoneBlank(currentUrlParamText)){
                        String[] split = currentUrlParamText.split("&");
                        for (String s : split) {
                            String[] kvArray = s.split("=");
                            if (kvArray.length <= 2) {
                                String value = kvArray.length < 2 ? "" :kvArray[1].replace("\n", "");
                                ParamKeyValue paramKeyValue = new ParamKeyValue(kvArray[0], value, 2, TypeUtil.calcTypeByStringValue(value));
                                currentUrlParamsKeyValueList.add(paramKeyValue);
                            }
                        }
                    }
                    urlParamsKeyValueList = currentUrlParamsKeyValueList;
                    //refreshTable(urlParamsTable);
                    urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
                    resizeTable(urlParamsTable);
                }
            }
        });

        //copy param
//        jsonParamsTextArea.addMouseListener(copyMouseAdapter(jsonParamsTextArea));
//        urlEncodedTextArea.addMouseListener(copyMouseAdapter(urlEncodedTextArea));
//        urlParamsTextArea.addMouseListener(copyMouseAdapter(urlParamsTextArea));
//        urlTextField.addMouseListener(copyMouseAdapterField(urlTextField));
        headerParamsKeyValueList = config.getHeaderList();
        sendRequestAction();
    }

    private void changeUrlParamsText(){
        String paramStr = conventDataToString(urlParamsKeyValueList);
        urlParamsTextArea.setText(paramStr);
        urlEncodedParamChangeFlag.set(false);
    }

    private void changeUrlEncodedParamsText(){
        String paramStr = conventDataToString(urlEncodedKeyValueList);
        urlEncodedTextArea.setText(paramStr);
        urlEncodedParamChangeFlag.set(false);
    }

    private String getCurlDataAndCopy(){
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        String methodType = (String) methodTypeComboBox.getSelectedItem();
        String domain = config.getDomain();
        String sendUrl = urlTextField.getText();
        List<DataMapping> headerList = config.getHeaderList();
        String urlParam = urlParamsTextArea.getText();
        String jsonParam = jsonParamsTextArea.getText();
        String urlEncodedParam = urlEncodedTextArea.getText();

        if(StringUtils.isEmpty(sendUrl)){
            Messages.showMessageDialog("Url not exist", "Error", Messages.getInformationIcon());
            return "";
        }
        String url = domain + sendUrl;
        if(StringUtils.isNotEmpty(urlParam)){
            String urlParamDeal = urlParam.lines().collect(Collectors.joining(""));
            url = url + "?" + urlParamDeal;
        }

        StringBuilder sb = new StringBuilder("curl -X ");
        sb.append("\"").append(methodType).append("\" ");
        sb.append("\"").append(url).append("\" \\\n");
        for (DataMapping header : headerList) {
            sb.append("-H '").append(header.getType()).append(": ").append(header.getValue()).append("' \\\n");
        }
        if(StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)){
            sb.append("-H '").append("Content-Type: application/json").append("' \\\n");
        }

        if(StringUtils.isNotEmpty(urlEncodedParam)){
            String urlEncodedParamDeal = urlEncodedParam.lines().collect(Collectors.joining(""));
            sb.append("-d '").append(urlEncodedParamDeal).append("' \\\n");
        }
        if(StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)){
            String jsonParamDeal = jsonParam.lines().collect(Collectors.joining(""));
            sb.append("-d '").append(jsonParamDeal).append("' \\\n");
        }

        for (ParamKeyValue paramKeyValue : multipartKeyValueList) {
            if(!TypeUtil.Type.File.name().equals(paramKeyValue.getType())){
                sb.append("-F \"").append(paramKeyValue.getKey()).append("=").append(paramKeyValue.getValue().toString()).append("\" \\\n");
            } else {
                sb.append("-F \"").append(paramKeyValue.getKey()).append("=").append("\" \\\n");
            }
        }
        String result = sb.toString();
        ToolUtil.setClipboardString(result);
        return result;
    }

    private void sendRequestAction(){
        //send request
        //2秒内不允许狂点
        sendButton.setToolTipText("Send Request");
        sendButton.setMultiClickThreshhold(2000);
        sendButton.addActionListener(e -> {
            sendButton.setEnabled(false);
                try {
                    FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
                    assert config != null;
                    String domain = config.getDomain();
                    String sendUrl = urlTextField.getText();
                    if((StringUtils.isEmpty(sendUrl) && StringUtils.isEmpty(domain))|| !UrlUtil.isURL(sendUrl)){
                        responseTextArea.setText("Correct url required");
                        tabbedPane.setSelectedIndex(4);
                        responseTabbedPanel.setSelectedIndex(2);
                        sendButton.setEnabled(true);
                        return;
                    }



                    String methodType = (String) methodTypeComboBox.getSelectedItem();
                    HttpRequest request = HttpUtil.createRequest(Method.valueOf(methodType), domain + sendUrl);
                    headerParamsKeyValueList = headerParamsKeyValueList == null?new ArrayList<>():headerParamsKeyValueList;
                    Map<String, List<String>> headerMap = headerParamsKeyValueList.stream().filter(DataMapping::getEnabled).collect(Collectors.toMap(DataMapping::getType, p -> Lists.newArrayList(p.getValue())));
                    request.header(headerMap);

                    Map<String, Object> multipartFormParam = multipartKeyValueList.stream().filter(ParamKeyValue::getEnabled)
                            .collect(HashMap::new, (m,v)-> {
                                        Object value = v.getValue();
                                        String key = v.getKey();
                                        if (TypeUtil.Type.File.name().equals(v.getType())) {
                                            if (value != null && !StringUtils.isBlank(value.toString())) {
                                                m.put(key,new File(value.toString()));
                                            } else {
                                                m.put(key,null);
                                            }
                                        } else {
                                            m.put(key,value);
                                        }
                                    }, HashMap::putAll);

                    Map<String, Object> formParam = urlParamsKeyValueList.stream().filter(ParamKeyValue::getEnabled).collect(Collectors.toMap(ParamKeyValue::getKey, ParamKeyValue::getValue));
                    String jsonParam = jsonParamsTextArea.getText();
                    StringBuilder urlEncodedParam = new StringBuilder("");
                    urlEncodedKeyValueList.stream().filter(ParamKeyValue::getEnabled).forEach(q->{
                        urlEncodedParam.append(q.getKey()).append("=").append(q.getValue()).append("&");
                    });

                    //json优先
                    if(StringUtils.isNotEmpty(urlEncodedParam)){
                        request.body(StringUtils.removeEnd(urlEncodedParam.toString(),"&"));
                    }
                    if(StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)){
                        request.body(JSON.toJSONString(JSON.parse(jsonParam)));
                    }

                    if(!formParam.isEmpty()){
                        request.form(formParam);
                    }
                    if(!multipartFormParam.isEmpty()){
                        request.form(multipartFormParam);
                    }
                    Future<?> future = ThreadUtil.execAsync(() -> {
                        try {
                            long start = System.currentTimeMillis();
                            HttpResponse response = request.execute();
                            long end = System.currentTimeMillis();
                            tabbedPane.setSelectedIndex(4);
                            int status = response.getStatus();
                            String body = response.body();
                            if (JsonUtil.isJSON2(body)) {
                                prettyResponseTextArea.setText(JSON.toJSONString(JSON.parse(body), true));
                                responseTextArea.setText(body);
                                responseTabbedPanel.setSelectedIndex(0);
                                refreshResponseTable(body);
                            } else {
                                String subBody = body.substring(0, Math.min(body.length(), 32768));
                                if (body.length() > 32768) {
                                    subBody += "\n\ntext too large only show 32768 characters\n.............";
                                }
                                prettyResponseTextArea.setText(subBody);
                                responseTextArea.setText(subBody);
                                responseTabbedPanel.setSelectedIndex(2);
                            }
                            String duration = String.valueOf(end - start);

                            responseInfoParamsKeyValueList = Lists.newArrayList(
                                    new ParamKeyValue("Url", request.getUrl(), 2, TypeUtil.Type.String.name()),
                                    new ParamKeyValue("Cost", duration + " ms", 2, TypeUtil.Type.String.name()),
                                    new ParamKeyValue("Response status", status + " " + Constant.HttpStatusDesc.STATUS_MAP.get(status)),
                                    new ParamKeyValue("Date", DateUtil.formatDateTime(new Date()))
                            );
                            //refreshTable(responseInfoTable);
                            responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));
                            responseStatusComboBox.setSelectedItem(status);


                            responseStatusComboBox.setBackground((status >= 200 && status < 300) ? MyColor.green : MyColor.red);
                        } catch (Exception ee){
                            String errorMsg = ee.getMessage();
                            responseTextArea.setText(errorMsg);
                            prettyResponseTextArea.setText("");
                            responseStatusComboBox.setSelectedItem(0);
                            responseStatusComboBox.setBackground(MyColor.red);
                            responseInfoParamsKeyValueList = Lists.newArrayList(
                                    new ParamKeyValue("Url", request.getUrl(), 2, TypeUtil.Type.String.name()),
                                    new ParamKeyValue("Error", errorMsg)
                            );
                            //refreshTable(responseInfoTable);
                            responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));

                            CustomNode root = new CustomNode("Root", "");
                            ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
                            tabbedPane.setSelectedIndex(4);
                            responseTabbedPanel.setSelectedIndex(2);
                        }
                    });
                    if (future.isDone()){
                        sendButton.setEnabled(true);
                    }
                } catch (Exception exception){
                    String errorMsg = exception.getMessage();
                    responseTextArea.setText(errorMsg);
                    prettyResponseTextArea.setText("");
                    responseStatusComboBox.setSelectedItem(0);
                    responseStatusComboBox.setBackground(MyColor.red);
                    responseInfoParamsKeyValueList = Lists.newArrayList(
                            new ParamKeyValue("Error", errorMsg)
                    );
                    //refreshTable(responseInfoTable);
                    responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));

                    CustomNode root = new CustomNode("Root", "");
                    ((DefaultTreeModel) responseTable.getTableModel()).setRoot(root);
                    tabbedPane.setSelectedIndex(4);
                    responseTabbedPanel.setSelectedIndex(2);
                }
                sendButton.setEnabled(true);

        });
    }

    private void refreshTable(JBTable table){
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

        String activeEnv = config.getEnableEnv();
        String activeProject = config.getEnableProject();
        if (StringUtils.isEmpty(activeEnv)) {
            config.setDomain(StringUtils.EMPTY);
            return;
        }
        if (StringUtils.isEmpty(activeProject)) {
            config.setDomain(StringUtils.EMPTY);
            return;
        }
        NameGroup defaultNameGroup = new NameGroup(StringUtils.EMPTY, new ArrayList<>());
        HostGroup defaultHostGroup = new HostGroup(StringUtils.EMPTY, StringUtils.EMPTY);
        String domain = config.getDataList().stream().filter(n -> activeProject.equals(n.getName())).findFirst().orElse(defaultNameGroup)
                .getHostGroup().stream().filter(h -> activeEnv.equals(h.getEnv())).findFirst().orElse(defaultHostGroup).getUrl();
        config.setDomain(domain);
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

        warnLabel1.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        manageConfigButton.setVisible(config.getEnvList().isEmpty() || config.getProjectList().isEmpty());
        warnLabel2.setVisible(StringUtils.isBlank(config.getDomain()));

        CollectionComboBoxModel<String> projectModel = new CollectionComboBoxModel<>(config.getProjectList());
        projectComboBox.setModel(projectModel);

        CollectionComboBoxModel<String> envModel = new CollectionComboBoxModel<>(config.getEnvList());
        envComboBox.setModel(envModel);

        projectComboBox.setSelectedItem(config.getEnableProject());
        envComboBox.setSelectedItem(config.getEnableEnv());
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
        }
        return jbColor;
    }

    public void refreshByCollection(CollectionConfiguration.CollectionDetail detail) {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        multipartKeyValueList = new ArrayList<>();
        urlParamsTextArea.setText("");
        jsonParamsTextArea.setText("");
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

        pathParamsKeyValueList = JSON.parseObject(pathParamsKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {});
        urlParamsKeyValueList = JSON.parseObject(urlParamsKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {});
        urlEncodedKeyValueList = JSON.parseObject(urlEncodedKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {});
        multipartKeyValueList = JSON.parseObject(multipartKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {});

        String methodType = paramGroup.getMethodType();

        methodTypeComboBox.setBackground(buildMethodColor(methodType));

        //method
        methodTypeComboBox.setSelectedItem(methodType);


        //默认urlParam是允许的即使是post json形式
        headerParamsKeyValueList = detail.getHeaderList() == null?new ArrayList<>():detail.getHeaderList();

        if ("GET".equals(methodType)) {
            urlParamsTextArea.setText(urlParamsKeyValueListText);
            if(pathParamsKeyValueList.isEmpty()){
                tabbedPane.setSelectedIndex(2);
            } else {
                tabbedPane.setSelectedIndex(1);
            }
            urlParamsTabbedPane.setSelectedIndex(0);
            //get请求urlencoded param参数为空
            urlEncodedKeyValueList = new ArrayList<>();
            urlEncodedTextArea.setText("");
            jsonParamsTextArea.setText("");
        } else {
            //body param
            if (!bodyKeyValueListJson.isBlank()) {
                //json
                jsonParamsTextArea.setText(bodyKeyValueListJson);
                tabbedPane.setSelectedIndex(3);
                bodyTabbedPane.setSelectedIndex(0);
                urlEncodedTextArea.setText("");
                urlEncodedKeyValueList = new ArrayList<>();
            } else {
                boolean isMultipart = multipartKeyValueList.stream().anyMatch(q -> TypeUtil.Type.File.name().equals(q.getType()));
                if(isMultipart) {
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
                jsonParamsTextArea.setText("");
                //如果是非get请求则request Param为空转到url Encoded参数下
                urlParamsKeyValueList = new ArrayList<>();
                urlParamsTextArea.setText("");
            }
        }

        //刷新table
        pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
        resizeTable(pathParamsTable);

        urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
        resizeTable(urlParamsTable);

        urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
        resizeTable(urlEncodedTable);

        multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
        resizeTable(multipartTable);

        urlTextField.setText(url);
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
        String mid = "id_" + paramGroup.getClassName()+"."+paramGroup.getMethod();
        CollectionConfiguration.CollectionDetail detail = filterById(mid, collectionConfiguration.getDetail());
        if(detail != null && !regenerate){
            refreshByCollection(detail);
            return;
        }

        //reset value
        multipartKeyValueList = new ArrayList<>();
        urlParamsTextArea.setText("");
        jsonParamsTextArea.setText("");
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
        pathParamsKeyValueList = conventMapToList(pathParamMap);
        headerParamsKeyValueList = config.getHeaderList() == null?new ArrayList<>():config.getHeaderList();

        if ("GET".equals(methodType)) {
            urlParamsTextArea.setText(requestParamStr);
            if (pathParamsKeyValueList.isEmpty()) {
                tabbedPane.setSelectedIndex(2);
                urlParamsTabbedPane.setSelectedIndex(0);
            } else {
                tabbedPane.setSelectedIndex(1);
            }
            //get请求urlencoded param参数为空
            urlEncodedKeyValueList = new ArrayList<>();
            urlEncodedTextArea.setText("");
            jsonParamsTextArea.setText("");
        } else {
            //body param
            if (!bodyParamMap.isEmpty()) {
                //json
                tabbedPane.setSelectedIndex(3);
                bodyTabbedPane.setSelectedIndex(0);
                jsonParamsTextArea.setText(bodyParamMapToJson());
                urlEncodedTextArea.setText("");
                urlEncodedKeyValueList = new ArrayList<>();
            } else {
                urlEncodedKeyValueList = conventMapToList(requestParamMap);
                boolean isMultipart = urlEncodedKeyValueList.stream().anyMatch(q -> TypeUtil.Type.File.name().equals(q.getType()));
                if(isMultipart) {
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
                //json设置为空
                jsonParamsTextArea.setText("");
                //如果是非get请求则request Param为空转到url Encoded参数下
                urlParamsKeyValueList = new ArrayList<>();
                urlParamsTextArea.setText("");
            }
        }
        //刷新table
        pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
        resizeTable(pathParamsTable);

        urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
        resizeTable(urlParamsTable);

        urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
        resizeTable(urlEncodedTable);

        multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
        resizeTable(multipartTable);

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
        warnLabel2.setVisible(StringUtils.isBlank(config.getDomain()));
    }

    private String buildPathParamUrl(String url) {
        List<String> paramNameList = UrlUtil.paramPathParam(url);
        if (paramNameList.isEmpty()) {
            return url;
        }
        for (ParamKeyValue paramKeyValue : pathParamsKeyValueList) {
            if(paramKeyValue.getEnabled()){
                String paramName = paramKeyValue.getKey();
                String paramNameWithSymbol = "{" + paramName + "}";
                url = url.replace(paramNameWithSymbol, paramKeyValue.getValue().toString());
            }
        }
        return url;
    }

    private void renderingHeaderTablePanel(){
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        headerTable = createHeaderTable();
        headerTable.getEmptyText().setText("No header params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(headerTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(anActionButton -> {
                    if(headerParamsKeyValueList == null){
                        headerParamsKeyValueList = new ArrayList<>();
                    }
                    int selectedRow = headerTable.getSelectedRow();
                    selectedRow = Math.min(selectedRow, headerParamsKeyValueList.size() - 1);
                    if (selectedRow == -1) {
                        headerParamsKeyValueList.add(new DataMapping("", ""));
                    } else {
                        headerParamsKeyValueList.add(selectedRow + 1, new DataMapping("", ""));
                    }
                    refreshTable(headerTable);
//                    headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = headerTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q->q > headerParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(headerParamsKeyValueList::remove);
//            headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
            refreshTable(headerTable);
        });
        headerPanel = toolbarDecorator.createPanel();
    }

    private void renderingResponseInfoPanel(){
        responseInfoTable = createResponseInfoTable();
        responseInfoTable.getEmptyText().setText("No info");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(responseInfoTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(null);
        toolbarDecorator.setRemoveAction(null);
        responseInfoPanel = toolbarDecorator.createPanel();
    }

    private void renderingJsonResponsePanel(){
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
                    if(dataMapping == null){
                        DataMapping addOne = new DataMapping(key,value.toString());
                        headerParamsKeyValueList.add(addOne);
                    } else {
                        dataMapping.setValue(value.toString());
                    }
                    FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
                    assert config != null;
                    config.setHeaderList(headerParamsKeyValueList);
                    refreshTable(headerTable);
                    //headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
                    tabbedPane.setSelectedIndex(0);
                }
        );
        toolbarDecorator.setRemoveAction(null);

        toolbarDecorator.setAddActionUpdater(e -> {
            int selectedRow = responseTable.getSelectedRow();
            ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) responseTable.getTableModel();
            CustomNode node = (CustomNode) myModel.getRowValue(selectedRow);
            return node != null && node.isLeaf() && selectedRow != 0;
        });
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
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q->q > urlParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(urlParamsKeyValueList::remove);
            refreshTable(urlParamsTable);
            //urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
            resizeTable(urlParamsTable);
            changeUrlParamsText();
        });
        urlParamsTablePanel = toolbarDecorator.createPanel();
    }

    public void refreshResponseTable(String body){
        if(StringUtils.isBlank(body)){
            return;
        }
        CustomNode root = new CustomNode("Root","");
        if(body.startsWith("{")){
            convertJsonObjectToNode(root,JSONObject.parseObject(body));
            ((DefaultTreeModel)responseTable.getTableModel()).setRoot(root);
        } else {
            convertJsonArrayToNode("index ",JSONObject.parseArray(body),root);
            ((DefaultTreeModel)responseTable.getTableModel()).setRoot(root);
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
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q->q > pathParamsKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(pathParamsKeyValueList::remove);
            refreshTable(pathParamsTable);
            //pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
            resizeTable(pathParamsTable);
            changeUrl();
        });
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
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q->q > urlEncodedKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(urlEncodedKeyValueList::remove);
            refreshTable(urlEncodedTable);
            //urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
            resizeTable(urlEncodedTable);
            changeUrlEncodedParamsText();
        });
        urlEncodedTablePanel = toolbarDecorator.createPanel();
    }

    public void renderingMultipartPanel(){
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
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.removeIf(q->q > multipartKeyValueList.size() - 1);
            indexes.stream().mapToInt(i -> i).forEach(multipartKeyValueList::remove);
            refreshTable(multipartTable);
            //multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
            resizeTable(multipartTable);
        });
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
        List<String> titleList = Lists.newArrayList("","Type", "Key", "Value");
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
        convertToNode(true,root, new LinkedHashMap<>());
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
                return false;
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

    private void jsonTableNodeToJson(CustomNode firstNode,JSONObject jsonObject){
        Iterator<TreeNode> treeNodeIterator = firstNode.children().asIterator();
        while(treeNodeIterator.hasNext()){
            CustomNode node = (CustomNode) treeNodeIterator.next();
            String key = node.getKey();
            String type = node.getType();
            Object value = node.getValue();
            if(TypeUtil.Type.Object.name().equals(type)){
                if(node.getChildCount() == 0){
                    continue;
                }
                if(key.contains("index ")){
                    JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                    jsonTableNodeToJson(node, jsonObjectChild);
                    jsonObject.putAll(jsonObjectChild);
                } else {
                    JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                    jsonTableNodeToJson(node, jsonObjectChild);
                    jsonObject.put(key, jsonObjectChild);
                }
            } else if(TypeUtil.Type.Array.name().equals(type)){
                if(node.getChildCount() == 0){
                    continue;
                }
                JSONArray jsonArrayChild = new JSONArray();
                jsonTableNodeToJsonArray(jsonArrayChild,node);
                jsonObject.put(key,jsonArrayChild);
            } else {
                jsonObject.put(key,convertCellData(value,type));
            }
        }
    }

    private void jsonTableNodeToJsonArray(JSONArray jsonArrayChild,CustomNode nodeHasChild){
        Iterator<TreeNode> treeNodeIterator = nodeHasChild.children().asIterator();
        while(treeNodeIterator.hasNext()){
            CustomNode node = (CustomNode) treeNodeIterator.next();
            String key = node.getKey();
            String type = node.getType();
            Object value = node.getValue();
            if(TypeUtil.Type.Object.name().equals(type)){
                if(node.getChildCount() == 0){
                    continue;
                }
                JSONObject jsonObjectChild = new JSONObject(new LinkedHashMap<>());
                jsonTableNodeToJson(node, jsonObjectChild);
                jsonArrayChild.add(jsonObjectChild);
            } else if(TypeUtil.Type.Array.name().equals(type)){
                if(node.getChildCount() == 0){
                    continue;
                }

                JSONArray jsonArrayChildChild = new JSONArray();
                jsonTableNodeToJsonArray(jsonArrayChildChild, node);
                jsonArrayChild.add(jsonArrayChildChild);
            } else {
                jsonArrayChild.add(convertCellData(value,type));
            }
        }
    }



    private String bodyParamMapToJson() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        convertToMap(bodyParamMap, map,false);
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
        LinkedHashMap<String, Object> linkedHashMap = JSON.parseObject(JSON.toJSONString(jsonObject), new TypeReference<>() {
        });
        Set<String> keys = linkedHashMap.keySet();
        keys.forEach(key -> {
//            node.setKey(key);
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                JSONObject valueJsonObject = (JSONObject) value;
                CustomNode customNode = new CustomNode(key, null);
                node.add(convertJsonObjectToNode(customNode, valueJsonObject));
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                if (jsonArray.size() == 0) {
                    return;
                }
                CustomNode nodeArray = new CustomNode(key, "");
                convertJsonArrayToNode("index ", jsonArray, nodeArray);
                node.add(nodeArray);
            } else {
                node.add(new CustomNode(key, value));
            }
        });
        return node;
    }

    private void convertJsonArrayToNode(String key, JSONArray jsonArray, CustomNode node) {
        AtomicInteger idx = new AtomicInteger();
        jsonArray.forEach(json -> {
            CustomNode nodeArray = new CustomNode(key + (idx.getAndIncrement()), null);
            if (json instanceof JSONObject) {
                JSONObject valueJsonObject = (JSONObject) json;
                node.add(convertJsonObjectToNode(nodeArray, valueJsonObject));
            } else if (json instanceof JSONArray) {
                JSONArray tmpJsonArray = (JSONArray) json;
                if (tmpJsonArray.size() == 0) {
                    return;
                }
                CustomNode nodeArrayIn = new CustomNode(key, "");
                convertJsonArrayToNode("index ", tmpJsonArray, nodeArrayIn);
                nodeArray.add(nodeArrayIn);
                node.add(nodeArray);
            } else {
                node.add(new CustomNode(key, json));
            }
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
    private void convertToMap(LinkedHashMap<String, Object> data, LinkedHashMap<String, Object> result,boolean isRoot) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            ParamKeyValue value = (ParamKeyValue) entry.getValue();
            String type = value.getType();
            Object dataValue = value.getValue();
            if (TypeUtil.Type.Object.name().equals(type)) {
                LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                LinkedHashMap<String, Object> kv = (LinkedHashMap<String, Object>) dataValue;
                if (kv != null) {
                    if(isRoot){
                        LinkedHashMap<String, Object> rootMap = new LinkedHashMap<>();
                        convertToMap(kv, rootMap,false);
                        result.putAll(rootMap);
                    } else {
                        convertToMap(kv, objectLinkedHashMap,false);
                        result.put(key, objectLinkedHashMap);
                    }
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                ArrayList<ParamKeyValue> dataList = (ArrayList<ParamKeyValue>) dataValue;
                if (dataList.size() == 0) {
                    continue;
                }
                LinkedHashMap<String, Object> arrayMap = new LinkedHashMap<>();
                List<Object> list = convertArrayToMap(dataList, arrayMap);
                result.put(key, list);
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
            if(o instanceof ParamKeyValue){
                list.add(((ParamKeyValue) o).getValue());
            } else{
                KV<String, ParamKeyValue> kv = (KV<String, ParamKeyValue>) o;
                kv.keySet().forEach(k -> {
                    ParamKeyValue paramKeyValue = kv.get(k);
                    String type = paramKeyValue.getType();
                    Object value = paramKeyValue.getValue();
                    if (TypeUtil.Type.Object.name().equals(type)) {
                        LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                        LinkedHashMap<String, Object> kvValue = (KV<String, Object>) value;
                        if (kvValue != null) {
                            convertToMap(kvValue, objectLinkedHashMap,false);
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
            if(o instanceof ParamKeyValue){
                //非对象进入
                ParamKeyValue paramKeyValue = (ParamKeyValue) o;
                paramKeyValue.setKey(key+"[0]");
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
                        list.add(new ParamKeyValue(arrayKey + "." + k, dataValue, 2, type,comment));
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
    private CustomNode convertToNode(boolean isRoot,CustomNode node, LinkedHashMap<String, Object> data) {
        Set<String> keys = data.keySet();
        keys.forEach(key -> {
//            node.setKey(key);
            ParamKeyValue value = (ParamKeyValue) data.get(key);
            String type = value.getType();
            String comment = value.getComment();
            if (TypeUtil.Type.Object.name().equals(type)) {
                KV valueJsonObject = (KV) value.getValue();
                if (valueJsonObject == null) {
                    CustomNode nodeObject = new CustomNode(key, null, TypeUtil.Type.Object.name(),comment);
                    node.add(nodeObject);
                    return;
                }
                if(isRoot){
                    convertToNode(false,node, valueJsonObject);
                } else {
                    CustomNode customNode = new CustomNode(key, null, type,comment);
                    node.add(convertToNode(false,customNode, valueJsonObject));
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                Object valueChild = value.getValue();
                if(valueChild instanceof KV){
                    CustomNode addNode;
                    if(isRoot){
                        addNode = new CustomNode("index 0", null, TypeUtil.Type.Object.name());
                    } else {
                        addNode = node;
                    }

                    KV k = (KV) valueChild;
                    Object o = k.entrySet().stream().findFirst().get();
                    if(o instanceof ArrayList){
                        KV<String,ArrayList<ParamKeyValue>> listKV = k;
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
                            if(TypeUtil.Type.Object.name().equals(childType)){
                                CustomNode customNode = new CustomNode(childKey, null, childType, childComment);
                                addNode.add(convertToNode(false,customNode,(KV)childValue));
                            } else if(TypeUtil.Type.Array.name().equals(childType)){
                                convertArrayToNode(false,childKey,childComment, (ArrayList) childValue, addNode);
                            } else {
                                CustomNode customNode = new CustomNode(childKey, childValue, childType, childComment);
                                addNode.add(customNode);
                            }
                        }
                    }
                    if(isRoot){
                        node.add(addNode);
                    }
                } else {
                    ArrayList list = (ArrayList) valueChild;
                    if (list.size() == 0) {
                        CustomNode nodeArray = new CustomNode(key, null, TypeUtil.Type.Array.name(), comment);
                        node.add(nodeArray);
                        return;
                    }

                    convertArrayToNode(isRoot,key, comment, list, node);
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
    private void convertArrayToNode(boolean isRoot,String key, String comment, ArrayList dataList, CustomNode node) {
        CustomNode addNode;
        if(isRoot){
            addNode = node;
        } else {
            addNode = new CustomNode(key, null, TypeUtil.Type.Array.name(), comment);
        }
        for (int j = 0; j < dataList.size(); j++) {
            Object o = dataList.get(j);
            if(o instanceof ParamKeyValue){
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
                        CustomNode customNode = new CustomNode(inKey, null, type,commentChild);
                        nodeArrayIndex.add(convertToNode(false, customNode, valueKvObject));
                    } else if (TypeUtil.Type.Array.name().equals(type)) {
                        ArrayList<KV<String, ParamKeyValue>> list = (ArrayList<KV<String, ParamKeyValue>>) value.getValue();
                        if (list.size() == 0) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            convertArrayToNode(false,inKey, commentChild,list, nodeArrayIndex);
                        }
                    } else {
                        nodeArrayIndex.add(new CustomNode(inKey, value.getValue(), type, commentChild));
                    }
                });
                addNode.add(nodeArrayIndex);
            }
        }
        if(!isRoot){
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

        public CustomNode(String key, Object value, String type,String comment) {
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
                if(column == 0){
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("",enable));
                }
                if(column == 1){
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
                    return new JCheckBox("",enabled);
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
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
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
                if(column == 0){
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("",enable));
                } else if(column == 1){
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
                    return new JCheckBox("",enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                }  else if(column == 2){
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row,column).toString());
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
                }
                else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                }
                else if (column == 2) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlEncodedParamChangeFlag.set(true);
                    }
                }
                else if (column == 3) {
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
                if(column == 0){
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("",enable));
                }
                if(column == 1){
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeAndFileComboBox(type));
                }
                if(column == 3){
                    String type = (String) getValueAt(row, 1);
                    String value = (String) getValueAt(row, 2);
                    if(TypeUtil.Type.File.name().equals(type)){
                        VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFileDescriptor(), myProject, LocalFileSystem.getInstance().findFileByIoFile(new File(value)));
                        String path = virtualFile == null?value:virtualFile.getCanonicalPath();
                        TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton(new JTextField(path));
                        return new FileChooseCellEditor (textFieldWithBrowseButton);
                    }
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    boolean enabled = paramKeyValue.getEnabled();
                    return new JCheckBox("",enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeAndFileComboBox(type);
                }  else if(column == 2){
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row,column).toString());
                    textField.setToolTipText(paramKeyValue.getComment());
                    textField.setOpaque(false);
                    return textField;
                }else if(column == 3){
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    if(TypeUtil.Type.File.name().equals(type)){
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
                    if(TypeUtil.Type.File.name().equals(paramKeyValue.getType())){
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
        return table;
    }

    private ColumnInfo<Object, Object>[] getColumns(List<String> titleList){
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

    private JBTable createHeaderTable(){
        ColumnInfo<Object, Object>[] columns = getColumns(Lists.newArrayList("","Header Name", "Header Value"));
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        headerParamsKeyValueList = config.getHeaderList();
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
                if (column == 0) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    boolean enabled = dataMapping.getEnabled();
                    return new JCheckBox("", enabled);
                }
                return super.prepareRenderer(renderer,row,column);
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if(column == 0){
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("",enable));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (headerParamsKeyValueList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = config.getHeaderList().get(row);
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
                config.setHeaderList(headerParamsKeyValueList);
            }
        };
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.setVisible(true);
        return table;
    }

    private JBTable createResponseInfoTable(){
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
                if(column == 0){
                    boolean enable = (boolean) getValueAt(row, column);
                    return new DefaultCellEditor(new JCheckBox("",enable));
                }else if(column == 1){
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
                    return new JCheckBox("",enabled);
                } else if (column == 1) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                }  else if(column == 2){
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row,column).toString());
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
        return table;
    }

    /*****getter setter*****/

    public JComponent getContent() {
        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }


    private CollectionConfiguration.CollectionDetail filterById(String id,CollectionConfiguration.CollectionDetail detail){
        if(detail.getId().equals(id) ){
            return detail;
        }
        for (CollectionConfiguration.CollectionDetail d : detail.getChildList()) {
            CollectionConfiguration.CollectionDetail filterResult = filterById(id, d);
            if(filterResult != null){
                return filterResult;
            }
        }
        return null;
    }

    private final class SaveRequestAction extends AnAction{
        public SaveRequestAction() {
            super(MyResourceBundleUtil.getKey("SaveRequest"), MyResourceBundleUtil.getKey("SaveRequest"), PluginIcons.ICON_SAVE);
        }
        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            assert config != null;
            ParamGroup paramGroup = config.getParamGroup();
            if(config.getDomain().isBlank()){
                Messages.showMessageDialog(MyResourceBundleUtil.getKey("msg_currentDomain_null"), "Error", Messages.getInformationIcon());
                return;
            }
            if(urlTextField.getText().isBlank()){
                Messages.showMessageDialog(MyResourceBundleUtil.getKey("msg_UrlNull"), "Error", Messages.getInformationIcon());
                return;
            }

            CollectionConfiguration collectionConfiguration = FastRequestCollectionComponent.getInstance(myProject).getState();
            assert collectionConfiguration != null;

            CollectionConfiguration.CollectionDetail collectionDetail;
            String id = "id_" + paramGroup.getClassName()+"."+paramGroup.getMethod();
            collectionDetail = filterById(id, collectionConfiguration.getDetail());
            boolean insertFlag = collectionDetail == null;
            if(insertFlag){
                //插入
                collectionDetail = new CollectionConfiguration.CollectionDetail();
                String mid = "id_" + paramGroup.getClassName()+"."+paramGroup.getMethod();
                collectionDetail.setId(mid);
            }
            ParamGroupCollection paramGroupCollection = new ParamGroupCollection();
            collectionDetail.setEnableEnv(config.getEnableEnv());
            collectionDetail.setEnableProject(config.getEnableProject());
            collectionDetail.setDomain(config.getDomain());

            collectionDetail.setName(StringUtils.isBlank(paramGroup.getMethodDescription())? "New Request" : paramGroup.getMethodDescription());
            collectionDetail.setType(2);
            paramGroupCollection.setOriginUrl(paramGroup.getOriginUrl());
            paramGroupCollection.setUrl(urlTextField.getText());
            paramGroupCollection.setMethodType((String) methodTypeComboBox.getSelectedItem());
            paramGroupCollection.setMethodDescription(StringUtils.isBlank(paramGroup.getMethodDescription())? "New Request" : paramGroup.getMethodDescription());
            paramGroupCollection.setClassName(paramGroup.getClassName());
            paramGroupCollection.setMethod(paramGroup.getMethod());
            paramGroupCollection.setPathParamsKeyValueListJson(JSON.toJSONString(pathParamsKeyValueList));
            paramGroupCollection.setUrlParamsKeyValueListJson(JSON.toJSONString(urlParamsKeyValueList));
            paramGroupCollection.setUrlParamsKeyValueListText(urlParamsTextArea.getText());
            paramGroupCollection.setUrlEncodedKeyValueListJson(JSON.toJSONString(urlEncodedKeyValueList));
            paramGroupCollection.setUrlEncodedKeyValueListText(urlEncodedTextArea.getText());
            paramGroupCollection.setBodyKeyValueListJson(jsonParamsTextArea.getText());
            paramGroupCollection.setMultipartKeyValueListJson(JSON.toJSONString(multipartKeyValueList));
            collectionDetail.setParamGroup(paramGroupCollection);
            collectionDetail.setHeaderList(config.getHeaderList());
            if(insertFlag){
                CollectionConfiguration.CollectionDetail defaultGroup = collectionConfiguration.getDetail().getChildList().get(0);
                List<CollectionConfiguration.CollectionDetail> childList = defaultGroup.getChildList();
                childList.add(collectionDetail);
                defaultGroup.setChildList(childList);
            }

            //send message to change param
            MessageBus messageBus = myProject.getMessageBus();
            messageBus.connect();
            ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.ADD_REQUEST_TOPIC);
            configChangeNotifier.configChanged(true);
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
            getCurlDataAndCopy();
            //兼容性处理code
            NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Success", MessageType.INFO).notify(myProject);
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
                    dp.browse(URI.create("https://github.com/kings1990/fast-request-doc"));
                }
            } catch (Exception e) {
                LOGGER.error("open url fail:https://github.com/kings1990/fast-request-doc", e);
            }
        }
    }

    private static final class CoffeeMeAction extends AnAction {
        public CoffeeMeAction() {
            super(MyResourceBundleUtil.getKey("CoffeeMe"), MyResourceBundleUtil.getKey("CoffeeMe"), PluginIcons.ICON_COFFEE);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            SupportView supportView = new SupportView();
            supportView.show();
        }
    }

    private class RetryAction extends AnAction {
        public RetryAction() {
            super(MyResourceBundleUtil.getKey("regenerate"), MyResourceBundleUtil.getKey("regenerate"), PluginIcons.ICON_RETRY);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent event) {
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            assert config != null;
            ParamGroup paramGroup = config.getParamGroup();
            String className = paramGroup.getClassName();
            String methodName = paramGroup.getMethod();
            PsiClass psiClass = JavaPsiFacade.getInstance(myProject).findClass(className, GlobalSearchScope.projectScope(myProject));
            if(psiClass != null){
                PsiElement[] elementArray = psiClass.findMethodsByName(methodName,true);
                if(elementArray.length > 0){
                    PsiMethod psiMethod = (PsiMethod) elementArray[0];
                    PsiNavigateUtil.navigate(psiMethod);
                    generatorUrlService.generate(psiMethod);
                    refresh(true);
                }
            }
        }
    }
}