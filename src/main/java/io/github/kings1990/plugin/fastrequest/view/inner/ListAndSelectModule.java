package io.github.kings1990.plugin.fastrequest.view.inner;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ListSpeedSearch;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBList;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ListAndSelectModule extends DialogWrapper {
    private final Project myProject;
    JBList<String> moduleList;
    private List<String> existList;

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
        return scrollPane;
    }

}
