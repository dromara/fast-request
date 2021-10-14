package io.github.kings1990.plugin.fastrequest.view.component;

import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.json.JsonLanguage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.ui.LanguageTextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JsonLanguageTextField extends LanguageTextField {


    private Project myProject;

    public LanguageTextField get() {
        return new LanguageTextField(JsonLanguage.INSTANCE, myProject, "", false) {
            EditorEx editor;

            @Override
            public Project getProject() {
                return myProject;
            }

            @Override
            protected @NotNull EditorEx createEditor() {
                editor = super.createEditor();
//                editor.setVerticalScrollbarVisible(true);
//                editor.setHorizontalScrollbarVisible(true);
                editor.setCaretEnabled(true);
                editor.setEmbeddedIntoDialogWrapper(true);
//                editor.getFoldingModel().setFoldingEnabled(true);
                editor.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void documentChanged(@NotNull DocumentEvent event) {
                        ApplicationManager.getApplication().invokeLater(() -> {
                            CodeFoldingManager.getInstance(myProject).updateFoldRegions(editor);
                        }, ModalityState.NON_MODAL);
                    }
                });
                EditorSettings settings = editor.getSettings();
                settings.setGutterIconsShown(true);
                settings.setSmartHome(true);
                settings.setLineNumbersShown(true);
                settings.setIndentGuidesShown(true);
                settings.setUseSoftWraps(true);
                settings.setAutoCodeFoldingEnabled(true);
                settings.setFoldingOutlineShown(true);
                settings.setAllowSingleLogicalLineFolding(true);
                settings.setRightMarginShown(true);
                settings.setCaretRowShown(true);
                settings.setLineMarkerAreaShown(true);
                settings.setDndEnabled(true);
                return editor;
            }


            @Nullable
            @Override
            public EditorEx getEditor() {
                return editor;
            }
        };
    }

    public JsonLanguageTextField() {
    }

    public Project getMyProject() {
        return myProject;
    }

    public void setMyProject(Project myProject) {
        this.myProject = myProject;
    }

    @Override
    public void setText(@Nullable String text) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            super.setText(text);
        });
    }
}
