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
    Icon ICON_FILE = IconLoader.getIcon("/icon/file.svg", PluginIcons.class);

    Icon ICON_SEND = IconLoader.getIcon("/icon/send.svg", PluginIcons.class);

    Icon ICON_VISIBLE = IconLoader.getIcon("/icon/visible.svg", PluginIcons.class);
    Icon ICON_INVISIBLE = IconLoader.getIcon("/icon/invisible.svg", PluginIcons.class);

    Icon ICON_EXPAND = IconLoader.getIcon("/icon/expand.svg", PluginIcons.class);
    Icon ICON_COLLAPSE = IconLoader.getIcon("/icon/collapse.svg", PluginIcons.class);

    Icon ICON_NAVIGATE = IconLoader.getIcon("/icon/navigate.svg", PluginIcons.class);
    Icon ICON_CONFIG = IconLoader.getIcon("/icon/config.svg", PluginIcons.class);

    Icon ICON_SAVE = IconLoader.getIcon("/icon/save.svg", PluginIcons.class);
    Icon ICON_CURL = IconLoader.getIcon("/icon/curl.svg", PluginIcons.class);
    Icon ICON_DOC = IconLoader.getIcon("/icon/readme.svg", PluginIcons.class);
    Icon ICON_COFFEE = IconLoader.getIcon("/icon/coffee.svg", PluginIcons.class);
    Icon ICON_RETRY = IconLoader.getIcon("/icon/retry.svg", PluginIcons.class);
    Icon ICON_FILTER = IconLoader.getIcon("/icon/filter.svg", PluginIcons.class);

    Icon ICON_CONTEXT_HELP = IconLoader.getIcon("/icon/contextHelp.svg", PluginIcons.class);


    Icon ICON_GET = IconLoader.getIcon("/icon/get.svg", PluginIcons.class);
    Icon ICON_POST = IconLoader.getIcon("/icon/post.svg", PluginIcons.class);
    Icon ICON_PUT = IconLoader.getIcon("/icon/put.svg", PluginIcons.class);
    Icon ICON_DELETE = IconLoader.getIcon("/icon/delete.svg", PluginIcons.class);
}