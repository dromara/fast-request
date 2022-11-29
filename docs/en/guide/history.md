---
title: History changes
icon: changelog
---

::: danger Increased Subscription Pricing for Plugin
Since the introduction of subscriptions, we have not increased the pricing of plugin. During this time, we are constantly developing new features, so far we have had 19 version, adding many very useful features, for example api doc sync, and is a very cost-effective plugin. We also lower the cost of subscriptions up to 40% when you renew. It is our way of thanking you for your loyalty.

However, we are at the point where we need to increase our subscription prices. The new prices  will come into effect on December 31, 2022. It will be adjusted from **$1/month** to **$1.9/month** and the annual subscription price will be 10 times the monthly subscription price (10 months).

> Prepay at the current price for more than one year

For both new and existing customers, we are extending the maximum permissible renewal period, you can go to [License page](https://account.jetbrains.com/licenses) to renew subscription. This is now three years for individual subscriptions, and two years for business subscriptions. While this will require an upfront payment, it will allow you to renew at the current price for more than one year.

::: right
2022-11-15 Kings

:::
## v2022.2.9 <Badge text="Free trial" type="tip"/> <Badge text="Newest version" color="LightGreen"/>
- <Badge text="Compatible with IDEA 2022.2.4" type="tip"/>
- <Badge text="Global animate config support" type="tip"/>
- <Badge text="Cookie bug" type="danger"/>
- <Badge text="Navigate tab load error" type="danger"/>

::: tip Global animate config support
You can close animate in this global config, if close, icon will no logger move

![animateConfig](/img/2022.2.9/animateConfig_en.png)
:::

## v2022.2.8 <Badge text="Free trial" type="tip"/>
- <Badge text="Directory download file support" type="info"/>
- <Badge text="@RequestParam support name attribute" type="info"/>
- <Badge text="Error when check update" type="info"/>

::: tip Directory download file support
When response contains `content-disposition:attachment`, click send will automatically adapt the download
:::

## v2022.2.7 <Badge text="Free trial" type="tip"/>

- <Badge text="Api doc sync" type="tip"/>
- <Badge text="Api doc show return type document" type="tip"/>
- <Badge text="Plugin update alert mechanism" type="info"/>
- <Badge text="Common header" type="tip"/>
- <Badge text="Full screen" type="tip"/>
- <Badge text="Comment preview" type="info"/>
- <Badge text="Module header value always be checked" type="danger"/>
- <Badge text="Parse bug" type="danger"/>
- <Badge text="Error when use string param in body" type="danger"/>

::: tip Api sync
Online Api doc sync---->[More detail](./features/apiDocSync.md)

![apiSync](/img/2022.2.7/apiSync_en.png)

![apiSyncSetting](/img/2022.2.7/apiSyncSetting_en.png)
:::

:::tip Api doc show return type document
![returnValueDoc](/img/2022.2.7/returnValueDoc.png)
:::

::: tip Plugin update notification mechanism
Change from an explicit dialog box to a notification, at the same time automatic updates have been changed to ==turn on==, if you want to turn it off, please go to the configuration page and turn it off manually.
But I recommend ==turning it on==, so that you can receive updates in time.

Note that the previous version of the update will still be a pop-up dialog, this version onwards will be a message notification.

![upgradeNotice](/img/2022.2.7/upgradeNotice_en.png)
:::

::: tip Common header

[---->More detail](./features/commonHeader.md)
![commonHeader](/img/2022.2.7/commonHeader.gif)
:::

::: tip Full screen
---->[More detail](./features/fullScreen.md)
![fullScreen](/img/2022.2.7/fullScreen.png)
:::

::: info Comment preview
![fullScreen](/img/2022.2.7/commentPreview.png)
:::

## v2022.2.6 <Badge text="Free trial" type="tip"/> <Badge text="Newest version" color="info"/>

- <Badge text="Clear params support" type="tip"/>
- <Badge text="Api comment preview" type="tip"/>
- <Badge text="Sort param column by key" type="tip"/>
- <Badge text="Api doc add required column" type="info"/>
- <Badge text="Long delay in checking for updates" type="info"/>
- <Badge text="Long delay when first open tool window" type="info"/>
- <Badge text="Multi print when use pre-script" type="danger"/>

::: tip Clear params support
clear all
![clear](/img/2022.2.6/clear.png)

batch clean column value
![clearColumnValue](/img/2022.2.6/clearColumnValue.png)
:::

::: tip Api comment preview
![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
:::

::: tip Sort param column by key
![sortColumn](/img/2022.2.6/sortColumn.png)
:::

## v2022.2.5.2 <Badge text="Free trial" type="tip"/>

- <Badge text="Post script cannot be cleared" type="danger"/>

## v2022.2.5.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Console syntax error" type="danger"/>

## v2022.2.5 <Badge text="Free trial" type="tip"/>

- <Badge text="Add console for script" type="tip"/>
- <Badge text="Add print script support" type="tip"/>
- <Badge text="Add currentProjectName and currentEnvName property in build-in property" type="tip"/>
- <Badge text="Get description from javadoc for path and request param" type="tip"/>
- <Badge text="@RequestPart support" type="tip"/>
- <Badge text="Add twitter in doc action group" type="tip"/>
- <Badge text="Post script not execute when response is success but code for example is 401" type="danger"/>

::: tip Console support
Console helps developers print some info you wanted

More info please see [script->console](./script.md#console)

![console](/img/2022.2.5/console_en.png)

:::

::: tip Add currentProjectName and currentEnvName property in build-in property
You can use these 2 variables for some judgment

More info please see [script->Built-in variable->rfr](./script.md#rfr)
:::

::: tip Get description from javadoc for path and request param
Parse parameter comments in the form of Javadoc, suitable for path parameters and request param is a parameter of non-entity class

![parseDocDesc](/img/2022.2.5/parseDocDesc.png)
:::

::: tip twitter
有推特账号的请关注我,感谢

![](/img/twitter.png)

![twitterAction](/img/2022.2.5/twitterAction.png)
:::

## v2022.2.4.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Fix the small screen EUAL agreement cannot display the agree button" type="danger"/>

## v2022.2.4 <Badge text="Free trial" type="tip"/>

- <Badge text="New logo" type="tip"/>
- <Badge text="Support parse date by annotation's pattern" type="tip"/>
- <Badge text="Support parse url of BaseController" type="tip"/>
- <Badge text="Change auto update configurable" type="info"/>
- <Badge text="Parameter encoding" type="info"/>
- <Badge text="Parse error when map not contains generics" type="danger"/>
- <Badge text="Post script can not add or remove header" type="danger"/>

::: tip New logo
Developers can freely choose and switch the color you want in the drop-down box, and can make it moving

![newLogo](/img/2022.2.4/newLogo.gif)

![newLogoConfig](/img/2022.2.4/newLogoConfig.png)

![newLogo](/img/2022.2.4/newLogo.png)

:::

::: tip Support parse date by DateTimeFormat annotation pattern
![dateTimeFormat](/img/2022.2.4/dateTimeFormat.png)
:::

::: tip Support parse url of BaseController
![baseController](/img/2022.2.4/baseController.png)
:::

::: info Change auto update configurable
If you don't need to receive automatic updates, you can turn it off and update it manually (recommend open)
![autoUpdate](/img/2022.2.4/autoUpdate.png)
:::

## v2022.2.3.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Fix the bug that the parameter cannot be cleared" type="danger"/>

## v2022.2.3 <Badge text="Free trial" type="tip"/>

- <Badge text="Pre-request Script" type="tip"/>
- <Badge text="Post-request Script" type="tip"/>
- <Badge text="One-click copy Url" type="tip"/>
- <Badge text="Compatible with IDEA 2021.2.1" type="info"/>
- <Badge text="RequestParam defaultValue parse" type="info"/>
- <Badge text="Apply table cell value change when click send request" type="info"/>
- <Badge text="Url can not generate after close tool window and reopen" type="danger"/>
- <Badge text="Jax-rs PATCH not supported" type="danger"/>

::: tip Script support
Scripts allow developers to more flexibly, dynamically and easily modify some input parameters of the request process and the processing of responses.
Please refer to [Script](./script.md)

![scriptProject](/img/2022.2.3/scriptProject_en.png)

![scriptModule](/img/2022.2.3/scriptModule_en.png)
:::

::: tip One-click copy Url
![copyUrl](/img/2022.2.3/copyUrl.png)
:::

::: info Apply table cell value change when click send request
Before ==2022.2.3==, when entering the parameters in the table, if the cursor is still inside the table, click the action button at this time, and in some scenarios, an error will be reported or the value cannot be modified, and the problem is solved in this version.

How the old version deals with it: [FAQ:After entering the parameters, the API call found that the parameters were invalid](./faq.md)
:::

## v2022.2.2 <Badge text="Free trial" type="warn"/>

- <Badge text="Add stop API request function" type="tip"/>
- <Badge text="Batch export API doc" type="tip"/>
- <Badge text="Add annotations when export to Postman" type="tip"/>
- <Badge text="Editor hangs in case of a large amount of response data" type="info"/>
- <Badge text="The experience and tips in some scenarios" type="info"/>
- <Badge text="SearchEveryWhere err in EAP/New ui version" type="danger"/>
- <Badge text="Error when modify number param in Multipart" type="danger"/>
- <Badge text="History request delete operation error in some cases" type="danger"/>

::: tip Add stop API request function
![stopApi](/img/2022.2.2/stopApi.gif)
:::

::: tip Batch export API doc
![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)
:::

## v2022.2.1 <Badge text="Free trial" type="warn"/>

- <Badge text="API grouping parameter save support" type="tip"/>
- <Badge text="Temporary request save support" type="tip"/>
- <Badge text="Add support for cURL import" type="tip"/>
- <Badge text="Add Response Header in response" type="tip"/>
- <Badge text="Add support for Url suffix" type="tip"/>
- <Badge text="Optimized parameter parsing" type="info"/>
- <Badge text="Optimize shortcut keys" type="info"/>
- <Badge text="Fix Word export bug" type="danger"/>

::: tip API grouping parameter save support
Purpose: Save parameters of different combinations under one API

==The original save operation will be classified into the Default group by default==

How to:After entering the parameters, click ==Save group param request==

![groupSave](/img/2022.2.1/groupSave_en.png)

**Toggle parameter**

The following is the query of the book list in 3 languages

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)
:::

::: tip Temporary request save support

Purpose: Save any request that does not belong to this project for temporary invocation, not associated with the current project code

The Url of the Request saved by the temporary request must start with ==http== or ==https==

![tempSave](/img/2022.2.1/tempSave_en.png)

:::

::: tip cURL import

![importByCurl](/img/2022.2.1/importByCurl_en.gif)

==Note that if you need to bind a method, you need to place the cursor on the method name==
:::

::: tip Add Response Header in response

![responseHeader](/img/2022.2.1/responseHeader.png)

:::

::: tip Add support for Url suffix

Purpose: Some requests need to add .do at the end of the url

![responseHeader](/img/2022.2.1/urlSuffix.png)

:::

::: info Optimized parameter parsing
Optimized parsing of `List<Entity>`
:::

## v2022.1.10 <Badge text="Free trial" type="warn"/>

- <Badge text="Add support for history request preview" type="tip"/>
- <Badge text="Add support for description search in SearchEveryWhere" type="tip"/>
- <Badge text="Add request timeout settings" type="tip"/>
- <Badge text="Shortcut key conflict optimization" type="info"/>
- <Badge text="Text editor character display cursor positioning optimization" type="info"/>
- <Badge text="Curl output format optimization" type="info"/>
- <Badge text="Optimization @Consumes(APPLICATION_JSON) ineffective problem(JAX-RS)" type="info"/>

::: tip Add support for history request preview
Hidden skills: Double-click the record to display details
![history](/img/history.png)
:::

::: info Add support for description search in SearchEveryWhere
Support description search API
![searchEveryWhereDescription](/img/searchEveryWhereDescription.png)
:::

::: tip Add request timeout settings
Default value: 60 seconds
![timeoutSetting](/img/timeoutSetting.png)
:::

::: info Optimization

- Shortcut key conflict optimization
  - Added Shift to the three shortcut keys for opening windows
- Text editor character display cursor positioning optimization
  - When the text editor generates a string, it focuses on the first line by default

:::

## v2022.1.9 <Badge text="Free trial" type="warn"/>

- <Badge text="Add shortcut keys for tool window jump" type="tip"/>
- <Badge text="Add auto add cookie support" type="tip"/>
- <Badge text="Add support for html preview in response raw" type="tip"/>
- <Badge text="Ignore field parsing optimizations" type="info"/>
- <Badge text="Generic parameter parsing support" type="info"/>
- <Badge text="Improve project global config" type="info"/>
- <Badge text="Interface optimization" type="info"/>
- <Badge text="Save API insertion order optimization" type="info"/>
- <Badge text="APIs list view optimization" type="info"/>
- <Badge text="Optimized export preview" type="info"/>
- <Badge text="Fixed bug when editing params at the same time delete param" type="danger"/>

::: tip Add shortcut keys for tool window jump
Provides shortcut keys to jump quickly, you can change it in `setting -> Keymap`
![toolwindowShortcut](/img/toolwindowShortcut.png)
:::

::: tip Add auto add cookie support
Then automatically stored in == project global parameters ==
If enable this configuration,cookie will automatically store in ==Project Global Config== -> `cookies`,This configuration is enabled by default.
![autoStoreCookie](/img/autoStoreCookie.png)
:::

::: info Add support for html preview in response raw
The response of the html return type is no longer truncated in raw, and the html can be previewed at the same time

![rawHtmlPreview](/img/rawHtmlPreview.png)
:::

::: info Ignore field parsing optimizations

- <Badge text="Ignore parsing annotated by `@JsonIgnore`、`@JSONField(serialize = false)` fields" type="info"/>
- <Badge text="Ignore parsing`transient` keyword-decorated fields" type="info"/>

:::

::: info Generic parameter parsing support
Support for such generic parameter parsing

```java
@Data
public class CommonDTO<T,R> implements Serializable {
    private String accessToken;
    private String appKey;
    private String clientId;
    private T data;
    private List<R> dataList;
}

@PostMapping(value = "/apply")
public Integer loanApply(@RequestBody CommonDTO<Book, TestDTO> req){
    return 1;
}
```

:::

::: info Improve project global config
Added `URL Params`、`URL-Encoded Params`、`Cookies`
![projectConfigParam](/img/projectGlobalParam.png)
:::

::: note Optimization

- Interface optimization
  - Optimized tab spacing(narrow under windows)、background color
- Save API insertion order optimization
  - The post-saved API will be placed on top

:::

::: tip APIs list view optimization
The rendering of the APIs interface has been optimized,Newly saved api icons are automatically optimized by default,The API saved in history can be right-clicked to select and change
![apisList](/img/apisList.png)
:::

## v2022.1.8 <Badge text="Free trial" type="warn"/>

- <Badge text="Project-level global parameters support" type="tip"/>
- <Badge text="Optimized response data quickly add to headers" type="info"/>
- <Badge text="Optimized cookies quickly added to the header" type="info"/>
- <Badge text="Postman export optimization" type="info"/>
- <Badge text="Fix navigate tree scan bug" type="danger"/>
- <Badge text="Fix some bug" type="danger"/>

::: tip Project-level global parameters support

Support global parameters within the project level, not affected by multiple modules.

Configure priority `api header > project header>global header`

![projectConfigParam](/img/projectConfigParam.png)
:::

::: info Quickly add to header parameters

Change ~~add params to global header~~ to `add params to project global header`

Cookie is also like this

![add2projectConfig](/img/add2projectConfig.png)

![cookie2projectConfig](/img/cookie2projectConfig.png)
:::

## v2022.1.7 <Badge text="Free trial" type="warn"/>

- <Badge text="Kotlin spring framework support" type="tip"/>
- <Badge text="Add SearchEveryWhere scope search strategy" type="tip"/>
- <Badge text="Add toggleUseSoftWrap support in editor" type="tip"/>
- <Badge text="Add support for add to Global Header from response" type="tip"/>
- <Badge text="Add support for navigating to the current method" type="tip"/>
- <Badge text="Optimize automatic binding projectName for saved api under multiple modules" type="info"/>

::: tip Kotlin spring framework support
![kotlinSupport](/img/kotlinSupport.png)
:::

::: info Add SearchEveryWhere scope search strategy
The 2022.1.7 version can search for mapping in SpringBoot feign or controller by default

The default search strategy is to search based on annotations, and two options are provided. If you need to exclude interfaces similar in SpringBoot feign interface during the search process, you can remove the check of Interface to deal with it.
![searchEveryWhereStrategy](/img/searchEveryWhereStrategy.png)
:::

::: note Add toggleUseSoftWrap support in editor

![toggleUseSoftWrap](/img/toggleUseSoftWrap.png)
:::

::: tip Add support for add to Global Header from response
It is recommended to use in multi-module projects, because Add to Headers can only be added to the current module project

![add2GlobalHeader](/img/add2GlobalHeader.png)
:::

::: note Add support for navigating to the current method
After you generate the url of the method, sometimes you will switch to another place in the code, and then want to start debugging the API method, you can use this function to quickly locate the code

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)
:::

::: info Optimize automatic binding projectName for saved api under multiple modules
Optimized the need to manually switch the project drop-down box for the saved api echo under the multi-module
:::

## v2022.1.6 <Badge text="Free trial" type="warn"/>

- <Badge text="Add support for one click to add cookie to header" type="tip"/>
- <Badge text="Add support for sharing API to Word and html" type="tip"/>
- <Badge text="Fix bug for `Basic Authorization` don't carry Basic" type="danger"/>
- <Badge text="Removes the drag function in APIs tab" type="danger"/>

::: tip Add support for one click to add cookie to header

For those that require cookie support for login, you can quickly add the cookie in the response to the Header to achieve this

![addCookie2Header](/img/addCookie2Header.png)
:::

::: tip Add support for sharing API to Word and html

Please note that the API in word form is implemented in html, so don't feel strange, just export it

![shareApi](/img/shareApi_en.png)
:::

## v2022.1.5 <Badge text="Free trial" type="warn"/>

- <Badge text="Add support for automatically generating parameter descriptions" type="tip"/>
- <Badge text="Add support for generate API documentation" type="tip"/>
- <Badge text="Add support for single class API list preview" type="tip"/>
- <Badge text="Remove text/html in Accept param in default header" type="danger"/>
- <Badge text="Fix some bugs" type="danger"/>

::: tip Add support for automatically generating parameter descriptions

You can hide or show description by toggle click

![paramDescription](/img/paramDescription.png)
:::

::: tip Add support for generate API documentation

After generating parameters, you can generate a Markdown document directly by sharing

![shareDocButton](/img/shareDocButton.png)

![apiDocExample](/img/apiDocExample.png)

:::

::: tip Add support for single class API list preview

After focusing on the window, enter the keyword, and you can quickly search according to the path keyword of the API

![apiDocExample](/img/apiPreview.gif)
:::

## v2022.1.4 <Badge text="Free trial" type="warn"/>

==require idea 2021.2+==

- <Badge text="SearchEveryWhere highlight optimization" type="info"/>
- <Badge text="Add support for import and export APIs" type="tip"/>
- <Badge text="Add support for run APIs" type="tip"/>
- <Badge text="Add timeout for api request" type="tip"/>
- <Badge text="Compatible with idea 2022.1" type="info"/>
- <Badge text="Make it optional for automatically generate parameters" type="tip"/>
- <Badge text="Swagger annotation default value parameter parsing support" type="tip"/>
- <Badge text="Merged Send and Send and Download buttons" type="info"/>
- <Badge text="APIs user interface optimization" type="info"/>
- <Badge text="API Navigate rename to Navigate in tab" type="info"/>
- <Badge text="Optimize the user guide of features" type="info"/>
- <Badge text="Url generation optimization" type="info"/>
- <Badge text="Fix Light files should have PSI only in one project" type="danger"/>

::: info SearchEveryWhere highlight optimization
Highlight search keywords to speed up the search for the API you really want to find.At the same time, the javadoc corresponding to the api is displayed

![help](/img/searchEveryWhereHighlight.png)
:::

::: tip Add support for import and export APIs
Using this function, you can easily share your existing APIs with other developers, or import to IDEA on other devices
![exportImportApis](/img/exportImportApis.gif)

More info **[Features->APIs import and export](./feature.md#apis-import-and-export)**
:::

::: tip Add support for run APIs
You can run your saved requests directly in the APIs tab

![runInApiManagement](/img/runInApiManagement.png)
:::

::: info Automatically generate parameter optionals
![generateSwitch](/img/generateSwitch_en.png)
:::

::: info Merged Send and Send and Download buttons
The buttons have been merged, because we usually use Send but not Send and Download, which reduces the number of toolbar buttons and looks simpler
![mergeRunAndDownload](/img/mergeRunAndDownload.png)
:::

::: tip Swagger default value parsing support
Added the parsing of the default value of swagger annotations, which is more user-friendly of input parameters

- @ApiParam(swagger2)
- @ApiImplicitParam(swagger2)
- @ApiModelProperty(swagger2)
- @Parameter(swagger3)
- @Schema(swagger3)

More info **[Features->swagger default value parsing support](./feature.md#swagger-default-value-parsing-support)**
:::

::: tip Optimize the user guide of features
We have added a ? option in different windows to display some guidelines. For first-time users, it's easier to operate

And with the iteration of the version, more prompt operation guidelines may be added in the future

![help](/img/help.png)
:::

::: info Url generation optimization
Historical logic will only take the first url, that is, test1, considering that in actual use, it is possible that you need another url, so random support has been added.

The following method url will be randomly generated to **/url1/test1,/url1/test2,/url2/test1,/url2/test2** by click <FontIcon icon="restfulFastRequest" />

```java
@RequestMapping({"url1","url2"})
@RestController
public class MultiUrlController {
    @GetMapping(value = {"test1","test2"})
    public Integer testUrl(){
        return 1;
    }
}
```

Expression support. The following demo url will be generated to **/url/public/test1**

```java
public class Constant {
  public static final String PUBLIC_URL = "/public";
}
@RequestMapping("url")
@RestController
public class MultiUrlController {
  @GetMapping(value = Constant.PUBLIC_URL + "/test1")
  public Integer testUrl(){
    return 1;
  }
}
```

:::

::: info API Navigate rename to Navigate in tab  
In the case where the tool window is relatively small, the API Navigate will be hidden. In order to display more content in the smallest tool window as possible, the name is shorter.
:::

## v2.1.3

- Add encode support for special symbol in parameter
- Add global headers to curl

## v2.1.2

- Add support for export api to Postman
- Open the idea lazy loading API Navigate tree for the first time
- SearchEveryWhere show module
- Optimize URL parsing
- Optimize the display of api navigate tree
- Optimize the group when save api
- Global header params support

::: tip Optimize URL parsing
support parse like following example

no longer need to configure the URL Replace Config separately
:::

::: code-tabs

@tab Scene 1: URLS are class constant references

```java
@RequestMapping(Url1.URL_TEST)
@RestController
public class UrlTestController {
    private static final String URL= "xxx";

    @GetMapping(value = URL)
    public Integer testUrl(){
        return 1;
    }
}
```

@tab Scene 2: value is an array

```java
@RequestMapping(
    value = {"/v1/save"},
    method = {RequestMethod.POST}
)
```

:::

::: tip Add support for export api to Postman

![export2postman](/img/export2postman.gif)
:::

::: tip SearchEveryWhere show module

![searchEveryWhereModule](/img/searchEveryWhereModule.png)
:::

::: tip Optimize the group when save api

```
when saving the api,it will save to a group named controller's name

```

![save2ControllerGroup](/img/save2ControllerGroup.png)
:::

::: tip Global header params support

![globalRequestHeader](/img/globalRequestHeader.png)
:::

## v2.1.1

- SearchEveryWhere support
- Add a what's new button to view the latest version at any time
- Optimize the parsing of enum
- Add support for http redirect

::: tip SearchEveryWhere support

```
example
/url
get /list         (used for search get method)
post /save        (used for search post method)
```

![searchEveryWhere](/img/searchEveryWhere.gif)
:::

::: tip Quick view of the latest version
![whatsnewNotifcation](/img/whatsnewNotifcation.png)
:::

## v2.1.0.2

- Joined the organization [**Dromara**](https://dromara.org/zh/)(An organization dedicated to native solutions for the
  microservice cloud)

## v2.1.0.1

- Fix get param error

## v2.1.0

- Fix post api parse error when using @RequestBody and @RequestParam
- Parsing support for nested classes
- Add support for url parse from the path property of the @RequestMapping in controller class level
- Added support for Api Tree scanning with @RequestMapping class

## v2.0.9

- Fix "Slow operations are prohibited on EDT" for 2021.3+
- Fix add headers from response
- Add project-level configuration,env and project will not change while switch project
- Add format action icon to textEditor

::: tip Add headers from response

```
if your api need token in header,you can do like this to add params to header from response
```

![format](/img/quickAddHeaders.gif)
:::

::: tip Add format action icon to textEditor
![format](/img/format.gif)
:::

## v2.0.8.1

- Fix conflicting error when searchEveryWhere
- Adjust position of toolbar action button
- remove unnecessary dependence,size 11.1M->5.9M

## v2.0.8

- Fix npe when first add project/env
- Json tree response character rendering maximum limit
- Add support for filter by methodType in Api navigate
- Add support for ignoring parameter parsing
- Fix some EDT problem
- Send/sendDownload button support for custom shortcut keys
- Fix response text not show in 2021.3

::: tip send/sendDownload button support for custom shortcut keys

```
shortcut is available anywhere to trigger the event,No longer need to focus on the tool window
```

![shortcutSendAndDownload](/img/shortcutSendAndDownload.png)
:::

::: tip Add support for filter by methodType in Api navigate
![shortcutSendAndDownload](/img/methodFilter.png)
:::

## v2.0.7

- Add support for api navigate tree
- Add support for automatic switching of Headers with project and environment switching
- Optimized Windows system. In some cases, downloading files cannot pop up the directory
- Optimize the parameter parse of @RequestParam
- Optimize tool window project and env drop-down components and layout
- Optimize the prompt message of curl copy
- Optimize the prompt message of curl regenerate
- Add confirm operation when delete project and env config
- Fix the order of json field output is disrupted
- Move send button to toolbar and support keyboard shortcut

::: tip API navigate tree

```
Select the tree and enter keywords
Press Enter again or double-click the left mouse button to locate the API

Hover the mouse to display the doc of the api
```

![apinavi](/img/apinav.gif)
:::

::: tip Headers Automatic switching

```
Scenes:Multi-module projects such as SpringBoot have different header parameters in different projects and different environments.
In order to quickly switch headers, header grouping is coming.
Operation method：
1.Modify in the headers group, constraint: the input value must be in standard json format
2.Switch the environment or project name directly, and then enter the corresponding key and value values in the headers form
```

![headerSwitch](/img/headerSwitch.gif)
:::

::: tip Optimize the parameter parse of @RequestParam
@RequestParam parameters will be prioritized according to the alias  
`@RequestParam("nameAlias")List<String> nameList` param name will be nameAlias
:::

::: tip Move send button to toolbar and support keyboard shortcut

```
send request: alt =
send and download: alt -
Prerequisites:Tool window needs to be focused
```

![toolbarSend](/img/toolbarSend.png)
:::

::: tip Optimize tool window project and env drop-down box and layout

```
Icon p represents project
Icon e represents environment
```

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
:::

## v2.0.6

- Fix "Light files should have PSI only in one project"
- Add support for json syntax check
- Add support for PATCH method
- Added support for saving the API to the corresponding module group
- Fix null value in response not output
- Add support for JAX-RS
- Display what's new

Without affecting the use, sometimes it will pop up "_Light files should have PSI only in one project"_.This has fixed in 2.0.6

::: tip json grammar check
![json](/img/json_en.png)
:::

::: tip API group automatic association
![apiGroup](/img/apiGroup_en.gif)

Module supports quick search
![apiGroup](/img/moduleSearch.gif)
:::

::: tip JAX-RS support
![apiGroup](/img/jaxrs.gif)
:::

::: tip Show what's new  
Will only be shown once
![apiGroup](/img/whatsnew_en.png)
:::

## v2.0.5

- JSON built-in editor support
- Fix checkbox hidden when change param by text
- Fix headers param show error when reshow the api
- Optimized the request progress bar display

## v2.0.4

- Fix the interface confusion caused by the response content aisle
- Optimization of response JSON ultra long display
- Optimized icon size
- Migrate Chinese documents to Gitee

## v2.0.3

- Add confirmation when deleting api
- Added the support of selecting all parameters and inverting selection
- Fix param parse for java.util.Set
- Fix parse like 【@RequestParam
- Fix the confusion of the quick add header interface

## v2.0.2

- Fix that the A request is displayed to the B project in the case of multiple open projects
- Fix array and list param parse error decorate by @ResponseBody
- Fix List without generic parse error
- Add support for send and download
- Show progressbar while sending request
- Brand new document\[click the doc icon in the toolbar\]
- Some optimization

## v2.0.1

- rename from Fast Request to Restful Fast Request
- merge Json、Form URL-Encoded、Multipart to Body Tab to reduce the tool window width
- fix bug when modify config in global mode
- add get、post、delete、put icon to saved request
- saved request support more search strategy
- rename tab name from collection to APIs
- support parse java.time.YearMonth

## v2.0.0

- support store request
- support params optional
- optimal iu
- fix some bugs
