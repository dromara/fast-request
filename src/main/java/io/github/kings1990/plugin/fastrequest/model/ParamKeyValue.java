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

public class ParamKeyValue implements Serializable {
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
