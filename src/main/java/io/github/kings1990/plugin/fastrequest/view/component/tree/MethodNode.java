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

package io.github.kings1990.plugin.fastrequest.view.component.tree;

import io.github.kings1990.plugin.fastrequest.model.ApiService;
import io.github.kings1990.plugin.fastrequest.util.FrIconUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MethodNode extends BaseNode<ApiService.ApiMethod> {

    public MethodNode(ApiService.ApiMethod apiMethod) {
        super(apiMethod);
    }

    @Override
    public String toString() {
        ApiService.ApiMethod method = this.getSource();
        return method.getUrl();
    }

    @Override
    public @Nullable Icon getIcon(boolean selected) {
        ApiService.ApiMethod method = this.getSource();
        return FrIconUtil.getIconByMethodType(method.getMethodType());
    }

    public String getToolTipText() {
        ApiService.ApiMethod method = this.getSource();
        return method.getDescription();
    }
}
