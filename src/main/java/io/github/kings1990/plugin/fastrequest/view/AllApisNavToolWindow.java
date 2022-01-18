/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kings1990.plugin.fastrequest.view;

import cn.hutool.core.collection.CollectionUtil;
import com.intellij.icons.AllIcons;
import com.intellij.ide.CommonActionsManager;
import com.intellij.ide.actions.searcheverywhere.PersistentSearchEverywhereContributorFilter;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.search.searches.AllClassesSearch;
import com.intellij.ui.*;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.util.PsiNavigateUtil;
import com.intellij.util.Query;
import io.github.kings1990.plugin.fastrequest.action.CheckBoxFilterAction;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.configurable.FastRequestSearchEverywhereConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ApiService;
import io.github.kings1990.plugin.fastrequest.model.MethodType;
import io.github.kings1990.plugin.fastrequest.view.component.tree.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class AllApisNavToolWindow extends SimpleToolWindowPanel implements Disposable {
    private final Project myProject;
    private JPanel panel;
    private ApiTree apiTree;
    private ToolWindow toolWindow;
    private List<ApiService> allApiList;
    private List<String> mySelectModule;
    //    PersistentSearchEverywhereContributorFilter<String> methodTypeFilter = createMethodTypeFilter();
    private CheckBoxFilterAction.Filter<String> moduleFilter;
    private CheckBoxFilterAction.Filter<String> methodTypeFilter;

    public AllApisNavToolWindow(Project project, ToolWindow toolWindow) {
        super(false, false);
        this.myProject = project;
        this.toolWindow = toolWindow;
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.setContent(panel);
        setLayout(new BorderLayout());
        apiTree = new ApiTree();
        initActionBar();

        apiTree.setCellRenderer(new MyCellRenderer());
        apiTree.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    navigateToMethod();
                }
            }
        });
        apiTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    navigateToMethod();
                }
            }
        });
        TreeSpeedSearch treeSpeedSearch = new TreeSpeedSearch(apiTree, path -> {
            BaseNode node = (BaseNode) path.getLastPathComponent();
            Object object = node.getUserObject();
            return object.toString();
        }, true);
        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(treeSpeedSearch.getComponent());
        panel.add(scrollPane);
