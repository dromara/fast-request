package io.github.kings1990.plugin.fastrequest.util;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocToken;
import com.intellij.psi.util.PsiUtil;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


public class KV<K, V> extends LinkedHashMap<K, V> {
    private static final Map<String, Object> normalTypes = new HashMap<>();
    private static final int randomStringLength;
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat df = new SimpleDateFormat(pattern);

    static {
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        normalTypes.put("java.lang.Byte", 1);
        normalTypes.put("java.lang.Short", 1);
        normalTypes.put("java.lang.Integer", 1);
        normalTypes.put("java.lang.Long", 1);
        normalTypes.put("java.lang.Character", "\\u0000");
        normalTypes.put("java.lang.Float", 1);
        normalTypes.put("java.lang.Double", 1);
        normalTypes.put("java.lang.Boolean", true);
        normalTypes.put("java.math.BigDecimal", 1);

        randomStringLength = config.getRandomStringLength();
        normalTypes.put("java.lang.String", "");

    }

    public <K, V> KV() {
    }

    public static <K, V> KV by(K key, V value) {
        return new KV().set(key, value);
    }

    public static <K, V> KV<K, V> create() {
        return new KV();
    }

    private static boolean isNormalType(String typeName) {
        return normalTypes.containsKey(typeName);
    }

