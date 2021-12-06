/*
 * Copyright 2021 kings1990
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