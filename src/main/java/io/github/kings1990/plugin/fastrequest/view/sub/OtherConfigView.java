package io.github.kings1990.plugin.fastrequest.view.sub;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.inner.UrlReplaceAddView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OtherConfigView extends AbstractConfigurableView {
    private JPanel panel;
    private JPanel urlReplacePanel;
    private List<DataMapping> viewUrlReplaceMappingList;
    private JBTable urlReplaceTable;
    private FastRequestConfiguration configOld;

    public OtherConfigView(FastRequestConfiguration config) {
        super(config);
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    private void createUIComponents() {
        renderingUrlReplacePanel();
    }

    private void renderingUrlReplacePanel() {
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewUrlReplaceMappingList = configOld.getUrlReplaceMappingList();
        if (viewUrlReplaceMappingList == null) {
            viewUrlReplaceMappingList = new ArrayList<>();
        }

        JBTable table = createTable();
        table.getEmptyText().setText("Target:${api-module}  replacement:base");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator.setAddAction(event -> {
            UrlReplaceAddView dataMappingAddView = new UrlReplaceAddView();
            if (dataMappingAddView.showAndGet()) {
                DataMapping dataMapping = dataMappingAddView.getValue();
                if (viewUrlReplaceMappingList.stream().anyMatch(q -> dataMapping.getType().equals(q.getType()))) {
                    Messages.showMessageDialog("Target already exist", "Error", Messages.getInformationIcon());
                    return;
                }
                viewUrlReplaceMappingList.add(dataMapping);
                table.setModel(new ListTableModel<>(getColumnInfo(), viewUrlReplaceMappingList));
                setUrlReplaceTable(table);
            }
        }).setRemoveAction(event -> {
            int selectedRow = table.getSelectedRow();
            viewUrlReplaceMappingList.remove(selectedRow);
            table.setModel(new ListTableModel<>(getColumnInfo(), viewUrlReplaceMappingList));
            setUrlReplaceTable(table);
        });

        urlReplacePanel = toolbarDecorator.createPanel();
    }

    public JBTable createTable() {
        ColumnInfo<Object, Object>[] columns = getColumnInfo();
        ListTableModel<DataMapping> model = new ListTableModel<>(columns, viewUrlReplaceMappingList);
        JBTable table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (viewUrlReplaceMappingList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                DataMapping dataMapping = viewUrlReplaceMappingList.get(row);
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

    public ColumnInfo<Object, Object>[] getColumnInfo() {
        ArrayList<String> columnListName = Lists.newArrayList("Target", "Replacement");
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

    public JBTable getUrlReplaceTable() {
        return urlReplaceTable;
    }

    public void setUrlReplaceTable(JBTable urlReplaceTable) {
        this.urlReplaceTable = urlReplaceTable;
    }

    public List<DataMapping> getViewUrlReplaceMappingList() {
        return viewUrlReplaceMappingList;
    }

    public void setViewUrlReplaceMappingList(List<DataMapping> viewUrlReplaceMappingList) {
        this.viewUrlReplaceMappingList = viewUrlReplaceMappingList;
    }
}
