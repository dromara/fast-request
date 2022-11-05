# Save request

::: tip Special Note

1. The saved request will be put in _Default Group_ by default, support drag and drop into other groups,Of course, it is best to join the module group, please see `API group automatic association`

1. API Name:If the api uses swagger annotations `@ApiOperation("xxx")`,The api is named xxx,If there is no swagger annotation,Use javadoc as the name of the api,Otherwise it will be named New Request

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
