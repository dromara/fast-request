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

package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.ui.UI;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GlobalHeaderAddView extends DialogWrapper {
    private JTextField keyTextField;
    private JTextField valueTextField;

    public GlobalHeaderAddView() {
        super(false);
        init();
        setSize(500, 100);
        setTitle("Add Global Request Header");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        keyTextField = new JTextField();
        valueTextField = new JTextField();
        return UI.PanelFactory.grid().splitColumns()
                .add(UI.PanelFactory.panel(keyTextField).withLabel("Key"))
                .add(UI.PanelFactory.panel(valueTextField).withLabel("Value"))
                .createPanel();
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(keyTextField.getText())) {
            return new ValidationInfo("Please set key");
        }
        if (StringUtils.isEmpty(valueTextField.getText())) {
            return new ValidationInfo("Please set value");
        }
        return super.doValidate();
    }

    public DataMapping getValue() {
        return new DataMapping(keyTextField.getText(), valueTextField.getText());
    }
}
