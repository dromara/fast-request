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

package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;
import io.github.kings1990.plugin.fastrequest.view.AllApisNavToolWindow;
import io.github.kings1990.plugin.fastrequest.view.FastRequestCollectionToolWindow;
import io.github.kings1990.plugin.fastrequest.view.FastRequestToolWindow;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FastRequestToolWindowFactory implements ToolWindowFactory, DumbAware {

    private FastRequestToolWindow window;

    private FastRequestCollectionToolWindow collectionToolWindow;

    private AllApisNavToolWindow allApisNavToolWindow;

    private final Map<String, FastRequestToolWindow> windowMap = new ConcurrentHashMap<>();
    private final Map<String, FastRequestCollectionToolWindow> apiWindowMap = new ConcurrentHashMap<>();
    private final Map<String, AllApisNavToolWindow> allApisNavToolWindowMap = new ConcurrentHashMap<>();

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        window = new FastRequestToolWindow(toolWindow, project);
        collectionToolWindow = new FastRequestCollectionToolWindow(project, toolWindow);
        allApisNavToolWindow = new AllApisNavToolWindow(project, toolWindow);
        windowMap.put(project.getName(), window);
        apiWindowMap.put(project.getName(), collectionToolWindow);
        allApisNavToolWindowMap.put(project.getName(), allApisNavToolWindow);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        window.getComponent().add(window.getContent());
        Content content = contentFactory.createContent(window, "Request", true);
        content.setIcon(AllIcons.General.RunWithCoverage);
        content.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
        toolWindow.getContentManager().addContent(content);

        Content contentCollection = contentFactory.createContent(collectionToolWindow.getContent(), "APIs", true);
        contentCollection.setIcon(AllIcons.Nodes.PpLib);
        contentCollection.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
        toolWindow.getContentManager().addContent(contentCollection);

        allApisNavToolWindow.getComponent().add(allApisNavToolWindow.getContent());
        Content allApis = contentFactory.createContent(allApisNavToolWindow, "API Navigate", true);
        allApis.setIcon(AllIcons.Ide.LocalScopeAction);
        allApis.putUserData(ToolWindow.SHOW_CONTENT_ICON, Boolean.TRUE);
        toolWindow.getContentManager().addContent(allApis);

        //change data
        MessageBus messageBus = project.getMessageBus();
        MessageBusConnection connect = messageBus.connect();
        connect.subscribe(ConfigChangeNotifier.PARAM_CHANGE_TOPIC, new ConfigChangeNotifier() {
            @Override
            public void configChanged(boolean active, String projectName) {
                windowMap.get(projectName).refresh(false);
            }
        });

        connect.subscribe(ConfigChangeNotifier.ENV_PROJECT_CHANGE_TOPIC,
                new ConfigChangeNotifier() {
                    @Override
                    public void configChanged(boolean active, String projectName) {
                        //有可能在全局配置下点击,
                        FastRequestToolWindow window = windowMap.get(projectName);
                        if (window != null) {
                            window.changeEnvAndProject();
                        }
                    }
                });

        connect.subscribe(ConfigChangeNotifier.ADD_REQUEST_TOPIC,
                new ConfigChangeNotifier() {
                    @Override
                    public void configChanged(boolean active, String projectName) {
                        apiWindowMap.get(projectName).refresh();
//                        collectionToolWindow.refresh();
                    }
                });

        connect.subscribe(ConfigChangeNotifier.LOAD_REQUEST,
                new ConfigChangeNotifier() {
                    @Override
                    public void loadRequest(CollectionConfiguration.CollectionDetail detail, String projectName) {
                        windowMap.get(projectName).refreshByCollection(detail);
//                    window.refreshByCollection(detail);
                    }
                });

    }
}
