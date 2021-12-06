/*
 * Copyright 2021 kings1990
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
