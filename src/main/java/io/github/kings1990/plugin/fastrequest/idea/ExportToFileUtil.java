/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
 *
 */

package io.github.kings1990.plugin.fastrequest.idea;

import com.intellij.CommonBundle;
import com.intellij.ide.ExporterToTextFile;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.impl.DocumentImpl;
import com.intellij.openapi.editor.impl.EditorFactoryImpl;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import com.intellij.util.concurrency.annotations.RequiresEdt;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TooManyListenersException;

public final class ExportToFileUtil {
  private static final Logger LOG = Logger.getInstance(ExportToFileUtil.class);

  @RequiresEdt
  public static void chooseFileAndExport(@NotNull Project project, @NotNull ExporterToTextFile exporter) {
    final ExportDialogBase dlg = new ExportDialogBase(project, exporter);

    if (!dlg.showAndGet()) {
      return;
    }

    exportTextToFile(project, dlg.getFileName(), dlg.getText());
    exporter.exportedTo(dlg.getFileName());
  }

  private static void exportTextToFile(Project project, String fileName, String textToExport) {
    boolean append = false;
    File file = new File(fileName);
    if (file.exists()) {
      int result = Messages.showYesNoCancelDialog(
        project,
        IdeBundle.message("error.text.file.already.exists", fileName),
        IdeBundle.message("dialog.title.export.to.file"),
        IdeBundle.message("action.overwrite"),
        IdeBundle.message("action.append"),
        CommonBundle.getCancelButtonText(),
        Messages.getWarningIcon()
      );

      if (result != Messages.NO && result != Messages.YES) {
        return;
      }
      if (result == Messages.NO) {
        append = true;
      }
    }

    try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8, append)) {
      writer.write(textToExport);
    }
    catch (IOException e) {
      Messages.showMessageDialog(
        project,
        IdeBundle.message("error.writing.to.file", fileName),
        CommonBundle.getErrorTitle(),
        Messages.getErrorIcon()
      );
    }
  }

  private static class ExportDialogBase extends DialogWrapper {
    private final Project myProject;
    private final ExporterToTextFile myExporter;
    protected Editor myTextArea;
    protected TextFieldWithBrowseButton myTfFile;
    private ChangeListener myListener;

    ExportDialogBase(Project project, ExporterToTextFile exporter) {
      super(project, true);
      myProject = project;
      myExporter = exporter;

      myTfFile = new TextFieldWithBrowseButton();
      myTfFile.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor(), myProject) {
        @NotNull
        @Override
        protected String chosenFileToResultingText(@NotNull VirtualFile chosenFile) {
          String res = super.chosenFileToResultingText(chosenFile);
          if (chosenFile.isDirectory()) {
            res += File.separator + PathUtil.getFileName(myExporter.getDefaultFilePath());
          }
          return res;
        }
      });

      setTitle(IdeBundle.message("title.export.preview"));
      setOKButtonText(IdeBundle.message("button.save"));
      init();
      try {
        myListener = new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent e) {
            initText();
          }
        };
        myExporter.addSettingsChangedListener(myListener);
      }
      catch (TooManyListenersException e) {
        LOG.error(e);
      }
      initText();
    }

    @Override
    public void dispose() {
      myExporter.removeSettingsChangedListener(myListener);
      EditorFactory.getInstance().releaseEditor(myTextArea);
      super.dispose();
    }

    private void initText() {
      myTextArea.getDocument().setText(myExporter.getReportText());
    }

    @Override
    protected JComponent createCenterPanel() {
      final Document document = ((EditorFactoryImpl)EditorFactory.getInstance()).createDocument(true);
      ((DocumentImpl)document).setAcceptSlashR(true);

      myTextArea = EditorFactory.getInstance().createEditor(document, myProject, FileTypes.PLAIN_TEXT, true);
      final EditorSettings settings = myTextArea.getSettings();
      settings.setLineNumbersShown(false);
      settings.setLineMarkerAreaShown(false);
      settings.setFoldingOutlineShown(false);
      settings.setRightMarginShown(false);
      settings.setAdditionalLinesCount(0);
      settings.setAdditionalColumnsCount(0);
      settings.setAdditionalPageAtBottom(false);

      EditorEx editorEx = (EditorEx)myTextArea;
      editorEx.setBackgroundColor(UIUtil.getInactiveTextFieldBackgroundColor());
      editorEx.setColorsScheme(EditorColorsManager.getInstance().getSchemeForCurrentUITheme());

      myTextArea.getComponent().setPreferredSize(new Dimension(700, 400));
      return myTextArea.getComponent();
    }

    @Override
    protected JComponent createNorthPanel() {
      JPanel filePanel = createFilePanel();
      JComponent settingsPanel = myExporter.getSettingsEditor();
      if (settingsPanel == null) {
        return filePanel;
      }
      JPanel northPanel = new JPanel(new BorderLayout());
      northPanel.add(filePanel, BorderLayout.NORTH);
      northPanel.add(settingsPanel, BorderLayout.CENTER);
      return northPanel;
    }

    protected JPanel createFilePanel() {
      JPanel panel = new JPanel();
      panel.setLayout(new GridBagLayout());
      GridBagConstraints gbConstraints = new GridBagConstraints();
      gbConstraints.fill = GridBagConstraints.HORIZONTAL;
      JLabel promptLabel = new JLabel(IdeBundle.message("editbox.export.to.file"));
      gbConstraints.weightx = 0;
      panel.add(promptLabel, gbConstraints);
      gbConstraints.weightx = 1;
      panel.add(myTfFile, gbConstraints);

      String defaultFilePath = myExporter.getDefaultFilePath();
      if (!new File(defaultFilePath).isAbsolute()) {
        defaultFilePath = PathMacroManager.getInstance(myProject).collapsePath(defaultFilePath);
      }
      myTfFile.setText(FileUtil.toSystemDependentName(defaultFilePath));

      panel.setBorder(JBUI.Borders.emptyBottom(5));

      return panel;
    }

    public String getText() {
      return myTextArea.getDocument().getText();
    }

    public void setFileName(@NlsSafe String s) {
      myTfFile.setText(s);
    }

    public String getFileName() {
      return myTfFile.getText();
    }

    @Override
    protected Action @NotNull [] createActions() {
      return new Action[]{getOKAction(), new CopyToClipboardAction(), getCancelAction()};
    }

    @Override
    protected String getDimensionServiceKey() {
      return "#com.intellij.ide.util.ExportDialog";
    }

    protected class CopyToClipboardAction extends AbstractAction {
      public CopyToClipboardAction() {
        super(IdeBundle.message("button.copy"));
        putValue(Action.SHORT_DESCRIPTION, IdeBundle.message("description.copy.text.to.clipboard"));
      }

      @Override
      public void actionPerformed(ActionEvent e) {
        String s = StringUtil.convertLineSeparators(getText());
        CopyPasteManager.getInstance().setContents(new StringSelection(s));
      }
    }
  }
}
