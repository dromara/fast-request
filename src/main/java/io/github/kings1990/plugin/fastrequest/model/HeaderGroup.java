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
import java.util.LinkedHashMap;
import java.util.Map;

public class HeaderGroup implements Serializable {
    private String projectName;

    private Map<String, LinkedHashMap<String, String>> envMap = new LinkedHashMap<>();

    public HeaderGroup() {
    }

    public HeaderGroup(String projectName, Map<String, LinkedHashMap<String, String>> envMap) {
        this.projectName = projectName;
        this.envMap = envMap;
    }

    public Map<String, LinkedHashMap<String, String>> getEnvMap() {
        return envMap;
    }

    public void setEnvMap(Map<String, LinkedHashMap<String, String>> envMap) {
        this.envMap = envMap;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
