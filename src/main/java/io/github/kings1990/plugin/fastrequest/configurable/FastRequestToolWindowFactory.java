/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
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
