# API 列表

::: tip API 列表 name 取值:

1. 如果方法使用了 swagger 的@io.swagger.annotations.ApiOperation,则取该注解的 value 值
1. 如果没有 swagger 直接修饰,则取方法的 javaDoc 描述
1. 如果以上都没有则返回 New Request

API 的 name 值支持修改
:::

![api](../../.vuepress/public/img/apis_hd.png)
![apiManager](../../.vuepress/public/img/apiManager.png)
