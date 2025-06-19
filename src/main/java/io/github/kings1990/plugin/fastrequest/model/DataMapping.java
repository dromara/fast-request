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
