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

package io.github.kings1990.plugin.fastrequest.view.sub;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.*;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.inner.DataMappingAddView;
import io.github.kings1990.plugin.fastrequest.view.inner.IgnoreDataMappingAddView;
import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMappingConfigViewNew extends AbstractConfigurableView {
    private static final Logger LOGGER = Logger.getInstance(DataMappingConfigViewNew.class);
    private JPanel defaultDataMappingPanel;
    private JPanel customDataMappingPanel;
    private JTextField randomStringTextField;
    private JComboBox<String> randomStringStrategyComboBox = new ComboBox<>(new ListComboBoxModel<>(Lists.newArrayList("name+random", "random", "none")));
    private JTextField randomStringDelimiterTextField;
    private List<DataMapping> viewCustomDataMappingList;
    private List<DataMapping> viewDefaultDataMappingList;
    private JBTable customTable;
    private JBTable defaultDataMappingTable;
    private FastRequestConfiguration configOld;
    private Integer viewRandomStringLength;
    private JBList<String> ignoreDateMappingJbList;
    private List<String> viewIgnoreDateMappingList;


    public DataMappingConfigViewNew(FastRequestConfiguration config) {
        super(config);
        randomStringTextField = new JTextField();
        randomStringDelimiterTextField = new JTextField();
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        int randomStringLength = configOld.getRandomStringLength();
        String randomStringStrategy = configOld.getRandomStringStrategy();
        String randomStringDelimiter = configOld.getRandomStringDelimiter();
        randomStringTextField.setText(randomStringLength + "");

        randomStringDelimiterTextField.setText(randomStringDelimiter);
        randomStringTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent jComponent) {
                String text = randomStringTextField.getText();
                try {
                    viewRandomStringLength = Integer.parseInt(text);
                    if(viewRandomStringLength < 0){
                        throw new Exception("Positive integer required");
                    }
                    return true;
                } catch (Exception e) {
                    Messages.showMessageDialog("Positive integer required", "Error", Messages.getInformationIcon());
                    randomStringTextField.setText(randomStringLength + "");
                    return false;
                }
            }
        });

        randomStringDelimiterTextField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent jComponent) {
                String text = randomStringDelimiterTextField.getText();
                try {
                    if("&".equals(text) || "?".equals(text)){
                        throw new Exception("content error");
                    }
                    return true;
                } catch (Exception e) {
                    Messages.showMessageDialog("Character & or ? is not allowed", "Error", Messages.getInformationIcon());
                    randomStringDelimiterTextField.setText(randomStringDelimiter);
                    return false;
                }
            }
        });
        setLayout(new BorderLayout());
        add(createMainComponent());
    }

    private JComponent createMainComponent(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, 4, 10))
                .setDefaultWeightX(1)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);

        JPanel stringGeneratorConfigPanel = createStringGeneratorConfigPanel();
        renderingCustomDataMappingPanel();
        renderingDefaultDataMappingPanel();

        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewIgnoreDateMappingList = configOld.getIgnoreDataMappingList();

        CollectionListModel<String> ignoreDataMappingModel = new CollectionListModel<>(viewIgnoreDateMappingList);
        ignoreDateMappingJbList = new JBList<>(ignoreDataMappingModel);
        ToolbarDecorator ignoreDataMappingDecorator = ToolbarDecorator.createDecorator(ignoreDateMappingJbList);
        ignoreDataMappingDecorator.setMoveUpAction(null).setMoveDownAction(null).setAddAction(e -> {
            IgnoreDataMappingAddView ignoreDataMappingAddView = new IgnoreDataMappingAddView();
            if (ignoreDataMappingAddView.showAndGet()) {
                String value = ignoreDataMappingAddView.getValue();
                if (viewIgnoreDateMappingList.contains(value)) {
                    Messages.showMessageDialog("Ignore class already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                viewIgnoreDateMappingList.add(value);
                ignoreDateMappingJbList.setModel(new CollectionListModel<>(viewIgnoreDateMappingList));
            }
        }).setRemoveAction(e -> {
            viewIgnoreDateMappingList.remove(ignoreDateMappingJbList.getSelectedIndex());
            ignoreDateMappingJbList.setModel(new CollectionListModel<>(viewIgnoreDateMappingList));
        }).setRemoveActionUpdater(e -> {
            int selectedIndex = ignoreDateMappingJbList.getSelectedIndex();
            return selectedIndex > 2;
        }).setToolbarPosition(ActionToolbarPosition.TOP);


        BorderLayoutPanel inlineCustomerDataMapping = JBUI.Panels.simplePanel().addToTop(UI.PanelFactory.panel(customDataMappingPanel).withLabel(MyResourceBundleUtil.getKey("CustomerDataMapping")).moveLabelOnTop().createPanel());
        BorderLayoutPanel inlineDefaultDataMappingPanel = JBUI.Panels.simplePanel().addToTop(UI.PanelFactory.panel(defaultDataMappingPanel).withLabel(MyResourceBundleUtil.getKey("DefaultDataMapping")).moveLabelOnTop().createPanel());
        BorderLayoutPanel inlineIgnoreDataMappingPanel = JBUI.Panels.simplePanel().addToTop(UI.PanelFactory.panel(ignoreDataMappingDecorator.createPanel()).withComment(MyResourceBundleUtil.getKey("IgnoreDataMappingDesc")).withLabel(MyResourceBundleUtil.getKey("IgnoreDataMapping")).moveLabelOnTop().createPanel());

        panel.add(stringGeneratorConfigPanel, gb.nextLine().next().fillCell());
        panel.add(inlineCustomerDataMapping, gb.nextLine().next().weighty(0.3).pady(10).fillCell());
        panel.add(inlineIgnoreDataMappingPanel, gb.nextLine().next().weighty(0.3).pady(10).fillCell());
        panel.add(inlineDefaultDataMappingPanel, gb.nextLine().next().weighty(0.4).pady(10).fillCell());
        return panel;
    }


    private JPanel createStringGeneratorConfigPanel(){
        JPanel stringGeneratorConfigPanel = UI.PanelFactory.grid().splitColumns()
                .add(UI.PanelFactory.panel(randomStringStrategyComboBox).withLabel(MyResourceBundleUtil.getKey("Strategy")))
                .add(UI.PanelFactory.panel(randomStringTextField).withLabel(MyResourceBundleUtil.getKey("Length")))
                .add(UI.PanelFactory.panel(randomStringDelimiterTextField).withLabel(MyResourceBundleUtil.getKey("Delimiter"))).createPanel();
        stringGeneratorConfigPanel.setBorder(IdeBorderFactory.createTitledBorder(MyResourceBundleUtil.getKey("StringGeneratorConfig")));
        return stringGeneratorConfigPanel;
    }

    /**
     * 渲染默认数据映射panel
     *
     * @author Kings
     * @date 2021/05/24
     */
    private void renderingDefaultDataMappingPanel() {
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewDefaultDataMappingList = configOld.getDefaultDataMappingList();
        if (viewDefaultDataMappingList == null) {
            viewDefaultDataMappingList = new ArrayList<>();
        }
        JBTable table = createTable();
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(null);
        toolbarDecorator.setRemoveAction(null);
        toolbarDecorator.setPreferredSize(new Dimension(-1,200));
        defaultDataMappingPanel = toolbarDecorator.createPanel();
        setDefaultDataMappingTable(table);
    }

    /**
     * 渲染自定义数据映射panel
     *
     * @author Kings
     * @date 2021/05/24
     */
    private void renderingCustomDataMappingPanel() {
        //实现深拷贝 被坑了半天
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewCustomDataMappingList = configOld.getCustomDataMappingList();
        if (viewCustomDataMappingList == null) {
            viewCustomDataMappingList = new ArrayList<>();
        }
        JBTable table = createCustomTable();
        table.getEmptyText().appendLine("Example-> java type: com.baomidou.mybatisplus.extension.plugins.pagination.Page").appendLine("Default value: {\"size\":10,\"current\":1}").appendLine("Help", SimpleTextAttributes.LINK_PLAIN_ATTRIBUTES,e -> {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
                        dp.browse(URI.create(Constant.CN_DOC_DOMAIN + "/guide/getstarted/dataMapping"));
                    } else {
                        dp.browse(URI.create(Constant.EN_DOC_DOMAIN + "/guide/getstarted/dataMapping"));
                    }
                }
            } catch (Exception exception) {
                LOGGER.error("open url fail:" + Constant.EN_DOC_DOMAIN + "/guide/getstarted/dataMapping", exception);
            }
        });
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setAddAction(event -> {
            DataMappingAddView dataMappingAddView = new DataMappingAddView();
            if (dataMappingAddView.showAndGet()) {
                DataMapping dataMapping = dataMappingAddView.getValue();
                if (contains(viewCustomDataMappingList, dataMapping)) {
                    Messages.showMessageDialog("Java Type already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                if(!JSONUtil.isJson(dataMapping.getValue())){
                    Messages.showMessageDialog("Please enter json string", "Error", Messages.getInformationIcon());
                    return;
                }
                viewCustomDataMappingList.add(dataMapping);
                table.setModel(new ListTableModel<>(getColumnInfo(), viewCustomDataMappingList));
                setCustomTable(table);
            }
        });
        toolbarDecorator.setRemoveAction(event -> {
            int selectedRow = table.getSelectedRow();
            viewCustomDataMappingList.remove(selectedRow);
            table.setModel(new ListTableModel<>(getColumnInfo(), viewCustomDataMappingList));
            setCustomTable(table);
        });
        toolbarDecorator.setToolbarPosition(ActionToolbarPosition.TOP);
        toolbarDecorator.setPreferredSize(new Dimension(-1, 150));
        customDataMappingPanel = toolbarDecorator.createPanel();
        setCustomTable(table);
    }

    /**
     * 是否包含
     *
     * @param list   列表
     * @param target 目标
     * @return boolean
     * @author Kings
     * @date 2021/05/24
     */
    private boolean contains(List<DataMapping> list, DataMapping target) {
        return list.stream().anyMatch(q -> target.getType().equals(q.getType()));
    }

    public ColumnInfo<Object, Object>[] getColumnInfo() {
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[2];
        List<String> envList = Lists.newArrayList("Java Type", "Default value");
        for (int i = 0; i < envList.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(envList.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };
            columnArray[i] = envColumn;
        }
        return columnArray;
    }

    public JBTable createCustomTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<DataMapping> model = new ListTableModel<>(columns, viewCustomDataMappingList);
        JBTable table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (viewCustomDataMappingList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = viewCustomDataMappingList.get(row);
                if (dataMapping == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return dataMapping.getType();
                } else {
                    return dataMapping.getValue();
                }
            }
        };
        table.setVisible(true);
        return table;
    }

    public JBTable createTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<DataMapping> model = new ListTableModel<>(columns, viewDefaultDataMappingList);
        JBTable table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (viewDefaultDataMappingList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = viewDefaultDataMappingList.get(row);
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
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }

            @Override
            public void setValueAt(Object newValue, int row, int column) {
                DataMapping oldMapping = viewDefaultDataMappingList.get(row);
                String oldValue = oldMapping.getValue();
                String v = newValue.toString();
                try {
                    switch (row) {
                        case 0:
                            Byte.parseByte(v);
                            break;
                        case 1:
                            if (!v.isBlank()) {
                                Byte.parseByte(v);
                            }
                            break;
                        case 2:
                            Short.parseShort(v);
                            break;
                        case 3:
                            if (!v.isBlank()) {
                                Short.parseShort(v);
                            }
                            break;
                        case 4:
                            Integer.parseInt(v);
                            break;
                        case 5:
                            if (!v.isBlank()) {
                                Integer.parseInt(v);
                            }
                            break;
                        case 6:
                            Long.parseLong(v);
                            break;
                        case 7:
                            if (!v.isBlank()) {
                                Long.parseLong(v);
                            }
                            break;
                        case 8:
                        case 9:
                            String rex = "[\u0000-\uFFFF]";
                            Pattern compile = Pattern.compile(rex);
                            Matcher matcher = compile.matcher(v);
                            if (!matcher.matches()) {
                                throw new Exception("value error");
                            }
                            break;
                        case 10:
                            Float.parseFloat(v);
                            break;
                        case 11:
                            if (!v.isBlank()) {
                                Float.parseFloat(v);
                            }
                            break;
                        case 12:
                            Double.parseDouble(v);
                            break;
                        case 13:
                            if (!v.isBlank()) {
                                Double.parseDouble(v);
                            }
                            break;
                        case 14:
                            if (!"true".equals(v) && !"false".equals(v)) {
                                throw new Exception("value error");
                            }
                            break;
                        case 15:
                            if (!v.isBlank()) {
                                if (!"true".equals(v) && !"false".equals(v)) {
                                    throw new Exception("value error");
                                }
                            }
                            break;
                        default:
                            new BigDecimal(v);
                            break;
                    }
                    oldMapping.setValue(v);
                } catch (Exception e){
                    oldMapping.setValue(oldValue);
                }
            }
        };
        table.setVisible(true);
        return table;
    }


    @Override
    public JComponent getComponent() {
        return this;
    }

    public List<DataMapping> getViewCustomDataMappingList() {
        return viewCustomDataMappingList;
    }

    public void setViewCustomDataMappingList(List<DataMapping> viewCustomDataMappingList) {
        this.viewCustomDataMappingList = viewCustomDataMappingList;
    }

    public List<DataMapping> getViewDefaultDataMappingList() {
        return viewDefaultDataMappingList;
    }

    public void setViewDefaultDataMappingList(List<DataMapping> viewDefaultDataMappingList) {
        this.viewDefaultDataMappingList = viewDefaultDataMappingList;
    }

    public FastRequestConfiguration getConfigOld() {
        return configOld;
    }

    public void setConfigOld(FastRequestConfiguration configOld) {
        this.configOld = configOld;
    }

    public JBTable getCustomTable() {
        return customTable;
    }

    public void setCustomTable(JBTable customTable) {
        this.customTable = customTable;
    }

    public JBTable getDefaultDataMappingTable() {
        return defaultDataMappingTable;
    }

    public void setDefaultDataMappingTable(JBTable defaultDataMappingTable) {
        this.defaultDataMappingTable = defaultDataMappingTable;
    }

    public Integer getViewRandomStringLength() {
        return viewRandomStringLength;
    }


    public JTextField getRandomStringTextField() {
        return randomStringTextField;
    }

    public JComboBox<String> getRandomStringStrategyComboBox() {
        return randomStringStrategyComboBox;
    }

    public JTextField getRandomStringDelimiterTextField() {
        return randomStringDelimiterTextField;
    }

    public void setViewIgnoreDateMappingList(List<String> viewIgnoreDateMappingList) {
        this.viewIgnoreDateMappingList = viewIgnoreDateMappingList;
    }

    public List<String> getViewIgnoreDateMappingList() {
        return viewIgnoreDateMappingList;
    }

    public JBList<String> getIgnoreDateMappingJbList() {
        return ignoreDateMappingJbList;
    }
}

