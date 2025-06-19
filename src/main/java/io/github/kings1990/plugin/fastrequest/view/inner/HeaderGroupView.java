/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
