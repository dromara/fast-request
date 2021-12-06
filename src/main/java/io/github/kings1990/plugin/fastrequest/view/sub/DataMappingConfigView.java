/*
 * Copyright 2021 kings1990
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kings1990.plugin.fastrequest.view.sub;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.ActionLink;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.inner.DataMappingAddView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated(since = "2.0.1")
public class DataMappingConfigView extends AbstractConfigurableView {
    private static final Logger LOGGER = Logger.getInstance(DataMappingConfigView.class);
    private JPanel panel;
    private JPanel defaultDataMappingPanel;
    private JPanel customDataMappingPanel;
    private JTextField randomStringTextField;
    private JComboBox<String> randomStringStrategyComboBox;
    private JTextField randomStringDelimiterTextField;
    private JButton helpButton;
    private List<DataMapping> viewCustomDataMappingList;
    private List<DataMapping> viewDefaultDataMappingList;
    private JBTable customTable;
    private JBTable defaultDataMappingTable;
    private FastRequestConfiguration configOld;
    private Integer viewRandomStringLength;
    private String viewRandomStringStrategy;
    private String viewRandomStringDelimiter;

    public DataMappingConfigView(FastRequestConfiguration config) {
        super(config);

        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        int randomStringLength = configOld.getRandomStringLength();
        String randomStringStrategy = configOld.getRandomStringStrategy();
        String randomStringDelimiter = configOld.getRandomStringDelimiter();
        randomStringTextField.setText(randomStringLength + "");
        randomStringStrategyComboBox.setSelectedItem(randomStringStrategy);
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


    }

    private void createUIComponents() {
        renderingDefaultDataMappingPanel();
        renderingCustomDataMappingPanel();

        ActionLink helpLink = new ActionLink("Help", e -> {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(URI.create("https://github.com/kings1990/fast-request-doc#32-data-mapping"));
                }
            } catch (Exception exception) {
                LOGGER.error("open url fail:https://github.com/kings1990/fast-request-doc#32-data-mapping", exception);
            }
        });
        helpLink.setExternalLinkIcon();
        helpButton = helpLink;
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
        table.getEmptyText().setText("Example-> java type: com.baomidou.mybatisplus.extension.plugins.pagination.page ,default value: {\"size\":10,\"current\":1}");
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
                    switch (row){
                        case 0:
                        case 1:
                            Byte.parseByte(v);
                            break;
                        case 2:
                        case 3:
                            Short.parseShort(v);
                            break;
                        case 4:
                        case 5:
                            Integer.parseInt(v);
                            break;
                        case 6:
                        case 7:
                            Long.parseLong(v);
                            break;
                        case 8:
                        case 9:
                            String rex = "[\u0000-\uFFFF]";
                            Pattern compile = Pattern.compile(rex);
                            Matcher matcher = compile.matcher(v);
                            if(!matcher.matches()){
                                throw new Exception("value error");
                            }
                            break;
                        case 10:
                        case 11:
                            Float.parseFloat(v);
                        case 12:
                        case 13:
                            Double.parseDouble(v);
                            break;
                        case 14:
                        case 15:
                            if(!"true".equals(v) && !"false".equals(v)){
                                throw new Exception("value error");
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
        return panel;
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
}

