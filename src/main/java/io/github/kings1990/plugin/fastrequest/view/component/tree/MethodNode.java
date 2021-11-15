package io.github.kings1990.plugin.fastrequest.view.component.tree;

import io.github.kings1990.plugin.fastrequest.model.ApiService;
import io.github.kings1990.plugin.fastrequest.util.FrIconUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MethodNode extends BaseNode<ApiService.ApiMethod> {

    public MethodNode(ApiService.ApiMethod apiMethod) {
        super(apiMethod);
    }

    @Override
    public String toString() {
        ApiService.ApiMethod method = this.getSource();
        return method.getUrl();
    }

    @Override
    public @Nullable Icon getIcon(boolean selected) {
        ApiService.ApiMethod method = this.getSource();
        return FrIconUtil.getIconByMethodType(method.getMethodType());
    }

    public String getToolTipText() {
        ApiService.ApiMethod method = this.getSource();
        return method.getDescription();
    }
}
