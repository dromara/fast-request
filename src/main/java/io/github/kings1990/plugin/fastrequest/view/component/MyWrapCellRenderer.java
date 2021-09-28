package io.github.kings1990.plugin.fastrequest.view.component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyWrapCellRenderer extends JTextArea implements TableCellRenderer {
    public MyWrapCellRenderer() {
        this.setOpaque(true);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.putClientProperty("html.disable", Boolean.TRUE);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setText(value == null ? "" : value.toString());
        return this;
    }
}