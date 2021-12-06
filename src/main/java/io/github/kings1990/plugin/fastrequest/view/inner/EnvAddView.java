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
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EnvAddView extends DialogWrapper {
    private JPanel panel;
    private JTextField textField;

    private String title;
    private String validMsg;


    public EnvAddView(String title, String validMsg) {
        super(false);
        this.title = title;
        this.validMsg = validMsg;
        init();
        setTitle(title);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel;
    }

    protected ValidationInfo doValidate() {
        if (textField.getText() == null || textField.getText().length() <= 0) {
            return new ValidationInfo(getValidMsg());
        }
        return super.doValidate();
    }

    public String getText() {
        return textField.getText();
    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(String validMsg) {
        this.validMsg = validMsg;
    }
}
