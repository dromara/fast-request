package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.ui.UI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SingleEditView extends DialogWrapper {
    private JTextField keyTextField;
    private String value;

    public SingleEditView(String value) {
        super(false);
        this.value = value;
        init();
        setSize(500, 100);
        setTitle("Edit");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        keyTextField = new JTextField();
        keyTextField.setText(value);
        return UI.PanelFactory.grid().splitColumns().add(UI.PanelFactory.panel(keyTextField).withLabel("Value")).createPanel();
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(keyTextField.getText())) {
            return new ValidationInfo("Please set value");
        }
        return super.doValidate();
    }

    public String getValue() {
        return keyTextField.getText();
    }
}
