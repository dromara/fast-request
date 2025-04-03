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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableMap;
import com.intellij.icons.AllIcons;
import com.intellij.ide.DataManager;
import com.intellij.ide.ExporterToTextFile;
import com.intellij.ide.HelpTooltip;
import com.intellij.ide.plugins.newui.ListPluginComponent;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupListener;
import com.intellij.openapi.ui.popup.LightweightWindowEvent;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.*;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.fields.ExtendableTextComponent;
import com.intellij.ui.content.Content;
import com.intellij.ui.dualView.TreeTableView;
import com.intellij.ui.popup.PopupFactoryImpl;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.ui.treeStructure.treetable.TreeTableTree;
import com.intellij.util.BooleanFunction;
import com.intellij.util.PsiNavigateUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.StatusText;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.FastRequestCollectionComponent;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.idea.ExportToFileUtil;
import io.github.kings1990.plugin.fastrequest.model.*;
import io.github.kings1990.plugin.fastrequest.util.*;
import io.github.kings1990.plugin.fastrequest.view.component.CollectionNodeSelection;
import io.github.kings1990.plugin.fastrequest.view.model.CollectionCustomNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FastRequestCollectionToolWindow extends SimpleToolWindowPanel {

    private Project myProject;
    private JPanel panel;
    private JPanel collectionPanel;
    private JLabel helpLabel;
    private JPanel searchPanel;
    private TreeTableView collectionTable;
    private CollectionConfiguration.CollectionDetail rootDetail;
    private DefaultActionGroup myInstalledSearchGroup;
    private Consumer<SearchOptionAction> mySearchCallback;
    private SearchTextField jbSearchPanelText;
    private Tree tree;

    public FastRequestCollectionToolWindow(Project project, ToolWindow toolWindow) {
        super(true, false);
        this.setContent(panel);
        this.myProject = project;

        helpLabel.setIcon(PluginIcons.ICON_CONTEXT_HELP);
        new HelpTooltip().setDescription(MyResourceBundleUtil.getKey("CollectionSearchHelp")).installOn(helpLabel);

        refresh();

        myInstalledSearchGroup = new DefaultActionGroup();
        for (SearchTypeEnum option : SearchTypeEnum.values()) {
            if (option.name().startsWith("separator")) {
                myInstalledSearchGroup.addSeparator("  " + option.name().split("_")[1]);
            } else {
                myInstalledSearchGroup.add(new SearchOptionAction(option));
            }
        }

        mySearchCallback = updateAction -> {
            String query = jbSearchPanelText.getText();
            String rule = "";
            if (updateAction.myState) {
                rule = updateAction.getQuery();
            } else {
                query = query.replace(updateAction.getQuery(), "");
            }
            jbSearchPanelText.setText(rule + query);
            filterRequest();
        };
    }

    private void createUIComponents() {
        renderingCollectionTablePanel();
        searchPanel = new SearchTextField(true);
        searchPanel.setFocusable(false);
        jbSearchPanelText = (SearchTextField) this.searchPanel;
        JBTextField searchTextField = jbSearchPanelText.getTextEditor();
        searchTextField.putClientProperty("StatusVisibleFunction", (BooleanFunction<JBTextField>) field -> field.getText().isEmpty());
        StatusText emptyText = searchTextField.getEmptyText();
        emptyText.appendText("Search by name or url ->", new SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, ListPluginComponent.GRAY_COLOR));
        searchTextField.putClientProperty("search.extension", ExtendableTextComponent.Extension
                .create(AllIcons.Actions.More, AllIcons.Actions.More, "Search options",
                        () -> showRightBottomPopup(searchTextField, "By", myInstalledSearchGroup)));
        searchTextField.putClientProperty("JTextField.variant", null);
        searchTextField.putClientProperty("JTextField.variant", "search");
        searchTextField.getDocument().addDocumentListener(new DelayedDocumentListener());
    }

    private class DelayedDocumentListener implements DocumentListener {

        private final Timer timer;

        public DelayedDocumentListener() {
            timer = new Timer(500, e -> filterRequest());
            timer.setRepeats(false);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            timer.restart();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            timer.restart();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            timer.restart();
        }

    }

    private static class GroupByActionGroup extends DefaultActionGroup implements CheckedActionGroup {
    }

    private static void showRightBottomPopup(@NotNull Component component, @NotNull @Nls String title, @NotNull ActionGroup group) {
        DefaultActionGroup actions = new GroupByActionGroup();
        actions.addSeparator(title);
        actions.addAll(group);

        DataContext context = DataManager.getInstance().getDataContext(component);

        JBPopup popup = new PopupFactoryImpl.ActionGroupPopup(null, actions, context, false, false, false, true, null, -1, null, null);
        popup.addListener(new JBPopupListener() {
            @Override
            public void beforeShown(@NotNull LightweightWindowEvent event) {
                Point location = component.getLocationOnScreen();
                Dimension size = popup.getSize();
                popup.setLocation(new Point(location.x + component.getWidth() - size.width, location.y + component.getHeight()));
            }
        });
        popup.show(component);
    }

    public void refresh() {
        CollectionConfiguration config = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert config != null;
        rootDetail = config.getDetail();
        CollectionCustomNode root = convertToNode(rootDetail);
        ((DefaultTreeModel) collectionTable.getTableModel()).setRoot(root);
        tree = collectionTable.getTree();
        SwingUtil.expandAll(tree, new TreePath(root), true);

        TableColumnModel columnModel = collectionTable.getColumnModel();

        columnModel.getColumn(2).setMaxWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
    }

    private void filterRequest(String query, String rule) {
        CollectionConfiguration config = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert config != null;
        CollectionConfiguration.CollectionDetail detail = config.getDetail();
        CollectionCustomNode root = (CollectionCustomNode) collectionTable.getTableModel().getRoot();


        if (StringUtils.isBlank(query)) {
            root = convertToNode(rootDetail);
            ((DefaultTreeModel) collectionTable.getTableModel()).setRoot(root);
        } else {
            CollectionCustomNode node = new CollectionCustomNode("0", "Root", 1);
            convertToNode(node, rootDetail.getChildList());
            filterNode(node, query, rule);
            ((DefaultTreeModel) collectionTable.getTableModel()).setRoot(node);
            SwingUtil.expandAll(collectionTable.getTree(), new TreePath(node), true);
        }
        SwingUtil.expandAll(collectionTable.getTree(), new TreePath(root), true);
    }

    private static Map<String, String> getQuery(String search) {
        String[] split = search.split("\\|");
        StringBuilder rule = new StringBuilder();
        String query = "";
        for (String s : split) {
            s = s.trim();
            if (SearchTypeEnum.fromValue(s) != null && search.indexOf("|", search.indexOf(s) + 1) != -1) {
                rule.append(s).append(",");
            } else {
                query = s;
            }
        }
        return ImmutableMap.<String, String>builder().put("query", query).put("rule", rule.toString()).build();
    }

    private void filterRequest() {
        CollectionConfiguration config = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert config != null;
        String search = ((SearchTextField) searchPanel).getText();
        CollectionCustomNode root = (CollectionCustomNode) collectionTable.getTableModel().getRoot();
        if (StringUtils.isBlank(search)) {
            root = convertToNode(rootDetail);
            checkRule("");
            ((DefaultTreeModel) collectionTable.getTableModel()).setRoot(root);
        } else {
            Map<String, String> queryMap = getQuery(search);
            String query = queryMap.get("query");
            String rule = queryMap.get("rule");
            //选择或去除rule
            checkRule(rule);
            CollectionCustomNode node = new CollectionCustomNode("0", "Root", 1);
            convertToNode(node, rootDetail.getChildList());
            filterNode(node, query, rule);
            ((DefaultTreeModel) collectionTable.getTableModel()).setRoot(node);
            SwingUtil.expandAll(collectionTable.getTree(), new TreePath(node), true);
        }
        SwingUtil.expandAll(collectionTable.getTree(), new TreePath(root), true);
    }

    private void checkRule(String rule) {
        AnAction[] children = myInstalledSearchGroup.getChildren(null);
        for (AnAction anAction : children) {
            if (anAction instanceof Separator) {
                continue;
            }
            SearchOptionAction child = (SearchOptionAction) anAction;
            if (rule.contains(child.myOption.name())) {
                child.myState = true;
            } else {
                child.myState = false;
            }
        }
    }

    private boolean filterNode(CollectionCustomNode node, String search, String rule) {
        if (node.isRoot()) {
            ArrayList<CollectionCustomNode> nodeList = (ArrayList<CollectionCustomNode>) IteratorUtils.toList(node.children().asIterator());
            for (CollectionCustomNode n : nodeList) {
                filterNode(n, search, rule);
            }
        } else {
            if (node.getChildCount() == 0) {
                boolean methodTypeSearchFlag = rule.contains(SearchTypeEnum.get.name()) || rule.contains(SearchTypeEnum.post.name()) ||
                        rule.contains(SearchTypeEnum.put.name()) || rule.contains(SearchTypeEnum.delete.name()) || rule.contains(SearchTypeEnum.patch.name());

                String targetText = "";

                if (rule.contains(SearchTypeEnum.name.name())) {
                    String name = node.getName();
                    targetText += name == null ? "" : name;
                }
                if (rule.contains(SearchTypeEnum.url.name())) {
                    String url = node.getUrl();
                    targetText += url == null ? "" : url;
                }
                if (methodTypeSearchFlag) {
                    String methodType = node.getDetail().getParamGroup().getMethodType();
                    if (methodType == null || !rule.contains(methodType.toLowerCase())) {
                        node.removeFromParent();
                        return true;
                    }
                }
                if (rule.isBlank() || (targetText.isBlank() && methodTypeSearchFlag)) {
                    targetText = node.getSearchText();
                }
                if (!targetText.toLowerCase().contains(search.toLowerCase())) {
                    node.removeFromParent();
                    return true;
                } else {
                    return false;
                }

            } else {
                ArrayList<CollectionCustomNode> nodeList = (ArrayList<CollectionCustomNode>) IteratorUtils.toList(node.children().asIterator());
                boolean removeGroup = true;
                for (CollectionCustomNode n : nodeList) {
                    removeGroup &= filterNode(n, search, rule);
                }
                if (removeGroup) {
                    node.removeFromParent();
                }
            }
        }
        return false;
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

    private CollectionConfiguration.CollectionDetail filterById(String id, List<CollectionConfiguration.CollectionDetail> collectionDetailList) {
        for (CollectionConfiguration.CollectionDetail detail : collectionDetailList) {
            if (detail.getId().equals(id)) {
                return detail;
            }
            List<CollectionConfiguration.CollectionDetail> childList = detail.getChildList();
            if (!childList.isEmpty()) {
                CollectionConfiguration.CollectionDetail detail1 = filterById(id, childList);
                if (detail1 != null) {
                    return detail1;
                }
            }
        }
        return null;
    }

    private void renderingCollectionTablePanel() {
        collectionTable = createCollectionTable();
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(collectionTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);
        toolbarDecorator.setRemoveActionUpdater(e -> {
            int selectedRow = collectionTable.getSelectedRow();
            CollectionCustomNode root = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(selectedRow);
            return !"0".equals(root.getId()) && !"1".equals(root.getId());
        });
        toolbarDecorator.setAddActionName("Add Group").setAddAction(e -> {
            int selectedRow = collectionTable.getSelectedRow();
            CollectionCustomNode root = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(0);
            String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            CollectionCustomNode addNode = null;
            CollectionConfiguration.CollectionDetail detail = null;
            if (selectedRow == -1 || selectedRow == 0) {
                addNode = new CollectionCustomNode(id, "Group " + root.getChildCount(), 1);
                detail = new CollectionConfiguration.CollectionDetail(id, "Group " + root.getChildCount(), 1);
                root.insert(addNode, 1);
                rootDetail.getChildList().add(detail);
            } else {
                CollectionCustomNode node = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(selectedRow);
                if (node.getType() == 1) {
                    String idGroup = node.getId();
                    CollectionConfiguration.CollectionDetail detailGroup = filterById(idGroup, rootDetail);
                    if (detailGroup != null) {
                        List<CollectionConfiguration.CollectionDetail> childList = detailGroup.getChildList();
                        long count = childList.stream().filter(q -> q.getType() == 1).count();
                        addNode = new CollectionCustomNode(id, "Group " + (count + 1), 1);
                        detail = new CollectionConfiguration.CollectionDetail(id, "Group " + (count + 1), 1);
                        childList.add(detail);
                        detailGroup.setChildList(childList);
                        node.insert(addNode, 0);
                    }
                } else {

                    CollectionCustomNode parent = (CollectionCustomNode) node.getParent();
                    String idParent = parent.getId();
                    CollectionConfiguration.CollectionDetail detailGroup = filterById(idParent, rootDetail);
                    if (detailGroup != null) {
                        List<CollectionConfiguration.CollectionDetail> childList = detailGroup.getChildList();
                        long count = childList.stream().filter(q -> q.getType() == 1).count();
                        addNode = new CollectionCustomNode(id, "Group " + (count + 1), 1);
                        detail = new CollectionConfiguration.CollectionDetail(id, "Group " + (count + 1), 1);
                        childList.add(detail);
                        detailGroup.setChildList(childList);
                        parent.insert(addNode, 0);
                    }
                }
            }
            int row = selectedRow == -1 ? 0 : selectedRow;
            collectionTable.setRowSelectionInterval(row, row);
            refreshTable();

        });
        toolbarDecorator.setRemoveAction(e -> {
            int i = Messages.showOkCancelDialog("Delete it?", "Delete", "Delete", "Cancel", Messages.getInformationIcon());
            if (i == 0) {
                int selectedRow = collectionTable.getSelectedRow();
                CollectionCustomNode node = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(selectedRow);
                CollectionCustomNode parent = (CollectionCustomNode) node.getParent();
                parent.remove(node);
                collectionTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
                refreshTable();
                CollectionConfiguration.CollectionDetail parentDetail = filterById(parent.getId(), rootDetail);
                parentDetail.getChildList().removeIf(q -> q.getId().equals(node.getId()));
            }
        });

        /**
        toolbarDecorator.addExtraAction(new ToolbarDecorator.ElementActionButton(MyResourceBundleUtil.getKey("button.addModuleGroup"), AllIcons.Nodes.ModuleGroup) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                CollectionCustomNode root = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(0);
                ArrayList<CollectionCustomNode> nodeList = (ArrayList<CollectionCustomNode>) IteratorUtils.toList(root.children().asIterator());
                List<String> existList = nodeList.stream().map(CollectionCustomNode::getName).collect(Collectors.toList());
                ListAndSelectModule dialog = new ListAndSelectModule(myProject, existList);
                if (dialog.showAndGet()) {
                    String moduleName = dialog.getValue();
                    String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                    CollectionCustomNode addNode = new CollectionCustomNode(id, moduleName, 1);
                    CollectionConfiguration.CollectionDetail detail = new CollectionConfiguration.CollectionDetail(id, moduleName, 1);
                    root.insert(addNode, 1);
                    rootDetail.getChildList().add(detail);
                    refreshTable();
                }
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
         */
        toolbarDecorator.addExtraAction(new ToolbarDecorator.ElementActionButton("Expand All", AllIcons.Actions.Expandall) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                CollectionCustomNode node = new CollectionCustomNode("0", "Root", 1);
                SwingUtil.expandAll(tree,new TreePath(tree.getModel().getRoot()),true);
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });

        toolbarDecorator.addExtraAction(new ToolbarDecorator.ElementActionButton("Collapse All", AllIcons.Actions.Collapseall) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                CollectionCustomNode node = new CollectionCustomNode("0", "Root", 1);
                SwingUtil.expandAll(tree,new TreePath(tree.getModel().getRoot()),false);
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
        toolbarDecorator.addExtraAction(new ToolbarDecorator.ElementActionButton(MyResourceBundleUtil.getKey("button.exportToPostman"), PluginIcons.ICON_POSTMAN) {

            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {

                List<DataMapping> headerParamsKeyValueList;
                FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(myProject);
                if(fastRequestToolWindow == null){
                    headerParamsKeyValueList = new ArrayList<>();
                } else {
                    headerParamsKeyValueList = fastRequestToolWindow.getHeaderParamsKeyValueList();
                }
                FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
                assert config != null;
                List<DataMapping> globalHeaderList = config.getGlobalHeaderList();
                List<DataMapping> globalHeaderListNew = JSONArray.parseArray(JSON.toJSONString(globalHeaderList), DataMapping.class);
                globalHeaderListNew.removeIf(q->headerParamsKeyValueList.stream().anyMatch(p->p.getType().equals(q.getType())));
                headerParamsKeyValueList.addAll(globalHeaderListNew);

                PostmanCollection postmanCollection = PostmanExportUtil.getPostmanCollection(headerParamsKeyValueList,rootDetail,myProject.getName());
                ExporterToTextFile exporterToTextFile = new ExporterToTextFile(){

                    @Override
                    public @NotNull String getReportText() {
                        return JSON.toJSONString(postmanCollection, SerializerFeature.DisableCircularReferenceDetect);
                    }

                    @Override
                    public @NotNull String getDefaultFilePath() {
                        VirtualFile virtualFile = ProjectUtil.guessProjectDir(myProject);
                        if(virtualFile != null){
                            return virtualFile.getPath() + File.separator + "FastRequest("+myProject.getName()+").postman_collection_v21.json";
                        }
                        return "";
                    }

                    @Override
                    public boolean canExport() {
                        return true;
                    }
                };
                ExportToFileUtil.chooseFileAndExport(myProject,exporterToTextFile);
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
        toolbarDecorator.setToolbarPosition(ActionToolbarPosition.TOP);
        collectionPanel = toolbarDecorator.createPanel();
    }

    private TreeTableView createCollectionTable() {
        //初始化为空
        CollectionCustomNode root = new CollectionCustomNode("0", "Root", 1);
        convertToNode(root, new ArrayList<>());
        ColumnInfo[] columnInfo = new ColumnInfo[]{
                new TreeColumnInfo("Name") {

                },   // <-- This is important!
                new ColumnInfo("Url") {
                    @Nullable
                    @Override
                    public Object valueOf(Object o) {
                        if (o instanceof CollectionCustomNode) {
                            return ((CollectionCustomNode) o).getUrl();
                        } else return o;
                    }
                },
                new ColumnInfo("Navigate") {
                    @Nullable
                    @Override
                    public Object valueOf(Object o) {
                        return null;
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
                CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                if (column == 0) {
                    return node.getName();
                } else if (column == 1) {
                    return node.getUrl();
                } else {
                    return null;
                }
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                if (node.getType() != 1 && column == 2) {
                    renderer = new ButtonRenderer();
                }
                return super.prepareRenderer(renderer, row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                return (column == 0 && row > 1) || (column == 2 && node.getType() != 1);
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 0) {
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    String name = (String) getValueAt(row, column);
                    return new DefaultCellEditor(new JTextField(name));
                } else if (column == 2) {
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                    if (node.getType() != 1) {
                        return new ButtonEditor(new JCheckBox());
                    }
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public void setValueAt(Object v, int row, int column) {
                if (column == 0) {
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                    CollectionConfiguration.CollectionDetail detail = filterById(node.getId(), rootDetail);
                    if (detail != null && !v.toString().isBlank()) {
                        detail.setName(v.toString());
                        node.setName(v.toString());
                        refreshTable();
                    }
                } else {
                    super.setValueAt(v, row, column);
                }
            }
        };

        table.setDragEnabled(true);
        table.setDropMode(DropMode.ON_OR_INSERT_ROWS);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        table.setTransferHandler(new TransferHelper());
        table.setRootVisible(true);
        table.setVisible(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    navigate(table);
                }
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    navigate(table);
                }
            }
        });

        return table;
    }

    private void navigate(TreeTableView table){
        int row = table.getSelectedRow();
        ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) collectionTable.getTableModel();
        Object rowValue = myModel.getRowValue(row);
        CollectionCustomNode node;
        if(rowValue !=null && (node = (CollectionCustomNode) rowValue).getType() == 2){
            load(node);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {

        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(PluginIcons.ICON_LOCAL_SCOPE);
            setBackground(new JBColor(JBColor.WHITE, new Color(60, 63, 65)));
            return this;
        }

    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton jButton;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            jButton = new JButton();
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) collectionTable.getTableModel();
                CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                load(node);
            }
            jButton.setIcon(PluginIcons.ICON_LOCAL_SCOPE);
            return jButton;
        }
    }

    class TransferHelper extends TransferHandler {
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_MOVE;
        }


        public Transferable createTransferable(JComponent comp) {
            JTable table = (JTable) comp;
            int row = table.getSelectedRow();
            ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) collectionTable.getTableModel();
            CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
            node.setRow(row);
            CollectionNodeSelection transferable = new CollectionNodeSelection(node);
            return transferable;
        }

        public boolean canImport(TransferSupport support) {
            return true;
        }

        public boolean importData(TransferSupport support) {
            JTable table = (JTable) support.getComponent();
            JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
            Point dp = dl.getDropPoint();
            int row = table.rowAtPoint(dp);
            int offset = row == -1 ? 0 : dl.getRow() - row;
            if (row == -1) {
                row = dl.getRow() - 1;
            }
            Transferable t = support.getTransferable();
            try {
                CollectionCustomNode add = (CollectionCustomNode) t.getTransferData(CollectionNodeSelection.CELL_DATA_FLAVOR);
                //从老节点删除
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) collectionTable.getTableModel();
                CollectionCustomNode old = (CollectionCustomNode) myModel.getRowValue(add.getRow());
                CollectionCustomNode oldParent = (CollectionCustomNode) old.getParent();
                String sourceParentId = oldParent.getId();
                int oldIndex = oldParent.getIndex(old);
                //新节点增加
                CollectionCustomNode toAdd = (CollectionCustomNode) myModel.getRowValue(row);
                String targetId;
                int position = 0;
                if (toAdd.getType() == 1) {
                    old.removeFromParent();
                    targetId = toAdd.getId();
                    toAdd.insert(add, 0);
                } else {
                    CollectionCustomNode parent = (CollectionCustomNode) toAdd.getParent();
                    position = parent.getIndex(toAdd);

                    if (sourceParentId.equals(parent.getId())) {
                        if (position == oldIndex) {
                            return false;
                        } else if (position > oldIndex) {
                            old.removeFromParent();
                        } else {
                            old.removeFromParent();
                            position += offset;
                        }
                    } else {
                        old.removeFromParent();
                        position += offset;
                    }

                    parent.insert(add, position);
                    targetId = parent.getId();
                }
                TreeTableTree tree = collectionTable.getTree();
                for (TreeNode treeNode : add.getPath()) {
                    tree.expandPath(new TreePath(((CollectionCustomNode) treeNode).getPath()));
                }
                refreshTable();
                moveData(sourceParentId, add.getId(), targetId, position);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    class Renderer extends ColoredTreeCellRenderer {
        @Override
        public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            //解决TreeTable key加上>
            CollectionCustomNode node = (CollectionCustomNode) value;
            append(node.getName());
            if (node.getType() == 2) {
                setIcon(FrIconUtil.getIconByMethodType(node.getDetail().getParamGroup().getMethodType()));
            }
        }
    }

    private CollectionCustomNode convertToNode(CollectionConfiguration.CollectionDetail detail) {
        String name = detail.getName();
        CollectionCustomNode node = new CollectionCustomNode(name, detail.getType(), detail.getParamGroup().getUrl());
        node.setId(detail.getId());
        node.setSearchText("<" + detail.getName() + detail.getDescription() + detail.getParamGroup().getUrl() + ">");
        node.setDetail(detail);
        List<CollectionConfiguration.CollectionDetail> child = detail.getChildList();
        if (CollectionUtils.isNotEmpty(child)) {
            for (CollectionConfiguration.CollectionDetail d : child) {
                CollectionCustomNode nodeIn = convertToNode(d);
                node.add(nodeIn);
            }
        }
        return node;
    }


    private void convertToNode(CollectionCustomNode node, List<CollectionConfiguration.CollectionDetail> collectionDetailList) {
        for (CollectionConfiguration.CollectionDetail c : collectionDetailList) {
            String name = c.getName();
            CollectionCustomNode nodeObject = new CollectionCustomNode(name, c.getType(), c.getParamGroup().getUrl());
            nodeObject.setId(c.getId());
            nodeObject.setDetail(c);
            List<CollectionConfiguration.CollectionDetail> childList = c.getChildList();
            if (CollectionUtils.isNotEmpty(childList)) {
                convertToNode(nodeObject, childList);
            }
            nodeObject.setSearchText("<" + c.getName() + c.getDescription() + c.getParamGroup().getUrl() + ">");
            node.add(nodeObject);
        }
    }

    private void moveData(String sourceParentId, String sourceId, String targetId, Integer position) {
        CollectionConfiguration.CollectionDetail sourceParent = filterById(sourceParentId, rootDetail);
        CollectionConfiguration.CollectionDetail from = filterById(sourceId, rootDetail);
        CollectionConfiguration.CollectionDetail to = filterById(targetId, rootDetail);

        sourceParent.getChildList().remove(from);
        to.getChildList().add(position, from);
    }

    private void load(CollectionCustomNode node) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            ApplicationManager.getApplication().runReadAction(() -> {
                boolean flag = false;
                //定位方法
                CollectionConfiguration collectionConfiguration = FastRequestCollectionComponent.getInstance(myProject).getState();
                assert collectionConfiguration != null;
                CollectionConfiguration.CollectionDetail detail = filterById(node.getId(), collectionConfiguration.getDetail());
                if (detail == null) {
                    return;
                }
                ParamGroupCollection paramGroup = detail.getParamGroup();
                String className = paramGroup.getClassName();
                String methodName = paramGroup.getMethod();
                PsiClass psiClass = null;
                try {
                    psiClass = JavaPsiFacade.getInstance(myProject).findClass(className, GlobalSearchScope.projectScope(myProject));
                } catch (IndexNotReadyException e) {
                    NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Index should be ready first", MessageType.INFO).notify(myProject);
                }
                //used to navigate
                if (psiClass != null) {
                    PsiElement[] psiClassMethodsByName = psiClass.findMethodsByName(methodName, true);
                    if (psiClassMethodsByName.length > 0) {
                        ApplicationManager.getApplication().invokeLater(() -> {
                            PsiNavigateUtil.navigate(psiClassMethodsByName[0]);
                        });
                        flag = true;
                    } else {
                        NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Method not found", MessageType.INFO).notify(myProject);
                    }
                }
                loadAncChangeTab(flag, detail);
            });
        });

