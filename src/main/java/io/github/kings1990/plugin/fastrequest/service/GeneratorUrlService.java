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
