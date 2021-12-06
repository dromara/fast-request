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
