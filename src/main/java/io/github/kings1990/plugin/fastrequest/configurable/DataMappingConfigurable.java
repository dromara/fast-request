package io.github.kings1990.plugin.fastrequest.configurable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.intellij.util.ui.ListTableModel;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.view.AbstractConfigurableView;
import io.github.kings1990.plugin.fastrequest.view.sub.DataMappingConfigView;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.ResourceBundle;

public class DataMappingConfigurable extends AbstractConfigConfigurable {
    protected FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
    private DataMappingConfigView view = new DataMappingConfigView(config);

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
        return ResourceBundle.getBundle(Constant.I18N_PATH).getString("DateTypeConfig");
    }

    @Override
    public boolean isModified() {
        List<DataMapping> currentCustomDataMappingList = view.getViewCustomDataMappingList();
        List<DataMapping> customDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> currentDefaultDataMappingList = view.getViewDefaultDataMappingList();
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();

        Integer randomStringLength = config.getRandomStringLength();
        Integer viewRandomStringLength = Integer.parseInt(view.getRandomStringTextField().getText());

        String randomStringStrategy = config.getRandomStringStrategy();
        String viewRandomStringStrategy = (String) view.getRandomStringStrategyComboBox().getSelectedItem();

        String randomStringDelimiter = config.getRandomStringDelimiter();
        String viewRandomStringDelimiter = view.getRandomStringDelimiterTextField().getText();

        return !randomStringDelimiter.equals(viewRandomStringDelimiter) ||
               !randomStringStrategy.equals(viewRandomStringStrategy) ||
               !randomStringLength.equals(viewRandomStringLength) ||
               !judgeEqual(currentCustomDataMappingList, customDataMappingList) ||
               !judgeEqual(currentDefaultDataMappingList, defaultDataMappingList);
    }

    @Override
    public void apply() {
        List<DataMapping> viewCustomDataMappingList = view.getViewCustomDataMappingList();
        List<DataMapping> changeCustomDataMappingList = JSONArray.parseArray(JSON.toJSONString(viewCustomDataMappingList), DataMapping.class);
        config.setCustomDataMappingList(changeCustomDataMappingList);

        List<DataMapping> viewDefaultDataMappingList = view.getViewDefaultDataMappingList();
        List<DataMapping> changeDefaultDataMappingList = JSONArray.parseArray(JSON.toJSONString(viewDefaultDataMappingList), DataMapping.class);
        config.setDefaultDataMappingList(changeDefaultDataMappingList);

        int viewRandomStringLength = Integer.parseInt(view.getRandomStringTextField().getText());
        config.setRandomStringLength(viewRandomStringLength);

        String viewRandomStringStrategy = (String) view.getRandomStringStrategyComboBox().getSelectedItem();
        config.setRandomStringStrategy(viewRandomStringStrategy);

        String viewRandomStringDelimiter = view.getRandomStringDelimiterTextField().getText();
        config.setRandomStringDelimiter(viewRandomStringDelimiter);

        KV.changeConfig();
    }

    @Override
    public void reset() {
        super.reset();
        List<DataMapping> oldCustomDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> oldCustomDataMappingListNew = JSONArray.parseArray(JSON.toJSONString(oldCustomDataMappingList), DataMapping.class);
        List<DataMapping> oldDefaultDataMappingList = config.getDefaultDataMappingList();
        List<DataMapping> oldDefaultDataMappingListNew = JSONArray.parseArray(JSON.toJSONString(oldDefaultDataMappingList), DataMapping.class);
        int randomStringLength = config.getRandomStringLength();
        String randomStringStrategy = config.getRandomStringStrategy();
        String randomStringDelimiter = config.getRandomStringDelimiter();
        view.setViewCustomDataMappingList(oldCustomDataMappingListNew);
        view.setViewDefaultDataMappingList(oldDefaultDataMappingListNew);
        view.getCustomTable().setModel(new ListTableModel<>(view.getColumnInfo(), oldCustomDataMappingListNew));
        view.getDefaultDataMappingTable().setModel(new ListTableModel<>(view.getColumnInfo(), oldDefaultDataMappingListNew));
        view.getRandomStringTextField().setText(randomStringLength + "");
        view.getRandomStringStrategyComboBox().setSelectedItem(randomStringStrategy);
        view.getRandomStringDelimiterTextField().setText(randomStringDelimiter);
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
