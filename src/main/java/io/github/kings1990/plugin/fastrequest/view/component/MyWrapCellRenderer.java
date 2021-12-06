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