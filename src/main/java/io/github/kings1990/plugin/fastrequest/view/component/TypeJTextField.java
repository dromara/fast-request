package io.github.kings1990.plugin.fastrequest.view.component;

import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class TypeJTextField extends JTextField {
    private Icon icon;

    public TypeJTextField(Icon icon) {
        this.icon = icon;
        Insets insets = JBUI.insetsLeft(40);
        //设置文本输入距左边20
        this.setMargin(insets);
    }

    @Override
    public void paintComponent(Graphics g) {
        Insets insets = getInsets();
        super.paintComponent(g);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int Height = this.getHeight();
        //在文本框中画上之前图片
        icon.paintIcon(this, g, 5, (Height - iconHeight) / 2);
    }
}
