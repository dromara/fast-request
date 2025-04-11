# Parse strategy

## Data mapping

[Learn more](../getstarted/dataMapping.md)

## How to ignore the field

Way 1: Use one of the following 2 annotations.

```
com.fasterxml.jackson.annotation.JsonIgnore

com.alibaba.fastjson.annotation.JSONField(serialize = false)
```

Way 2: Add the `static` modifier to the field.

Way 3: Add `@fastRequestParseIgnore` in comment

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField;
```

Way 4: Ignore field name

![ignoreFiled](/img/2024.1.1/ignoreFiled_en.png)

Only need to add field names in the configuration to ignore target fields in the entity class.
