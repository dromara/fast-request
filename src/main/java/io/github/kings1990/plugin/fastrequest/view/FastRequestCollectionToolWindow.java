package io.github.kings1990.plugin.fastrequest.view;

import com.intellij.ide.HelpTooltip;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.GotItTooltip;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.content.Content;
import com.intellij.ui.dualView.TreeTableView;
import com.intellij.ui.treeStructure.treetable.ListTreeTableModelOnColumns;
import com.intellij.ui.treeStructure.treetable.TreeColumnInfo;
import com.intellij.ui.treeStructure.treetable.TreeTableTree;
import com.intellij.util.PsiNavigateUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.ColumnInfo;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.FastRequestCollectionComponent;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.util.SwingUtil;
import io.github.kings1990.plugin.fastrequest.view.component.CollectionNodeSelection;
import io.github.kings1990.plugin.fastrequest.view.model.CollectionCustomNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FastRequestCollectionToolWindow extends SimpleToolWindowPanel {

    private Project myProject;
    private JPanel panel;
    private JTextField searchTextField;
    private JPanel collectionPanel;
    private JLabel urlNameLabel;
    private JLabel helpLabel;
    private TreeTableView collectionTable;
    private CollectionConfiguration.CollectionDetail rootDetail;


    public FastRequestCollectionToolWindow(Project project, ToolWindow toolWindow) {
        super(true, false);
        this.setContent(panel);
        this.myProject = project;
        urlNameLabel.setIcon(PluginIcons.ICON_FILTER);

        helpLabel.setIcon(PluginIcons.ICON_CONTEXT_HELP);
        new HelpTooltip().setDescription(MyResourceBundleUtil.getKey("CollectionSearchHelp")).installOn(helpLabel);

        refresh();

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                filterRequest();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                filterRequest();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                filterRequest();
            }
        });

    }

    private void createUIComponents() {
        renderingCollectionTablePanel();
    }

    public void refresh() {
        CollectionConfiguration config = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert config != null;
        rootDetail = config.getDetail();
        CollectionCustomNode root = convertToNode(rootDetail);
        ((DefaultTreeModel)collectionTable.getTableModel()).setRoot(root);
        TreeTableTree tree = collectionTable.getTree();
        SwingUtil.expandAll(tree,new TreePath(root),true);

        TableColumnModel columnModel = collectionTable.getColumnModel();

        columnModel.getColumn(2).setMaxWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
    }



    private void filterRequest(){
        CollectionConfiguration config = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert config != null;
        CollectionConfiguration.CollectionDetail detail = config.getDetail();
        String search = searchTextField.getText();
        CollectionCustomNode root = (CollectionCustomNode) collectionTable.getTableModel().getRoot();
        if(StringUtils.isBlank(search)){
            root = convertToNode(rootDetail);
            ((DefaultTreeModel)collectionTable.getTableModel()).setRoot(root);
        } else {
            CollectionCustomNode node = new CollectionCustomNode("0","Root",1);
            convertToNode(node,rootDetail.getChildList());
            filterNode(node,search);
            ((DefaultTreeModel)collectionTable.getTableModel()).setRoot(node);
            SwingUtil.expandAll(collectionTable.getTree(),new TreePath(node),true);
        }
        SwingUtil.expandAll(collectionTable.getTree(),new TreePath(root),true);
    }

    private boolean filterNode(CollectionCustomNode node,String search){
        if(node.isRoot()){
            ArrayList<CollectionCustomNode> nodeList = (ArrayList<CollectionCustomNode>) IteratorUtils.toList(node.children().asIterator());
            for (CollectionCustomNode n : nodeList) {
                filterNode(n,search);
            }
        } else {
            if(node.getChildCount() == 0){
                if(!node.getSearchText().toLowerCase().contains(search.toLowerCase())){
                    node.removeFromParent();
                    return true;
                } else {
                    return false;
                }
            } else {
                ArrayList<CollectionCustomNode> nodeList = (ArrayList<CollectionCustomNode>) IteratorUtils.toList(node.children().asIterator());
                boolean removeGroup = true;
                for (CollectionCustomNode n : nodeList) {
                    removeGroup &= filterNode(n,search);
                }
                if(removeGroup){
                    node.removeFromParent();
                }
            }
        }
        return false;
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

    private CollectionConfiguration.CollectionDetail filterById(String id,List<CollectionConfiguration.CollectionDetail> collectionDetailList){
        for (CollectionConfiguration.CollectionDetail detail : collectionDetailList) {
            if(detail.getId().equals(id) ){
                return detail;
            }
            List<CollectionConfiguration.CollectionDetail> childList = detail.getChildList();
            if(!childList.isEmpty()){
                CollectionConfiguration.CollectionDetail detail1 = filterById(id,childList);
                if(detail1 != null){
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
            return !"0".equals(root.getId()) && !"1".equals(root.getId()) ;
        });
        toolbarDecorator.setAddActionName("Add Group").setAddAction(e->{
            int selectedRow = collectionTable.getSelectedRow();
            CollectionCustomNode root = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(0);
            String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            CollectionCustomNode addNode = null;
            CollectionConfiguration.CollectionDetail detail = null;
            if (selectedRow == -1 || selectedRow == 0) {
                addNode = new CollectionCustomNode(id,"Group "+root.getChildCount() , 1);
                detail = new CollectionConfiguration.CollectionDetail(id,"Group "+ root.getChildCount(), 1);
                root.insert(addNode, 1);
                rootDetail.getChildList().add(detail);
            } else {
                CollectionCustomNode node = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(selectedRow);
                if(node.getType() == 1){
                    String idGroup = node.getId();
                    CollectionConfiguration.CollectionDetail detailGroup = filterById(idGroup,rootDetail);
                    if(detailGroup != null){
                        List<CollectionConfiguration.CollectionDetail> childList = detailGroup.getChildList();
                        long count = childList.stream().filter(q -> q.getType() == 1).count();
                        addNode = new CollectionCustomNode(id,"Group "+ (count + 1), 1);
                        detail = new CollectionConfiguration.CollectionDetail(id,"Group "+ (count + 1), 1);
                        childList.add(detail);
                        detailGroup.setChildList(childList);
                        node.insert(addNode,0);
                    }
                } else {

                    CollectionCustomNode parent = (CollectionCustomNode) node.getParent();
                    String idParent = parent.getId();
                    CollectionConfiguration.CollectionDetail detailGroup = filterById(idParent,rootDetail);
                    if(detailGroup != null){
                        List<CollectionConfiguration.CollectionDetail> childList = detailGroup.getChildList();
                        long count = childList.stream().filter(q -> q.getType() == 1).count();
                        addNode = new CollectionCustomNode(id,"Group "+ (count + 1), 1);
                        detail = new CollectionConfiguration.CollectionDetail(id,"Group "+ (count + 1), 1);
                        childList.add(detail);
                        detailGroup.setChildList(childList);
                        parent.insert(addNode, 0);
                    }
                }
            }
            int row =selectedRow == -1?0:selectedRow;
            collectionTable.setRowSelectionInterval(row,row);
            refreshTable();

        });
        toolbarDecorator.setRemoveAction(e->{
            CollectionCustomNode root = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(0);
            int selectedRow = collectionTable.getSelectedRow();
            CollectionCustomNode node = (CollectionCustomNode) ((ListTreeTableModelOnColumns) collectionTable.getTableModel()).getRowValue(selectedRow);
            CollectionCustomNode parent = (CollectionCustomNode) node.getParent();
            parent.remove(node);
            collectionTable.setRowSelectionInterval(selectedRow-1,selectedRow-1);
            refreshTable();
            CollectionConfiguration.CollectionDetail parentDetail = filterById(parent.getId(),rootDetail);
            parentDetail.getChildList().removeIf(q -> q.getId().equals(node.getId()));
        });
        collectionPanel = toolbarDecorator.createPanel();
    }

    private TreeTableView createCollectionTable() {
        //初始化为空
        CollectionCustomNode root = new CollectionCustomNode("0","Root", 1);
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
                } else if(column == 1){
                    return node.getUrl();
                } else {
                    return null;
                }
            }

            @Override
            public @NotNull Component prepareRenderer(@NotNull TableCellRenderer renderer, int row, int column) {
                ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                if(node.getType() != 1 && column == 2){
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
                if(column == 0){
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                    String name = (String) getValueAt(row, column);
                    return new DefaultCellEditor(new JTextField(name));
                } else if(column == 2){
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                    if(node.getType() != 1){
                        return new ButtonEditor(new JCheckBox());
                    }

                }
                return super.getCellEditor(row, column);
            }

            @Override
            public void setValueAt(Object v, int row, int column) {
                if(column == 0){
                    ListTreeTableModelOnColumns myModel = (ListTreeTableModelOnColumns) getTableModel();
                    CollectionCustomNode node = (CollectionCustomNode) myModel.getRowValue(row);
                    CollectionConfiguration.CollectionDetail detail = filterById(node.getId(), rootDetail);
                    if(detail != null){
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
        return table;
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {

        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(PluginIcons.ICON_NAVIGATE);
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
            jButton.setIcon(PluginIcons.ICON_NAVIGATE);
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
            int offset = row == -1?0:dl.getRow()-row;
            if(row == -1){
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
                if(toAdd.getType() == 1){
                    old.removeFromParent();
                    targetId = toAdd.getId();
                    toAdd.insert(add,0);
                } else {
                    CollectionCustomNode parent = (CollectionCustomNode)toAdd.getParent();
                    position = parent.getIndex(toAdd);

                    if(sourceParentId.equals(parent.getId())){
                        if(position == oldIndex){
                            return false;
                        } else if(position > oldIndex){
                            old.removeFromParent();
                        } else {
                            old.removeFromParent();
                            position += offset;
                        }
                    } else {
                        old.removeFromParent();
                        position += offset;
                    }

                    parent.insert(add,position);
                    targetId = parent.getId();
                }
                TreeTableTree tree = collectionTable.getTree();
                for (TreeNode treeNode : add.getPath()) {
                    tree.expandPath(new TreePath(((CollectionCustomNode) treeNode).getPath()));
                }
                refreshTable();
                moveData(sourceParentId,add.getId(),targetId,position);
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
        }
    }

    private CollectionCustomNode convertToNode(CollectionConfiguration.CollectionDetail detail) {
        String name = detail.getName();
        CollectionCustomNode node = new CollectionCustomNode(name, detail.getType(), detail.getParamGroup().getUrl());
        node.setId(detail.getId());
        node.setSearchText("<" + detail.getName() + detail.getDescription() + detail.getParamGroup().getUrl() + ">");
        node.setDetail(detail);
        List<CollectionConfiguration.CollectionDetail> child = detail.getChildList();
        if(CollectionUtils.isNotEmpty(child)){
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
            if(CollectionUtils.isNotEmpty(childList)){
                convertToNode(nodeObject,childList);
            }
            nodeObject.setSearchText("<" + c.getName() + c.getDescription() + c.getParamGroup().getUrl() + ">");
            node.add(nodeObject);
        }
    }

    private void moveData(String sourceParentId,String sourceId,String targetId,Integer position){
        CollectionConfiguration.CollectionDetail sourceParent = filterById(sourceParentId, rootDetail);
        CollectionConfiguration.CollectionDetail from = filterById(sourceId, rootDetail);
        CollectionConfiguration.CollectionDetail to = filterById(targetId, rootDetail);

        sourceParent.getChildList().remove(from);
        to.getChildList().add(position,from);
    }

    private void load(CollectionCustomNode node){
        boolean flag = false;
        //定位方法
        CollectionConfiguration collectionConfiguration = FastRequestCollectionComponent.getInstance(myProject).getState();
        assert collectionConfiguration != null;
        CollectionConfiguration.CollectionDetail detail = filterById(node.getId(), collectionConfiguration.getDetail());
        if(detail == null){
            return;
        }
        String className = detail.getParamGroup().getClassName();
        String methodName = detail.getParamGroup().getMethod();
        PsiClass psiClass = JavaPsiFacade.getInstance(myProject).findClass(className, GlobalSearchScope.projectScope(myProject));
        if(psiClass != null){
            PsiElement[] findUserPages = psiClass.findMethodsByName(methodName,true);
            if(findUserPages.length > 0){
                PsiNavigateUtil.navigate(findUserPages[0]);
                flag = true;
            }
        }

        //切换tab
        if(flag){
            //change data
            ToolWindow fastRequestToolWindow = ToolWindowManager.getInstance(myProject).getToolWindow("Fast Request");
            Content content = fastRequestToolWindow.getContentManager().getContent(0);
            assert content != null;
            fastRequestToolWindow.getContentManager().setSelectedContent(content);

            MessageBus messageBus = myProject.getMessageBus();
            messageBus.connect();
            ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.LOAD_REQUEST);
            configChangeNotifier.loadRequest(detail);
        }
    }

    private void refreshTable(){
        SwingUtilities.invokeLater(() -> collectionTable.updateUI());
    }

}
