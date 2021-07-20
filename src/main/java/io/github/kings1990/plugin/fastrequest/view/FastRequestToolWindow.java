package io.github.kings1990.plugin.fastrequest.view;

import cn.hutool.core.date.DateUtil;
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
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.*;
import com.intellij.ui.dualView.TreeTableView;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.ui.treeStructure.treetable.TreeTableTree;
import com.intellij.util.ui.AbstractTableCellEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.*;
import io.github.kings1990.plugin.fastrequest.util.*;
import io.github.kings1990.plugin.fastrequest.view.inner.SupportView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.*;
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

    private JPanel panel;
    private JComboBox<String> envComboBox;
    private JComboBox<String> projectComboBox;
    private JCheckBox enableProjectCheckBox;
    private JButton starButton;
    private JButton coffeeMeButton;
    private JTextField currentDomainTextField;
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
    private JPanel jsonTreeTablePanel;
    private JTabbedPane jsonTabbedPanel;
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
    private JButton cURLExportButton;
    private JPanel jsonResponsePanel;
    private JButton manageConfigButton;
    private JScrollPane prettyJsonPanle;
    private JTextArea prettyResponseTextArea;
    private JButton requestToggleButton;
    private JButton responseToggleButton;
    private JPanel titlePanel;
    private JScrollPane responseBodyScrollPane;

    private JBTable urlParamsTable;
    private JBTable urlEncodedTable;
    private JBTable multipartTable;
    private JBTable pathParamsTable;
    private TreeTableView jsonTreeTable;
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
        renderingJsonTreeTablePanel();
        renderingResponseInfoPanel();

        renderingJsonResponsePanel();

        sendButton = new JButton(PluginIcons.ICON_SEND);
        requestToggleButton = new JButton("Request",PluginIcons.ICON_INVISIBLE);
        requestToggleButton.setToolTipText("Toggle request");
        responseToggleButton = new JButton("Response",PluginIcons.ICON_INVISIBLE);
        responseToggleButton.setToolTipText("Toggle response");
    }

    public FastRequestToolWindow(ToolWindow toolWindow) {
        super(true, false);
        this.setContent(panel);
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        //star
        starButton.addActionListener(event -> {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(URI.create("https://github.com/kings1990/fast-request-doc"));
                }
            } catch (Exception e) {
                LOGGER.error("open url fail:https://github.com/kings1990/fast-request-doc", e);
            }
        });

        //coffee
        coffeeMeButton.addActionListener(event -> {
            SupportView supportView = new SupportView();
            supportView.show();
        });

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
        methodTypeComboBox.setBackground(new JBColor(new Color(0, 191, 255),new Color(70, 130, 180)));

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
                        config.setEnableFlag(false);
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
                String project = selectProject.toString();
                List<String> projectList = config.getProjectList();
                if (!projectList.contains(project)) {
                    //配置删除了当前的env则默认选中第一个env
                    if (projectList.isEmpty()) {
                        //project被删除完了 补全域名开关自动关闭
                        config.setEnableProject(null);
                        config.setEnableFlag(false);
                        config.setDomain(StringUtils.EMPTY);
                        enableProjectCheckBox.setSelected(false);
                    } else {
                        config.setEnableProject(projectList.get(0));
                    }
                    projectModel.setSelectedItem(config.getEnableProject());
                } else {
                    config.setEnableProject(project);
                }
                //根据当前的env和project设置url
                setDomain(config);
            }
        });
        projectModel.setSelectedItem(config.getEnableProject());

        //选中事件
        enableProjectCheckBox.setSelected(config.isEnableFlag());
        enableProjectCheckBox.addItemListener(event -> {
            boolean enabled = enableProjectCheckBox.isSelected();
            config.setEnableFlag(enabled);
            setDomain(config);
        });

        //更新域名
        config.getParamGroup().setOriginUrl("");
        setDomain(config);

        //动态更新text中的内容
        urlEncodedTabbedPane.addChangeListener(changeEvent -> {
            if (urlEncodedTabbedPane.getSelectedIndex() == 1 && urlEncodedParamChangeFlag.get()) {
                //table change 引发重新构建text
                String paramStr = conventDataToString(urlEncodedKeyValueList);
                urlEncodedTextArea.setText(paramStr);
                urlEncodedParamChangeFlag.set(false);
            }
            if (urlEncodedTabbedPane.getSelectedIndex() == 0) {
                String paramStr = conventDataToString(urlEncodedKeyValueList);
                String currentUrlParamText = urlEncodedTextArea.getText();
                if (!paramStr.equals(currentUrlParamText)) {
                    String[] split = currentUrlParamText.split("&");
                    List<ParamKeyValue> currentUrlParamsKeyValueList = new ArrayList<>();
                    for (String s : split) {
                        String[] kvArray = s.split("=");
                        if (kvArray.length == 2) {
                            String value = kvArray[1].replace("\n", "");
                            ParamKeyValue paramKeyValue = new ParamKeyValue(kvArray[0], kvArray[1], 2, TypeUtil.calcTypeByStringValue(value));
                            currentUrlParamsKeyValueList.add(paramKeyValue);
                        }
                    }
                    urlEncodedKeyValueList = currentUrlParamsKeyValueList;
                    urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
                    resizeTable(urlEncodedTable);
                }
            }
        });

        //动态更新text中的内容
        urlParamsTabbedPane.addChangeListener(changeEvent -> {
            //table change 引发重新构建text
            if (urlParamsTabbedPane.getSelectedIndex() == 1 && urlParamsChangeFlag.get()) {
                String paramStr = conventDataToString(urlParamsKeyValueList);
                urlParamsTextArea.setText(paramStr);
                urlParamsChangeFlag.set(false);
            }
            if (urlParamsTabbedPane.getSelectedIndex() == 0) {
                String paramStr = conventDataToString(urlParamsKeyValueList);
                String currentUrlParamText = urlParamsTextArea.getText();
                if (!paramStr.equals(currentUrlParamText)) {
                    String[] split = currentUrlParamText.split("&");
                    List<ParamKeyValue> currentUrlParamsKeyValueList = new ArrayList<>();
                    for (String s : split) {
                        String[] kvArray = s.split("=");
                        if (kvArray.length == 2) {
                            String value = kvArray[1].replace("\n", "");
                            ParamKeyValue paramKeyValue = new ParamKeyValue(kvArray[0], kvArray[1], 2, TypeUtil.calcTypeByStringValue(value));
                            currentUrlParamsKeyValueList.add(paramKeyValue);
                        }
                    }
                    urlParamsKeyValueList = currentUrlParamsKeyValueList;
                    urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
                    resizeTable(urlParamsTable);
                }
            }
        });

        //copy param
        jsonParamsTextArea.addMouseListener(copyMouseAdapter(jsonParamsTextArea));
        urlEncodedTextArea.addMouseListener(copyMouseAdapter(urlEncodedTextArea));
        urlParamsTextArea.addMouseListener(copyMouseAdapter(urlParamsTextArea));
        urlTextField.addMouseListener(copyMouseAdapterField(urlTextField));
        headerParamsKeyValueList = config.getHeaderList();
        sendRequestAction();

        cURLExportAction();


        manageConfigButton.setMultiClickThreshhold(2000);
        manageConfigButton.addActionListener(e -> {
            DataContext dataContext = DataManager.getInstance().getDataContext(panel);
            Project project = dataContext.getData(LangDataKeys.PROJECT);
            ShowSettingsUtil.getInstance().showSettingsDialog(project,"Fast Request");
        });

        requestToggleButton.addActionListener(e -> {
            boolean visible = tabbedPane.isVisible();
            tabbedPane.setVisible(!visible);
            Icon icon = visible ? PluginIcons.ICON_VISIBLE : PluginIcons.ICON_INVISIBLE;
            requestToggleButton.setIcon(icon);
        });

        responseToggleButton.addActionListener(e -> {
            boolean visible = responseTabbedPanel.isVisible();
            responseTabbedPanel.setVisible(!visible);
            Icon icon = visible ? PluginIcons.ICON_VISIBLE : PluginIcons.ICON_INVISIBLE;
            responseToggleButton.setIcon(icon);
        });
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

    public void cURLExportAction(){
        cURLExportButton.setMultiClickThreshhold(2000);
        cURLExportButton.addActionListener(e -> {
            cURLExportButton.setEnabled(false);
            getCurlDataAndCopy();
            cURLExportButton.setEnabled(true);
        });
    }

    private void sendRequestAction(){
        //send request
        //2秒内不允许狂点
        sendButton.setToolTipText("Send Request");
        sendButton.setMultiClickThreshhold(2000);
        sendButton.addActionListener(e -> {
            sendButton.setEnabled(false);
                try {
                    String sendUrl = urlTextField.getText();
                    if(StringUtils.isEmpty(sendUrl) || !UrlUtil.isURL(sendUrl)){
                        responseTextArea.setText("Correct url required");
                        sendButton.setEnabled(true);
                        return;
                    }
                    FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
                    assert config != null;
                    String domain = config.getDomain();

                    String methodType = (String) methodTypeComboBox.getSelectedItem();
                    HttpRequest request = HttpUtil.createRequest(Method.valueOf(methodType), domain + sendUrl);
                    headerParamsKeyValueList = headerParamsKeyValueList == null?new ArrayList<>():headerParamsKeyValueList;
                    Map<String, List<String>> headerMap = headerParamsKeyValueList.stream().collect(Collectors.toMap(DataMapping::getType, p -> Lists.newArrayList(p.getValue())));
                    request.header(headerMap);

                    Map<String, Object> multipartFormParam = multipartKeyValueList.stream()
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

                    Map<String, Object> formParam = urlParamsKeyValueList.stream().collect(Collectors.toMap(ParamKeyValue::getKey, ParamKeyValue::getValue));
                    String jsonParam = jsonParamsTextArea.getText();
                    String urlEncodedParam = urlEncodedTextArea.getText();

                    //json优先
                    if(StringUtils.isNotEmpty(urlEncodedParam)){
                        request.body(urlEncodedParam);
                    }
                    if(StringUtils.isNotEmpty(jsonParam) && !"{}".equals(jsonParam) && !"[]".equals(jsonParam)){
                        request.body(jsonParam);
                    }

                    if(!formParam.isEmpty()){
                        request.form(formParam);
                    }
                    if(!multipartFormParam.isEmpty()){
                        request.form(multipartFormParam);
                    }

                    long start = System.currentTimeMillis();
                    HttpResponse response = request.execute();
                    long end = System.currentTimeMillis();
                    int status = response.getStatus();
                    String body = response.body();
                    if(JsonUtil.isJSON2(body)){
                        prettyResponseTextArea.setText(JSON.toJSONString(JSON.parse(body),true));
                        responseTextArea.setText(body);
                        responseTabbedPanel.setSelectedIndex(0);
                        refreshResponseTable(body);
                    } else {
                        String subBody = body.substring(0, Math.min(body.length(), 32768));
                        if(body.length() > 32768){
                            subBody += "\n\ntext too large only show 32768 characters\n.............";
                        }
                        prettyResponseTextArea.setText(subBody);
                        responseTextArea.setText(subBody);
                        responseTabbedPanel.setSelectedIndex(2);
                    }
                    String duration = String.valueOf(end - start);

                    responseInfoParamsKeyValueList = Lists.newArrayList(
                            new ParamKeyValue("Cost",duration + " ms",2,TypeUtil.Type.String.name()),
                            new ParamKeyValue("Response status",status+" "+ Constant.HttpStatusDesc.STATUS_MAP.get(status)),
                            new ParamKeyValue("Date", DateUtil.formatDateTime(new Date()))
                    );
                    responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));
                    responseStatusComboBox.setSelectedItem(status);

                    JBColor green = new JBColor(new Color(0, 250, 154), new Color(60, 179, 113));
                    JBColor red = new JBColor(new Color(220, 20, 60),new Color(178, 34, 34));

                    responseStatusComboBox.setBackground((status >= 200 && status < 300) ? green : red);

                } catch (Exception exception){
                    String errorMsg = exception.getMessage();
                    responseTextArea.setText(errorMsg);
                    prettyResponseTextArea.setText("");
                    responseStatusComboBox.setSelectedItem(0);
                    responseStatusComboBox.setBackground(new JBColor(new Color(220, 20, 60),new Color(178, 34, 34)));
                    responseInfoParamsKeyValueList = Lists.newArrayList(
                            new ParamKeyValue("Error", errorMsg)
                    );
                    responseInfoTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Name", "Value")), responseInfoParamsKeyValueList));

                    CustomNode root = new CustomNode("Root","");
                    ((DefaultTreeModel)responseTable.getTableModel()).setRoot(root);
                    responseTabbedPanel.setSelectedIndex(2);
                }
                sendButton.setEnabled(true);

        });
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
        if (!config.isEnableFlag()) {
            //没有开启开关则url默认返回空字符串
            config.setDomain(StringUtils.EMPTY);
            currentDomainTextField.setText(StringUtils.EMPTY);
            changeUrl();
            return;
        }
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
        currentDomainTextField.setText(domain);
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

        CollectionComboBoxModel<String> projectModel = new CollectionComboBoxModel<>(config.getProjectList());
        projectComboBox.setModel(projectModel);

        CollectionComboBoxModel<String> envModel = new CollectionComboBoxModel<>(config.getEnvList());
        envComboBox.setModel(envModel);

        projectComboBox.setSelectedItem(config.getEnableProject());
        envComboBox.setSelectedItem(config.getEnableEnv());
        enableProjectCheckBox.setSelected(config.isEnableFlag());
        setDomain(config);
    }

    private JBColor buildMethodColor(String method) {
        JBColor jbColor = JBColor.darkGray;
        if ("POST".equals(method)) {
            jbColor = new JBColor(new Color(0, 250, 154), new Color(60, 179, 113));
        } else if ("DELETE".equals(method)) {
            jbColor = new JBColor(new Color(220, 20, 60),new Color(178, 34, 34));
        } else if ("PUT".equals(method)) {
            jbColor = new JBColor(new Color(218, 165, 32),new Color(202, 111, 30));
        } else if ("GET".equals(method)) {
            jbColor = new JBColor(new Color(0, 191, 255),new Color(70, 130, 180));
        }
        return jbColor;
    }

    /**
     * message事件:action操作生成数据,修改ToolWindow中的内容
     *
     * @author Kings
     * @date 2021/06/02
     */
    public void refresh() {
        //reset value
        multipartKeyValueList = new ArrayList<>();

        urlParamsTextArea.setText("");
        jsonParamsTextArea.setText("");
        urlEncodedTextArea.setText("");

        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        ParamGroup paramGroup = config.getParamGroup();
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
            if (!bodyParamMap.isEmpty()) {
                //json
                tabbedPane.setSelectedIndex(3);
                jsonTabbedPanel.setSelectedIndex(0);
                urlEncodedTextArea.setText("");
                urlEncodedKeyValueList = new ArrayList<>();
            } else {
                urlEncodedKeyValueList = conventMapToList(requestParamMap);
                boolean isMultipart = urlEncodedKeyValueList.stream().anyMatch(q -> TypeUtil.Type.File.name().equals(q.getType()));
                if(isMultipart){
                    if (requestParamStr.isEmpty()) {
                        tabbedPane.setSelectedIndex(2);
                    } else {
                        tabbedPane.setSelectedIndex(5);
                    }
                    multipartKeyValueList = new ArrayList<>(urlEncodedKeyValueList);
                    urlEncodedTextArea.setText("");
                    urlEncodedKeyValueList = new ArrayList<>();
                } else {
                    //urlencoded
                    urlEncodedTextArea.setText(requestParamStr);
                    if (requestParamStr.isEmpty()) {
                        tabbedPane.setSelectedIndex(2);
                    } else {
                        tabbedPane.setSelectedIndex(4);
                    }
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

        //json table
        refreshFirstInitJsonTable();
        setDomain(config);


    }

    private void refreshFirstInitJsonTable() {
        CustomNode root;
        if(!bodyParamMap.isEmpty()){
            ParamKeyValue firstValue = (ParamKeyValue) bodyParamMap.get(bodyParamMap.keySet().stream().findFirst().get());
            root = new CustomNode("Root", "", firstValue.getType());
        } else {
            root = new CustomNode("Root", "", TypeUtil.Type.Object.name());
        }
        convertToNode(true,root, bodyParamMap);

        ((DefaultTreeModel)jsonTreeTable.getTableModel()).setRoot(root);
        //列宽
        TableColumnModel columnModel = jsonTreeTable.getColumnModel();
//        columnModel.getColumn(0).setCellEditor(new KeyCellEditor(new JTextField()));
//        setUpTypeColumn(columnModel.getColumn(1));
        columnModel.getColumn(0).setPreferredWidth(JSON_TABLE_COLUMN_NAME_WIDTH);
        columnModel.getColumn(1).setPreferredWidth(JSON_TABLE_COLUMN_TYPE_WIDTH);

        expandAll(jsonTreeTable.getTree(), new TreePath(root), true);
        jsonTreeTable.setTreeCellRenderer(new Renderer());

        String type = root.getType();
        if(TypeUtil.Type.Array.name().equals(type)){
            JSONArray jsonArray = new JSONArray();
            jsonTableNodeToJsonArray(jsonArray,root);
            jsonParamsTextArea.setText(JSON.toJSONString(jsonArray, true));
        } else {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonTableNodeToJson(root,jsonObject);
            jsonParamsTextArea.setText(JSON.toJSONString(jsonObject, true));
        }

    }


    public void resizeTable(JBTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth((int) Math.round(table.getWidth() * 0.12));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) Math.round(table.getWidth() * 0.33));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) Math.round(table.getWidth() * 0.55));
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
    }

    private String buildPathParamUrl(String url) {
        List<String> paramNameList = UrlUtil.paramPathParam(url);
        if (paramNameList.isEmpty()) {
            return url;
        }
        for (ParamKeyValue paramKeyValue : pathParamsKeyValueList) {
            String paramName = paramKeyValue.getKey();
            String paramNameWithSymbol = "{" + paramName + "}";
            url = url.replace(paramNameWithSymbol, paramKeyValue.getValue().toString());
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
                    if (selectedRow == -1) {
                        headerParamsKeyValueList.add(new DataMapping("", ""));
                    } else {
                        headerParamsKeyValueList.add(selectedRow + 1, new DataMapping("", ""));
                    }
                    headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = headerTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.stream().mapToInt(i -> i).forEach(headerParamsKeyValueList::remove);
            headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
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
        toolbarDecorator.setAddActionName("Add to Header Params").setAddAction(anActionButton -> {
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
                    headerTable.setModel(new ListTableModel<>(getColumns(Lists.newArrayList("Header Name","Header Value")), headerParamsKeyValueList));
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
                    if (selectedRow == -1) {
                        urlParamsKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        urlParamsKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
                    resizeTable(urlParamsTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = urlParamsTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.stream().mapToInt(i -> i).forEach(urlParamsKeyValueList::remove);
            urlParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlParamsKeyValueList));
            resizeTable(urlParamsTable);
        });
        urlParamsTablePanel = toolbarDecorator.createPanel();
    }

    private void renderingJsonTreeTablePanel() {
        jsonTreeTable = createJsonParamKeyValueTable();
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(jsonTreeTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        AnActionButton expandButton = new AnActionButton("Expand",PluginIcons.ICON_EXPAND) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                CustomNode root = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(0);
                expandAll(jsonTreeTable.getTree(), new TreePath(root), true);
            }
        };
        toolbarDecorator.addExtraAction(expandButton);
        AnActionButton collapseButton = new AnActionButton("Collapse",PluginIcons.ICON_COLLAPSE) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                CustomNode root = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(0);
                expandAll(jsonTreeTable.getTree(), new TreePath(root), false);
            }

        };
        toolbarDecorator.addExtraAction(collapseButton);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = jsonTreeTable.getSelectedRow();
                    CustomNode root = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(0);
                    CustomNode addNode = new CustomNode("","",TypeUtil.Type.String.name());
                    if (selectedRow == -1 || selectedRow == 0) {
                        if(TypeUtil.Type.Array.name().equals(root.getType())){
                            int childCount = root.getChildCount();
                            if(childCount > 0){
                                CustomNode firstNode = (CustomNode) root.getChildAt(0);
                                addNode = new CustomNode("index " + childCount, "", firstNode.getType());
                            } else {
                                addNode = new CustomNode("index " + childCount, "", TypeUtil.Type.String.name());
                            }
                            root.insert(addNode, childCount);
                        } else {
                            addNode = new CustomNode("", "", TypeUtil.Type.String.name());
                            root.insert(addNode, 0);
                        }
                    } else {
                        CustomNode node = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(selectedRow);
                        if(TypeUtil.Type.Object.name().equals(node.getType())){
                            node.insert(addNode,0);
                        } else if(TypeUtil.Type.Array.name().equals(node.getType())){
                            int childCount = node.getChildCount();
                            if(childCount > 0){
                                CustomNode firstNode = (CustomNode) node.getChildAt(0);
                                addNode = new CustomNode("index " + childCount, "", firstNode.getType());
                            } else {
                                addNode = new CustomNode("index " + childCount, "", TypeUtil.Type.String.name());
                            }
                            node.insert(addNode, childCount);
                        }else {
                            CustomNode parentNode = (CustomNode) node.getParent();
                            if(TypeUtil.Type.Array.name().equals(parentNode.getType())){
                                int childCount = parentNode.getChildCount();
                                if(childCount > 0){
                                    CustomNode firstNode = (CustomNode) parentNode.getChildAt(0);
                                    addNode = new CustomNode("index " + childCount, "", firstNode.getType());
                                } else {
                                    addNode = new CustomNode("index " + childCount, "", TypeUtil.Type.String.name());
                                }
                                parentNode.insert(addNode, childCount);
                            } else {
                                parentNode.insert(addNode, parentNode.getIndex(node) + 1);
                            }
                        }
                    }
                    refreshJsonParamTable(root,false);
                    //展开节点
                    TreeTableTree tree = jsonTreeTable.getTree();
                    expandAll(tree,new TreePath(root),false);
                    for (TreeNode treeNode : addNode.getPath()) {
                        tree.expandPath(new TreePath(((CustomNode) treeNode).getPath()));
                    }
                    jsonTreeTable.setRowSelectionInterval(selectedRow,selectedRow);
                }
        );
        toolbarDecorator.setRemoveAction(anActionButton -> {
            CustomNode root = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(0);
            int selectedRow = jsonTreeTable.getSelectedRow();
            CustomNode node = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(selectedRow);
            CustomNode parent = (CustomNode) node.getParent();
            parent.remove(node);
            if(TypeUtil.Type.Array.name().equals(parent.getType())){
                int idx = 0;
                for (Enumeration<TreeNode> e = parent.children(); e.hasMoreElements(); ) {
                    CustomNode n = (CustomNode) e.nextElement();
                    n.setKey("index " + idx++);
                }
            }
            refreshJsonParamTable(root,false);
            //展开节点
            TreeTableTree tree = jsonTreeTable.getTree();
            expandAll(tree,new TreePath(root),false);
            for (TreeNode treeNode : parent.getPath()) {
                tree.expandPath(new TreePath(((CustomNode) treeNode).getPath()));
            }
            jsonTreeTable.setRowSelectionInterval(selectedRow,selectedRow);

        });
        toolbarDecorator.setRemoveActionUpdater(e -> jsonTreeTable.getSelectedRow()!= 0);
        jsonTreeTablePanel = toolbarDecorator.createPanel();

    }

    private void refreshJsonParamTable(CustomNode root,boolean expandAll){

        ((DefaultTreeModel)jsonTreeTable.getTableModel()).setRoot(root);
        //列宽
        TableColumnModel columnModel = jsonTreeTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(JSON_TABLE_COLUMN_NAME_WIDTH);
        columnModel.getColumn(1).setPreferredWidth(JSON_TABLE_COLUMN_TYPE_WIDTH);
        if(expandAll){
            expandAll(jsonTreeTable.getTree(), new TreePath(root), true);
        }
        jsonTreeTable.setTreeCellRenderer(new Renderer());

        String type = root.getType();
        if(TypeUtil.Type.Array.name().equals(type)){
            JSONArray jsonArray = new JSONArray();
            jsonTableNodeToJsonArray(jsonArray,root);
            jsonParamsTextArea.setText(JSON.toJSONString(jsonArray, true));
        } else {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonTableNodeToJson(root,jsonObject);
            jsonParamsTextArea.setText(JSON.toJSONString(jsonObject, true));
        }
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
            convertJsonObjectToNode(root,JSONObject.parseObject(body));
            ((DefaultTreeModel)responseTable.getTableModel()).setRoot(root);
        }

        expandAll(responseTable.getTree(), new TreePath(root), true);
    }

    private void renderingPathParamsPanel() {
        pathParamsTable = createPathParamKeyValueTable();
        pathParamsTable.getEmptyText().setText("No params");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(pathParamsTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(anActionButton -> {
                    int selectedRow = pathParamsTable.getSelectedRow();
                    if (selectedRow == -1) {
                        pathParamsKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        pathParamsKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
                    resizeTable(pathParamsTable);
                    changeUrl();
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = pathParamsTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.stream().mapToInt(i -> i).forEach(pathParamsKeyValueList::remove);
            pathParamsTable.setModel(new ListTableModel<>(getPathColumnInfo(), pathParamsKeyValueList));
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
                    if (selectedRow == -1) {
                        urlEncodedKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        urlEncodedKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
                    resizeTable(urlEncodedTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = urlEncodedTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.stream().mapToInt(i -> i).forEach(urlEncodedKeyValueList::remove);
            urlEncodedTable.setModel(new ListTableModel<>(getPathColumnInfo(), urlEncodedKeyValueList));
            resizeTable(urlEncodedTable);
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
                    if (selectedRow == -1) {
                        multipartKeyValueList.add(new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    } else {
                        multipartKeyValueList.add(selectedRow + 1, new ParamKeyValue("", "", 2, TypeUtil.Type.String.name()));
                    }
                    multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
                    resizeTable(multipartTable);
                }
        ).setRemoveAction(anActionButton -> {
            int[] selectedIndices = multipartTable.getSelectionModel().getSelectedIndices();
            List<Integer> indexes= Arrays.stream(selectedIndices).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            indexes.stream().mapToInt(i -> i).forEach(multipartKeyValueList::remove);
            multipartTable.setModel(new ListTableModel<>(getPathColumnInfo(), multipartKeyValueList));
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
    private List<ParamKeyValue> conventMapToList(LinkedHashMap<String, Object> paramLinkedMap) {
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
            if (paramKeyValue.getCustomFlag() == 2) {
                //基本类型映射  key=value
                sb.append(paramKeyValue.getKey()).append("=").append(paramKeyValue.getValue()).append("\n&");
            } else {
                //对象 直接拼上value
                sb.append(paramKeyValue.getValue()).append("\n&");
            }
        });
        return StringUtils.removeEnd(sb.toString(), "\n&");
    }


    private ColumnInfo<Object, Object>[] getPathColumnInfo() {
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[3];
        List<String> titleList = Lists.newArrayList("Type", "Key", "Value");
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

    private TreeTableView createJsonParamKeyValueTable() {
        //初始化为空
        LinkedHashMap<String, Object> bodyParamMap = new LinkedHashMap<>();
        CustomNode root = new CustomNode("Root", "", TypeUtil.Type.Object.name());
        convertToNode(true,root, bodyParamMap);
        ListTreeTableModelOnColumns model = new ListTreeTableModelOnColumns(root, jsonColumnInfo());
        TreeTableView table = new TreeTableView(model) {

            @Override
            public void setTreeCellRenderer(TreeCellRenderer renderer) {
                super.setTreeCellRenderer(new Renderer());
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if(column == 0){
                    String key = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getKeyTextField(key));
                }
                if(column == 1){
                    String type = (String) getValueAt(row, column);
                    if(row == 0){
                        return new DefaultCellEditor(getRootTypeComboBox(type));
                    } else {
                        return new DefaultCellEditor(getTypeComboBox(type));
                    }
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 1) {
                    String type = (String) getValueAt(row, column);
                    if(row ==0){
                        return getRootTypeComboBox(type);
                    } else {
                        return getTypeComboBox(type);
                    }
                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CustomNode node = (CustomNode) myModel.getRowValue(row);
                if(column == 0){
                    return node.getKey();
                } else if(column == 1){
                    return node.getType();
                } else if(column == 2){
                    return node.getValue();
                }
                return super.getValueAt(row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CustomNode node = (CustomNode) myModel.getRowValue(row);
                CustomNode parent = (CustomNode)node.getParent();
                return (node.isRoot() && column == 1)   ||
                        (!node.isRoot() &&
                                (((TypeUtil.Type.Array.name().equals(node.getType()) || TypeUtil.Type.Object.name().equals(node.getType())) && column == 1))
                                ||
                                ((!TypeUtil.Type.Array.name().equals(node.getType()) && !TypeUtil.Type.Object.name().equals(node.getType())) &&
                                    (!TypeUtil.Type.Array.name().equals(parent.getType()) || column != 0)
                        )
                        || (column == 0 && (parent != null && !parent.getType().equals(TypeUtil.Type.Array.name())))
                        )
                        ;
            }


            @Override
                public void setValueAt(Object value, int row, int column) {
                boolean changeFlag = false;
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CustomNode node = (CustomNode) myModel.getRowValue(row);
                String oldType = node.getType();
                if (column == 2 && (oldType.equals(TypeUtil.Type.Array.name()) || oldType.equals(TypeUtil.Type.Object.name()))) {
                    return;
                }
                if (column == 0) {
                    if (!value.equals(node.getKey())) {
                        changeFlag = true;
                    }
                    node.setKey(value.toString());
                } else if (column == 1) {
                    if (!value.equals(oldType)) {
                        changeFlag = true;
                    }
                    String newType = value.toString();

                    if (TypeUtil.Type.Object.name().equals(oldType) && (!TypeUtil.Type.Object.name().equals(newType) && !TypeUtil.Type.Array.name().equals(newType)) ||
                            TypeUtil.Type.Array.name().equals(oldType) && (!TypeUtil.Type.Object.name().equals(newType) && !TypeUtil.Type.Array.name().equals(newType))) {
                        node.removeAllChildren();
                        Object v = convertCellData(node.getValue(), newType);
                        node.setValue(v);
                    } else if (TypeUtil.Type.Object.name().equals(newType)) {
                        node.setValue(null);
                        if(TypeUtil.Type.Array.name().equals(oldType)){
                            for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements(); ) {
                                CustomNode n = (CustomNode) e.nextElement();
                                n.setKey("");
                            }
                        }
                    } else if (TypeUtil.Type.Array.name().equals(newType)) {
                        node.setValue(null);
                        if(TypeUtil.Type.Object.name().equals(oldType)){
                            int idx = 0;
                            for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements(); ) {
                                CustomNode n = (CustomNode) e.nextElement();
                                n.setKey("index "+ idx++);
                            }
                        }
                    } else {
                        Object v = convertCellData(node.getValue(), newType);
                        node.setValue(v);
                    }
                    node.setType(newType);
                } else {
                    if (!value.equals(node.getValue())) {
                        changeFlag = true;
                    }
                    node.setValue(value);
                }
                if (changeFlag) {
                    CustomNode root = (CustomNode) ((ListTreeTableModelOnColumns) jsonTreeTable.getTableModel()).getRowValue(0);
                    refreshJsonParamTable(root,false);
                    TreeTableTree tree = jsonTreeTable.getTree();
                    expandAll(tree,new TreePath(root),false);
                    for (TreeNode treeNode : node.getPath()) {
                        tree.expandPath(new TreePath(((CustomNode) treeNode).getPath()));
                    }
                }
            }
        };
        table.setRootVisible(true);
        table.setVisible(true);
        return table;
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
        convertToMap(bodyParamMap, map);
        return JSON.toJSONString(map, true);
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
    private void convertToMap(LinkedHashMap<String, Object> data, LinkedHashMap<String, Object> result) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            ParamKeyValue value = (ParamKeyValue) entry.getValue();
            String type = value.getType();
            Object dataValue = value.getValue();
            if (TypeUtil.Type.Object.name().equals(type)) {
                LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                LinkedHashMap<String, Object> kv = (LinkedHashMap<String, Object>) dataValue;
                if (kv != null) {
                    convertToMap(kv, objectLinkedHashMap);
                    result.put(key, objectLinkedHashMap);
                }
            } else if (TypeUtil.Type.Array.name().equals(type)) {
                ArrayList<KV<String, ParamKeyValue>> dataList = (ArrayList<KV<String, ParamKeyValue>>) dataValue;
                if (dataList.size() == 0) {
                    return;
                }
                LinkedHashMap<String, Object> arrayMap = new LinkedHashMap<>();
                List<LinkedHashMap<String, Object>> list = convertArrayToMap(dataList, arrayMap);
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
    private List<LinkedHashMap<String, Object>> convertArrayToMap(ArrayList<KV<String, ParamKeyValue>> data, LinkedHashMap<String, Object> result) {
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        for (KV<String, ParamKeyValue> kv : data) {
            kv.keySet().forEach(k -> {
                ParamKeyValue paramKeyValue = kv.get(k);
                String type = paramKeyValue.getType();
                Object value = paramKeyValue.getValue();
                if (TypeUtil.Type.Object.name().equals(type)) {
                    LinkedHashMap<String, Object> objectLinkedHashMap = new LinkedHashMap<>();
                    LinkedHashMap<String, Object> kvValue = (KV<String, Object>) value;
                    if (kvValue != null) {
                        convertToMap(kvValue, objectLinkedHashMap);
                        result.put(k, objectLinkedHashMap);
                    }
                } else if (TypeUtil.Type.Array.name().equals(type)) {
                    ArrayList<KV<String, ParamKeyValue>> dataList = (ArrayList<KV<String, ParamKeyValue>>) value;
                    if (dataList.size() != 0) {
                        LinkedHashMap<String, Object> arrayDataList = new LinkedHashMap<>();
                        List<LinkedHashMap<String, Object>> l = convertArrayToMap(dataList, arrayDataList);
                        result.put(k, l);
                    }
                } else {
                    result.put(k, value);
                }
            });
            list.add(result);
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
                //todo add index
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
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
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
                    return keyValue.getType();
                } else if (column == 1) {
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
                    ParamKeyValue paramKeyValue = pathParamsKeyValueList.get(row);
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 1) {
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
                if (column == 2) {
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
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                }  else if(column == 1){
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
                    return keyValue.getType();
                } else if (column == 1) {
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
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 1) {
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlEncodedParamChangeFlag.set(true);
                    }
                }
                if (column == 2) {
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = urlEncodedKeyValueList.get(row);
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlEncodedParamChangeFlag.set(true);
                    }
                    paramKeyValue.setValue(value);
                }
            }
        };
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
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeAndFileComboBox(type));
                }
                if(column == 2){
                    String type = (String) getValueAt(row, 0);
                    String value = (String) getValueAt(row, 2);
                    if(TypeUtil.Type.File.name().equals(type)){
                        DataContext dataContext = DataManager.getInstance().getDataContext(panel);
                        Project project = dataContext.getData(LangDataKeys.PROJECT);
                        VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFileDescriptor(), project, LocalFileSystem.getInstance().findFileByIoFile(new File(value)));
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
                    String type = paramKeyValue.getType();
                    return getNormalTypeAndFileComboBox(type);
                }  else if(column == 1){
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    JTextField textField = new JTextField();
                    textField.setText(getValueAt(row,column).toString());
                    textField.setToolTipText(paramKeyValue.getComment());
                    textField.setOpaque(false);
                    return textField;
                }else if(column == 2){
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
                    return keyValue.getType();
                } else if (column == 1) {
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
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 1) {
                    ParamKeyValue paramKeyValue = multipartKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                }
                if (column == 2) {
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
        ColumnInfo<Object, Object>[] columns = getColumns(Lists.newArrayList("Header Name", "Header Value"));
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
                //默认只允许修改value不允许修改key
                return true;
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
                    return dataMapping.getType();
                } else {
                    return dataMapping.getValue();
                }
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    dataMapping.setType(aValue.toString());
                }
                if (column == 1) {
                    DataMapping dataMapping = headerParamsKeyValueList.get(row);
                    dataMapping.setValue(aValue.toString());
                }
                config.setHeaderList(headerParamsKeyValueList);
            }
        };

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
                    String type = (String) getValueAt(row, column);
                    return new DefaultCellEditor(getNormalTypeComboBox(type));
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                if (column == 0) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    String type = paramKeyValue.getType();
                    return getNormalTypeComboBox(type);
                }  else if(column == 1){
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
                    return keyValue.getType();
                } else if (column == 1) {
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
                    paramKeyValue.setType(aValue.toString());
                }
                if (column == 1) {
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    paramKeyValue.setKey(aValue.toString());
                    String value = aValue.toString();
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlParamsChangeFlag.set(true);
                    }
                }
                if (column == 2) {
                    String value = aValue.toString();
                    ParamKeyValue paramKeyValue = urlParamsKeyValueList.get(row);
                    if (!paramKeyValue.getValue().equals(value)) {
                        urlParamsChangeFlag.set(true);
                    }
                    paramKeyValue.setValue(value);
                }
            }

        };
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

}
