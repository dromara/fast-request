/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *  
 */

package io.github.kings1990.plugin.fastrequest.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeUtil {
    public static String calcType(String dataMappingType) {
        switch (dataMappingType) {
            case "byte":
            case "java.lang.Byte":
            case "short":
            case "java.lang.Short":
            case "int":
            case "java.lang.Integer":
            case "long":
            case "java.lang.Long":
            case "float":
            case "java.lang.Float":
            case "double":
            case "java.lang.Double":
            case "java.math.BigDecimal":
                return Type.Number.name();
            case "boolean":
            case "java.lang.Boolean":
                return Type.Boolean.name();
            case "java.lang.String":
            case "java.util.Date":
            case "java.sql.Date":
            case "java.sql.Timestamp":
            case "java.time.LocalDate":
            case "java.time.LocalTime":
            case "java.time.LocalDateTime":
            case "java.time.YearMonth":
                return Type.String.name();
            case "List":
            case "Set":
                return Type.Array.name();
            default:
                return Type.Object.name();
        }
    }

    public static String calcTypeByValue(Object value) {
        String name = value.getClass().getName();
        switch (name){
            case "java.lang.Boolean":
                return Type.Boolean.name();
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer":
            case "java.lang.Long":
            case "java.lang.Float":
            case "java.lang.Double":
            case "java.lang.Character":
            case "java.math.BigDecimal":
                return Type.Number.name();
            default:
                return Type.String.name();
        }
    }

    public static String calcTypeByStringValue(String value) {
        if ("true".equals(value) || "false".equals(value)) {
            return Type.Boolean.name();
        } else if (isNumeric(value)) {
            return Type.Number.name();
        } else {
            return Type.String.name();
        }
    }

    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public enum Type {
        Object,
        Array,
        String,
        Number,
        Boolean,
        File
    }
}
