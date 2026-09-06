---
url: /en/guide/feature.md
---
\==Deprecated==,[Please click here](./features/README.md)

\[\[toc]]

## Make icon move&#x20;

Developers can freely choose and switch the color you want in the drop-down box, and can make it moving

![newLogo](/img/2022.2.4/newLogo.gif)

![newLogoConfig](/img/2022.2.4/newLogoConfig.png)

## Debug API & send request&#x20;

![example](/img/example_en.gif)

In new version, send button have move to toolbar

![sendRequest](/img/sendRequest.png)

## SearchEveryWhere support&#x20;

```
example
/url              (search by url)
get /list         (search by get method and url)
post /save        (search by post method and url)
description keywords  (search by keywords)
```

![searchEveryWhere](/img/searchEveryWhere.gif)

## Send and download&#x20;

![example\_download](/img/downloadFile.png)

## Script&#x20;

Scripts allow developers to more flexibly, dynamically and easily modify some input parameters of the request process and the processing of responses.
Please refer to [Script](./script.md)

![scriptProject](/img/2022.2.3/scriptProject_en.png)

![scriptModule](/img/2022.2.3/scriptModule_en.png)

## APIs export to Postman&#x20;

![export2postman](/img/export2postman.gif)

## API List&#x20;

::: tip API name value calculate:

1. If the method uses swagger annotation **@io.swagger.annotations.ApiOperation**,Then take the value of the annotation
2. If there is no swagger annotation,Then take the java Doc description of the method
3. If two above not matched, return **New Request**

API name value supports modification\
:::

![apis](/img/apis_hd.png)
![apiManager](/img/apiManager_en.png)

## Save Request&#x20;

::: tip Special Note

1.The saved request will be put in *Default Group* by default, support drag and drop into other groups,Of course, it is best to join the module group, please see `API group automatic association`

2.API Name:If the api uses swagger annotations `@ApiOperation("xxx")`,The api is named xxx,If there is no swagger annotation,Use javadoc as the name of the api,Otherwise it will be named New Request

```java
if (@ApiOperation("xxx"))
    apiName = xxx
else if(java doc)
    apiName = java doc
else
    apiName = New Request
```

:::
![example\_download](/img/saveRequest.png)

## API grouping parameter save support&#x20;

Purpose: Save parameters of different combinations under one API

\==The original save operation will be classified into the Default group by default==

How to:After entering the parameters, click ==Save group param request==

![groupSave](/img/2022.2.1/groupSave_en.png)

**Toggle parameter**

The following is the query of the book list in 3 languages

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)

## Temporary request save support&#x20;

Purpose: Save any request that does not belong to this project for temporary invocation, not associated with the current project code

The Url of the Request saved by the temporary request must start with ==http== or ==https==

![tempSave](/img/2022.2.1/tempSave_en.png)

## Regenetate&#x20;

::: tip Special Note\
If you have saved a request,But if you want to completely re-modify the parameters, then you can choose this operation\
:::

![regenerate](/img/regenerate_en.png)

## CURL copy&#x20;

After generate the method url and parameters, click on the toolbar![curl](/img/icon/curl_dark.svg)

![curl](/img/curl_en.png)

## Quickly add header&#x20;

If your request requires a token and the token can be obtained through a login interface, then you don't need to manually add it each time, just visit the login interface and then process it through the following operations

![fastAddToken](/img/fastAddToken_en.gif)

## API group automatic association&#x20;

The API group is to save the saved apis into different groups to distinguish different apis,When in a multi-module project,plugin support scan modules in projects,and quickly add the module name to the group, this way will add the group to the root group

**v2.1.2 will automatically create module group**

![apiGroup](/img/apiGroup_en.gif)

::: tip Special Note

1. If the module group is not created, then when saving the request, the saved request will be placed in the Default Group
2. If you create a module group, when you save the request, it will be automatically classified into the corresponding Module Group according to the module where the current API is located.
3. Of course, you can move the API to the corresponding smaller group by dragging

:::

Module supports quick search(Put the cursor on the list and enter keywords)

![moduleSearch](/img/moduleSearch.gif)

## Json grammar check&#x20;

The upper right corner of the json parameter input box provides a json syntax check. If the json is incorrect, the corresponding error will be prompted\
Of course it also supports formatting and other operations

![json](/img/json_en.png)

