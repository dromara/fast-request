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
