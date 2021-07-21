package io.github.kings1990.plugin.fastrequest.parse;

import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
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
                result.put(name, new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name()));
            }
        }
        return result;
    }
}
