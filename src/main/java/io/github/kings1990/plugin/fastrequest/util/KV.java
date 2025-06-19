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

import cn.hutool.core.lang.tree.Tree;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocToken;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import com.siyeh.ig.psiutils.CollectionUtils;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;


public class KV<K, V> extends LinkedHashMap<K, V> {
    public static Map<String, Object> normalTypes = new HashMap<>();
    private static int randomStringLength;
    private static String randomStringStrategy;
    private static String randomStringDelimiter;
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat df = new SimpleDateFormat(pattern);
    public static Tree<String> root = new Tree<>();
    public static Map<String, Integer> parseCount = new HashMap<>();
    public static Map<String, Integer> parseListEntityCount = new HashMap<>();
    public static Map<String, Integer> parseArrayEntityCount = new HashMap<>();

    public static void reset() {
        root = new Tree<>();
        parseCount = new HashMap<>();
        parseListEntityCount = new HashMap<>();
        parseArrayEntityCount = new HashMap<>();
    }

    static {

        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        changeConfig();
        randomStringLength = config.getRandomStringLength();
        randomStringStrategy = config.getRandomStringStrategy();
        randomStringDelimiter = config.getRandomStringDelimiter();

        normalTypes.put("java.lang.String", "");

    }

    public static void changeConfig(){
        FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
        assert config != null;
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();
        normalTypes.put("byte", Byte.parseByte(defaultDataMappingList.get(0).getValue()));
        normalTypes.put("java.lang.Byte", Byte.parseByte(defaultDataMappingList.get(1).getValue()));
        normalTypes.put("short", Short.parseShort(defaultDataMappingList.get(2).getValue()));
        normalTypes.put("java.lang.Short", Short.parseShort(defaultDataMappingList.get(3).getValue()));
        normalTypes.put("int", Integer.parseInt(defaultDataMappingList.get(4).getValue()));
        normalTypes.put("java.lang.Integer", Integer.parseInt(defaultDataMappingList.get(5).getValue()));
        normalTypes.put("long", Long.parseLong(defaultDataMappingList.get(6).getValue()));
        normalTypes.put("java.lang.Long", Long.parseLong(defaultDataMappingList.get(7).getValue()));
        normalTypes.put("char", defaultDataMappingList.get(8).getValue());
        normalTypes.put("java.lang.Character", defaultDataMappingList.get(9).getValue());
        normalTypes.put("float", Float.parseFloat(defaultDataMappingList.get(10).getValue()));
        normalTypes.put("java.lang.Float", Float.parseFloat(defaultDataMappingList.get(11).getValue()));
        normalTypes.put("double", Double.parseDouble(defaultDataMappingList.get(12).getValue()));
        normalTypes.put("java.lang.Double", Double.parseDouble(defaultDataMappingList.get(13).getValue()));
        normalTypes.put("boolean", Boolean.parseBoolean(defaultDataMappingList.get(14).getValue()));
        normalTypes.put("java.lang.Boolean", Boolean.parseBoolean(defaultDataMappingList.get(15).getValue()));
        normalTypes.put("java.math.BigDecimal", new BigDecimal(defaultDataMappingList.get(16).getValue()));

        randomStringLength = config.getRandomStringLength();
        randomStringStrategy = config.getRandomStringStrategy();
        randomStringDelimiter = config.getRandomStringDelimiter();
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
        normalTypes.put("java.time.YearMonth", YearMonth.now(ZoneId.of(JSON.defaultTimeZone.getID())).toString());


        KV kv = KV.create();
//        KV commentKV = KV.create();


        if (psiClass != null) {
            String psiClassType = PsiTypesUtil.getClassType(psiClass).getCanonicalText();
            Tree<String> parent = new Tree<>();
            parent.setId(psiClassType);
            parent.setName(psiClassType);
//            if (root.getNode(psiClassType) == null) {
//                root.addChildren(parent);
//            }


            for (PsiField field : psiClass.getAllFields()) {
                PsiType type = field.getType();
                String name = field.getName();
                PsiDocComment docComment = field.getDocComment();
                StringBuilder commentStringBuilder = new StringBuilder();
                if (docComment != null) {
                    PsiElement[] descriptionElements = docComment.getDescriptionElements();
                    for (PsiElement descriptionElement : descriptionElements) {
                        if (descriptionElement instanceof PsiDocToken) {
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
                            String v = StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy);
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
                            ParamKeyValue paramKeyValue = new ParamKeyValue("", defaultValue, 2, TypeUtil.calcTypeByValue(defaultValue), comment);
                            list.add(paramKeyValue);
                        } else if (isNormalType(deepTypeName)) {
                            if ("java.lang.String".equals(deepTypeName)) {
                                String v = StringUtils.randomString(name, randomStringDelimiter, randomStringLength, randomStringStrategy);
                                ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.Type.String.name(), comment);
                                list.add(paramKeyValue);
                            } else {
                                Object v = normalTypes.get(deepTypeName);
                                ParamKeyValue paramKeyValue = new ParamKeyValue(name, v, 2, TypeUtil.Type.Array.name(), comment);
                                kv.set(name, paramKeyValue);
                            }
                        } else if ("org.springframework.web.multipart.MultipartFile".equals(deepTypeName)) {
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name(), comment);
                            list.add(paramKeyValue);
                        } else {
                            PsiClass psiClassInArray = PsiUtil.resolveClassInType(deepType);
                            String canonicalText = PsiTypesUtil.getClassType(psiClassInArray).getCanonicalText();
                            String key = canonicalText + ":" + name;
                            if (parseArrayEntityCount.get(key) == null) {
                                parseArrayEntityCount.put(key, 1);
                            } else {
                                parseArrayEntityCount.put(key, 2);
                            }
                            if (parseArrayEntityCount.get(key) > 1) {
                                continue;
                            }
                            list.add(getFields(psiClassInArray));
                        }
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment);
                        kv.set(name, paramKeyValue);
                    } else if (CollectionUtils.isCollectionClassOrInterface(type)) {   //list type
                        PsiType iterableType = PsiUtil.extractIterableTypeParameter(type, false);
                        //无泛型指定
                        if (iterableType == null) {
                            continue;
                        }
                        PsiClass iterableClass = PsiUtil.resolveClassInClassTypeOnly(iterableType);
                        ArrayList list = new ArrayList<>();
                        String classTypeName = iterableClass.getQualifiedName();

                        if (isNormalType(classTypeName)) {
                            ParamKeyValue paramKeyValue;
                            if ("java.lang.String".equals(classTypeName) || "String".equals(classTypeName)) {
                                String v = StringUtils.randomString(name, randomStringDelimiter, randomStringLength, randomStringStrategy);
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
                            String canonicalText = PsiTypesUtil.getClassType(iterableClass).getCanonicalText();
                            String key = canonicalText + ":" + name;
                            if (parseListEntityCount.get(key) == null) {
                                parseListEntityCount.put(key, 1);
                            } else {
                                parseListEntityCount.put(key, 2);
                            }
                            if (parseListEntityCount.get(key) > 1) {
                                continue;
                            }
                            list.add(getFields(iterableClass));
                            ParamKeyValue paramKeyValue = new ParamKeyValue(name, list, 2, TypeUtil.Type.Array.name(), comment);
                            kv.set(name, paramKeyValue);
                        }
                    }  else if("org.springframework.web.multipart.MultipartFile".equals(fieldTypeName)){
                        //文件上传
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, "", 2, TypeUtil.Type.File.name(), comment);
                        kv.set(name, paramKeyValue);
                    } else if (PsiUtil.resolveClassInClassTypeOnly(type).isEnum()) { //enum
                        String value = null;
                        PsiField[] fieldList = PsiUtil.resolveClassInClassTypeOnly(type).getFields();
                        if (fieldList != null && fieldList.length > 0) {
                            for (PsiField f : fieldList) {
                                if (f instanceof PsiEnumConstant) {
                                    value = f.getName();
                                    break;
                                }
                            }
                        }
                        PsiClass psiClassDeep = PsiUtil.resolveClassInClassTypeOnly(PsiTypesUtil.getClassType(psiClass).getDeepComponentType());
                        boolean outIsEnum = psiClassDeep != null && psiClassDeep.isEnum();
                        ParamKeyValue paramKeyValue = new ParamKeyValue(name, value, 2, TypeUtil.Type.String.name(), comment);
                        kv.set(name, paramKeyValue);
                        if (outIsEnum) {
                            //最外层参数直接是enum
                            break;
                        }
                    } else {    //class type
                        String canonicalText = type.getCanonicalText();
                        Tree<String> child = new Tree<>();
                        child.setId(canonicalText);
                        child.setName(canonicalText);
                        child.setParentId(psiClassType);
                        if (canonicalText.equals(psiClassType)) {
                            Integer count = parseCount.get(canonicalText + ":" + name);
                            if (count == null) {
                                parseCount.put(canonicalText + ":" + name, 1);
                            } else {
                                parseCount.put(canonicalText + ":" + name, 2);
                            }
                        } else {
                            Tree<String> calcParent = root.getNode(psiClassType);
                            if (calcParent != null) {
                                parent = calcParent;
                            }
                            if (parent.getNode(canonicalText) == null) {
                                parent.addChildren(child);
                            }
                            List<CharSequence> parentsName = parent.getParentsName(canonicalText, false);
                            if (parentsName.contains(canonicalText) || canonicalText.equals(parent.getParentId())) {
                                continue;
                            }
                        }

                        if (canonicalText.equals(psiClassType)) {
                            if (parseCount.get(canonicalText + ":" + name) > 1) {
                                continue;
                            }
                        }
                        if (root.getNode(psiClassType) == null) {
                            root.addChildren(parent);
                        }
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
        return normalTypes.get(type.getCanonicalText());
    }
}
