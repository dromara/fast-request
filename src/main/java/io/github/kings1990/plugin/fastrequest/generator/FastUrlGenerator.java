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

package io.github.kings1990.plugin.fastrequest.generator;

import com.google.common.base.Splitter;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;

import java.util.List;
import java.util.regex.Pattern;

public abstract class FastUrlGenerator {
    public abstract String generate(PsiElement psiElement);

    /**
     * 获取方法的mappingUrl
     * //RequestMapping("/url") GetMapping("url")
     *
     * @param psiMethod psi的方法
     * @return {@link String }
     * @author Kings
     * @date 2021/05/23
     */
    public abstract String getMethodRequestMappingUrl(PsiMethod psiMethod);

    /**
     * 得到类的mappingUrl
     * //@Controller("url") @RestController("url")
     *
     * @param psiMethod psi的方法
     * @return {@link String }
     * @author Kings
     * @date 2021/05/23
     */
    public abstract String getClassRequestMappingUrl(PsiMethod psiMethod);


    /**
     * 获取方法参数列表
     *
     * @param psiMethod psi的方法
     * @return {@link List<String> }
     * @author Kings
     * @date 2021/05/24
     */
    public abstract List<ParamNameType> getMethodUrlParamList(PsiMethod psiMethod);

    public abstract List<ParamNameType> getMethodBodyParamList(PsiMethod psiMethod);


    /**
     * 获取方法描述
     *
     * @param psiMethod psi的方法
     * @return {@link String }
     * @author Kings
     * @date 2021/07/27
     */
    public abstract String getMethodDescription(PsiMethod psiMethod);

    public abstract String getMethodType(PsiMethod psiMethod);

    public boolean judgeIgnore(List<String> ignoreList, String canonicalText) {
        List<String> allTypeList = Splitter.on(Pattern.compile("<|>|[|]")).trimResults().omitEmptyStrings().splitToList(canonicalText);
        return allTypeList.stream().anyMatch(ignoreList::contains);
    }
}
