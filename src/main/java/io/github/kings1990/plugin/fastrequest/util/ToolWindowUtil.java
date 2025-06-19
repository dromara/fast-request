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

package io.github.kings1990.plugin.fastrequest.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;

public class ToolWindowUtil {
    public static FastRequestToolWindow getFastRequestToolWindow(Project myProject) {
        final ToolWindow toolWindow = ToolWindowManager.getInstance(myProject).getToolWindow("Fast Request");
        if (toolWindow != null) {
            final Content content = toolWindow.getContentManager().getContent(0);
            if (content != null) {
                return (FastRequestToolWindow) content.getComponent();
            }
        }
        return null;
    }
}
