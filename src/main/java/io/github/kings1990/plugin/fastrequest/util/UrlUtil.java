package io.github.kings1990.plugin.fastrequest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {
    public static List<String> paramPathParam(String url) {
        List<String> result = new ArrayList<>();
        Pattern p = Pattern.compile("\\{([^}]*)}");
        Matcher m = p.matcher(url);
        while (m.find()) {
            result.add(m.group(1));
        }
        return result;
    }
}
