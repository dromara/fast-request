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
