package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.impl.ActionButton;
import com.intellij.openapi.editor.Editor;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.AiPromptConfig;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AiPromptAction extends ActionGroup implements Toggleable {
    public AiPromptAction() {
        super("AI", "AI", PluginIcons.ICON_OPEN_AI);
        setPopup(true);
    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        List<AiPromptConfig> aiPromptList = config.getAiPromptList();
        for (int i = 0; i < aiPromptList.size(); i++) {
            AiPromptConfig aiPromptConfig = aiPromptList.get(i);
            defaultActionGroup.add(new AiPromptLineAction(aiPromptConfig.getKey(), aiPromptConfig.getKey(), i));
        }
        defaultActionGroup.add(ActionManager.getInstance().getAction("fastRequest.addYourPromptsAction"));
        defaultActionGroup.add(ActionManager.getInstance().getAction("fastRequest.aiSettingAction"));
        return defaultActionGroup.getChildren(e);
    }


    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().putClientProperty(ActionButton.HIDE_DROPDOWN_ICON, true);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor != null && e.getProject() != null) {
            List<String> actionList = editor.getUserData(Constant.KEY_FASTREQUEST_ACTION_LIST);
            e.getPresentation().setEnabledAndVisible(actionList != null && actionList.contains("fastRequest.aiPromptAction"));
        } else {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
    
}
