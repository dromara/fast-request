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
