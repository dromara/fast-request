package io.github.kings1990.plugin.fastrequest.view;

import com.intellij.icons.AllIcons;
import com.intellij.ide.CommonActionsManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
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
import io.github.kings1990.plugin.fastrequest.model.ApiService;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.component.ModuleFilterPopup;
import io.github.kings1990.plugin.fastrequest.view.component.tree.ApiTree;
import io.github.kings1990.plugin.fastrequest.view.component.tree.BaseNode;
import io.github.kings1990.plugin.fastrequest.view.component.tree.MethodNode;
import io.github.kings1990.plugin.fastrequest.view.component.tree.NodeUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AllApisNavToolWindow extends SimpleToolWindowPanel implements Disposable {
    private final Project myProject;
    private JPanel panel;
    private ApiTree apiTree;
    private ToolWindow toolWindow;
    private List<ApiService> allApiList;

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
        renderData(myProject);
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

    public void refreshFilterModule(List<String> selectModule) {
        DumbService.getInstance(myProject).smartInvokeLater(() -> {
            Task.Backgroundable task = new Task.Backgroundable(myProject, "Reload apis...") {
                @Override
                public void run(@NotNull ProgressIndicator indicator) {
                    indicator.setIndeterminate(false);
                    ApplicationManager.getApplication().runReadAction(() -> {
                        List<ApiService> filterList = allApiList.stream().filter(q -> selectModule.contains(q.getModuleName())).collect(Collectors.toList());
                        indicator.setText("Rendering");
                        long count = filterList.stream().mapToInt(q -> q.getApiMethodList().size()).sum();
                        BaseNode root = new BaseNode<>(count + " apis") {
                        };
                        NodeUtil.convertToRoot(root, NodeUtil.convertToMap(filterList));
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

    private void rendingTree(List<String> moduleNameList) {
        Task.Backgroundable task = new Task.Backgroundable(myProject, "Reload apis...") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                ApplicationManager.getApplication().runReadAction(() -> {
                    Query<PsiClass> query = AllClassesSearch.search(ProjectScope.getContentScope(myProject), myProject);
                    Collection<PsiClass> controller = query.filtering(cls -> cls.getAnnotation("org.springframework.web.bind.annotation.RestController") != null ||
                                    cls.getAnnotation("org.springframework.stereotype.Controller") != null
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
                    long count = allApiList.stream().mapToInt(q -> q.getApiMethodList().size()).sum();
                    BaseNode root = new BaseNode<>(count + " apis") {
                    };
                    NodeUtil.convertToRoot(root, NodeUtil.convertToMap(allApiList));
                    apiTree.setModel(new DefaultTreeModel(root));
                });
            }
        };
        ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
    }

    private void initActionBar() {
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new HelpAction());
        group.addSeparator();
        group.add(new RefreshApiAction());
        group.add(CommonActionsManager.getInstance().createExpandAllAction(apiTree, apiTree));
        group.add(CommonActionsManager.getInstance().createCollapseAllAction(apiTree, apiTree));
        group.add(new ModuleFilterAction());

        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLWINDOW_CONTENT, group, false);
        actionToolbar.setTargetComponent(panel);
        JComponent toolbarComponent = actionToolbar.getComponent();
        Border border = IdeBorderFactory.createBorder(SideBorder.BOTTOM);
        actionToolbar.getComponent().setBorder(border);
        setToolbar(toolbarComponent);
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

    private final class HelpAction extends AnAction {
        public HelpAction() {
            super(MyResourceBundleUtil.getKey("type.to.search"), MyResourceBundleUtil.getKey("type.to.search"), AllIcons.Actions.Help);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {

        }
    }

    private final class ModuleFilterAction extends AnAction {
        private ModuleFilterPopup moduleFilterPopup;

        public ModuleFilterAction() {
            super("Filter Module", "Filter module", AllIcons.Actions.GroupByModule);
            moduleFilterPopup = new ModuleFilterPopup(myProject);
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e) {
            moduleFilterPopup.show(toolWindow.getComponent(), 0, moduleFilterPopup.getY());
        }


    }

    @Override
    public void dispose() {

    }
}
