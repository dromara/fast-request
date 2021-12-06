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
