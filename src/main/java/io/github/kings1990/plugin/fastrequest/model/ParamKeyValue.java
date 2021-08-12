package io.github.kings1990.plugin.fastrequest.model;

public class ParamKeyValue {
    private String key;
    private Object value;
    /**
     * 1-自定义(实体)dataMapping映射  2-基本类型映射
     */
    private Integer customFlag;
    /**
     * Object Array String Number Boolean
     */
    private String type;

    private String comment;

    private Boolean enabled = true;

    public ParamKeyValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public ParamKeyValue(String key, Object value, Integer customFlag) {
        this.key = key;
        this.value = value;
        this.customFlag = customFlag;
    }

    public ParamKeyValue(String key, Object value, Integer customFlag, String type) {
        this.key = key;
        this.value = value;
        this.customFlag = customFlag;
        this.type = type;
    }

    public ParamKeyValue(String key, Object value, Integer customFlag, String type,String comment) {
        this.key = key;
        this.value = value;
        this.customFlag = customFlag;
        this.type = type;
        this.comment = comment;
    }

    public ParamKeyValue(String key, Object value, Integer customFlag, String type, String comment, Boolean enabled) {
        this.key = key;
        this.value = value;
        this.customFlag = customFlag;
        this.type = type;
        this.comment = comment;
        this.enabled = enabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getCustomFlag() {
        return customFlag;
    }

    public void setCustomFlag(Integer customFlag) {
        this.customFlag = customFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
