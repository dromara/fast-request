/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
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
