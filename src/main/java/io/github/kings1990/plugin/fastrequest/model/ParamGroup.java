package io.github.kings1990.plugin.fastrequest.model;

import java.util.LinkedHashMap;

public class ParamGroup {
    private String originUrl;
    private String url;
    private String methodType;
    private String postType = "json";
    private LinkedHashMap<String, Object> pathParamMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> requestParamMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> bodyParamMap = new LinkedHashMap<>();

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
}