    public static KV getFields(PsiClass psiClass) {
        normalTypes.put("java.util.Date", df.format(new Date()));
        normalTypes.put("java.sql.Date", df.format(new Date()));
        normalTypes.put("java.sql.Timestamp", System.currentTimeMillis());
        normalTypes.put("java.time.LocalDate", LocalDate.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString());
        normalTypes.put("java.time.LocalTime", LocalTime.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString());
        normalTypes.put("java.time.LocalDateTime", LocalDateTime.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString());

        KV kv = KV.create();
//        KV commentKV = KV.create();

        if (psiClass != null) {
            for (PsiField field : psiClass.getAllFields()) {
                PsiType type = field.getType();
                String name = field.getName();
                PsiDocComment docComment = field.getDocComment();
                StringBuilder commentStringBuilder = new StringBuilder();
                if(docComment != null){
                    PsiElement[] descriptionElements = docComment.getDescriptionElements();
                    for (PsiElement descriptionElement : descriptionElements) {
                        if(descriptionElement instanceof PsiDocToken){
                            commentStringBuilder.append(descriptionElement.getText());
                        }
                    }
                }

                String comment = commentStringBuilder.toString().trim();

                if (Objects.requireNonNull(field.getModifierList()).hasExplicitModifier(PsiModifier.STATIC)) {
                    //fix static变量不转化
                    continue;
                }
                //doc comment
//                if (field.getDocComment() != null && field.getDocComment().getText() != null) {
//                    commentKV.set(name, field.getDocComment().getText());
//                }

                if (type instanceof PsiPrimitiveType) {       //primitive Type
                    //基本类型
                    Object defaultValue = getPrimitiveDefaultValue(type);
                    ParamKeyValue paramKeyValue = new ParamKeyValue(name, defaultValue, 2, TypeUtil.calcTypeByValue(defaultValue), comment);
                    kv.set(name, paramKeyValue);
                } else {    //reference Type
//                    String fieldTypeName = type.getPresentableText();
                    String fieldTypeName = type.getCanonicalText();
                    if (isNormalType(fieldTypeName)) {    //normal Type
                        if ("java.lang.String".equals(fieldTypeName)) {
                            String v = randomStringLength < 1 ? "" : RandomUtil.randomString(randomStringLength);
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.Type.String.name(), comment);
                            kv.set(name, paramKeyValue);
                        } else {
                            Object v = normalTypes.get(fieldTypeName);
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.calcTypeByValue(v), comment);
                            kv.set(name, paramKeyValue);
                        }

                    } else if (type instanceof PsiArrayType) {   //数组
                        PsiType deepType = type.getDeepComponentType();
                        ArrayList list = new ArrayList<>();
//                        String deepTypeName = deepType.getPresentableText();
                        String deepTypeName = deepType.getCanonicalText();
                        if (deepType instanceof PsiPrimitiveType) {
                            Object defaultValue = getPrimitiveDefaultValue(deepType);
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, defaultValue, 2, TypeUtil.calcTypeByValue(defaultValue), comment);
                            KV kvIn = KV.create();
                            kvIn.set(name,paramKeyValue);
                            list.add(kvIn);
                        } else if (isNormalType(deepTypeName)) {
                            if ("java.lang.String".equals(deepTypeName)) {
                                String v = randomStringLength < 1 ? "" : RandomUtil.randomString(randomStringLength);
                                ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.Type.String.name(), comment);
                                KV kvIn = KV.create();
                                kvIn.set(name,paramKeyValue);
                                list.add(kvIn);
                            } else {
                                Object v = normalTypes.get(deepTypeName);
                                ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.calcTypeByValue(v), comment);
                                KV kvIn = KV.create();
                                kvIn.set(name,paramKeyValue);
                                list.add(kvIn);
                            }
                        }  else if("org.springframework.web.multipart.MultipartFile".equals(deepTypeName)){
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name(), comment);
                            list.add(paramKeyValue);
                        } else {
                            KV fields = getFields(PsiUtil.resolveClassInType(deepType));
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, fields, 2, TypeUtil.Type.Object.name(), comment);
                            KV kvIn = KV.create();
                            kvIn.set(name,paramKeyValue);
                            list.add(kvIn);
                        }
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment);
                        kv.set(name, paramKeyValue);
                    } else if (fieldTypeName.contains("List")) {   //list type
                        PsiType iterableType = PsiUtil.extractIterableTypeParameter(type, false);
                        PsiClass iterableClass = PsiUtil.resolveClassInClassTypeOnly(iterableType);
                        ArrayList list = new ArrayList<>();
                        String classTypeName = iterableClass.getQualifiedName();

                        if (isNormalType(classTypeName)) {
                            ParamKeyValue paramKeyValue;
                            if ("java.lang.String".equals(classTypeName) || "String".equals(classTypeName)) {
                                String v = randomStringLength < 1 ? "" : RandomUtil.randomString(randomStringLength);
                                paramKeyValue = new ParamKeyValue("", v, 2, TypeUtil.Type.String.name(), comment);
                                list.add(paramKeyValue);
                            } else {
                                Object v = normalTypes.get(classTypeName);
                                paramKeyValue = new ParamKeyValue("", v, 2, TypeUtil.calcTypeByValue(v), comment);
                                list.add(paramKeyValue);
                            }
                            kv.set(name, new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment));
                        } else if("org.springframework.web.multipart.MultipartFile".equals(classTypeName)){
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name(), comment);
                            list.add(paramKeyValue);
                            kv.set(name, new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment));
                        } else {
                            list.add(getFields(iterableClass));
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment);
                            kv.set(name, paramKeyValue);
                        }
                    }  else if("org.springframework.web.multipart.MultipartFile".equals(fieldTypeName)){
                        //文件上传
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name(), comment);
                        kv.set(name, paramKeyValue);
                    } else if (PsiUtil.resolveClassInClassTypeOnly(type).isEnum()) { //enum
                        ArrayList namelist = new ArrayList<String>();
                        PsiField[] fieldList = PsiUtil.resolveClassInClassTypeOnly(type).getFields();
                        if (fieldList != null) {
                            for (PsiField f : fieldList) {
                                if (f instanceof PsiEnumConstant) {
                                    namelist.add(f.getName());
                                }
                            }
                        }
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, namelist, 2, TypeUtil.Type.Array.name(), comment);
                        kv.set(name, paramKeyValue);
                    } else {    //class type
                        KV fields = getFields(PsiUtil.resolveClassInType(type));
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, fields, 2, TypeUtil.Type.Object.name(), comment);
                        kv.set(name, paramKeyValue);
                    }
                }
            }
        }

        return kv;
    }

    public KV set(K key, V value) {
        super.put(key, value);
        return this;
    }

    public KV set(Map map) {
        super.putAll(map);
        return this;
    }

    public KV set(KV KV) {
        super.putAll(KV);
        return this;
    }

    public KV delete(Object key) {
        super.remove(key);
        return this;
    }

    public <T> T getAs(Object key) {
        return (T) get(key);
    }

    public String getStr(Object key) {
        return (String) get(key);
    }

    public Integer getInt(Object key) {
        return (Integer) get(key);
    }

    public Long getLong(Object key) {
        return (Long) get(key);
    }

    public Boolean getBoolean(Object key) {
        return (Boolean) get(key);
    }

    public Float getFloat(Object key) {
        return (Float) get(key);
    }

    /**
     * key 存在，并且 value 不为 null
     */
    public boolean notNull(Object key) {
        return get(key) != null;
    }

    /**
     * key 不存在，或者 key 存在但 value 为null
     */
    public boolean isNull(Object key) {
        return get(key) == null;
    }

    /**
     * key 存在，并且 value 为 true，则返回 true
     */
    public boolean isTrue(Object key) {
        Object value = get(key);
        return (value instanceof Boolean && ((Boolean) value));
    }

    /**
     * key 存在，并且 value 为 false，则返回 true
     */
    public boolean isFalse(Object key) {
        Object value = get(key);
        return (value instanceof Boolean && (!((Boolean) value)));
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toPrettyJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    public boolean equals(Object KV) {
        return KV instanceof KV && super.equals(KV);
    }


    public static Object getPrimitiveDefaultValue(PsiType type) {
        if (!(type instanceof PsiPrimitiveType)) return null;
        switch (type.getCanonicalText()) {
            case "boolean":
                return true;
            case "byte":
                return (byte)1;
            case "char":
                return '\1';
            case "short":
                return (short)1;
            case "int":
                return 1;
            case "long":
                return 1L;
            case "float":
                return 1F;
            case "double":
                return 1D;
            default:
                return null;
        }
    }
}