package io.github.kings1990.plugin.fastrequest.service;

import com.google.common.collect.ImmutableMap;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.OtherUrlGenerator;
import io.github.kings1990.plugin.fastrequest.generator.impl.SpringMethodUrlGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Objects;

public class GeneratorUrlService {
    private Map<Class<? extends PsiElement>, FastUrlGenerator> fastUrlGeneratorMap
            = ImmutableMap.<Class<? extends PsiElement>, FastUrlGenerator>builder()
            .put(PsiClass.class, new OtherUrlGenerator())
            .put(PsiMethod.class, new SpringMethodUrlGenerator())
            .build();

    public String generate(PsiElement psiElement) {
        FastUrlGenerator fastUrlGenerator = null;
        for (Map.Entry<Class<? extends PsiElement>, FastUrlGenerator> entry : fastUrlGeneratorMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(psiElement.getClass())) {
                fastUrlGenerator = entry.getValue();
                break;
            }
        }
        if (Objects.isNull(fastUrlGenerator)) {
            return StringUtils.EMPTY;
        }
        return fastUrlGenerator.generate(psiElement);
    }
}
