package io.github.kings1990.plugin.fastrequest.configurable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.sub.OtherConfigView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class OtherConfigConfigurable extends AbstractConfigConfigurable {
    protected FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
    private OtherConfigView view = new OtherConfigView(config);

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
        return "Other Config";
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
