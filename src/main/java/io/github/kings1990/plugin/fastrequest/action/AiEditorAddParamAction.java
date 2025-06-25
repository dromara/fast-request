package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AiEditorAddParamAction extends AnAction {
    
    public AiEditorAddParamAction(){
        super("Add Selection","Add selection", PluginIcons.ICON_JSON);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        WriteCommandAction.runWriteCommandAction(e.getProject(), () -> {
            String text = " ${SELECTION} ";
            editor.getDocument().insertString(editor.getCaretModel().getOffset(), text);
            editor.getCaretModel().moveToOffset(editor.getCaretModel().getOffset() + text.length());
        });
    }
    
    @Override
    public void update(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null && e.getProject() != null) {
            List<String> actionList = editor.getUserData(Constant.KEY_FASTREQUEST_ACTION_LIST);
            e.getPresentation().setEnabledAndVisible(actionList != null && actionList.contains("fastRequest.aiEditorAddParamAction"));
        } else {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
}


