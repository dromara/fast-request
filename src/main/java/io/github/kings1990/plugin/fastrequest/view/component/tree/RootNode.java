package io.github.kings1990.plugin.fastrequest.view.component.tree;

import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RootNode extends BaseNode<String> {
    public RootNode(String name) {
        super(name);
    }

    @Override
    public @Nullable Icon getIcon(boolean selected) {
        return AllIcons.Actions.ShortcutFilter;
    }
}
