# 保存请求

::: tip 特别说明

1. 保存的请求默认会放入*Default Group*中，支持拉拽放入别的组，当然最好是加入 module 分组，请查看 api 分组自动关联

1. API 取名:如果 api 使用了 swagger 注解 `@ApiOperation("xxx")`，则 api 取名 xxx，如果没加 swagger 注解，则使用 javadoc 作为 api 的名称，否则将取名 New Request

```
if (@ApiOperation("xxx"))
    apiName = xxx
else if(java doc)
    apiName = java doc
else
    apiName = New Request
```

:::
![example_download](/img/saveRequest.png)
