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

package io.github.kings1990.plugin.fastrequest.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import io.github.kings1990.plugin.fastrequest.model.FastRequestCurrentProjectConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@State(name = "fastRequestCurrentProjectConfig", storages = {@Storage("fastRequestCurrentProjectConfig.xml")})
public class FastRequestCurrentProjectConfigComponent implements PersistentStateComponent<FastRequestCurrentProjectConfiguration> {
    private FastRequestCurrentProjectConfiguration config;

    @Override
    public @Nullable FastRequestCurrentProjectConfiguration getState() {
        if (config == null) {
            config = new FastRequestCurrentProjectConfiguration();
        }
        return config;
    }

    @Override
    public void loadState(@NotNull FastRequestCurrentProjectConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }

    public static FastRequestCurrentProjectConfigComponent getInstance(Project project) {
        return project.getService(FastRequestCurrentProjectConfigComponent.class);
    }
}
