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
