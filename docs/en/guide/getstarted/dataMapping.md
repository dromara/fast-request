---
title: Data mapping
icon: dataMapping
---

## Custom type mapping

Scenes: there are 10 attributes in `com.baomidou.mybatisplus.extension.plugins.pagination.Page`, but we just need size and current property.

A total of 2 values need to be set for custom type mapping.

**Java Type** is the corresponding object type. It must contain the package name and class name.

```java
com.baomidou.mybatisplus.extension.plugins.pagination.Page
```

**Default value** must be in json format

```json
{ "size": 10, "current": 1 }
```

![](/img/customDataMapping_en.png)

## Default type mapping

This configuration determines that the basic java type will be resolved to the appropriate value, supporting modification.

![](/img/defaultDataMapping_en.png)

## Ignore data mapping

This configuration determines whether the corresponding class is resolved or not and requires full path of java class(packageName+className)

![](/img/ignoreDataMapping_en.png)
