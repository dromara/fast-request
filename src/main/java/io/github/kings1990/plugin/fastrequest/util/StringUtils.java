/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kings1990.plugin.fastrequest.util;

import cn.hutool.core.util.RandomUtil;

public class StringUtils {
    public static String randomString(String name, String delimiter, int length, String mode) {
        //������
        if ("none".equals(mode)) {
            return "";
        } else if ("name+random".equals(mode)) {
            //�ֶ���+���
            if(length < 1){
                return name;
            }
            return name + delimiter + RandomUtil.randomString(length);
        } else {
            //���
            return RandomUtil.randomString(length);
        }
    }
}
