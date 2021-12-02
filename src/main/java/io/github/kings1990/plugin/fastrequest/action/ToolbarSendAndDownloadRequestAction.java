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