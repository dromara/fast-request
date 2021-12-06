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

package io.github.kings1990.plugin.fastrequest.view.linemarker;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.content.Content;
import com.intellij.util.messages.MessageBus;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;
import io.github.kings1990.plugin.fastrequest.service.GeneratorUrlService;
import org.jetbrains.annotations.NotNull;

public class FastRequestLineMarkerProvider implements LineMarkerProvider {

    public LineMarkerInfo<PsiElement> getLineMarkerInfo(@NotNull PsiElement element) {

        LineMarkerInfo<PsiElement> lineMarkerInfo;

        if (element instanceof PsiIdentifier && element.getParent() instanceof PsiMethod) {
            if (!judgeMethod(element)) {
                return null;
            }
            PsiMethod methodElement = (PsiMethod) element.getParent();
            lineMarkerInfo = new LineMarkerInfo<>(element, element.getTextRange(), PluginIcons.fastRequest_editor,
                    new FunctionTooltip(methodElement),
                    (e, elt) -> {
                        Project project = elt.getProject();
                        GeneratorUrlService generatorUrlService = ApplicationManager.getApplication().getService(GeneratorUrlService.class);
                        generatorUrlService.generate(methodElement);

                        //打开工具窗口
                        ToolWindow fastRequestToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Fast Request");
                        if (fastRequestToolWindow != null && !fastRequestToolWindow.isActive()) {
                            fastRequestToolWindow.activate(null);
                            Content content = fastRequestToolWindow.getContentManager().getContent(0);
                            assert content != null;
                            fastRequestToolWindow.getContentManager().setSelectedContent(content);
                        }
                        //send message to change param
                        MessageBus messageBus = project.getMessageBus();
                        messageBus.connect();
                        ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.PARAM_CHANGE_TOPIC);
                        configChangeNotifier.configChanged(true, project.getName());
                    },
                    GutterIconRenderer.Alignment.LEFT, () -> "fastRequest");
            lineMarkerInfo.createGutterRenderer();
            return lineMarkerInfo;
        }
        return null;
    }



    private boolean judgeMethod(@NotNull PsiElement psiElement) {
        PsiMethod psiMethod = (PsiMethod) psiElement.getParent();
        Constant.SpringMappingConfig[] mappingConfigArray = Constant.SpringMappingConfig.values();
        PsiAnnotation annotationRequestMapping = null;
        for (Constant.SpringMappingConfig mappingConfig : mappingConfigArray) {
            String code = mappingConfig.getCode();
            annotationRequestMapping = psiMethod.getAnnotation(code);
            if (annotationRequestMapping != null) {
                break;
            }
        }
        if (annotationRequestMapping == null) {
            Constant.JaxRsMappingMethodConfig[] jaxRsMappingConfigArray = Constant.JaxRsMappingMethodConfig.values();
            for (Constant.JaxRsMappingMethodConfig mappingConfig : jaxRsMappingConfigArray) {
                String code = mappingConfig.getCode();
                annotationRequestMapping = psiMethod.getAnnotation(code);
                if (annotationRequestMapping != null) {
                    break;
                }
            }
        }
        return annotationRequestMapping != null;
    }

}
