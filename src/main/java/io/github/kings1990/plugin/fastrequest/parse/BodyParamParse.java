package io.github.kings1990.plugin.fastrequest.parse;

import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.util.TypeUtil;

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
        for (ParamNameType paramNameType : requestParamList) {
            String type = paramNameType.getType();
            String name = paramNameType.getName();
            DataMapping dataMapping = customDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
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
//            dataMapping = defaultDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
//            if (dataMapping != null) {
//                String value = dataMapping.getValue();
//                nameValueMap.put(name, name + "=" + value);
//                break;
//            }
            //json解析
            KV kv = KV.getFields(paramNameType.getPsiClass());

            String json = kv.toPrettyJson();
//            Map parse = JSON.parseObject(json, Map.class);
//            String queryParam = URLUtil.buildQuery(parse, null);
            nameValueMap.put(name, new ParamKeyValue(name, kv, 2, TypeUtil.Type.Object.name()));
            break;
        }

        return nameValueMap;
    }
}
