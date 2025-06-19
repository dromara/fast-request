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
import java.util.List;

public class NameGroup implements Serializable {
    private String name;
    private List<HostGroup> hostGroup;

    public NameGroup() {

    }

    public NameGroup(String name, List<HostGroup> hostGroup) {
        this.name = name;
        this.hostGroup = hostGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HostGroup> getHostGroup() {
        return hostGroup;
    }

    public void setHostGroup(List<HostGroup> hostGroup) {
        this.hostGroup = hostGroup;
    }
}
