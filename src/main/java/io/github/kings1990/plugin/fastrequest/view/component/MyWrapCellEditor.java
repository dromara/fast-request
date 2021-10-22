package io.github.kings1990.plugin.fastrequest.view.component;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyWrapCellEditor extends AbstractCellEditor implements TableCellEditor {
    JTextArea comp = new JTextArea();
    JTable table;
    int row;

    public MyWrapCellEditor() {
        comp.setLineWrap(true);
        comp.setWrapStyleWord(true);
        comp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                int height = Math.max((int) (comp.getPreferredSize().getHeight()), 20);
                table.setRowHeight(row, height);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                table.setRowHeight(row, 20);
            }
        });

    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        Object valueAt = table.getValueAt(row, column);
        String v = valueAt == null ? "null" : valueAt.toString();
        comp.setText(v);
        comp.setFont(table.getFont());
        return comp;
    }

    public Object getCellEditorValue() {
        return comp.getText();
    }
}