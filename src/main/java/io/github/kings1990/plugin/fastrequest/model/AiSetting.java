package io.github.kings1990.plugin.fastrequest.model;

import java.io.Serializable;

public class AiSetting implements Serializable {
    private Integer type;
    
    private String apiLink;
    private String token;
    
    private String model;

    public AiSetting() {
    }

    public AiSetting(Integer type, String apiLink, String token, String model) {
        this.type = type;
        this.apiLink = apiLink;
        this.token = token;
        this.model = model;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getApiLink() {
        return apiLink;
    }

    public void setApiLink(String apiLink) {
        this.apiLink = apiLink;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
