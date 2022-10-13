# 保存请求

:::tip 特别说明
1.保存的请求默认会放入*Default Group*中,支持拉拽放入别的组,当然最好是加入module分组,请查看api分组自动关联

2.API取名:如果api使用了swagger注解`@ApiOperation("xxx")`,则api取名xxx,如果没加swagger注解,则使用javadoc作为api的名称,否则将取名New Request
```
if (@ApiOperation("xxx"))
    apiName = xxx
else if(java doc)
    apiName = java doc
else 
    apiName = New Request       
```
:::
![example_download](../../.vuepress/public/img/saveRequest.png)