//        renderData(myProject);
        Disposer.register(myProject, this);
    }

    private void navigateToMethod() {
        if (!apiTree.isEnabled()) {
            return;
        }
        Object component = apiTree.getLastSelectedPathComponent();
        if (!(component instanceof MethodNode)) {
            return;
        }
        MethodNode methodNode = (MethodNode) component;
        PsiMethod psiMethod = methodNode.getSource().getPsiMethod();
        PsiNavigateUtil.navigate(psiMethod);
    }

    public void refreshFilterModule(List<String> selectModule, List<String> selectMethodType) {
        DumbService.getInstance(myProject).smartInvokeLater(() -> {
            Task.Backgroundable task = new Task.Backgroundable(myProject, "Reload apis...") {
                @Override
                public void run(@NotNull ProgressIndicator indicator) {
                    indicator.setIndeterminate(false);
                    ApplicationManager.getApplication().runReadAction(() -> {
                        if (allApiList == null) {
                            return;
                        }
                        List<ApiService> filterList = allApiList.stream().filter(q -> selectModule.contains(q.getModuleName())).collect(Collectors.toList());
                        indicator.setText("Rendering");
                        List<ApiService.ApiMethod> filterMethodList = new ArrayList<>();
                        filterList.stream().map(ApiService::getApiMethodList).filter(CollectionUtil::isNotEmpty).forEach(filterMethodList::addAll);
                        long count = filterMethodList.stream().filter(q -> selectMethodType.contains(q.getMethodType())).count();
                        RootNode root = new RootNode(count + " apis") {
                        };

                        NodeUtil.convertToRoot(root, NodeUtil.convertToMap(filterList), selectMethodType);
                        apiTree.setModel(new DefaultTreeModel(root));
                    });
                }
            };
            ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
        });
    }

    private static class MyCellRenderer extends ColoredTreeCellRenderer {

        @Override
        public void customizeCellRenderer(@NotNull JTree tree, Object target, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            BaseNode node = null;
            if (target instanceof BaseNode) {
                node = (BaseNode<?>) target;
            }
            if (node == null) {
                return;
            }
            append(node.toString(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            setIcon(node.getIcon(true));
            if (target instanceof MethodNode) {
                setToolTipText(((MethodNode) target).getToolTipText());
            }

            SpeedSearchUtil.applySpeedSearchHighlighting(this, this, false, true);
        }
    }

    private void renderData(Project project) {
        DumbService.getInstance(project).smartInvokeLater(() -> rendingTree(null));
    }

    public static PersistentSearchEverywhereContributorFilter<String> createMethodTypeFilter() {
        List<String> methodNameList = Constant.METHOD_TYPE_LIST.stream().map(MethodType::getName).collect(Collectors.toList());
        Map<String, Icon> iconMap = Constant.METHOD_TYPE_LIST.stream().collect(Collectors.toMap(MethodType::getName, MethodType::getIcon));
        return new PersistentSearchEverywhereContributorFilter<>(
                methodNameList,
                FastRequestSearchEverywhereConfiguration.getInstance(),
                methodName -> methodName, iconMap::get);
    }

    private void rendingTree(List<String> moduleNameList) {
        Task.Backgroundable task = new Task.Backgroundable(myProject, "Reload apis...") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                ApplicationManager.getApplication().runReadAction(() -> {
                    Query<PsiClass> query = AllClassesSearch.search(ProjectScope.getContentScope(myProject), myProject);
                    Collection<PsiClass> controller = query.filtering(cls -> cls.getAnnotation("org.springframework.web.bind.annotation.RestController") != null ||
                                    cls.getAnnotation("org.springframework.stereotype.Controller") != null
                                    || cls.getAnnotation("org.springframework.web.bind.annotation.RequestMapping") != null
                                    || cls.getAnnotation("javax.ws.rs.Path") != null
                            )
                            .filtering(
                                    cls -> {
                                        if (moduleNameList == null) {
                                            return true;
                                        }
                                        Module module = ModuleUtil.findModuleForFile(cls.getContainingFile());
                                        if (module == null) {
                                            return false;
                                        }
                                        return moduleNameList.contains(module.getName());
                                    }
                            )
                            .allowParallelProcessing()
                            .findAll();
                    allApiList = NodeUtil.getAllApiList(controller);
                    indicator.setText("Rendering");
                    List<String> selectMethodType = methodTypeFilter.getSelectedElementList();
                    List<ApiService.ApiMethod> filterMethodList = new ArrayList<>();
                    allApiList.stream().map(ApiService::getApiMethodList).forEach(filterMethodList::addAll);
                    long count = filterMethodList.stream().filter(q -> selectMethodType.contains(q.getMethodType())).count();
                    RootNode root = new RootNode(count + " apis");
                    NodeUtil.convertToRoot(root, NodeUtil.convertToMap(
                            allApiList.stream().filter(q->CollectionUtil.isNotEmpty(q.getApiMethodList())).collect(Collectors.toList())
                    ), methodTypeFilter.getSelectedElementList());
                    apiTree.setModel(new DefaultTreeModel(root));
                    NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Reload apis complete", MessageType.INFO)
                            .notify(myProject);
                });
            }
        };
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }

    private void initActionBar() {
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new RefreshApiAction());
        group.add(CommonActionsManager.getInstance().createExpandAllAction(apiTree, apiTree));
        group.add(CommonActionsManager.getInstance().createCollapseAllAction(apiTree, apiTree));

        Module[] modules = ModuleManager.getInstance(myProject).getModules();
        List<String> moduleList = Arrays.stream(modules).map(Module::getName).sorted().collect(Collectors.toList());
        moduleFilter = new CheckBoxFilterAction.Filter<>(moduleList, module -> module, module -> null, FastRequestSearchEverywhereConfiguration.getInstance());
        group.add(new CheckBoxFilterAction<>("Filter Module", "Filter module", AllIcons.Actions.GroupByModule, moduleFilter, this::refresh));


        List<String> methodNameList = Constant.METHOD_TYPE_LIST.stream().map(MethodType::getName).collect(Collectors.toList());
        Map<String, Icon> iconMap = Constant.METHOD_TYPE_LIST.stream().collect(Collectors.toMap(MethodType::getName, MethodType::getIcon));
        methodTypeFilter = new CheckBoxFilterAction.Filter<>(methodNameList, methodName -> methodName, iconMap::get, FastRequestSearchEverywhereConfiguration.getInstance());
        group.add(new CheckBoxFilterAction<>("Filter Method", "Filter Method", AllIcons.Actions.GroupByMethod, methodTypeFilter, this::refresh));


        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLWINDOW_CONTENT, group, false);
        actionToolbar.setTargetComponent(panel);
        JComponent toolbarComponent = actionToolbar.getComponent();
        Border border = IdeBorderFactory.createBorder(SideBorder.BOTTOM);
        actionToolbar.getComponent().setBorder(border);
        setToolbar(toolbarComponent);
    }

    private void refresh() {
        List<String> moduleList = moduleFilter.getSelectedElementList();
        List<String> methodList = methodTypeFilter.getSelectedElementList();
        refreshFilterModule(moduleList, methodList);
    }


    private final class RefreshApiAction extends AnAction {
        public RefreshApiAction() {
            super("Refresh", "Refresh", AllIcons.Actions.Refresh);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            renderData(myProject);
        }
    }

    @Override
    public void dispose() {

    }
}
