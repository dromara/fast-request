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

package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.util.messages.Topic;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;

public interface ConfigChangeNotifier {

    Topic<ConfigChangeNotifier> PARAM_CHANGE_TOPIC = Topic.create("param change", ConfigChangeNotifier.class);
    Topic<ConfigChangeNotifier> ENV_PROJECT_CHANGE_TOPIC = Topic.create("env project change", ConfigChangeNotifier.class);

    Topic<ConfigChangeNotifier> ADD_REQUEST_TOPIC = Topic.create("add request", ConfigChangeNotifier.class);

    Topic<ConfigChangeNotifier> LOAD_REQUEST = Topic.create("load request", ConfigChangeNotifier.class);

    Topic<ConfigChangeNotifier> FILTER_MODULE = Topic.create("filter module", ConfigChangeNotifier.class);

    default void configChanged(boolean active, String projectName) {

    }

    default void loadRequest(CollectionConfiguration.CollectionDetail detail, String projectName) {

    }

}