package io.github.kings1990.plugin.fastrequest.util;

import cn.hutool.core.util.RandomUtil;

public class StringUtils {
    public static String randomString(String name, String delimiter, int length, String mode) {
        //不生成
        if ("none".equals(mode)) {
            return "";
        } else if ("name+random".equals(mode)) {
            //字段名+随机
            if(length < 1){
                return name;
            }
            return name + delimiter + RandomUtil.randomString(length);
        } else {
            //随机
            return RandomUtil.randomString(length);
        }
    }
}
