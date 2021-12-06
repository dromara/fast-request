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
