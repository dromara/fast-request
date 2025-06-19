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

package io.github.kings1990.plugin.fastrequest.parse;

import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.util.StringUtils;
import io.github.kings1990.plugin.fastrequest.util.TypeUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PathValueParamParse extends AbstractParamParse {
    @Override
    public LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<DataMapping> customDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();
        List<ParamNameType> pathParamList = paramNameTypeList.stream().filter(q -> q.getParseType() == 1).collect(Collectors.toList());
        int randomStringLength = config.getRandomStringLength();
        String randomStringStrategy = config.getRandomStringStrategy();
        String randomStringDelimiter = config.getRandomStringDelimiter();
        for (ParamNameType paramNameType : pathParamList) {
            String name = paramNameType.getName();
            String type = paramNameType.getType();
            //random String
            if ("java.lang.String".equals(type)) {
                ParamKeyValue value = new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name());
                result.put(name, value);
                continue;
            }
            //pathValueParam 不支持自定义类解析 只能是基础类型
//            DataMapping dataMapping = customDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
//            if (dataMapping != null) {
//                Object value = dataMapping.getValue();
//                result.put(name, value);
//                continue;
//            }
            DataMapping dataMapping = defaultDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                String calcType = TypeUtil.calcType(type);
                result.put(name, new ParamKeyValue(name, value, 2, calcType));
            } else {
                //匹配不到默认匹配string

                PsiClass psiClass = PsiUtil.resolveClassInClassTypeOnly(PsiTypesUtil.getClassType(paramNameType.getPsiClass()).getDeepComponentType());
                boolean isEnum = psiClass != null && psiClass.isEnum();
                if (isEnum) {

                    KV kv = KV.getFields(paramNameType.getPsiClass());
                    Object enumParamKeyValue = kv.values().stream().findFirst().orElse(null);
                    if (enumParamKeyValue != null) {
                        result.put(name, new ParamKeyValue(name, ((ParamKeyValue) enumParamKeyValue).getValue(), 2, TypeUtil.Type.String.name()));
                    }
                    continue;
                }

                result.put(name, new ParamKeyValue(name, StringUtils.randomString(name, randomStringDelimiter, randomStringLength, randomStringStrategy), 2, TypeUtil.Type.String.name()));
            }
        }
        return result;
    }
}
