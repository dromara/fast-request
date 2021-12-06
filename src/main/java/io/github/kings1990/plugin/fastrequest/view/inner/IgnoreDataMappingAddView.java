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

package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.ui.JBUI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IgnoreDataMappingAddView extends DialogWrapper {
    private JTextField ignoreTextField;

    public IgnoreDataMappingAddView() {
        super(false);
        init();
        setSize(500, 100);
        setTitle("Add Ignore DataMapping");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        ignoreTextField = new JTextField();
        return JBUI.Panels.simplePanel().addToCenter(ignoreTextField);
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(ignoreTextField.getText())) {
            return new ValidationInfo("Please set value");
        }
        return super.doValidate();
    }

    public String getValue() {
        return ignoreTextField.getText();
    }
}
