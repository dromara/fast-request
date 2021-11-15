package io.github.kings1990.plugin.fastrequest.view.component.tree;

import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PackageNode extends BaseNode<String> {
    public PackageNode(String packageName) {
        super(packageName);
    }

    @Override
    public @Nullable Icon getIcon(boolean selected) {
        return AllIcons.Nodes.Package;
    }
}
