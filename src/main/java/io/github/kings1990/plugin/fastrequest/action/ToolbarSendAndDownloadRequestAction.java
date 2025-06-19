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

package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.util.ToolWindowUtil;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import org.jetbrains.annotations.NotNull;

public class ToolbarSendAndDownloadRequestAction extends DumbAwareAction {

    public ToolbarSendAndDownloadRequestAction() {
        super(() -> "Send and Download", PluginIcons.ICON_SEND_DOWNLOAD);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project myProject = e.getData(LangDataKeys.PROJECT);
        if (myProject == null) {
            return;
        }
        FastRequestToolWindow fastRequestToolWindow = ToolWindowUtil.getFastRequestToolWindow(myProject);
        if (fastRequestToolWindow != null) {
            fastRequestToolWindow.sendRequestEvent(true);
        }
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