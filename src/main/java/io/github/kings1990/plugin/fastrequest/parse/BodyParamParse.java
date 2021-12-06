/*
 * Copyright 2021 kings1990
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

package io.github.kings1990.plugin.fastrequest.parse;

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
import java.util.stream.Collectors;

public class BodyParamParse extends AbstractParamParse {
    @Override
    public LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList) {
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
                nameValueMap.put(name, value);
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
                if(arrayFlag || listFlag){
                    ParamKeyValue paramKeyValue = new ParamKeyValue("", value, 2, targetType);
                    ParamKeyValue p = new ParamKeyValue("",Lists.newArrayList(paramKeyValue),2,"Array");
                    nameValueMap.put(name,p);
                } else {
                    nameValueMap.put(name, new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, targetType));
                }
                continue;
            }
            //json解析
            KV kv = KV.getFields(paramNameType.getPsiClass());

            //String json = kv.toPrettyJson();
//            Map parse = JSON.parseObject(json, Map.class);
//            String queryParam = URLUtil.buildQuery(parse, null);
            String key = (String) kv.keySet().stream().findFirst().orElse(null);
            if(key != null){
                Object firstValue = kv.get(key);
                String targetType = TypeUtil.Type.Object.name();
                if(firstValue instanceof ArrayList || arrayFlag || listFlag){
                    targetType = TypeUtil.Type.Array.name();
                }
                nameValueMap.put(name, new ParamKeyValue(name, kv, 2, targetType));
            }
            break;
        }

        return nameValueMap;
    }
}
