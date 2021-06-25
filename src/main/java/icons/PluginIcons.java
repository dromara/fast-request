package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface PluginIcons {
    Icon fastRequest = IconLoader.getIcon("/fastRequest.svg", PluginIcons.class);

    Icon ICON_ARRAY = IconLoader.getIcon("/icon/array.svg", PluginIcons.class);
    Icon ICON_BOOLEAN = IconLoader.getIcon("/icon/boolean.svg", PluginIcons.class);
    Icon ICON_NUMBER = IconLoader.getIcon("/icon/number.svg", PluginIcons.class);
    Icon ICON_OBJECT = IconLoader.getIcon("/icon/object.svg", PluginIcons.class);
    Icon ICON_STRING = IconLoader.getIcon("/icon/string.svg", PluginIcons.class);

    Icon ICON_SEND = IconLoader.getIcon("/icon/send.svg", PluginIcons.class);
    Icon ICON_ADD = IconLoader.getIcon("/icon/add.svg", PluginIcons.class);
    Icon ICON_DELETE = IconLoader.getIcon("/icon/delete.svg", PluginIcons.class);
}