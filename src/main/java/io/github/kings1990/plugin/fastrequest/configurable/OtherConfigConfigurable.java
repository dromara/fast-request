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
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.sub.OtherConfigView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.ResourceBundle;

public class OtherConfigConfigurable extends AbstractConfigConfigurable {
    protected FastRequestConfiguration config;
    private final OtherConfigView view;

    public OtherConfigConfigurable() {
        config = FastRequestComponent.getInstance().getState();
        view = new OtherConfigView(config);
    }

    @Override
    public AbstractConfigurableView getView() {
        return view;
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        return view.getComponent();
    }

    @Override
    public String getDisplayName() {
        return ResourceBundle.getBundle(Constant.I18N_PATH).getString("OtherConfig");
    }

    @Override
    public boolean isModified() {
        List<DataMapping> currentUrlReplaceMappingList = view.getViewUrlReplaceMappingList();
        List<DataMapping> urlReplaceMappingList = config.getUrlReplaceMappingList();

        return !judgeEqual(currentUrlReplaceMappingList, urlReplaceMappingList);
    }

    @Override
    public void apply() {
        List<DataMapping> viewUrlReplaceMappingList = view.getViewUrlReplaceMappingList();
        List<DataMapping> changeUrlReplaceMappingList = JSONArray.parseArray(JSON.toJSONString(viewUrlReplaceMappingList), DataMapping.class);
        config.setUrlReplaceMappingList(changeUrlReplaceMappingList);
    }

    @Override
    public void reset() {
        super.reset();
        List<DataMapping> oldUrlReplaceMappingList = config.getUrlReplaceMappingList();
        List<DataMapping> oldUrlReplaceMappingListNew = JSONArray.parseArray(JSON.toJSONString(oldUrlReplaceMappingList), DataMapping.class);
        if(view.getUrlReplaceTable() != null){
            view.setViewUrlReplaceMappingList(oldUrlReplaceMappingListNew);
            view.getUrlReplaceTable().setModel(new ListTableModel<>(view.getColumnInfo(), oldUrlReplaceMappingListNew));
        }
    }

    public boolean judgeEqual(List<DataMapping> list1, List<DataMapping> list2) {
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

}
