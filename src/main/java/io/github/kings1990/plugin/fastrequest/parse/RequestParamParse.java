package io.github.kings1990.plugin.fastrequest.parse;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.util.StringUtils;
import io.github.kings1990.plugin.fastrequest.util.TypeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestParamParse extends AbstractParamParse {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList) {
        List<DataMapping> customDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();
        List<ParamNameType> requestParamList = paramNameTypeList.stream().filter(q -> q.getParseType() == 2).collect(Collectors.toList());
        LinkedHashMap<String, Object> nameValueMap = new LinkedHashMap<>();
        int randomStringLength = config.getRandomStringLength();
        String randomStringStrategy = config.getRandomStringStrategy();
        String randomStringDelimiter = config.getRandomStringDelimiter();
        for (ParamNameType paramNameType : requestParamList) {
            String type = paramNameType.getType();
            if("javax.servlet.http.HttpServletRequest".equals(type) || "javax.servlet.http.HttpServletResponse".equals(type)){
                continue;
            }
            boolean arrayFlag = type.contains("[]");
            if(arrayFlag){
                type = type.substring(0,type.indexOf("["));
            }
            boolean listFlag = type.contains("List<");
            if(listFlag){
                type = type.substring(type.indexOf("<")+1,type.indexOf(">"));
            }

            String name = paramNameType.getName();
            if ("java.lang.String".equals(type)) {
                if(arrayFlag || listFlag){
                    ParamKeyValue paramKeyValue = new ParamKeyValue(name+"[]", StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name());
                    nameValueMap.put(name,paramKeyValue);
                } else {
                    nameValueMap.put(name, new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name()));
                }
                continue;
            }
            String finalType = type;
            DataMapping dataMapping = customDataMappingList.stream().filter(q -> finalType.contains(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                if (JSONUtil.isJson(value.toString())) {
                    Map<String, Object> parse = JSON.parseObject(value.toString(), Map.class);
                    for (Map.Entry<String, Object> entry : parse.entrySet()) {
                        String k = entry.getKey();
                        Object v = entry.getValue();
                        String calcType = TypeUtil.calcTypeByValue(v);
                        nameValueMap.put(k, new ParamKeyValue(k, v, 2, calcType));
                    }
                }
                continue;
            }
            //默认的数据映射解析参数
            dataMapping = defaultDataMappingList.stream().filter(q -> finalType.equals(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                String calcType = TypeUtil.calcType(type);
                if (arrayFlag || listFlag) {
                    ParamKeyValue paramKeyValue = new ParamKeyValue(name + "[]", value, 2, calcType);
                    nameValueMap.put(name, paramKeyValue);
                } else {
                    nameValueMap.put(name, new ParamKeyValue(name, value, 2, calcType));
                }
                continue;
            }
            //特殊参数处理
            if (dealTypeParams(nameValueMap, paramNameType, name)) continue;

            //json解析
            KV kv = KV.getFields(paramNameType.getPsiClass());
            //    String json = kv.toPrettyJson();
            //   Map parse = JSON.parseObject(json, Map.class);
//            String queryParam = URLUtil.buildQuery(parse, null);
            nameValueMap.put(name, new ParamKeyValue(name, kv, 1, TypeUtil.Type.Object.name()));
        }

        return nameValueMap;
    }

    private boolean dealTypeParams(LinkedHashMap<String, Object> nameValueMap, ParamNameType paramNameType, String name) {
        if ("org.springframework.web.multipart.MultipartFile".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name()));
            return true;
        }
        if ("java.util.Date".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, df.format(new Date()), 2, TypeUtil.Type.String.name()));
            return true;
        }
        if ("java.sql.Date".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, df.format(new Date()), 2, TypeUtil.Type.String.name()));
            return true;
        }
        if ("java.sql.Timestamp".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, System.currentTimeMillis(), 2, TypeUtil.Type.String.name()));
            return true;
        }
        if ("java.time.LocalDate".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, LocalDate.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString(), 2, TypeUtil.Type.String.name()));
            return true;
        }
        if ("java.time.LocalTime".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, LocalTime.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString(), 2, TypeUtil.Type.String.name()));
            return true;
        }
        if ("java.time.LocalDateTime".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, LocalDateTime.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString(), 2, TypeUtil.Type.String.name()));
            return true;
        }

        if ("java.time.YearMonth".equals(paramNameType.getType())) {
            nameValueMap.put(name, new ParamKeyValue(name, YearMonth.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString(), 2, TypeUtil.Type.String.name()));
            return true;
        }

        return false;
    }
}
