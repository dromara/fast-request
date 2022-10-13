# API列表

::: tip API列表name取值:

1. 如果方法使用了swagger的@io.swagger.annotations.ApiOperation,则取该注解的value值
2. 如果没有swagger直接修饰,则取方法的javaDoc描述
3. 如果以上都没有则返回New Request

API的name值支持修改
:::

![api](../../.vuepress/public/img/apis_hd.png)
![apiManager](../../.vuepress/public/img/apiManager.png)