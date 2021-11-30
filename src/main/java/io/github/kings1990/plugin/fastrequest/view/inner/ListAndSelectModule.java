package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ListSpeedSearch;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.UI;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ListAndSelectModule extends DialogWrapper {
    private final Project myProject;
    JBList<String> moduleList;
    private final List<String> existList;
    private static final Logger LOGGER = Logger.getInstance(ListAndSelectModule.class);

    public ListAndSelectModule(Project project, List<String> existList) {
        super(project, false);
        this.myProject = project;
        this.existList = existList;
        init();
        setTitle(MyResourceBundleUtil.getKey("button.addModuleGroup"));
    }

    public String getValue() {
        return moduleList.getSelectedValue();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        List<String> moduleNameList = ModuleManager.getInstance(myProject).moduleGraph().getNodes().stream().map(Module::getName).collect(Collectors.toList());
        moduleNameList.removeAll(existList);
        moduleList = new JBList<>(new CollectionListModel<>(moduleNameList));
        moduleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSpeedSearch<String> speedSearch = new ListSpeedSearch<>(moduleList);
        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(speedSearch.getComponent());
        scrollPane.setMaximumSize(new Dimension(400, 400));
        scrollPane.setPreferredSize(new Dimension(400, 400));
        String comment;
        if ("zh".equals(MyResourceBundleUtil.getKey("language"))) {
            comment = "<p>帮助将api移动到控制器所在的模块.<a href =\"" + Constant.CN_DOC_DOMAIN + "/guide/feature.html#api%E5%88%86%E7%BB%84\">查看技巧</a></p>";
        } else {
            comment = "<p>Help move the API to the module for the controller.<a href =\"" + Constant.EN_DOC_DOMAIN + "/guide/feature.html#api-group\">see knowledge</a></p>";
        }

        return UI.PanelFactory.panel(scrollPane).
                withComment(comment)
                .createPanel();
    }

}
