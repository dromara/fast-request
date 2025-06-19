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

package io.github.kings1990.plugin.fastrequest.service;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.JaxRsGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.SpringMethodUrlGenerator;
import org.apache.commons.lang3.StringUtils;

public class GeneratorUrlService {
    SpringMethodUrlGenerator springMethodUrlGenerator = new SpringMethodUrlGenerator();
    JaxRsGenerator jaxRsGenerator = new JaxRsGenerator();

    public String generate(PsiElement psiElement) {
        FastUrlGenerator fastUrlGenerator;
        if (!(psiElement instanceof PsiMethod)) {
            return StringUtils.EMPTY;
        }
        PsiMethod psiMethod = (PsiMethod) psiElement;
        String jaxPathAnnotation = Constant.JaxRsMappingConfig.PATH.getCode();
        PsiAnnotation annotation = psiMethod.getAnnotation(jaxPathAnnotation);
        if (annotation != null) {
            fastUrlGenerator = jaxRsGenerator;
        } else {
            fastUrlGenerator = springMethodUrlGenerator;
        }
        return fastUrlGenerator.generate(psiElement);
    }
}
