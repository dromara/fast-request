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

package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface PluginIcons {
    //light:#515151 dark:#BFBFBF
    Icon fastRequest = IconLoader.getIcon("/fastRequest.svg", PluginIcons.class);
    Icon fastRequest_editor = IconLoader.getIcon("/icon/fastRequest_editor.svg", PluginIcons.class);
    Icon fastRequest_toolwindow = IconLoader.getIcon("/icon/fastRequest_toolwindow.svg", PluginIcons.class);

    Icon ICON_ARRAY = IconLoader.getIcon("/icon/array.svg", PluginIcons.class);
    Icon ICON_BOOLEAN = IconLoader.getIcon("/icon/boolean.svg", PluginIcons.class);
    Icon ICON_NUMBER = IconLoader.getIcon("/icon/number.svg", PluginIcons.class);
    Icon ICON_OBJECT = IconLoader.getIcon("/icon/object.svg", PluginIcons.class);
    Icon ICON_STRING = IconLoader.getIcon("/icon/string.svg", PluginIcons.class);
    Icon ICON_FILE = IconLoader.getIcon("/icon/file.svg", PluginIcons.class);

    Icon ICON_SEND = IconLoader.getIcon("/icon/send.svg", PluginIcons.class);
    Icon ICON_SEND_DOWNLOAD = IconLoader.getIcon("/icon/send_download.svg", PluginIcons.class);

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
    Icon ICON_PATCH = IconLoader.getIcon("/icon/patch.svg", PluginIcons.class);
    Icon ICON_CODE = IconLoader.getIcon("/icon/code.svg", PluginIcons.class);

    Icon NOTIFICATIONS_NEW = IconLoader.getIcon("/icon/notificationsNew.svg", PluginIcons.class);

    Icon ICON_POSTMAN = IconLoader.getIcon("/icon/postman.svg", PluginIcons.class);
    Icon ICON_LOCAL_SCOPE = IconLoader.getIcon("/icon/localScope.svg", PluginIcons.class);
}