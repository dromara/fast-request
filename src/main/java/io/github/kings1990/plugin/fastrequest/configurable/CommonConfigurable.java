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

package io.github.kings1990.plugin.fastrequest.configurable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.ui.CollectionListModel;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.HostGroup;
import io.github.kings1990.plugin.fastrequest.model.NameGroup;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.CommonConfigView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class CommonConfigurable extends AbstractConfigConfigurable {
    protected FastRequestConfiguration config;
    private final CommonConfigView view;

    public CommonConfigurable() {
        config = FastRequestComponent.getInstance().getState();
        view = new CommonConfigView(config);
    }

    @Override
    public String getDisplayName() {
        return "Restful Fast Request";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return view.getComponent();
    }

    @Override
    public AbstractConfigurableView getView() {
        return view;
    }

    @Override
    public boolean isModified() {
        //project
        List<String> viewProjectList = view.getViewProjectList();
        List<String> configProjectList = config.getProjectList();
        boolean projectEqualFlag = judgeEqual(viewProjectList, configProjectList);
        if (!projectEqualFlag) {
            return true;
        }

        //env
        List<String> viewEnvList = view.getViewEnvList();
        List<String> configEnvList = config.getEnvList();
        boolean envEqualFlag = judgeEqual(viewEnvList, configEnvList);
        if (!envEqualFlag) {
            return true;
        }

        //globalHeader
        List<DataMapping> viewGlobalHeaderList = view.getGlobalHeaderList();
        List<DataMapping> configGlobalHeaderList = config.getGlobalHeaderList();
        boolean globalHeaderEqualFlag = judgeDataMappingEqual(viewGlobalHeaderList, configGlobalHeaderList);
        if (!globalHeaderEqualFlag) {
            return true;
        }

        //table
        List<NameGroup> viewDataList = view.getViewDataList();
        List<NameGroup> configDataList = config.getDataList();
        boolean dataEqualFlag = judgeDataEqual(viewDataList, configDataList);
        return !dataEqualFlag;
    }

    @Override
    public void apply() {
        //env
        List<String> viewEnvList = view.getViewEnvList();
        List<String> changeEnvList = JSONArray.parseArray(JSON.toJSONString(viewEnvList), String.class);
        config.setEnvList(changeEnvList);

        //project
        List<String> viewProjectList = view.getViewProjectList();
        List<String> changeProjectList = JSONArray.parseArray(JSON.toJSONString(viewProjectList), String.class);
        config.setProjectList(changeProjectList);

        //table
        List<NameGroup> viewDataList = view.getViewDataList();
        List<NameGroup> changeDataList = JSONArray.parseArray(JSON.toJSONString(viewDataList), NameGroup.class);
        config.setDataList(changeDataList);

        config.setEnableEnv(view.getViewEnableEnv());
        config.setEnableProject(view.getViewEnableProject());


        //globalHeaderList
        List<DataMapping> viewGlobalHeaderList = view.getGlobalHeaderList();
        List<DataMapping> changeGlobalHeaderList = JSONArray.parseArray(JSON.toJSONString(viewGlobalHeaderList), DataMapping.class);
        config.setGlobalHeaderList(changeGlobalHeaderList);

        //send message to change param
        DataContext dataContext = DataManager.getInstance().getDataContext(view.getComponent());
        Project project = dataContext.getData(LangDataKeys.PROJECT);
        if (project != null) {
            MessageBus messageBus = project.getMessageBus();
            messageBus.connect();
            ConfigChangeNotifier configChangeNotifier = messageBus.syncPublisher(ConfigChangeNotifier.ENV_PROJECT_CHANGE_TOPIC);
            configChangeNotifier.configChanged(true, project.getName());
        }
    }

    @Override
    public void reset() {
        //env
        List<String> oldEnvList = config.getEnvList();
        List<String> oldEnvListNew = JSONArray.parseArray(JSON.toJSONString(oldEnvList), String.class);
        view.setViewEnvList(oldEnvListNew);
        view.getEnvJbList().setModel(new CollectionListModel<>(oldEnvListNew));

        //project
        List<String> oldProjectList = config.getProjectList();
        List<String> oldProjectListNew = JSONArray.parseArray(JSON.toJSONString(oldProjectList), String.class);
        view.setViewProjectList(oldProjectListNew);
        view.getProjectNameJbList().setModel(new CollectionListModel<>(oldProjectListNew));

        //table
        List<NameGroup> oldDataList = config.getDataList();
        List<NameGroup> oldDataListNew = JSONArray.parseArray(JSON.toJSONString(oldDataList), NameGroup.class);
        view.setViewDataList(oldDataListNew);
        view.getTable().setModel(new ListTableModel<>(view.getColumnInfo(), oldDataListNew));


        //globalHeaderList
        List<DataMapping> oldGlobalHeaderList = config.getGlobalHeaderList();
        List<DataMapping> oldGlobalHeaderListNew = JSONArray.parseArray(JSON.toJSONString(oldGlobalHeaderList), DataMapping.class);
        if(view.getGlobalHeaderTable() != null){
            view.setGlobalHeaderList(oldGlobalHeaderListNew);
            view.getGlobalHeaderTable().setModel(new ListTableModel<>(view.getGlobalColumnInfo(), oldGlobalHeaderListNew));
        }

    }

    public boolean judgeDataMappingEqual(List<DataMapping> list1, List<DataMapping> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            DataMapping dataMapping1 = list1.get(i);
            DataMapping dataMapping2 = list2.get(i);
            if (!dataMapping1.getType().equals(dataMapping2.getType()) || !dataMapping1.getValue().equals(dataMapping2.getValue())) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeEqual(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            String value1 = list1.get(i);
            String value2 = list2.get(i);
            if (!value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeDataEqual(List<NameGroup> list1, List<NameGroup> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            NameGroup nameGroup1 = list1.get(i);
            NameGroup nameGroup2 = list2.get(i);
            if (!nameGroup1.getName().equals(nameGroup2.getName())) {
                return false;
            }
            List<HostGroup> hostGroupList1 = nameGroup1.getHostGroup();
            List<HostGroup> hostGroupList2 = nameGroup2.getHostGroup();
            if (hostGroupList1.size() != hostGroupList2.size()) {
                return false;
            }
            for (int j = 0; j < hostGroupList1.size(); j++) {
                HostGroup hostGroup1 = hostGroupList1.get(j);
                HostGroup hostGroup2 = hostGroupList2.get(j);
                if (!hostGroup1.getUrl().equals(hostGroup2.getUrl())) {
                    return false;
                }
            }
        }
        return true;
    }

}
