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
