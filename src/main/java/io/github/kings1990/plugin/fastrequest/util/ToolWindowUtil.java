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
