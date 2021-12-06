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
