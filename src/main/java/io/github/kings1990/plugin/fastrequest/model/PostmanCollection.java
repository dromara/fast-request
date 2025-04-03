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

package io.github.kings1990.plugin.fastrequest.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PostmanCollection implements Serializable {
    private Info info;
    private List<Item> item;
    private List<Variable> variable;

    public PostmanCollection() {
    }

    public static class Info {
        private String name;
        private String schema = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }

    public static class Variable extends KeyValue{

    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Info()));
    }

    public static class Item {
        private String name;
        private Request request;
        private List<Item> item;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Request getRequest() {
            return request;
        }

        public void setRequest(Request request) {
            this.request = request;
        }

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
    }

    public static class Request{
        private String method;
        private List<Header> header;
        private Body body;
        private Url url;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public List<Header> getHeader() {
            return header;
        }

        public void setHeader(List<Header> header) {
            this.header = header;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }
    }

    public static class KeyValue{
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Header extends KeyValue{
        private Boolean disabled;

        public Boolean getDisabled() {
            return disabled;
        }

        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }
    }

    public static class Body{
        private String mode;
        private String raw;
        private List<Formdata> formdata;
        private List<Urlencoded> urlencoded;
        private Map<String,Object> options;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public List<Formdata> getFormdata() {
            return formdata;
        }

        public void setFormdata(List<Formdata> formdata) {
            this.formdata = formdata;
        }

        public List<Urlencoded> getUrlencoded() {
            return urlencoded;
        }

        public void setUrlencoded(List<Urlencoded> urlencoded) {
            this.urlencoded = urlencoded;
        }

        public Map<String, Object> getOptions() {
            return options;
        }

        public void setOptions(Map<String, Object> options) {
            this.options = options;
        }
    }

    public static class Formdata{
        private String key;
        private String type;
        private String src;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static final Map<String,Object> options = Map.of("raw",Map.of("language","json"));

    public static class Urlencoded{
        private String key;
        private String value;
        private String type;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Url{
       private String raw;
       private String protocol;
       private List<String> host;
       private String port;
       private List<String>path;
       private List<Query>query;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public List<String> getHost() {
            return host;
        }

        public void setHost(List<String> host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }

        public List<Query> getQuery() {
            return query;
        }

        public void setQuery(List<Query> query) {
            this.query = query;
        }
    }

    public static class Query extends KeyValue{

    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public List<Variable> getVariable() {
        return variable;
    }

    public void setVariable(List<Variable> variable) {
        this.variable = variable;
    }
}
