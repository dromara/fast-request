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