## API navigate tree&#x20;

```
Select the tree and enter keywords
Press Enter again or double-click the left mouse button to locate the API

Hover the mouse to display the doc of the api

The API Navigate tree is lazy loaded by default, you need to click the refresh button,
and every time you add an API, you also need to refresh to get it

Lazy loading helps to speed up idea startup
```

![apinavi](/img/apinav.gif)

## Headers group(Automatic switching)&#x20;

```
Scenes:Multi-module projects such as SpringBoot have different header parameters in different projects and different environments. In order to quickly switch headers, header grouping is introduced.
Operation method：
1.Modify in the headers group, constraint: the input value must be in standard json format
2.Switch the environment or project name directly, and then enter the corresponding key and value values in the headers form
```

![headerSwitch](/img/headerSwitch.gif)

## APIs import and export&#x20;

Using this function, you can easily share your existing APIs with other developers, or import to IDEA on other devices

::: caution Attention

* A new file named fastRequestCollection.xml will be added when exporting,You can't rename it, it is exported to the current project path by default.

* When importing, it will do a default backup,And will generate a file named fastRequestCollection-yyyyMMddHHmmssSSS.xml under the .idea folder ,
  If it is imported by mistake, it can be restored by importing it

* Click file->Reload All from Disk to force refresh to get fastRequestCollection.xml if it is not visible

:::

![headerSwitch](/img/exportImportApis.gif)

## Swagger default value parsing support&#x20;

Below are some examples

Priority: swagger default value > config default value

::: code-tabs

@tab swagger2

```java
* @ApiParam

@GetMapping(value="/test/{id}")
public String test3(@ApiParam(name = "id",example="2") @PathVariable("id") Integer id) {
    return "";
}

@GetMapping(value="/test/{id}")
public String test3(@ApiParam(name = "id",defaultValue="2") @PathVariable("id") Integer id) {
    return "";
}


* @ApiImplicitParam

@ApiImplicitParams({
    @ApiImplicitParam(paramType="query",name="pageNo",dataType="String",required=true,value="pageNo",defaultValue="1"),
    @ApiImplicitParam(paramType="query",name="pageSize",dataType="String",required=true,value="pageSize",defaultValue="10")
})
@GetMapping(value="/testPage)
public String testPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
    return "";
}


* @ApiModelProperty
@Data
public class UserDto {
    @ApiModelProperty(example = "Bob")
    private String userName;
}
```

@tab swagger3

```java
* @Parameter

@GetMapping(value="/test/{id}")
public String test3(@Parameter(name = "id",example="2") @PathVariable("id") Integer id) {
    return "";
}

* @Schema(swagger3)

@Data
public class UserDto {
    @Schema(example = "Bob")
    private String userName;
}
```

:::

## Auto Description&#x20;

Field description need to conform to standard javadoc. Please use`/**some description*/`

You can hide or show description by toggle click

![paramDescription](/img/paramDescription.png)

## API share document&#x20;

Please note that the API in word form is implemented in html, so don't feel strange, just export it

Response Example requires running your API to be displayed

![shareDocButton](/img/shareApi_en.png)

![apiDocExample](/img/apiDocExample.png)

## API list preview&#x20;

After focusing on the window, enter the keyword, and you can quickly search according to the path keyword of the API

![apiDocExample](/img/apiPreview.gif)

## Project-level global parameters support&#x20;

Support global parameters within the project level, not affected by multiple modules.

Configure priority `api header > project header>global header`

![projectConfigParam](/img/projectConfigParam.png)

## cURL import&#x20;

![importByCurl](/img/2022.2.1/importByCurl_en.gif)

## Navigate to current method&#x20;

After you generate the url of the method, sometimes you will switch to another place in the code, and then want to start debugging the API method, you can use this function to quickly locate the code

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)

## History request&#x20;

Hidden skills: Double-click the record to display details

![history](/img/history.png)
:::

## Stop API request&#x20;

![stopApi](/img/2022.2.2/stopApi.gif)

## Batch export API doc&#x20;

![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)

## One click copy url&#x20;

![copyUrl](/img/2022.2.3/copyUrl.png)

## Html preview in response raw&#x20;

The response of the html return type is no longer truncated in raw, and the html can be previewed at the same time

![rawHtmlPreview](/img/rawHtmlPreview.png)

## Api comment preview&#x20;

![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
