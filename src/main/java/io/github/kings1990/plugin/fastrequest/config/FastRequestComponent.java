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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.NameGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@State(name = "fastRequest", storages = {@Storage("fastRequest.xml")})
public class FastRequestComponent implements PersistentStateComponent<FastRequestConfiguration> {

    private FastRequestConfiguration config;

    @Nullable
    @Override
    public FastRequestConfiguration getState() {
        if (config == null) {
            config = new FastRequestConfiguration();
            List<String> projectList = new ArrayList<>();
            List<String> envList = new ArrayList<>();
            List<NameGroup> dataList = new ArrayList<>();
            config.setDataList(dataList);
            config.setEnvList(envList);
            config.setProjectList(projectList);
            config.setRandomStringLength(5);
        }
        return config;
    }

    public static FastRequestComponent getInstance() {
        return ApplicationManager.getApplication().getService(FastRequestComponent.class);
    }

    @Override
    public void loadState(@NotNull FastRequestConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }


}
