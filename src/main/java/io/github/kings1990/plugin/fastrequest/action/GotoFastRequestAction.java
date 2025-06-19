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
