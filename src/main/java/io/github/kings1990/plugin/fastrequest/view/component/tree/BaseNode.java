package io.github.kings1990.plugin.fastrequest.view.component.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public abstract class BaseNode<T> extends DefaultMutableTreeNode {
    @NotNull
    private T source;

    public BaseNode(@NotNull T source) {
        super(source);
        setUserObject(source);
        this.source = source;
    }

    @Nullable
    public Icon getIcon(boolean selected) {
        return null;
    }

    @Override
    public String toString() {
        return source.toString();
    }

    public @NotNull T getSource() {
        return source;
    }

    public void setSource(@NotNull T source) {
        this.source = source;
    }

}
