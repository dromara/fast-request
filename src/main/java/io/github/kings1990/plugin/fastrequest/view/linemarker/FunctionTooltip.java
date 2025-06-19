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