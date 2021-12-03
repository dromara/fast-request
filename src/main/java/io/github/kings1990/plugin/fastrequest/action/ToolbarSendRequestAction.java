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