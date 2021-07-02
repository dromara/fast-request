package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class UrlReplaceAddView extends DialogWrapper {
    private JPanel panel;
    private JTextField targetTextField;
    private JTextField replacementTextField;

    public UrlReplaceAddView() {
        super(false);
        init();
        setTitle("Add UrlReplace DataMapping");
    }

    protected ValidationInfo doValidate() {
        if(StringUtils.isEmpty(targetTextField.getText())){
            return new ValidationInfo("Please add Target");
        }
        if(StringUtils.isEmpty(replacementTextField.getText())){
            return new ValidationInfo("Please add Replacement");
        }
        return super.doValidate();
    }

    public DataMapping getValue() {
        return new DataMapping(targetTextField.getText(), replacementTextField.getText());
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel;
    }
}
