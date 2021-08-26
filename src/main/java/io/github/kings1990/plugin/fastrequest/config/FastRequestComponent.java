package io.github.kings1990.plugin.fastrequest.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.NameGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@State(name = "fastRequest", storages = {@Storage("fastRequest.xml")})
public class FastRequestComponent implements PersistentStateComponent<FastRequestConfiguration> {

    private FastRequestConfiguration config;

    @Nullable
    @Override
    public FastRequestConfiguration getState() {
        if (config == null) {
            config = new FastRequestConfiguration();
            List<String> projectList = new ArrayList<>();
            List<String> envList = new ArrayList<>();
            List<NameGroup> dataList = new ArrayList<>();
            config.setDataList(dataList);
            config.setEnvList(envList);
            config.setProjectList(projectList);
            config.setRandomStringLength(5);
        }
        return config;
    }

    public static FastRequestComponent getInstance() {
        return ApplicationManager.getApplication().getService(FastRequestComponent.class);
    }

    @Override
    public void loadState(@NotNull FastRequestConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }


}
