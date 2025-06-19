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

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.siyeh.ig.psiutils.CollectionUtils;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.util.StringUtils;
import io.github.kings1990.plugin.fastrequest.util.TypeUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BodyParamParse extends AbstractParamParse {
    @Override
    public LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList) {
        //每次解析重置解析的类
        KV.reset();

        List<DataMapping> customDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();

        List<ParamNameType> requestParamList = paramNameTypeList.stream().filter(q -> q.getParseType() == 3).collect(Collectors.toList());
        LinkedHashMap<String, Object> nameValueMap = new LinkedHashMap<>();
        int randomStringLength = config.getRandomStringLength();
        String randomStringStrategy = config.getRandomStringStrategy();
        String randomStringDelimiter = config.getRandomStringDelimiter();
        for (ParamNameType paramNameType : requestParamList) {
            String type = paramNameType.getType();
            boolean arrayFlag = type.contains("[]");
            if(arrayFlag){
                type = type.substring(0, type.indexOf("["));
            }
            boolean listFlag = CollectionUtils.isCollectionClassOrInterface(paramNameType.getPsiType());
            if(listFlag){
                type = type.substring(type.indexOf("<") + 1, type.indexOf(">"));
            }
            String name = paramNameType.getName();
            if ("java.lang.String".equals(type)) {
                if(arrayFlag || listFlag){
                    ParamKeyValue paramKeyValue = new ParamKeyValue("", StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name());
                    ParamKeyValue p = new ParamKeyValue("",Lists.newArrayList(paramKeyValue),2,"Array");
                    nameValueMap.put(name,p);
                } else {
                    nameValueMap.put(name, new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name()));
                }
                continue;
            }

            String finalType = type;
            DataMapping dataMapping = customDataMappingList.stream().filter(q -> finalType.equals(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                LinkedHashMap<String, Object> parse = JSON.parseObject(value.toString(), LinkedHashMap.class);
                KV kv = KV.create();
                for (Map.Entry<String, Object> entry : parse.entrySet()) {
                    kv.put(entry.getKey(),entry.getValue());
                    ParamKeyValue p = new ParamKeyValue(name, kv, 2, TypeUtil.calcTypeByValue(entry.getValue()));
                    nameValueMap.put(name, p);
                }
                continue;
//                if(Constant.SpringParamTypeConfig.JSON.getCode().equals(config.getParamGroup().getPostType())){
//                    nameValueMap.put(name, value);
//                } else {
//                    StringBuilder parseOut = new StringBuilder();
//                    if (JsonUtil.isJSON2(value)) {
//                        Map<String, Object> parse = JSON.parseObject(value, Map.class);
//                        for (Map.Entry<String, Object> entry : parse.entrySet()) {
//                            String k = entry.getKey();
//                            String v = entry.getValue().toString();
//                            parseOut.append(k).append("=").append(v).append("&");
//                        }
//                    } else {
//                        break;
//                    }
//                    String parseOutStr = parseOut.toString();
//                    if (parseOutStr.endsWith("&")) {
//                        parseOutStr = parseOutStr.substring(0, parseOutStr.length() - 1);
//                    }
//                    nameValueMap.put(name, parseOutStr);
//                    break;
//                }
            }
            //默认的数据映射解析参数
            dataMapping = defaultDataMappingList.stream().filter(q -> finalType.equals(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                String defaultType = dataMapping.getType();
                String targetType =  ("boolean".equals(defaultType) ||"java.lang.Boolean".equals(defaultType))?TypeUtil.Type.Boolean.name():TypeUtil.Type.Number.name();
                if (arrayFlag || listFlag) {
                    ParamKeyValue paramKeyValue = new ParamKeyValue("", value, 2, targetType);
                    ParamKeyValue p = new ParamKeyValue("", Lists.newArrayList(paramKeyValue), 2, "Array");
                    nameValueMap.put(name, p);
                } else {
                    nameValueMap.put(name, new ParamKeyValue(name, StringUtils.randomString(name, randomStringDelimiter, randomStringLength, randomStringStrategy), 2, targetType));
                }
                continue;
            }
            //json解析
            KV kv = KV.getFields(paramNameType.getPsiClass());

            //String json = kv.toPrettyJson();
//            Map parse = JSON.parseObject(json, Map.class);
//            String queryParam = URLUtil.buildQuery(parse, null);
            String key = (String) kv.keySet().stream().findFirst().orElse(null);
            if (key != null) {
                Object firstValue = kv.get(key);
                String targetType = TypeUtil.Type.Object.name();
                if (firstValue instanceof ArrayList || arrayFlag || listFlag) {
                    targetType = TypeUtil.Type.Array.name();
                }
                nameValueMap.put(name, new ParamKeyValue(name, kv, 2, targetType));
            }
            break;
        }

        return nameValueMap;
    }
}
