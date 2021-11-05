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
