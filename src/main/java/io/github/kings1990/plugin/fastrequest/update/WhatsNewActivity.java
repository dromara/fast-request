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

package io.github.kings1990.plugin.fastrequest.update;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.util.RunOnceUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileEditor.impl.HTMLEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.component.tree.RemoveConfig;
import org.jetbrains.annotations.NotNull;

public class WhatsNewActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        String GITHUB_DOC_URL = String.format("%s/guide/whatsnew", Constant.EN_DOC_DOMAIN);
        String GITEE_DOC_URL = String.format("%s/guide/whatsnew", "https://dromara.gitee.io/fast-request");
        IdeaPluginDescriptor plugin = PluginManagerCore.getPlugin(PluginId.getId("io.github.kings1990.FastRequest"));
        if (plugin != null) {
            String lastVersion = plugin.getVersion();
            RunOnceUtil.runOnceForApp(lastVersion, () -> {
                ApplicationManager.getApplication().invokeLater(() -> {
                    if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
                        HTMLEditorProvider.openEditor(project, "最新变化", GITEE_DOC_URL, "<div style=\"padding-top: 1rem; margin-bottom: 0.8rem;\">加载失败!</div> <br/><div><a href=" + GITEE_DOC_URL + " target=\"_blank\" style=\"font-size: 2rem\">浏览器打开</a></div>");
                    } else {
                        HTMLEditorProvider.openEditor(project, "What's New", GITHUB_DOC_URL, "<div style=\"padding-top: 1rem; margin-bottom: 0.8rem;\">Failed to load!</div> <br/><div><a href=" + GITHUB_DOC_URL + " target=\"_blank\" style=\"font-size: 2rem\">Open in browser</a></div>");
                    }
                });
            });
        }
        RemoveConfig.removeConfig(project);


    }
}
