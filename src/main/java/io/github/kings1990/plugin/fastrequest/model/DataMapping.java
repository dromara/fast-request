package io.github.kings1990.plugin.fastrequest.model;

import java.io.Serializable;

public class DataMapping implements Serializable {
    private String type;
    private String value;
    private Boolean enabled = true;

    public DataMapping() {
    }

    public DataMapping(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public DataMapping(String type, String value, Boolean enabled) {
        this.type = type;
        this.value = value;
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
