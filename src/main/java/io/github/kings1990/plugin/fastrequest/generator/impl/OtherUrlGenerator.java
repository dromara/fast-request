package io.github.kings1990.plugin.fastrequest.generator.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class OtherUrlGenerator extends FastUrlGenerator {

    @Override
    public String generate(PsiElement psiElement) {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        String domain = config.getDomain();
        if (!(psiElement instanceof PsiMethod)) {
            return StringUtils.EMPTY;
        }
        PsiMethod psiMethod = (PsiMethod) psiElement;


        return null;
    }

    @Override
    public String getMethodRequestMappingUrl(PsiMethod psiMethod) {
        return null;
    }

    @Override
    public String getClassRequestMappingUrl(PsiMethod psiMethod) {
        return null;
    }

    @Override
    public List<ParamNameType> getMethodUrlParamList(PsiMethod psiMethod) {
        return null;
    }

    @Override
    public List<ParamNameType> getMethodBodyParamList(PsiMethod psiMethod) {
        return null;
    }

    @Override
    public String getMethodDescription(PsiMethod psiMethod) {
        return null;
    }
}
