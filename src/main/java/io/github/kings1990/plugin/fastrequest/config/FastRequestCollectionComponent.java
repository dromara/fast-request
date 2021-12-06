/*
 * Copyright 2021 kings1990
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
