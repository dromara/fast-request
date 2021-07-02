package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.util.messages.MessageBus;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.service.GeneratorUrlService;
import org.jetbrains.annotations.NotNull;

public class GenerateUrlAction extends AnAction {
    private GeneratorUrlService generatorUrlService = ServiceManager.getService(GeneratorUrlService.class);

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(LangDataKeys.PROJECT);
        if (project == null) {
            return;
        }
        PsiElement psiElement = anActionEvent.getData(LangDataKeys.PSI_ELEMENT);
        if (psiElement == null || psiElement.getNode() == null) {
            return;
        }

        generatorUrlService.generate(psiElement);

        //打开工具窗口
        ToolWindow fastRequestToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Fast Request");
        if(fastRequestToolWindow != null && !fastRequestToolWindow.isActive()){
            fastRequestToolWindow.activate(null);
        }

        //send message to change param
        MessageBus messageBus = project.getMessageBus();
        messageBus.connect();
        ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.PARAM_CHANGE_TOPIC);
        configChangeNotifier.configChanged(true);
    }
}
