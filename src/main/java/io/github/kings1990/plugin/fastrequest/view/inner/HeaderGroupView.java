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

package io.github.kings1990.plugin.fastrequest.view.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.intellij.json.JsonFileType;
import com.intellij.json.JsonLanguage;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.JBUI;
import io.github.kings1990.plugin.fastrequest.model.HeaderGroup;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import io.github.kings1990.plugin.fastrequest.view.component.MyLanguageTextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HeaderGroupView extends DialogWrapper {
    private final Project myProject;
    private final String checkdEnv;
    private final String enableProjectName;
    private final List<String> envList;
    private HeaderGroup viewHeaderGroup;
    private Map<String, Boolean> validMap = new HashMap<>();

    public HeaderGroupView(Project project, HeaderGroup currentHeaderGroup, String enableProjectName, String enableEnv, List<String> envList) {
        super(project, false);
        this.myProject = project;
        this.checkdEnv = enableEnv;
        this.envList = envList;
        this.enableProjectName = enableProjectName;
        this.viewHeaderGroup = JSONObject.parseObject(JSONObject.toJSONString(currentHeaderGroup), HeaderGroup.class);
        init();
        setTitle(MyResourceBundleUtil.getKey("header.group.manage"));
    }

    public HeaderGroup changeAndGet() {
        return this.viewHeaderGroup;
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        MyLanguageTextField languageTextField = new MyLanguageTextField(myProject, JsonLanguage.INSTANCE, JsonFileType.INSTANCE);
        languageTextField.setMinimumSize(new Dimension(-1, 120));
        languageTextField.setPreferredSize(new Dimension(-1, 120));
        languageTextField.setMaximumSize(new Dimension(-1, 1000));
        languageTextField.setPlaceholder(MyResourceBundleUtil.getKey("header.text.example"));


        JBList<String> groupNameJbList = new JBList<>(new CollectionListModel<>(envList));
        groupNameJbList.setSelectedIndex(0);
        groupNameJbList.setSelectedValue(checkdEnv, false);
        groupNameJbList.addListSelectionListener(e -> {
            boolean isAdjusting = e.getValueIsAdjusting();
            if (isAdjusting) {
                String selectedEnv = groupNameJbList.getSelectedValue();
                if (viewHeaderGroup != null) {
                    Map<String, LinkedHashMap<String, String>> envMap = viewHeaderGroup.getEnvMap();
                    Map<String, String> keyValueMap = envMap.get(selectedEnv);
                    if (keyValueMap != null && !keyValueMap.isEmpty()) {
                        languageTextField.setText(JSON.toJSONString(keyValueMap));
                    } else {
                        languageTextField.setText("");
                        validMap.put(selectedEnv, true);
                    }
                } else {
                    languageTextField.setText("");
                    validMap.put(selectedEnv, true);
                }
                setOKActionEnabled(calcValid());
            }
        });

        languageTextField.addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                DocumentListener.super.documentChanged(event);
                String envName = groupNameJbList.getSelectedValue();
                String jsonText = languageTextField.getText();
                try {
                    validMap.put(envName, true);
                    setOKActionEnabled(calcValid());
                    LinkedHashMap<String, String> keyValueMap = JSON.parseObject(jsonText, new TypeReference<LinkedHashMap<String, String>>() {
                    });
                    if (viewHeaderGroup == null) {
                        LinkedHashMap<String, LinkedHashMap<String, String>> envMap = new LinkedHashMap<>();
                        envMap.put(envName, keyValueMap);
                        viewHeaderGroup = new HeaderGroup(enableProjectName, envMap);
                    } else {
                        Map<String, LinkedHashMap<String, String>> envMap = viewHeaderGroup.getEnvMap();
                        if (envMap == null) {
                            envMap = new LinkedHashMap<>();
                        }
                        envMap.put(envName, keyValueMap);
                    }
                } catch (Exception e) {
                    setOKActionEnabled(false);
                    validMap.put(envName, false);
                }
            }
        });

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(groupNameJbList);
        decorator.setMoveUpAction(null).setMoveDownAction(null).setAddAction(null).setRemoveAction(null);


        if (viewHeaderGroup != null) {
            Map<String, LinkedHashMap<String, String>> envMap = viewHeaderGroup.getEnvMap();
            if (envMap != null) {
                Map<String, String> headerKeyValueMap = envMap.get(checkdEnv);
                if (headerKeyValueMap != null && !headerKeyValueMap.isEmpty()) {
                    languageTextField.setText(JSON.toJSONString(headerKeyValueMap));
                }
            }
        }

        decorator.setPreferredSize(new Dimension(200, -1));
        return JBUI.Panels.simplePanel()
                .withPreferredSize(600, 400)
                .addToLeft(decorator.createPanel())
                .addToCenter(languageTextField);
    }

    private boolean calcValid() {
        return validMap.values().stream().filter(q -> !q).findAny().orElse(true);
    }
}
