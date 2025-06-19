/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *  
 */

package io.github.kings1990.plugin.fastrequest.view;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.treetable.TreeTableModel;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.HostGroup;
import io.github.kings1990.plugin.fastrequest.model.NameGroup;
import io.github.kings1990.plugin.fastrequest.view.inner.EnvAddView;
import io.github.kings1990.plugin.fastrequest.view.inner.GlobalHeaderAddView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class CommonConfigView extends AbstractConfigurableView {
    private JPanel panel;
    private JPanel tablePanel;
    private JPanel envListPanel;
    private JPanel projectNameListPanel;
    private JPanel globalRequestHeaderPanel;
    private JBList<String> envJbList;
    private JBList<String> projectNameJbList;
    private JBTable table;

    private List<String> viewEnvList;
    private List<String> viewProjectList;
    private List<NameGroup> viewDataList;
    private String viewEnableEnv;
    private String viewEnableProject;

    private List<DataMapping> globalHeaderList;
    private JBTable globalHeaderTable;



    public CommonConfigView(FastRequestConfiguration config) {
        super(config);
    }

    public JComponent getComponent() {
        return panel;
    }

    /**
     * 创建uicomponent
     *
     * @return
     * @author Kings
     * @date 2021/05/21
     */
    private void createUIComponents() {
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewDataList = configOld.getDataList();

        renderingEnvPanel(configOld);
        renderingProjectPanel(configOld);
        renderingTablePanel();
        renderingGlobalRequestHeaderPanel(configOld);
    }

    /**
     * 渲染table面板
     *
     * @return
     * @author Kings
     * @date 2021/05/21
     */
    private void renderingTablePanel() {
        JBTable table = createTable();
        table.getEmptyText().setText("Please set domain url");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table);
        toolbarDecorator.setRemoveAction(null);
        toolbarDecorator.setAddAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setMoveDownAction(null);
        tablePanel = toolbarDecorator.createPanel();
    }

    /**
     * 渲染项目名面板
     *
     * @return
     * @author Kings
     * @date 2021/05/21
     */
    private void renderingProjectPanel(FastRequestConfiguration configOld) {
        viewProjectList = configOld.getProjectList();
        viewEnableProject = configOld.getEnableProject();
        projectNameJbList = new JBList<>(new CollectionListModel<>(viewProjectList));
        projectNameJbList.setEmptyText("Please add project name");
        ToolbarDecorator toolbarDecoratorProject = ToolbarDecorator.createDecorator(projectNameJbList);
        toolbarDecoratorProject.setMoveUpAction(null).setMoveDownAction(null);
        toolbarDecoratorProject.setAddAction(button -> {
            //add界面
            EnvAddView envAddView = new EnvAddView("Add project", "Please add project");
            if (envAddView.showAndGet()) {
                String project = envAddView.getText();
                if (viewProjectList.contains(project)) {
                    //已存在弹出警告
                    Messages.showMessageDialog("Project already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                refreshDataListWithProject(viewProjectList, project, false);
            }
        });
        toolbarDecoratorProject.setRemoveAction(anActionButton -> {
            int i = Messages.showOkCancelDialog("Danger operation: Delete it?", "Delete", "Delete", "Cancel", Messages.getInformationIcon());
            if (i == 0) {
                String project = projectNameJbList.getSelectedValue();
                String enableProject = config.getEnableProject();
                if (project.equals(enableProject)) {
                    //                config.setEnableProject(null);
                    viewEnableProject = null;
                }
                refreshDataListWithProject(viewProjectList, project, true);
            }
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        projectNameListPanel = toolbarDecoratorProject.createPanel();
    }

    /**
     * 渲染env面板
     *
     * @author Kings
     * @date 2021/05/21
     */
    private void renderingEnvPanel(FastRequestConfiguration configOld) {
        viewEnvList = configOld.getEnvList();
        viewEnableEnv = configOld.getEnableEnv();
        envJbList = new JBList<>(new CollectionListModel<>(viewEnvList));
        envJbList.setEmptyText("Please add env");
        ToolbarDecorator toolbarDecoratorEnv = ToolbarDecorator.createDecorator(envJbList);
        toolbarDecoratorEnv.setMoveUpAction(null).setMoveDownAction(null);
        toolbarDecoratorEnv.setAddAction(button -> {
            EnvAddView envAddView = new EnvAddView("Add env", "Please add env");
            if (envAddView.showAndGet()) {
                String env = envAddView.getText();
                if (viewEnvList.contains(env)) {
                    Messages.showMessageDialog("Env already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                if (viewProjectList == null || viewProjectList.isEmpty()) {
                    Messages.showMessageDialog("Please add project first", "Error", Messages.getInformationIcon());
                    return;
                }
                TableColumn tableColumn = new TableColumn();
                tableColumn.setHeaderValue(env);
                table.addColumn(tableColumn);
                refreshDataListWithEnv(viewEnvList, env, false);
            }
        });
        toolbarDecoratorEnv.setRemoveAction(anActionButton -> {
            int i = Messages.showOkCancelDialog("Danger operation: Delete it?", "Delete", "Delete", "Cancel", Messages.getInformationIcon());
            if (i == 0) {
                String env = envJbList.getSelectedValue();
                table.removeColumn(table.getColumnModel().getColumn(viewEnvList.indexOf(env) + 1));

                String enableEnv = config.getEnableEnv();
                if (env.equals(enableEnv)) {
//                config.setEnableEnv(null);
                    viewEnableEnv = null;
                }
                refreshDataListWithEnv(viewEnvList, env, true);
            }
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        envListPanel = toolbarDecoratorEnv.createPanel();
    }

    private void renderingGlobalRequestHeaderPanel(FastRequestConfiguration configOld){
        globalHeaderList = configOld.getGlobalHeaderList();
        if(globalHeaderList == null){
            globalHeaderList = new ArrayList<>();
        }
        JBTable table = createGlobalRequestHeaderTable();
        table.getEmptyText().setText("Key:token  Value:xxx");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table, null);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(event -> {
            GlobalHeaderAddView dataMappingAddView = new GlobalHeaderAddView();
            if (dataMappingAddView.showAndGet()) {
                DataMapping dataMapping = dataMappingAddView.getValue();
                if (globalHeaderList.stream().anyMatch(q -> dataMapping.getType().equals(q.getType()))) {
                    Messages.showMessageDialog("Key already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                globalHeaderList.add(dataMapping);
                table.setModel(new ListTableModel<>(getGlobalColumnInfo(), globalHeaderList));
                setGlobalHeaderTable(table);
            }
        }).setRemoveAction(event -> {
            int i = Messages.showOkCancelDialog("Delete it?", "Delete", "Delete", "Cancel", Messages.getInformationIcon());
            if (i == 0) {
                int selectedRow = table.getSelectedRow();
                globalHeaderList.remove(selectedRow);
                table.setModel(new ListTableModel<>(getGlobalColumnInfo(), globalHeaderList));
                setGlobalHeaderTable(table);
            }
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        setGlobalHeaderTable(table);
        globalRequestHeaderPanel = toolbarDecorator.createPanel();
    }

    public JBTable createGlobalRequestHeaderTable() {
        ColumnInfo<Object, Object>[] columns = getGlobalColumnInfo();
        ListTableModel<DataMapping> model = new ListTableModel<>(columns, globalHeaderList);
        JBTable table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (globalHeaderList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = globalHeaderList.get(row);
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
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                DataMapping dataMapping = globalHeaderList.get(row);
                if (column == 0) {
                    dataMapping.setType(aValue.toString());
                } else {
                    dataMapping.setValue(aValue.toString());
                }
            }

        };
        table.setVisible(true);
        return table;
    }

    public ColumnInfo<Object, Object>[] getGlobalColumnInfo() {
        ArrayList<String> columnListName = Lists.newArrayList("Key", "Value");
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[columnListName.size()];
        for (int i = 0; i < columnListName.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(columnListName.get(i)) {
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
     * 修改Env->刷新数据列表
     *
     * @param viewEnvList 视图层env列表
     * @param env         env名字
     * @param isRemove    是删除
     * @return
     * @author Kings
     * @date 2021/05/26
     */
    private void refreshDataListWithEnv(List<String> viewEnvList, String env, boolean isRemove) {
        if (isRemove) {
            viewEnvList.remove(env);
            viewDataList.forEach(q -> q.getHostGroup().removeIf(p -> env.equals(p.getEnv())));
        } else {
            viewEnvList.add(env);
            viewDataList.forEach(q -> {
                if (q.getHostGroup() == null) {
                    List<HostGroup> hostGroupList = new ArrayList<>();
                    viewEnvList.forEach(e -> hostGroupList.add(new HostGroup(e, StringUtils.EMPTY)));
                    q.setHostGroup(hostGroupList);
                }
                q.getHostGroup().add(new HostGroup(env, StringUtils.EMPTY));
            });
        }
        envJbList.setModel(new CollectionListModel<>(viewEnvList));
    }

    /**
     * 修改项目->刷新数据列表
     *
     * @param viewProjectList 试图项目列表
     * @param project         项目
     * @param isRemove        是删除
     * @return
     * @author Kings
     * @date 2021/05/26
     */
    private void refreshDataListWithProject(List<String> viewProjectList, String project, boolean isRemove) {
        if (isRemove) {
            viewProjectList.remove(project);
            viewDataList.removeIf(q -> project.equals(q.getName()));
        } else {
            viewProjectList.add(project);
            List<HostGroup> hostGroupList = new ArrayList<>();
            viewEnvList.forEach(env -> hostGroupList.add(new HostGroup(env, StringUtils.EMPTY)));
            viewDataList.add(new NameGroup(project, hostGroupList));
        }
        //fast show table
        table.setModel(new ListTableModel<>(getColumnInfo(), viewDataList));
        projectNameJbList.setModel(new CollectionListModel<>(viewProjectList));
    }

    /**
     * 创建列
     *
     * @return {@link ColumnInfo<Object,Object>[] }
     * @author Kings
     * @date 2021/05/21
     */
    public ColumnInfo<Object, Object>[] getColumnInfo() {
//        List<String> envList = config.getEnvList();
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[viewEnvList.size() + 1];
        ColumnInfo<Object, Object> firstColumn = new ColumnInfo<>("Project/Env") {
            @Override
            public Object valueOf(Object o) {
                if (o instanceof CustomNode) {
                    return ((CustomNode) o).getName();
                } else return o;
            }
        };
        columnArray[0] = firstColumn;
        for (int i = 0; i < viewEnvList.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(viewEnvList.get(i)) {
                @Override
                public Object valueOf(Object o) {
                    if (o instanceof CustomNode) {
                        return ((CustomNode) o).getName();
                    } else return o;
                }
            };
            columnArray[i + 1] = envColumn;
        }
        return columnArray;
    }

    /**
     * 创建project/env表
     *
     * @return {@link JBTable }
     * @author Kings
     * @date 2021/05/21
     */
    public JBTable createTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<NameGroup> model = new ListTableModel<>(columns, viewDataList);
        JBTable table = new JBTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? TreeTableModel.class : String.class;
            }

            @Override
            public Object getValueAt(int row, int column) {

                Object valueAt = super.getValueAt(row, column);
                if (valueAt instanceof DefaultMutableTreeNode) {
                    if (((DefaultMutableTreeNode) valueAt).isRoot()) return null;
                }
                NameGroup nameGroup = viewDataList.get(row);
                String name = nameGroup.getName();
                if (column == 0) {
                    return name;
                } else {
                    List<HostGroup> hostGroupList = nameGroup.getHostGroup();
                    if (hostGroupList.isEmpty() || hostGroupList.size() < column) {
                        return StringUtils.EMPTY;
                    }
                    HostGroup hostGroup = hostGroupList.get(column - 1);
                    if (hostGroup == null) {
                        return StringUtils.EMPTY;
                    }
                    return hostGroup.getUrl();
                }
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                String value = aValue.toString();
                List<HostGroup> hostGroupList = viewDataList.get(row).getHostGroup();
                hostGroupList.get(column - 1).setUrl(value);
                super.setValueAt(aValue, row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        table.setVisible(true);
        setTable(table);
        return table;
    }


    /**
     * 自定义节点
     *
     * @author Kings
     * @date 2021/05/21
     * @see DefaultMutableTreeNode
     */
    private static class CustomNode extends DefaultMutableTreeNode {
        String name;
        Object value;

        CustomNode(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public JBTable getTable() {
        return table;
    }

    public void setTable(JBTable table) {
        this.table = table;
    }

    public JBList<String> getEnvJbList() {
        return envJbList;
    }

    public void setEnvJbList(JBList<String> envJbList) {
        this.envJbList = envJbList;
    }

    public JBList<String> getProjectNameJbList() {
        return projectNameJbList;
    }

    public void setProjectNameJbList(JBList<String> projectNameJbList) {
        this.projectNameJbList = projectNameJbList;
    }

    public List<String> getViewEnvList() {
        return viewEnvList;
    }

    public void setViewEnvList(List<String> viewEnvList) {
        this.viewEnvList = viewEnvList;
    }

    public List<String> getViewProjectList() {
        return viewProjectList;
    }

    public void setViewProjectList(List<String> viewProjectList) {
        this.viewProjectList = viewProjectList;
    }

    public List<NameGroup> getViewDataList() {
        return viewDataList;
    }

    public void setViewDataList(List<NameGroup> viewDataList) {
        this.viewDataList = viewDataList;
    }

    public String getViewEnableEnv() {
        return viewEnableEnv;
    }

    public void setViewEnableEnv(String viewEnableEnv) {
        this.viewEnableEnv = viewEnableEnv;
    }

    public String getViewEnableProject() {
        return viewEnableProject;
    }

    public void setViewEnableProject(String viewEnableProject) {
        this.viewEnableProject = viewEnableProject;
    }

    public List<DataMapping> getGlobalHeaderList() {
        return globalHeaderList;
    }

    public void setGlobalHeaderList(List<DataMapping> globalHeaderList) {
        this.globalHeaderList = globalHeaderList;
    }

    public JBTable getGlobalHeaderTable() {
        return globalHeaderTable;
    }

    public void setGlobalHeaderTable(JBTable globalHeaderTable) {
        this.globalHeaderTable = globalHeaderTable;
    }
}
