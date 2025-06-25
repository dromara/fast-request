package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.view.inner.AiSettingView;
import org.jetbrains.annotations.NotNull;

public class AiSettingAction extends AnAction {

    public AiSettingAction(){
        super("Setting","Setting", PluginIcons.SETTING);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        AiSettingView aiSettingView = new AiSettingView(e.getProject());
        if(aiSettingView.showAndGet()){
            FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
            config.setAiSetting(aiSettingView.getValue());
        }
    }

}
