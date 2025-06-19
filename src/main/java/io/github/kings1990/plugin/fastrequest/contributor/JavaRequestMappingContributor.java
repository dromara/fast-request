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

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.impl.java.stubs.index.JavaAnnotationIndex;
import com.intellij.psi.search.GlobalSearchScope;

import java.util.ArrayList;
import java.util.List;

public class JavaRequestMappingContributor extends RequestMappingByNameContributor{


    @Override
    List<PsiAnnotation> getAnnotationSearchers(String annotationName, Project project) {
        return new ArrayList<>(JavaAnnotationIndex
                .getInstance()
                .get(annotationName, project, GlobalSearchScope.projectScope(project)));
    }



}
