package io.github.kings1990.plugin.fastrequest.util;

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
                return Type.String.name();
            case "List":
                return Type.Array.name();
            default:
                return Type.Object.name();
        }
    }

    public static String calcTypeByValue(Object value) {
        String name = value.getClass().getName();
        if ("java.lang.Boolean".equals(name)) {
            return Type.Boolean.name();
        } else if ("java.lang.Integer".equals(name)) {
            return Type.Number.name();
        } else {
            return Type.String.name();
        }
    }

    public enum Type {
        Object,
        Array,
        String,
        Number,
        Boolean
    }
}
