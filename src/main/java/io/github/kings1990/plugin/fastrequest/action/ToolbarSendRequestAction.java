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

package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.util.ToolWindowUtil;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolbarSendRequestAction extends DumbAwareAction {

    public ToolbarSendRequestAction() {
        super(() -> "Send", PluginIcons.ICON_SEND);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project myProject = e.getData(LangDataKeys.PROJECT);
        if (myProject == null) {
            return;
        }
        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(myProject);
        if (fastRequestToolWindow != null) {
            fastRequestToolWindow.sendRequestEvent(false);
        }
    }

    @Override
    public @Nullable @NlsActions.ActionText String getTemplateText() {
        return "Fast Request Send";
    }


    @Override
    public void update(@NotNull AnActionEvent e) {
        Project myProject = e.getData(LangDataKeys.PROJECT);
        if (myProject == null) {
            return;
        }

        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(myProject);
        if (fastRequestToolWindow != null) {
            e.getPresentation().setEnabled(fastRequestToolWindow.getSendButtonFlag());
        }
    }
}