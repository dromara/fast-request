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

package io.github.kings1990.plugin.fastrequest.contributor;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiUtilCore;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.JaxRsGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.SpringMethodUrlGenerator;
import io.github.kings1990.plugin.fastrequest.util.FrPsiUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class RequestMappingByNameContributor implements ChooseByNameContributor {
    SpringMethodUrlGenerator springMethodUrlGenerator = ApplicationManager.getApplication().getService(SpringMethodUrlGenerator.class);
    JaxRsGenerator jaxRsGenerator = ApplicationManager.getApplication().getService(JaxRsGenerator.class);

    abstract List<PsiAnnotation> getAnnotationSearchers(String annotationName, Project project);
    private List<RequestMappingItem> navigationItems = new ArrayList<>();
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        navigationItems = Constant.SUPPORTED_ANNOTATIONS.stream().flatMap(annotation -> findRequestMappingItems(project, annotation).stream()).collect(Collectors.toList());
        String[] strings = navigationItems.stream()
                .map(RequestMappingItem::getName).distinct().toArray(String[]::new);
        return strings;
    }




    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        RequestMappingItem[] requestMappingItems = navigationItems.stream().filter(q -> q.getName().equals(name)).toArray(RequestMappingItem[]::new);
        return requestMappingItems;
    }

    private List<RequestMappingItem> findRequestMappingItems(Project project, String annotationName) {
        List<PsiAnnotation> annotationSearchers = getAnnotationSearchers(annotationName, project);
        List<RequestMappingItem> collect = annotationSearchers.stream().filter(q -> fetchAnnotatedPsiElement(q) instanceof PsiMethod)
                .map(annotation -> mapItems(annotation))
                .collect(Collectors.toList());
        return collect;

    }



    private RequestMappingItem mapItems(PsiAnnotation psiAnnotation){
        PsiMethod method = (PsiMethod) fetchAnnotatedPsiElement(psiAnnotation);
        Constant.FrameworkType frameworkType = FrPsiUtil.calcFrameworkType(method);
        FastUrlGenerator generator;
        if (frameworkType.equals(Constant.FrameworkType.SPRING)) {
            generator = springMethodUrlGenerator;
        } else {
            generator = jaxRsGenerator;
        }
        String methodUrl = generator.getMethodRequestMappingUrl(method);
        String classUrl = generator.getClassRequestMappingUrl(method);
        String originUrl = classUrl + "/" + methodUrl;
        originUrl = (originUrl.startsWith("/") ? "" : "/") + originUrl.replace("//", "/");
        String methodType = generator.getMethodType(method);
        return new RequestMappingItem(method,originUrl,methodType);
    }


    private PsiElement fetchAnnotatedPsiElement(PsiElement psiElement) {
        PsiElement parent = psiElement.getParent();
        parent = parent == null? PsiUtilCore.NULL_PSI_ELEMENT : parent;
        if (parent instanceof PsiMethod || parent instanceof PsiClass) return parent;
        return fetchAnnotatedPsiElement(parent);
    }
}
