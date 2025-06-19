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

import com.intellij.openapi.options.Configurable;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class AbstractConfigConfigurable implements Configurable {

    @Override
    public @Nullable JComponent createComponent() {
        return getView();
    }

    public abstract AbstractConfigurableView getView();
}
