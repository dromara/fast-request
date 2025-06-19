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

    @Override
    public String getMethodType(PsiMethod psiMethod) {
        return null;
    }
}
