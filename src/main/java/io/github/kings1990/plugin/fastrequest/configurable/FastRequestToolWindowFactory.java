package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import org.jetbrains.annotations.NotNull;

public class FastRequestToolWindowFactory implements ToolWindowFactory {

    private FastRequestToolWindow window;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        window = new FastRequestToolWindow(toolWindow);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //displayName="" let content in first tab
        Content content = contentFactory.createContent(window.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);

        //change data
        MessageBus messageBus = project.getMessageBus();
        MessageBusConnection connect = messageBus.connect();
        connect.subscribe(ConfigChangeNotifier.PARAM_CHANGE_TOPIC, active -> {
            if (active) {
                window.refresh();
            }
        });
        connect.subscribe(ConfigChangeNotifier.ENV_PROJECT_CHANGE_TOPIC, active -> {
            if (active) {
                window.changeEnvAndProject();
            }
        });
    }
}
