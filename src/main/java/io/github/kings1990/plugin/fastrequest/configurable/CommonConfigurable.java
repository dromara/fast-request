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
