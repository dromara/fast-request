package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DataMappingAddView extends DialogWrapper {
    private JTextField javaTypeTextField;
    private JTextField defaultValueTextField;
    private JPanel panel;

    public DataMappingAddView() {
        super(false);
        init();
        setSize(500, 100);
        setTitle("Add DataMapping");
    }

    protected ValidationInfo doValidate() {
        if(StringUtils.isEmpty(javaTypeTextField.getText())){
            return new ValidationInfo("Please add javaType");
        }
        if(StringUtils.isEmpty(defaultValueTextField.getText())){
            return new ValidationInfo("Please add default value");
        }
        return super.doValidate();
    }

    public DataMapping getValue() {
        return new DataMapping(javaTypeTextField.getText(), defaultValueTextField.getText());
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel;
    }



}
