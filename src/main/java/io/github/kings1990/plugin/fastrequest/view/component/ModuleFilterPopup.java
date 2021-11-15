package io.github.kings1990.plugin.fastrequest.view.component;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.messages.MessageBus;
import io.github.kings1990.plugin.fastrequest.configurable.ConfigChangeNotifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleFilterPopup extends JPopupMenu {
    private Project myProject;
    private List<JBCheckBox> checkBoxList = new ArrayList<>();
    private List<String> allModuleList;

    public ModuleFilterPopup(Project project) {
        super();
        this.myProject = project;
        initView();
    }

    private void initView() {
        Module[] modules = ModuleManager.getInstance(myProject).getModules();
        List<String> moduleList = Arrays.stream(modules).map(Module::getName).sorted().collect(Collectors.toList());

        JPanel checkboxPane = new JPanel();
        JPanel buttonPane = new JPanel();
        this.setLayout(new BorderLayout());

        checkboxPane.setLayout(new GridLayout(moduleList.size(), 1, 3, 3));
        for (String moduleName : moduleList) {
            JBCheckBox checkBox = new JBCheckBox(moduleName, true);
            checkBox.addActionListener(e -> {
                refresh();
            });
            checkBoxList.add(checkBox);
            checkboxPane.add(checkBox);
        }


        JButton selectAll = new JButton("Select All");
        selectAll.addActionListener(e -> {
            checkBoxList.forEach(q -> q.setSelected(true));
            refresh();
        });
        buttonPane.add(selectAll);

        JButton unSelectAll = new JButton("Un Select All");
        unSelectAll.addActionListener(e -> {
            checkBoxList.forEach(q -> q.setSelected(false));
            refresh();
        });
        buttonPane.add(unSelectAll);
        JButton close = new JButton("Close");
        close.addActionListener(e -> this.setVisible(false));
        buttonPane.add(close);

        this.add(checkboxPane, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.SOUTH);
    }

    private void refresh() {
        List<String> selectModule = checkBoxList.stream().filter(AbstractButton::isSelected).map(JBCheckBox::getText).collect(Collectors.toList());
        MessageBus messageBus = myProject.getMessageBus();
        messageBus.connect();
        ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.FILTER_MODULE);
        configChangeNotifier.filterModule(selectModule, myProject.getName());
    }
}
