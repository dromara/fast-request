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

package io.github.kings1990.plugin.fastrequest.editor;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.toolbar.floating.AbstractFloatingToolbarProvider;
import com.intellij.openapi.editor.toolbar.floating.FloatingToolbarComponent;
import org.jetbrains.annotations.NotNull;

public class FastRequestFloatingToolbarProvider extends AbstractFloatingToolbarProvider {

    public FastRequestFloatingToolbarProvider() {
        super("fastRequest.editor.floatGroup");
    }

    public int getPriority() {
        return 0;
    }


    @Override
    public void register(@NotNull FloatingToolbarComponent toolbar, @NotNull Disposable parentDisposable) {
        super.register(toolbar, parentDisposable);
        toolbar.scheduleShow();
    }

    @Override
    public boolean getAutoHideable() {
        return true;
    }
}
