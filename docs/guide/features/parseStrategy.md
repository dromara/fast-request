# 字段解析

## 类型映射

[了解详情](../getstarted/dataMapping.md)

## 忽略字段

方式 1： 给字段使用如下 2 种注解中的一种

```
com.fasterxml.jackson.annotation.JsonIgnore

com.alibaba.fastjson.annotation.JSONField(serialize = false)
```

方式 2： 给字段增加 `static` 修饰符

方式 3：注释上加@fastRequestParseIgnore

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

方式 4: 忽略字段名解析配置

![ignoreFiled](/img/2024.1.1/ignoreFiled.png)

只需要在配置中增加字段名，即可将实体类中的特定字段忽略生成
