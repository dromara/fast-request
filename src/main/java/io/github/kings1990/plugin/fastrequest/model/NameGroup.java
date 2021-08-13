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
