package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;
import io.github.kings1990.plugin.fastrequest.view.FastRequestCollectionToolWindow;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import org.jetbrains.annotations.NotNull;

public class FastRequestToolWindowFactory implements ToolWindowFactory , DumbAware {

    private FastRequestToolWindow window;

    private FastRequestCollectionToolWindow collectionToolWindow;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        window = new FastRequestToolWindow(toolWindow, project);
        collectionToolWindow = new FastRequestCollectionToolWindow(project, toolWindow);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        window.getComponent().add(window.getContent());
        Content content = contentFactory.createContent(window, "Request", true);
        toolWindow.getContentManager().addContent(content);

        Content contentCollection = contentFactory.createContent(collectionToolWindow.getContent(), "APIs", true);
        contentCollection.setIcon(PluginIcons.ICON_CODE);
        contentCollection.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
        toolWindow.getContentManager().addContent(contentCollection);

        //change data
        MessageBus messageBus = project.getMessageBus();
        MessageBusConnection connect = messageBus.connect();
        connect.subscribe(ConfigChangeNotifier.PARAM_CHANGE_TOPIC, new ConfigChangeNotifier() {
            @Override
            public void configChanged(boolean active) {
                window.refresh(false);
            }

            @Override
            public void loadRequest(CollectionConfiguration.CollectionDetail detail) {
            }
        });

        connect.subscribe(ConfigChangeNotifier.ENV_PROJECT_CHANGE_TOPIC,
            new ConfigChangeNotifier() {
                @Override
                public void configChanged(boolean active) {
                    window.changeEnvAndProject();
                }

                @Override
                public void loadRequest(CollectionConfiguration.CollectionDetail detail) {

                }
        });

        connect.subscribe(ConfigChangeNotifier.ADD_REQUEST_TOPIC,
                new ConfigChangeNotifier() {
                    @Override
                    public void configChanged(boolean active) {
                        collectionToolWindow.refresh();
                    }

                    @Override
                    public void loadRequest(CollectionConfiguration.CollectionDetail detail) {
                    }
                });

        connect.subscribe(ConfigChangeNotifier.LOAD_REQUEST,
            new ConfigChangeNotifier() {
                @Override
                public void configChanged(boolean active) {

                }

                @Override
                public void loadRequest(CollectionConfiguration.CollectionDetail detail) {
                    window.refreshByCollection(detail);
                }
        });
    }
}
