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
