# API list

::: tip API name value calculate:

1. If the method uses swagger annotation **@io.swagger.annotations.ApiOperation**, then take the value of the annotation
2. If there is no swagger annotation, then take the java Doc description of the method.
3. If the two above do not match, return **New Request**.

API name value supports modification.
:::

![apis](/img/apis_hd_en.png "APIs")

![apiManager](/img/apiManager_en.png "APIs manager")
