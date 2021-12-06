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

package io.github.kings1990.plugin.fastrequest.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class ParamGroup implements Serializable {
    private String originUrl;
    private String url;
    private String methodType;
    private String postType = "json";
    private LinkedHashMap<String, Object> pathParamMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> requestParamMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> bodyParamMap = new LinkedHashMap<>();
    private String methodDescription;
    private String className;
    private String method;
    private String module;

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public LinkedHashMap<String, Object> getPathParamMap() {
        return pathParamMap;
    }

    public void setPathParamMap(LinkedHashMap<String, Object> pathParamMap) {
        this.pathParamMap = pathParamMap;
    }

    public LinkedHashMap<String, Object> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(LinkedHashMap<String, Object> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }

    public LinkedHashMap<String, Object> getBodyParamMap() {
        return bodyParamMap;
    }

    public void setBodyParamMap(LinkedHashMap<String, Object> bodyParamMap) {
        this.bodyParamMap = bodyParamMap;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
