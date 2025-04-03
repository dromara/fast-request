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

package io.github.kings1990.plugin.fastrequest.action;

import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.ide.util.gotoByName.ChooseByNameFilterConfiguration;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Toggleable;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.JBPopupListener;
import com.intellij.openapi.ui.popup.LightweightWindowEvent;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CheckBoxFilterAction<T> extends AnAction {
    private JBPopup myPopup;
    private List<JCheckBox> checkBoxList;
    private final Filter<T> filter;
    private final Runnable rebuildRunnable;

    public CheckBoxFilterAction(String title, String description, Icon icon, Filter<T> filter, Runnable rebuildRunnable) {
        super(title, description, icon);
        this.filter = filter;
        this.rebuildRunnable = rebuildRunnable;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        JPanel panel = initView(filter, rebuildRunnable);
        createPopup(e, panel);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Icon icon = getTemplatePresentation().getIcon();
        boolean isActive = filter.getAllElements().size() != filter.getSelectedElementList().size();
        boolean isSelected = myPopup != null && !myPopup.isDisposed();
        e.getPresentation().setIcon(isActive ? ExecutionUtil.getLiveIndicator(icon) : icon);
        e.getPresentation().setEnabled(true);
        Toggleable.setSelected(e.getPresentation(), isSelected);
    }

    public void createPopup(AnActionEvent e, JPanel contentPanel) {
        JBPopupListener popupCloseListener = new JBPopupListener() {
            @Override
            public void onClosed(@NotNull LightweightWindowEvent event) {
                checkBoxList = null;
                myPopup = null;
            }
        };
        myPopup = JBPopupFactory.getInstance()
                .createComponentPopupBuilder(contentPanel, null)
                .setModalContext(false)
                .setFocusable(false)
                .setResizable(true)
                .setCancelOnClickOutside(true)
                .setMinSize(new Dimension(200, 200))
                .addListener(popupCloseListener)
                .createPopup();
        myPopup.showUnderneathOf(e.getInputEvent().getComponent());
    }

    private JPanel initView(Filter<T> filter, Runnable rebuildRunnable) {
        checkBoxList = new ArrayList<>();
        List<T> elementList = filter.getAllElements();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel checkboxPane = new JPanel();
        List<T> selectedElementList = filter.getSelectedElementList();
        checkboxPane.setLayout(new BoxLayout(checkboxPane, BoxLayout.Y_AXIS));
        JPanel buttonPane = new JPanel();
        for (T element : elementList) {
            String text = filter.getElementText(element);
            Icon icon = filter.getElementIcon(element);
            JCheckBox checkBox = new JCheckBox(text, selectedElementList.contains(element));
            checkBox.addActionListener(e -> {
                filter.setSelected(element, checkBox.isSelected());
                rebuildRunnable.run();
            });
            checkBoxList.add(checkBox);
            if (icon == null) {
                checkboxPane.add(checkBox);
            } else {
                checkBox.setText("");
                checkboxPane.add(JBUI.Panels.simplePanel().addToLeft(checkBox).addToCenter(new JLabel(text, icon, JLabel.LEFT)).withPreferredHeight(24).withMaximumHeight(24));
            }

        }


        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(checkboxPane);
        scrollPane.setMinimumSize(new Dimension(200, 200));
        scrollPane.setPreferredSize(new Dimension(200, 200));

        JButton all = new JButton(MyResourceBundleUtil.getKey("filter.all"));
        all.addActionListener(e -> {
            for (int i = 0; i < checkBoxList.size(); i++) {
                checkBoxList.get(i).setSelected(true);
                filter.setSelected(elementList.get(i), true);
            }
            rebuildRunnable.run();
        });
        buttonPane.add(all);

        JButton none = new JButton(MyResourceBundleUtil.getKey("filter.none"));
        none.addActionListener(e -> {
            for (int i = 0; i < checkBoxList.size(); i++) {
                checkBoxList.get(i).setSelected(false);
                filter.setSelected(elementList.get(i), false);
            }
            rebuildRunnable.run();
        });
        buttonPane.add(none);

        JButton invert = new JButton(MyResourceBundleUtil.getKey("filter.invert"));
        invert.addActionListener(e -> {
            for (int i = 0; i < checkBoxList.size(); i++) {
                boolean selected = checkBoxList.get(i).isSelected();
                checkBoxList.get(i).setSelected(!selected);
                filter.setSelected(elementList.get(i), !selected);
            }
            rebuildRunnable.run();
        });
        buttonPane.add(invert);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.SOUTH);

        return panel;
    }


    public static class Filter<T> {
        private final List<T> myElementList;
        private final Function<? super T, @Nls String> myTextExtractor;
        private final Function<? super T, ? extends Icon> myIconExtractor;
        private final ChooseByNameFilterConfiguration<? super T> myPersistentConfiguration;

        public List<T> getAllElements() {
            return myElementList;
        }

        public Filter(List<T> myElementList, Function<? super T, @Nls String> textExtractor, Function<? super T, ? extends Icon> iconExtractor, ChooseByNameFilterConfiguration<? super T> myPersistentConfiguration) {
            this.myElementList = myElementList;
            this.myTextExtractor = textExtractor;
            this.myIconExtractor = iconExtractor;
            this.myPersistentConfiguration = myPersistentConfiguration;
        }

        public String getElementText(T element) {
            return myTextExtractor.apply(element);
        }

        public Icon getElementIcon(T element) {
            return myIconExtractor.apply(element);
        }

        public List<T> getSelectedElementList() {
            return ContainerUtil.filter(myElementList, myPersistentConfiguration::isFileTypeVisible);
        }

        public boolean isSelected(T element) {
            return myPersistentConfiguration.isFileTypeVisible(element);
        }

        public void setSelected(T element, boolean selected) {
            myPersistentConfiguration.setVisible(element, selected);
        }
    }
}
