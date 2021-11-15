package io.github.kings1990.plugin.fastrequest.view.component.tree;

import com.intellij.ide.TreeExpander;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;

public class ApiTree extends Tree implements TreeExpander {
    @Override
    public boolean canExpand() {
        DefaultMutableTreeNode baseNode = (DefaultMutableTreeNode) getModel().getRoot();
        return baseNode != null && baseNode.getChildCount() > 0;
    }

    @Override
    public boolean canCollapse() {
        DefaultMutableTreeNode baseNode = (DefaultMutableTreeNode) getModel().getRoot();
        return baseNode != null && baseNode.getChildCount() > 0;
    }

    @Override
    public void expandAll() {
        expandAll(new TreePath(getModel().getRoot()), true);
    }

    @Override
    public void collapseAll() {
        expandAll(new TreePath(getModel().getRoot()), false);
    }

    private void expandAll(@NotNull TreePath parent, boolean expand) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration<?> e = node.children(); e.hasMoreElements(); ) {
                javax.swing.tree.TreeNode n = (javax.swing.tree.TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(path, expand);
            }
        }

        // 展开或收起必须自下而上进行
        if (expand) {
            expandPath(parent);
        } else {
            if (node.isRoot()) {
                return;
            }
            collapsePath(parent);
        }
    }
}
