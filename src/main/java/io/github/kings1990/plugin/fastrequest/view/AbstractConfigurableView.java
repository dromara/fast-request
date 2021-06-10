package io.github.kings1990.plugin.fastrequest.view;

import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;

import javax.swing.*;

public abstract class AbstractConfigurableView {
    protected FastRequestConfiguration config;
    public abstract JComponent getComponent();

    public AbstractConfigurableView(FastRequestConfiguration config) {
        this.config = config;
    }

}
