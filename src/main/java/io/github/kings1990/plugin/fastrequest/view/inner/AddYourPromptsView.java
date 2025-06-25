package io.github.kings1990.plugin.fastrequest.view.inner;

import com.google.common.collect.Lists;
import com.intellij.ide.fileTemplates.impl.FileTemplateHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.JBUI;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.AiPromptConfig;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.component.MyLanguageTextField;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class AddYourPromptsView extends DialogWrapper {
    private final Project myProject;
    public AddYourPromptsView(Project project) {
        super(project, false);
        this.myProject = project;
        init();
        setSize(900, 700);
        setTitle(MyResourceBundleUtil.getKey("AIPromptsManagement"));
    }
    
    @Override
    protected @Nullable JComponent createCenterPanel() {
        MyLanguageTextField summaryLanguageTextField = new MyLanguageTextField(myProject, PlainTextLanguage.INSTANCE, PlainTextFileType.INSTANCE);
        summaryLanguageTextField.setEditorHighlighter(createHighlighter());
        summaryLanguageTextField.setMinimumSize(new Dimension(-1, 200));
        summaryLanguageTextField.setPreferredSize(new Dimension(-1, 200));
        summaryLanguageTextField.setPlaceholder(MyResourceBundleUtil.getKey("SummaryPromptEditorDesc"));
        
        MyLanguageTextField preQueryLanguageTextField = new MyLanguageTextField(myProject, PlainTextLanguage.INSTANCE, PlainTextFileType.INSTANCE);
        preQueryLanguageTextField.setEditorHighlighter(createHighlighter());
        preQueryLanguageTextField.setMinimumSize(new Dimension(-1, 150));
        preQueryLanguageTextField.setPreferredSize(new Dimension(-1, 150));
        preQueryLanguageTextField.setPlaceholder(MyResourceBundleUtil.getKey("PreQueryPromptEditorDesc"));

        MyLanguageTextField preAnswerLanguageTextField = new MyLanguageTextField(myProject, PlainTextLanguage.INSTANCE, PlainTextFileType.INSTANCE);
        preAnswerLanguageTextField.setEditorHighlighter(createHighlighter());
        preAnswerLanguageTextField.setMinimumSize(new Dimension(-1, 150));
        preAnswerLanguageTextField.setPreferredSize(new Dimension(-1, 150));
        preAnswerLanguageTextField.setPlaceholder(MyResourceBundleUtil.getKey("PreAnswerPromptEditorDesc"));
        
        
        
        MyLanguageTextField languageTextField = new MyLanguageTextField(myProject, PlainTextLanguage.INSTANCE, PlainTextFileType.INSTANCE, Lists.newArrayList("fastRequest.aiEditorAddParamAction"));
        languageTextField.setEditorHighlighter(createHighlighter());
        languageTextField.setMinimumSize(new Dimension(-1, 200));
        languageTextField.setPreferredSize(new Dimension(-1, 200));
        languageTextField.setPlaceholder(MyResourceBundleUtil.getKey("PromptEditorDesc"));
        
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        List<AiPromptConfig> aiPromptList = config.getAiPromptList();
        languageTextField.setText(CollectionUtils.isNotEmpty(aiPromptList) ? aiPromptList.get(0).getPrompt() : "");
        summaryLanguageTextField.setText(CollectionUtils.isNotEmpty(aiPromptList) ? aiPromptList.get(0).getSummary() : "");
        preQueryLanguageTextField.setText(CollectionUtils.isNotEmpty(aiPromptList) ? aiPromptList.get(0).getPreQuery() : "");
        preAnswerLanguageTextField.setText(CollectionUtils.isNotEmpty(aiPromptList) ? aiPromptList.get(0).getPreAnswer() : "");
        
        
        JBList<AiPromptConfig> promptJbList = new JBList<>(new CollectionListModel<>(aiPromptList));
        promptJbList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                AiPromptConfig aiPromptConfig = (AiPromptConfig) value;
                return super.getListCellRendererComponent(list, aiPromptConfig.getKey(), index, isSelected, cellHasFocus);
            }
        });
        promptJbList.setSelectedIndex(0);
        promptJbList.addListSelectionListener(e -> {
            boolean isAdjusting = e.getValueIsAdjusting();
            if (isAdjusting) {
                return;
            }
            int selectedIndex = promptJbList.getSelectedIndex();
            if(selectedIndex != -1){
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                languageTextField.setText(aiPromptConfig.getPrompt());
                summaryLanguageTextField.setText(aiPromptConfig.getSummary());
                preQueryLanguageTextField.setText(aiPromptConfig.getPreQuery());
                preAnswerLanguageTextField.setText(aiPromptConfig.getPreAnswer());
            }
        });
        
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(promptJbList);
        decorator.setMoveUpAction(null).setMoveDownAction(null).setAddAction(button -> {
            aiPromptList.add(0,new AiPromptConfig("New prompt","","","",""));
            promptJbList.setModel(new CollectionListModel<>(aiPromptList));
            promptJbList.setSelectedIndex(0);
            languageTextField.setText("");
            summaryLanguageTextField.setText("");
            preQueryLanguageTextField.setText("");
            preAnswerLanguageTextField.setText("");
        })
                
                .addExtraAction(AnActionButton.fromAction(new AnAction(MyResourceBundleUtil.getKey("Duplicate"),MyResourceBundleUtil.getKey("Duplicate"), PluginIcons.COPY) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                int selectedIndex = promptJbList.getSelectedIndex();
                if (selectedIndex == - 1) {
                    return;
                }
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                aiPromptList.add(selectedIndex + 1, new AiPromptConfig(aiPromptConfig.getKey() + "-dup", aiPromptConfig.getSummary(),aiPromptConfig.getPreQuery(),aiPromptConfig.getPreAnswer(),aiPromptConfig.getPrompt()));
                promptJbList.setModel(new CollectionListModel<>(aiPromptList));
                promptJbList.setSelectedIndex(selectedIndex + 1);
                languageTextField.setText(aiPromptList.get(selectedIndex + 1).getPrompt());
                summaryLanguageTextField.setText(aiPromptConfig.getSummary());
                preQueryLanguageTextField.setText(aiPromptConfig.getPreQuery());
                preAnswerLanguageTextField.setText(aiPromptConfig.getPreAnswer());
            }
            
            @Override
            public void update(AnActionEvent e) {
                int selectedIndex = promptJbList.getSelectedIndex();
                e.getPresentation().setEnabled(selectedIndex != -1);
            }
        })).setRemoveAction(e->{
            int selectedIndex = promptJbList.getSelectedIndex();
            aiPromptList.remove(selectedIndex);
            promptJbList.setModel(new CollectionListModel<>(aiPromptList));
            int idx= Math.max(0, selectedIndex - 1);
            promptJbList.setSelectedIndex(idx);
            if(!aiPromptList.isEmpty()){
                AiPromptConfig aiPromptConfig = aiPromptList.get(idx);
                languageTextField.setText(aiPromptConfig.getPrompt());
                summaryLanguageTextField.setText(aiPromptConfig.getSummary());
                preQueryLanguageTextField.setText(aiPromptConfig.getPreQuery());
                preAnswerLanguageTextField.setText(aiPromptConfig.getPreAnswer());
            } else {
                languageTextField.setText("");
                summaryLanguageTextField.setText("");
                preQueryLanguageTextField.setText("");
                preAnswerLanguageTextField.setText("");
            }
        }).setEditAction(e->{
            int selectedIndex = promptJbList.getSelectedIndex();
            AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
            SingleEditView singleEditView = new SingleEditView(aiPromptConfig.getKey());
            if(singleEditView.showAndGet()){
                aiPromptConfig.setKey(singleEditView.getValue());
                aiPromptList.set(selectedIndex, aiPromptConfig);
                promptJbList.setModel(new CollectionListModel<>(aiPromptList));
                promptJbList.setSelectedIndex(selectedIndex);
            }
        }).setMoveUpAction(e -> {
            int selectedIndex = promptJbList.getSelectedIndex();
            if(selectedIndex > 0){
                Collections.swap(aiPromptList, selectedIndex, selectedIndex - 1);
                promptJbList.setModel(new CollectionListModel<>(aiPromptList));
                promptJbList.setSelectedIndex(selectedIndex - 1);
            }
        }).setMoveDownAction(e -> {
            int selectedIndex = promptJbList.getSelectedIndex();
            if(selectedIndex < aiPromptList.size() - 1){
                Collections.swap(aiPromptList, selectedIndex, selectedIndex + 1);
                promptJbList.setModel(new CollectionListModel<>(aiPromptList));
                promptJbList.setSelectedIndex(selectedIndex + 1);
            } 
         }).setMoveUpActionUpdater(e-> promptJbList.getSelectedIndex() > 0)
         .setMoveDownActionUpdater(e-> promptJbList.getSelectedIndex() < aiPromptList.size() - 1)
        .setToolbarPosition(ActionToolbarPosition.TOP);
        
        languageTextField.addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                int selectedIndex = promptJbList.getSelectedIndex();
                if(selectedIndex == -1){
                    return;
                }
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                aiPromptConfig.setPrompt(languageTextField.getText());
            }
        });

        summaryLanguageTextField.addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                int selectedIndex = promptJbList.getSelectedIndex();
                if(selectedIndex == -1){
                    return;
                }
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                aiPromptConfig.setSummary(summaryLanguageTextField.getText());
            }
        });
        
        preQueryLanguageTextField.addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                int selectedIndex = promptJbList.getSelectedIndex();
                if(selectedIndex == -1){
                    return;
                }
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                aiPromptConfig.setPreQuery(preQueryLanguageTextField.getText());
            }
        });

        preAnswerLanguageTextField.addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                int selectedIndex = promptJbList.getSelectedIndex();
                if(selectedIndex == -1){
                    return;
                }
                AiPromptConfig aiPromptConfig = aiPromptList.get(selectedIndex);
                aiPromptConfig.setPreAnswer(preAnswerLanguageTextField.getText());
            }
        });
        
        
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(summaryLanguageTextField);
        rightPanel.add(preQueryLanguageTextField);
        rightPanel.add(preAnswerLanguageTextField);
        rightPanel.add(languageTextField);
        
        
        decorator.setPreferredSize(new Dimension(200, -1));
        return JBUI.Panels.simplePanel()
                .withPreferredSize(600, 400)
                .addToLeft(decorator.createPanel())
                .addToCenter(rightPanel);
    }

    private EditorHighlighter createHighlighter() {
        SyntaxHighlighter syntaxHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(FileTypes.PLAIN_TEXT, null, null);
        if (syntaxHighlighter == null) {
            syntaxHighlighter = (new PlainSyntaxHighlighter());
        }
        IElementType TEXT_TOKEN = new IElementType("TEXT", Language.ANY);
        SyntaxHighlighter originalHighlighter = syntaxHighlighter;
        EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
        LayeredLexerEditorHighlighter highlighter = new LayeredLexerEditorHighlighter((new FileTemplateHighlighter()), scheme);
        highlighter.registerLayer(TEXT_TOKEN, new LayerDescriptor(originalHighlighter, ""));
        return highlighter;
    }

}


