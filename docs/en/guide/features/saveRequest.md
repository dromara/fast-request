# Save request

::: tip Note
**API Naming**: If the API uses swagger annotations `@ApiOperation("xxx")`, the API is named `xxx`. If there is no swagger annotation, Use JavaDoc as the name of the API. Otherwise, it will be named with `New Request`.

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
