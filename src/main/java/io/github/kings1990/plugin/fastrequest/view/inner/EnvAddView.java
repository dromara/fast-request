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
