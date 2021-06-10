package io.github.kings1990.plugin.fastrequest.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

public class JsonUtil {
    public static boolean isJSON2(String str) {
        try {
            JSON.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Object a = "true";
        boolean json = JSONUtil.isJson("2");
        System.out.println(json);
        System.out.println(a.getClass());
    }
}
