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
