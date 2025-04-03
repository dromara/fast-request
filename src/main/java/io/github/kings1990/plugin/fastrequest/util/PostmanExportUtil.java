/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
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
