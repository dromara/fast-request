package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.openapi.options.Configurable;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public abstract class AbstractConfigConfigurable implements Configurable {

    @Override
    public @Nullable JComponent createComponent() {
        return getView();
    }

    public abstract AbstractConfigurableView getView();
}
