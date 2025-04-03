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

import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.ide.util.gotoByName.GotoFileCellRenderer;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFilePathWrapper;
import com.intellij.openapi.vfs.newvfs.VfsPresentationUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.presentation.java.SymbolPresentationUtil;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.Optional;

public class SearchEverywherePsiRenderer extends PsiElementListCellRenderer<PsiElement> {
  private EditorColorsScheme scheme = EditorColorsManager.getInstance().getSchemeForCurrentUITheme();

  public SearchEverywherePsiRenderer(Disposable parent) {
    setLayout(new SELayout());

    ApplicationManager.getApplication().getMessageBus().connect(parent).subscribe(LafManagerListener.TOPIC, __ -> {
      scheme = EditorColorsManager.getInstance().getSchemeForCurrentUITheme();
    });
  }



  @Override
  public String getElementText(PsiElement element) {
    VirtualFile file = element instanceof PsiFile ? PsiUtilCore.getVirtualFile(element) :
                       element instanceof VirtualFile ? (VirtualFile)element : null;
    if (file != null) {
      return VfsPresentationUtil.getPresentableNameForUI(element.getProject(), file);
    }

    if (element instanceof NavigationItem) {
      String name = Optional.ofNullable(((NavigationItem)element).getPresentation())
        .map(presentation -> presentation.getPresentableText())
        .orElse(null);
      if (name != null) return name;
    }

    String name = element instanceof PsiNamedElement ? ((PsiNamedElement)element).getName() : null;
    return StringUtil.notNullize(name, "<unnamed>");
  }

  @Nullable
  @Override
  protected String getContainerText(PsiElement element, String name) {
    return getContainerTextForLeftComponent(element, name, -1, null);
  }

  @Nullable
  @Override
  protected String getContainerTextForLeftComponent(PsiElement element, String name, int maxWidth, FontMetrics fm) {
    String presentablePath = extractPresentablePath(element);
    String text = ObjectUtils.chooseNotNull(presentablePath, SymbolPresentationUtil.getSymbolContainerText(element));

    if (text == null || text.equals(name)) return null;

    if (text.startsWith("(") && text.endsWith(")")) {
      text = text.substring(1, text.length() - 1);
    }

    if (presentablePath == null && (text.contains("/") || text.contains(File.separator)) && element instanceof PsiFileSystemItem) {
      Project project = element.getProject();
      String basePath = Optional.ofNullable(project.getBasePath())
        .map(FileUtil::toSystemDependentName)
        .orElse(null);
      VirtualFile file = ((PsiFileSystemItem)element).getVirtualFile();
      if (file != null) {
        text = FileUtil.toSystemDependentName(text);
        String filePath = FileUtil.toSystemDependentName(file.getPath());
        if (basePath != null && FileUtil.isAncestor(basePath, filePath, true)) {
          text = ObjectUtils.notNull(FileUtil.getRelativePath(basePath, text, File.separatorChar), text);
        }
        else {
          String rootPath = Optional.ofNullable(GotoFileCellRenderer.getAnyRoot(file, project))
            .map(root -> FileUtil.toSystemDependentName(root.getPath()))
            .filter(root -> basePath != null && FileUtil.isAncestor(basePath, root, true))
            .orElse(null);
          text = rootPath != null
                 ? ObjectUtils.notNull(FileUtil.getRelativePath(rootPath, text, File.separatorChar), text)
                 : FileUtil.getLocationRelativeToUserHome(text);
        }
      }
    }

    boolean in = text.startsWith("in ");
    if (in) text = text.substring(3);
    String left = in ? "in " : "";
    String adjustedText = left + text;
    if (maxWidth < 0) return adjustedText;

    int fullWidth = fm.stringWidth(adjustedText);
    if (fullWidth < maxWidth) return adjustedText;
    String separator = text.contains("/") ? "/" :
                       SystemInfo.isWindows && text.contains("\\") ? "\\" :
                       text.contains(".") ? "." :
                       text.contains("-") ? "-" : " ";
    LinkedList<String> parts = new LinkedList<>(StringUtil.split(text, separator));
    int index;
    while (parts.size() > 1) {
      index = parts.size() / 2 - 1;
      parts.remove(index);
      if (fm.stringWidth(left + StringUtil.join(parts, separator) + "...") < maxWidth) {
        parts.add(index, "...");
        return left + StringUtil.join(parts, separator);
      }
    }
    int adjustedWidth = Math.max(adjustedText.length() * maxWidth / fullWidth - 1, left.length() + 3);
    return StringUtil.trimMiddle(adjustedText, adjustedWidth);
  }

  @Nullable
  private static String extractPresentablePath(@Nullable PsiElement element) {
    if (element == null) return null;

    PsiFile file = element.getContainingFile();
    if (file != null) {
      VirtualFile virtualFile = file.getVirtualFile();
      if (virtualFile instanceof VirtualFilePathWrapper) return ((VirtualFilePathWrapper)virtualFile).getPresentablePath();
    }

    return null;
  }

  @Override
  protected boolean customizeNonPsiElementLeftRenderer(ColoredListCellRenderer renderer,
                                                       JList list,
                                                       Object value,
                                                       int index,
                                                       boolean selected,
                                                       boolean hasFocus) {
    return GotoFileCellRenderer.doCustomizeNonPsiElementLeftRenderer(
      renderer, list, value, getNavigationItemAttributes(value));
  }

  @Override
  protected int getIconFlags() {
    return Iconable.ICON_FLAG_READ_STATUS;
  }

  public static class SELayout extends BorderLayout {
    @Override
    public void layoutContainer(Container target) {
      super.layoutContainer(target);
      final Component right = getLayoutComponent(EAST);
      final Component left = getLayoutComponent(WEST);

      //IDEA-140824
      if (right != null && left != null && left.getBounds().x + left.getBounds().width > right.getBounds().x) {
        final Rectangle bounds = right.getBounds();
        final int newX = left.getBounds().x + left.getBounds().width;
        right.setBounds(newX, bounds.y, bounds.width - (newX - bounds.x), bounds.height);
      }
    }
  }
}
