package io.github.kings1990.plugin.fastrequest.util;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.config.Constant;

public class FrPsiUtil {

    public static Constant.FrameworkType calcFrameworkType(PsiMethod psiMethod) {
        PsiAnnotation jaxRsAnno = psiMethod.getAnnotation(Constant.JaxRsMappingConfig.PATH.getCode());
        if (jaxRsAnno != null) {
            return Constant.FrameworkType.JAX_RS;
        } else {
            return Constant.FrameworkType.SPRING;
        }
    }
}
