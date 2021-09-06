package io.github.kings1990.plugin.fastrequest.config;

import com.google.common.collect.Lists;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@State(name = "fastRequestCollection", storages = {@Storage("fastRequestCollection.xml")})
public class FastRequestCollectionComponent implements PersistentStateComponent<CollectionConfiguration> {
    private CollectionConfiguration config;

    @Override
    public @Nullable CollectionConfiguration getState() {
        if(config == null || config.getDetail() == null){
            config = new CollectionConfiguration();
            CollectionConfiguration.CollectionDetail detail = new CollectionConfiguration.CollectionDetail();
            detail.setType(1);
            detail.setId("0");
            detail.setGroupId("-1");
            detail.setName("Root");
            detail.setParamGroup(new ParamGroupCollection());

            CollectionConfiguration.CollectionDetail defaultGroup = new CollectionConfiguration.CollectionDetail();
            defaultGroup.setType(1);
            defaultGroup.setId("1");
            defaultGroup.setGroupId("1");
            defaultGroup.setGroupId("1");
            defaultGroup.setName("Default Group");
            detail.setChildList(Lists.newArrayList(defaultGroup));
            config.setDetail(detail);
            return config;
        }
        return config;
    }

    @Override
    public void loadState(@NotNull CollectionConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }

    public static FastRequestCollectionComponent getInstance(Project project) {
        return project.getService(FastRequestCollectionComponent.class);
    }
}
