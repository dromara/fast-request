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

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.*;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.inner.UrlReplaceAddView;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OtherConfigView extends AbstractConfigurableView {

    private List<DataMapping> viewUrlReplaceMappingList;
    private JBTable urlReplaceTable;
    private FastRequestConfiguration configOld;


    public OtherConfigView(FastRequestConfiguration config) {
        super(config);
        setLayout(new BorderLayout());
        add(createMainComponent());
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    private JComponent createMainComponent() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBag gb = new GridBag()
                .setDefaultInsets(JBUI.insets(0, 0, 4, 10))
                .setDefaultWeightX(1)
                .setDefaultFill(GridBagConstraints.HORIZONTAL);
        panel.add(createMyTablePanel(), gb.nextLine().fillCell().weighty(1.0));
        return panel;
    }

    private JPanel createMyTablePanel() {
        FastRequestConfiguration configOld = JSONObject.parseObject(JSONObject.toJSONString(config), FastRequestConfiguration.class);
        viewUrlReplaceMappingList = configOld.getUrlReplaceMappingList();
        if (viewUrlReplaceMappingList == null) {
            viewUrlReplaceMappingList = new ArrayList<>();
        }

        JBTable table = createTable();
        table.getEmptyText().setText("Target:${api-module}  replacement:base");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(table, null);
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
        }).setToolbarPosition(ActionToolbarPosition.TOP);
        JPanel tablePanel = toolbarDecorator.createPanel();
        return JBUI.Panels.simplePanel(UI.PanelFactory.panel(tablePanel)
                .withLabel(MyResourceBundleUtil.getKey("UrlReplaceConfig")).moveLabelOnTop()
                .withComment(MyResourceBundleUtil.getKey("OtherConfigTitle1") + " " + MyResourceBundleUtil.getKey("OtherConfigTitle2"), false).resizeY(true).createPanel());
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
