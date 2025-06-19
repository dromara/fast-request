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
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import org.jetbrains.annotations.NotNull;

public class FormatAction extends AnAction {

    public FormatAction() {
        super("Format", "Format", AllIcons.Diff.MagicResolve);
    }


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        if (psiFile != null && !editor.getDocument().getText().isBlank()) {
            WriteCommandAction.runWriteCommandAction(
                    project,
                    () -> {
                        CodeStyleManager.getInstance(project).reformat(psiFile);
                    }
            );
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        Presentation presentation = e.getPresentation();
        if (presentation.isEnabled()) {
            Editor editor = e.getData(CommonDataKeys.EDITOR);
            if (editor != null && e.getProject() != null) {
                Integer fastRequestFlag = editor.getUserData(Constant.KEY_FASTREQUEST);
                VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
                e.getPresentation().setEnabledAndVisible(fastRequestFlag != null && file != null && file.getName().startsWith("Dummy."));
            } else {
                e.getPresentation().setEnabledAndVisible(false);
            }
        }
    }
}
