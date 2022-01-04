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

package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.SearchEverywhereBaseAction;
import com.intellij.ide.actions.searcheverywhere.SymbolSearchEverywhereContributor;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ShortcutSet;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.registry.Registry;
import io.github.kings1990.plugin.fastrequest.contributor.FastRequestGotoContributor;
import org.jetbrains.annotations.NotNull;

public class GotoFastRequestAction extends SearchEverywhereBaseAction implements DumbAware {
    public GotoFastRequestAction() {
        Presentation templatePresentation = getTemplatePresentation();
        templatePresentation.setIcon(AllIcons.Actions.Find);
        templatePresentation.setText("Search Api");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        boolean dumb = DumbService.isDumb(project);
        if (!dumb || new SymbolSearchEverywhereContributor(e).isDumbAware()) {
            String tabID = Registry.is("search.everywhere.group.contributors.by.type")
                    ? "SearchEverywhere.Project"
                    : FastRequestGotoContributor.class.getSimpleName();
            showInSearchEverywherePopup(tabID, e, true, true);
        }
    }

    @Override
    protected void setShortcutSet(@NotNull ShortcutSet shortcutSet) {
        super.setShortcutSet(shortcutSet);
    }
}
