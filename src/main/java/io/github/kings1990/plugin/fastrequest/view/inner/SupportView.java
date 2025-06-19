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
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;


public class SupportView extends DialogWrapper {

    private JLabel alipayLabel;
    private JLabel wechatLabel;
    private JPanel panel;

    public SupportView() {
        super(false);
        init();
        setTitle("Thank You !");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    private void createUIComponents() {
        ImageIcon alipayIcon = new ImageIcon(this.getClass().getResource("/alipay.png"));
        double scale = 400d / alipayIcon.getIconHeight();
        alipayIcon.setImage(alipayIcon.getImage().getScaledInstance((int)(alipayIcon.getIconWidth() * scale), 400, Image.SCALE_DEFAULT));
        alipayLabel = new JLabel(alipayIcon);
        alipayLabel.setVisible(true);

        ImageIcon wechatIcon = new ImageIcon(this.getClass().getResource("/wechat.jpg"));
        scale = 400d / wechatIcon.getIconHeight();
        wechatIcon.setImage(wechatIcon.getImage().getScaledInstance((int)(wechatIcon.getIconWidth() * scale), 400, Image.SCALE_DEFAULT));
        wechatLabel = new JLabel(wechatIcon);
        wechatLabel.setVisible(true);
    }
}
