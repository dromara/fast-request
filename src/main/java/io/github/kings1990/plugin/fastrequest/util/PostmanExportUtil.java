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

package io.github.kings1990.plugin.fastrequest.util;

import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.github.kings1990.plugin.fastrequest.model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PostmanExportUtil {
    static List<String> hostList = new ArrayList<>();
    static LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();
    static List<DataMapping> globalHeaderParamsKeyValueList;

    public static PostmanCollection getPostmanCollection(List<DataMapping> headerParamsKeyValueList, CollectionConfiguration.CollectionDetail rootDetail, String projectName) {
        globalHeaderParamsKeyValueList = headerParamsKeyValueList;
        List<PostmanCollection.Item> item = getItem(rootDetail, new ArrayList<>());
        PostmanCollection postmanCollection = new PostmanCollection();
        postmanCollection.setItem(item);
        PostmanCollection.Info info = new PostmanCollection.Info();
        info.setName("FastRequest(" + projectName + ")");
        postmanCollection.setInfo(info);

        List<PostmanCollection.Variable> variableList = new ArrayList<>();
        for (int i = 0; i < hostList.size(); i++) {
            PostmanCollection.Variable variable = new PostmanCollection.Variable();
            variable.setKey("host" + (i == 0 ? "" : i));
            variable.setValue(hostList.get(i));
            variableList.add(variable);
        }
        headerMap.forEach((k, v) -> {
            PostmanCollection.Variable variable = new PostmanCollection.Variable();
            variable.setKey(k);
            variable.setValue(v);
            variableList.add(variable);
        });

        postmanCollection.setVariable(variableList);
        hostList = new ArrayList<>();
        headerMap = new LinkedHashMap<>();
        globalHeaderParamsKeyValueList = new ArrayList<>();
        return postmanCollection;
    }

    public static List<PostmanCollection.Item> getItem(CollectionConfiguration.CollectionDetail node, List<PostmanCollection.Item> itemList) {
        Integer type = node.getType();
        if (type == 2) {
            parseRequest(node, itemList);
        } else {
            PostmanCollection.Item item = new PostmanCollection.Item();
            item.setName(node.getName());
            List<PostmanCollection.Item> childItem = new ArrayList<>();
            List<CollectionConfiguration.CollectionDetail> childList = node.getChildList();
            for (CollectionConfiguration.CollectionDetail childNode : childList) {
                getItem(childNode, childItem);
            }
            item.setItem(childItem);
            itemList.add(item);
        }
        return itemList;
    }

    private static List<PostmanCollection.Item> parseRequest(CollectionConfiguration.CollectionDetail node, List<PostmanCollection.Item> itemList) {
        PostmanCollection.Item item = new PostmanCollection.Item();
        PostmanCollection.Request request = new PostmanCollection.Request();
        item.setName(node.getName());

        ParamGroupCollection paramGroup = node.getParamGroup();
        List<DataMapping> headerListMapping = node.getHeaderList();

        String urlParamsKeyValueListJson = paramGroup.getUrlParamsKeyValueListJson();
        String urlEncodedKeyValueListJson = paramGroup.getUrlEncodedKeyValueListJson();
        String multipartKeyValueListJson = paramGroup.getMultipartKeyValueListJson();
        String bodyKeyValueListJson = paramGroup.getBodyKeyValueListJson();
        List<ParamKeyValue> urlParamsKeyValueList = JSON.parseObject(urlParamsKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });
        List<ParamKeyValue> urlEncodedKeyValueList = JSON.parseObject(urlEncodedKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });
        List<ParamKeyValue> multipartKeyValueList = JSON.parseObject(multipartKeyValueListJson, new TypeReference<List<ParamKeyValue>>() {
        });

        request.setMethod(paramGroup.getMethodType());
        request.setHeader(convertToHeader(globalHeaderParamsKeyValueList));
        request.setUrl(convertToUrl(node.getDomain(), paramGroup.getUrl(), urlParamsKeyValueList));
        request.setBody(convertToBody(urlEncodedKeyValueList, multipartKeyValueList, bodyKeyValueListJson));
        item.setRequest(request);
        itemList.add(item);
        return itemList;
    }


    private static PostmanCollection.Body convertToBody(List<ParamKeyValue> urlEncodedKeyValueList, List<ParamKeyValue> multipartKeyValueList, String bodyKeyValueListJson) {
        PostmanCollection.Body result = new PostmanCollection.Body();
        if (!bodyKeyValueListJson.isBlank()) {
            //json
            result.setMode("raw");
            result.setRaw(bodyKeyValueListJson);
            result.setOptions(PostmanCollection.options);
        } else if (!urlEncodedKeyValueList.isEmpty()) {
            result.setMode("urlencoded");
            List<PostmanCollection.Urlencoded> urlencodedList = urlEncodedKeyValueList.stream().map(q -> {
                PostmanCollection.Urlencoded urlencoded = new PostmanCollection.Urlencoded();
                urlencoded.setKey(q.getKey());
                urlencoded.setValue(q.getValue().toString());
                urlencoded.setType("text");
                return urlencoded;
            }).collect(Collectors.toList());
            result.setUrlencoded(urlencodedList);
        } else if (!multipartKeyValueList.isEmpty()) {
            result.setMode("formdata");
            List<PostmanCollection.Formdata> formdataList = multipartKeyValueList.stream().map(q -> {
                PostmanCollection.Formdata formdata = new PostmanCollection.Formdata();
                formdata.setKey(q.getKey());
                if (TypeUtil.Type.File.name().equals(q.getType())) {
                    formdata.setSrc(q.getValue().toString());
                    formdata.setType("file");
                } else {
                    formdata.setValue(q.getValue().toString());
                    formdata.setType("text");
                }
                return formdata;
            }).collect(Collectors.toList());
            result.setFormdata(formdataList);
        }
        return result;
    }

    private static PostmanCollection.Url convertToUrl(String domain, String rawUrl, List<ParamKeyValue> urlParamsKeyValueList) {
        PostmanCollection.Url result = new PostmanCollection.Url();
        String completeUrl = domain + rawUrl;

        URL url = URLUtil.url(completeUrl);
//        result.setProtocol(url.getProtocol());
        if (!hostList.contains(domain)) {
            hostList.add(domain);
        }
        int idx = hostList.indexOf(domain);
        String host = idx == 0 ? "{{host}}" : "{{host" + idx + "}}";
        result.setHost(List.of(host));
        result.setPort(null);
        result.setPath(Arrays.asList(url.getPath().split("/")));
        result.setRaw(host + rawUrl);
        List<PostmanCollection.Query> queryList = urlParamsKeyValueList.stream().filter(ParamKeyValue::getEnabled).map(q -> {
            PostmanCollection.Query query = new PostmanCollection.Query();
            query.setKey(q.getKey());
            query.setValue(q.getValue().toString());
            return query;
        }).collect(Collectors.toList());
        result.setQuery(queryList);
        return result;
    }

    private static List<PostmanCollection.Header> convertToHeader(List<DataMapping> headerListMapping) {
        if (headerListMapping.isEmpty()) {
            return new ArrayList<>();
        }
        return headerListMapping.stream().map(q -> {
            PostmanCollection.Header header = new PostmanCollection.Header();
            header.setKey(q.getType());
            header.setValue("{{" + q.getType() + "}}");
            header.setDisabled(!q.getEnabled());
            headerMap.put(q.getType(), q.getValue());
            return header;
        }).collect(Collectors.toList());
    }
}
