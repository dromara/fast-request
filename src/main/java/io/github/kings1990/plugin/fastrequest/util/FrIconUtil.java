package io.github.kings1990.plugin.fastrequest.util;

import icons.PluginIcons;

import javax.swing.*;

public class FrIconUtil {
    public static Icon getIconByMethodType(String methodType) {
        switch (methodType) {
            case "POST":
                return PluginIcons.ICON_POST;
            case "PUT":
                return PluginIcons.ICON_PUT;
            case "DELETE":
                return PluginIcons.ICON_DELETE;
            case "PATCH":
                return PluginIcons.ICON_PATCH;
            default:
                return PluginIcons.ICON_GET;
        }
    }
}
