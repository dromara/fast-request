package io.github.kings1990.plugin.fastrequest.i18n;

import com.intellij.AbstractBundle;
import com.intellij.DynamicBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class FastRequestBundle extends DynamicBundle {
    public static final String PATH_TO_BUNDLE = "io.github.kings1990.fastrequest.18n.fr";

    public static String message(@NotNull @PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, @NotNull Object... params) {
        return ourInstance.getMessage(key, params);
    }

    private static final AbstractBundle ourInstance = new FastRequestBundle();

    private FastRequestBundle() {
        super(PATH_TO_BUNDLE);
    }

    public FastRequestBundle(@NotNull String pathToBundle) {
        super(pathToBundle);
    }
}