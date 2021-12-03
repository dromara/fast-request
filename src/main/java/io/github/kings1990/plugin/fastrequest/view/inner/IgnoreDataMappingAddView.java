package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.ui.JBUI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IgnoreDataMappingAddView extends DialogWrapper {
    private JTextField ignoreTextField;

    public IgnoreDataMappingAddView() {
        super(false);
        init();
        setSize(500, 100);
        setTitle("Add Ignore DataMapping");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        ignoreTextField = new JTextField();
        return JBUI.Panels.simplePanel().addToCenter(ignoreTextField);
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(ignoreTextField.getText())) {
            return new ValidationInfo("Please set value");
        }
        return super.doValidate();
    }

    public String getValue() {
        return ignoreTextField.getText();
    }
}
