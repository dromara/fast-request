/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
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

public class ParamGroupCollection implements Serializable {
    private String originUrl;
    private String url = "";
    private String methodType;
    private String postType = "json";

    private String pathParamsKeyValueListJson;
    private String urlParamsKeyValueListJson;
    private String urlParamsKeyValueListText;
    private String urlEncodedKeyValueListJson;
    private String urlEncodedKeyValueListText;
    private String bodyKeyValueListJson;
    private String multipartKeyValueListJson;


    private String methodDescription;
    private String className;
    private String method;


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

    public String getPathParamsKeyValueListJson() {
        return pathParamsKeyValueListJson;
    }

    public void setPathParamsKeyValueListJson(String pathParamsKeyValueListJson) {
        this.pathParamsKeyValueListJson = pathParamsKeyValueListJson;
    }

    public String getUrlParamsKeyValueListJson() {
        return urlParamsKeyValueListJson;
    }

    public void setUrlParamsKeyValueListJson(String urlParamsKeyValueListJson) {
        this.urlParamsKeyValueListJson = urlParamsKeyValueListJson;
    }

    public String getUrlEncodedKeyValueListJson() {
        return urlEncodedKeyValueListJson;
    }

    public void setUrlEncodedKeyValueListJson(String urlEncodedKeyValueListJson) {
        this.urlEncodedKeyValueListJson = urlEncodedKeyValueListJson;
    }

    public String getBodyKeyValueListJson() {
        return bodyKeyValueListJson;
    }

    public void setBodyKeyValueListJson(String bodyKeyValueListJson) {
        this.bodyKeyValueListJson = bodyKeyValueListJson;
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

    public String getUrlParamsKeyValueListText() {
        return urlParamsKeyValueListText;
    }

    public void setUrlParamsKeyValueListText(String urlParamsKeyValueListText) {
        this.urlParamsKeyValueListText = urlParamsKeyValueListText;
    }

    public String getUrlEncodedKeyValueListText() {
        return urlEncodedKeyValueListText;
    }

    public void setUrlEncodedKeyValueListText(String urlEncodedKeyValueListText) {
        this.urlEncodedKeyValueListText = urlEncodedKeyValueListText;
    }

    public String getMultipartKeyValueListJson() {
        return multipartKeyValueListJson;
    }

    public void setMultipartKeyValueListJson(String multipartKeyValueListJson) {
        this.multipartKeyValueListJson = multipartKeyValueListJson;
    }
}
