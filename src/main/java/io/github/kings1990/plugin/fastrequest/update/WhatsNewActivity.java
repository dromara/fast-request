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


    }
}
