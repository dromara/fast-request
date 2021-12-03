package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.ide.util.gotoByName.ChooseByNameFilterConfiguration;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;

@Service
@State(name = "SearchEverywhereConfiguration", storages = @Storage(StoragePathMacros.CACHE_FILE))
public final class MySearchEverywhereConfiguration extends ChooseByNameFilterConfiguration<String> {

    public static MySearchEverywhereConfiguration getInstance() {
        return ApplicationManager.getApplication().getService(MySearchEverywhereConfiguration.class);
    }

    @Override
    protected String nameForElement(String type) {
        return type;
    }
}