//
//        Task.Backgroundable task = new Task.Backgroundable(myProject, "") {
//≤
//            @Override
//            public void run(@NotNull ProgressIndicator indicator) {
//
//            }
//        };
//        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }

    private void loadAncChangeTab(boolean flag, CollectionConfiguration.CollectionDetail detail) {
        //切换tab
        if (flag) {
            //change data
            ApplicationManager.getApplication().invokeLater(() -> {
                ToolWindow fastRequestToolWindow = ToolWindowManager.getInstance(myProject).getToolWindow("Fast Request");
                Content content = fastRequestToolWindow.getContentManager().getContent(0);
                assert content != null;
                fastRequestToolWindow.getContentManager().setSelectedContent(content);

                MessageBus messageBus = myProject.getMessageBus();
                messageBus.connect();
                ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.LOAD_REQUEST);
                configChangeNotifier.loadRequest(detail, myProject.getName());
            });
        }
    }

    private void refreshTable() {
        SwingUtilities.invokeLater(() -> collectionTable.updateUI());
    }

    private enum SearchTypeEnum {
        name,
        url,
        separator_Method,
        get,
        post,
        put,
        delete,
        patch;

        public static SearchTypeEnum fromValue(String name) {
            for (SearchTypeEnum v : values()) {
                if (v.name().equals(name)) {
                    return v;
                }
            }
            return null;
        }
    }

    private final class SearchOptionAction extends ToggleAction implements DumbAware {
        private final SearchTypeEnum myOption;
        private boolean myState;

        private SearchOptionAction(@NotNull SearchTypeEnum option) {
            super(option.name());
            myOption = option;
        }

        @Override
        public boolean isSelected(@NotNull AnActionEvent e) {
            return myState;
        }

        @Override
        public void setSelected(@NotNull AnActionEvent e, boolean state) {
            myState = state;
            mySearchCallback.accept(this);
        }

        @NotNull
        public String getQuery() {
            return StringUtil.decapitalize(myOption.name() + "|");
        }
    }
}
