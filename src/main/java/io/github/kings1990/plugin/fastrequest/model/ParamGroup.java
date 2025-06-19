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

import java.io.Serializable;
import java.util.LinkedHashMap;

public class ParamGroup implements Serializable {
    private String originUrl = "";
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

    public ParamGroup() {
    }

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
