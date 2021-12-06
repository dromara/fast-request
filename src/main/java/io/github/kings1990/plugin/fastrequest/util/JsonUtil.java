/*
 * Copyright 2021 kings1990
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
