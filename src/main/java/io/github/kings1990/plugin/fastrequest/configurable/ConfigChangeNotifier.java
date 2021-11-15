package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.util.messages.Topic;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;

import java.util.List;

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

    default void filterModule(List<String> selectModule, String projectName) {

    }
}