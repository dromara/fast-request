package io.github.kings1990.plugin.fastrequest.view.component.tree;

import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ModuleNode extends BaseNode<String> {

    public ModuleNode(String moduleName) {
        super(moduleName);
    }

    @Override
    public @Nullable Icon getIcon(boolean selected) {
        return AllIcons.Nodes.Module;
    }
}
