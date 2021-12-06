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
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UrlReplaceAddView extends DialogWrapper {
    private JPanel panel;
    private JTextField targetTextField;
    private JTextField replacementTextField;

    public UrlReplaceAddView() {
        super(false);
        init();
        setTitle("Add UrlReplace DataMapping");
    }

    protected ValidationInfo doValidate() {
        if(StringUtils.isEmpty(targetTextField.getText())){
            return new ValidationInfo("Please add Target");
        }
        if(StringUtils.isEmpty(replacementTextField.getText())){
            return new ValidationInfo("Please add Replacement");
        }
        return super.doValidate();
    }

    public DataMapping getValue() {
        return new DataMapping(targetTextField.getText(), replacementTextField.getText());
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel;
    }
}
