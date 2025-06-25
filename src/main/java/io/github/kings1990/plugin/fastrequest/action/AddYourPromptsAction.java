package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.view.inner.AddYourPromptsView;
import org.jetbrains.annotations.NotNull;

public class AddYourPromptsAction extends AnAction {
    
    public AddYourPromptsAction(){
        super("Add Prompts...","Add prompts", PluginIcons.ADD);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        AddYourPromptsView addYourPromptsView = new AddYourPromptsView(e.getProject());
        addYourPromptsView.show();
    }
    
}


