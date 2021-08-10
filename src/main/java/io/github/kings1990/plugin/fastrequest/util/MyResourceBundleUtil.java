package io.github.kings1990.plugin.fastrequest.util;

import io.github.kings1990.plugin.fastrequest.config.Constant;

import java.util.ResourceBundle;

public class MyResourceBundleUtil {

    public static String getKey(String key){
        return ResourceBundle.getBundle(Constant.I18N_PATH).getString(key);
    }
}
