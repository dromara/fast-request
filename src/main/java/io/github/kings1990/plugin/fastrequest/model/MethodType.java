package io.github.kings1990.plugin.fastrequest.model;

import javax.swing.*;


public class MethodType {
    private String name;
    private Icon icon;

    public MethodType(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
