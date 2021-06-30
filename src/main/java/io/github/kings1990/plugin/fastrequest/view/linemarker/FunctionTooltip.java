package io.github.kings1990.plugin.fastrequest.view.linemarker;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.util.Function;

public class FunctionTooltip implements Function<PsiElement,String> {

    String msg = "generate url and params for ";
    PsiElement element;

    public FunctionTooltip() {
    }

    public FunctionTooltip(PsiElement element) {
        this.element = element;
    }


    @Override
    public String fun(PsiElement psiElement) {
        PsiMethod psiMethod = (PsiMethod) psiElement.getParent();
        return msg + psiMethod.getName();
    }
}