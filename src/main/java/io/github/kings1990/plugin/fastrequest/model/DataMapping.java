package io.github.kings1990.plugin.fastrequest.model;

public class DataMapping {
    private String type;
    private String value;

    public DataMapping() {
    }

    public DataMapping(String type, String value) {
        this.type = type;
        this.value = value;
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
}
