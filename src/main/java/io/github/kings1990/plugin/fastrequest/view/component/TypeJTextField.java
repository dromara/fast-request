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

package io.github.kings1990.plugin.fastrequest.view.component;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class TypeJTextField extends JTextField {
    private Icon icon;

    public TypeJTextField(Icon icon) {
        this.icon = icon;
        Insets insets = JBUI.insetsRight(40);
        //�����ı�������ұ�20
        this.setMargin(insets);
    }

    @Override
    public void paintComponent(Graphics g) {
        Insets insets = getInsets();
        super.paintComponent(g);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int Height = this.getHeight();
        //���ı����л���֮ǰͼƬ
        icon.paintIcon(this, g, this.getWidth() - 30, (Height - iconHeight) / 2);
    }
}
