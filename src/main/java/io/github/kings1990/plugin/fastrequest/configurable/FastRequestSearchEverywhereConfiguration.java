package io.github.kings1990.plugin.fastrequest.configurable;

import com.intellij.ide.util.gotoByName.ChooseByNameFilterConfiguration;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;

@Service
@State(name = "FastRequestSearchEverywhereConfiguration", storages = @Storage(StoragePathMacros.CACHE_FILE))
public final class FastRequestSearchEverywhereConfiguration extends ChooseByNameFilterConfiguration<String> {

    public static FastRequestSearchEverywhereConfiguration getInstance() {
        return ApplicationManager.getApplication().getService(FastRequestSearchEverywhereConfiguration.class);
    }

    @Override
    protected String nameForElement(String type) {
        return type;
    }
}