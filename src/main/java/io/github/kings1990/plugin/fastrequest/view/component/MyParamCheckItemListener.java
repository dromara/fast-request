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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyParamCheckItemListener implements ItemListener {
    private JTable table;

    public MyParamCheckItemListener(JTable table){
        this.table = table;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean checked = e.getStateChange() == ItemEvent.SELECTED;
        for (int x = 0, y = table.getRowCount(); x < y; x++) {
            table.setValueAt(checked, x, 0);
        }
    }
}
