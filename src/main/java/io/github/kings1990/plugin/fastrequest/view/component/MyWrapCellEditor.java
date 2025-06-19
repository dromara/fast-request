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