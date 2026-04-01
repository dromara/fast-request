---
url: /en/guide/history.md
---
::: important Announcement
[**Paid vs Free**](./versionCompare.md) ｜ [**Buy plugin**](./buy.md)

Issue report please refer to [GitHub](https://github.com/dromara/fast-request/issues)

Join and follow us to solve your question ❤️❤️❤️↓↓↓

| [![Slack](https://img.shields.io/static/v1?label=Slack\&message=Restful%20Fast%20Request\&logo=slack\&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw) | [![twitter](https://img.shields.io/static/v1?label=Twitter\&message=FastRequest666\&logo=twitter\&color=FC8D34)](https://twitter.com/FastRequest666) |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![slack](/img/slack.png)                                                                                                                                                                                | ![twitter](/img/twitter.png)                                                                                                             |

:::

> **Because developing version is still in development, the documentation of developing version may not be entirely accurate and is subject to change.** >&#x20;
> important:  feat:&#x20;
>
> perf:       fix:&#x20;

## 2025.1.8   &#x20;

## 2025.1.7   &#x20;

::: hint Local mode support&#x20;

![](/img/2025.1.7/globalLocalMode_en.png)

In global local mode, `.fastRequest` directory is ignored and made invisible, which is consistent with Solution 1 in [How to ignore .fastRequest directory](./faq.md#s-how-to-ignore-fastrequest-directory).
It is suitable for non-git projects.

![](/img/2025.1.7/localMode.png)
It is suggested to use project-level local mode for git projects.

:::

::: hint Domain generation support pom variable scan&#x20;
![](/img/2025.1.7/pomScanProp_en.png)

When generating domain, it supports scanning variables in `pom.xml`, such as `${server.port}`.
:::

::: hint Field speed search&#x20;

![](/img/2025.1.7/fieldSpeedSearch.png)

Support quickly locate the field key in the list, after entering the keyword, you can quickly locate the corresponding field by pressing the arrow keys. This feature is supported in `Headers`, `Path Params`, `URL Params`, `Form URL-Encoded`, `Form-data` tabs.

:::

::: hint Field enabled search&#x20;
![](/img/2025.1.7/enabledFieldList.png)

This feature allows you to quickly locate the value of an enabled field when there are too many fields.
:::

::: hint Quick modify shortcuts support&#x20;
![](/img/2025.1.7/shortcuts_en.png)

Support quickly locate and modify shortcuts for the action.

:::

## 2025.1.6    &#x20;

:::hint Search URL truncate segment support&#x20;

![](/img/2025.1.6/searchUrlTruncate_en.png)

If there is a URL prefix fragment such as context-path or gateway in the project, add a truncated fragment to the project global config, and the SearchEveryWhere search will automatically truncate the fragment and search for the corresponding results.
:::

## 2025.1.5.1  &#x20;

## 2025.1.5  &#x20;

## 2025.1.4  &#x20;

::: hint Script supports calling local project class method&#x20;

![](/img/2025.1.4/script_en.png)

You can click on the code snippet shortcut to quickly generate code.

```groovy
def XxxUtil = new FrLocalClassLoader("path/to/classes").loadClass("some.package.XxxUtil")
```

After the SpringBoot project is compiled, a `classes` directory will be generated in the `target` directory of the module.

You can use `FrLocalClassLoader` to load the classes in this directory and call the methods directly.

Note that you need to replace `path/to/classes` with the absolute path and `some.package.XxxUtil` with the full name of the class.

:::

## 2025.1.3  &#x20;

::: hint Support scan Api of super class&#x20;
![](/img/2025.1.3/apiPreviewSuperClass.png)

Api preview supports scanning of super class, supports generating parent API in Api preview, and supports saving.

:::

::: hint Quickly make filed as ignore field&#x20;

For some fields that you do not want to generate parameters globally, you can select and quickly add them to the Ignore Field configuration. More ways to ignore fields please refer [Parse strategy](/en/guide/features/parseStrategy)

:::

::: hint Api navigate not jump to method support&#x20;
![](/img/2025.1.3/apisJumpMethodConfig.png)

With this configuration, code navigate will not be executed, which can effectively avoid opening multiple code tabs when executing the saved API.

:::

::: hint Batch sync api to Apifox support&#x20;
![](/img/2025.1.3/apifoxBatchSync.png)

After selecting a directory, you can synchronize the APIs in the directory to Apifox in batches.

:::

## 2025.1.2 &#x20;

::: hint Some non-core function disabled by default&#x20;
![](/img/2025.1.2/customToolbar_en.png)

For new devices, the non-core API debugging function is not enabled by default. For old devices, you need to manually check the configuration to remove it,
and it will take effect after restarting.

Non-core functions will be disabled by default, and you can enable it by check the config and restart the IDE.

:::

::: hint Fixed API list preview was not displayed&#x20;
![](/img/2025.1.2/apiPreview.png)

Fixed the issue that the API list preview was not displayed in previous versions

:::

## 2025.1.1  &#x20;

::: hint OpenAPI batch export&#x20;
![](/img/2025.1.1/openApiBatch.png)

Support export [OpenAPI](https://spec.openapis.org/oas/latest.html) batch for saved apis.

:::

## 2024.1.9  &#x20;

::: hint OpenAPI export support&#x20;
![](/img/2024.1.9/openApiExport_en.png)

Support export [OpenAPI](https://spec.openapis.org/oas/latest.html) document of the current request.

:::

::: hint Run switch tab configurable&#x20;
![](/img/2024.1.9/runSwitchTab.png)

You can run the API directly in the APIs tab without switching to the run tab, this is configurable.
This makes it possible to run one API on the APIs tab and then run the next.

:::

::: hint Response document&#x20;
![](/img/2024.1.9/responseDocument.png)

In the response tab, you can view the field meaning.

:::

::: hint Solon Solon framework support&#x20;
![](/img/2024.1.9/solon.png)

Support [Solon](https://solon.noear.org/) framework.

:::

::: hint File export&#x20;
![](/img/2024.1.9/exportPath_en.png)

The default export directory, for example, markdown、html、word、openapi export can be customized in project-level config.

:::

## 2024.1.8.1  &#x20;

## 2024.1.8  &#x20;

::: hint Header Preset&#x20;


Header presets allow you to create different header groups, manage header groups,
and add all preset headers easily by selecting the dropping down item.

:::

::: hint Binary support&#x20;
![](/img/2024.1.8/binary.png)

Support binary upload.

:::

## 2024.1.7  &#x20;

::: hint Functions and environment code completion&#x20;


Support environment value like `{{xx}}` and function value `{{$functionName}}` code completion

For more information about function support, see ----> [Function](/en/guide/features/function.md)
:::

::: hint JSON filter&#x20;
![jsonFilter](/img/2024.1.7/jsonFilterEn.png)

When there are many fields in JSON, but only part of them are needed, this feature can help filter the JSON fields.
:::

::: hint Common Header code completion&#x20;
![header](/img/2024.1.7/header.png)

Code completion is supported for some common header keys, such as `Authorization`
:::

::: hint @FeignClient scan support&#x20;
![feignClientSupport](/img/2024.1.7/feignClientSupport.png)

Support scan `org.springframework.cloud.openfeign.FeignClient`, optimized the path parse result
:::

::: hint Default file path for multipart&#x20;
![defaultMultipartFile](/img/2024.1.7/defaultMultipartFile.png)

Multipart type fields can be set default path by this setting.
:::

::: hint Error report support&#x20;
![errorReport](/img/2024.1.7/errorReport.png)

It is easier to report bugs, which allows authors to better fix and locate bugs.
:::

::: hint SearchEveryWhere result sorting optimization&#x20;
![searchEveryWhere](/img/2024.1.7/searchEveryWhere.png)

Optimized the collation for matching by URL path
:::

::: hint Script grammar hints optimization&#x20;
![scriptHint](/img/2024.1.7/scriptHint.png)

Need to click `Add Script library` first. After that, use built-in variables `rfr.request`、`rfr.response` to get code completion.

Old built-in variables `request` and `response` can still work, but the code completion hint is not friendly.

For more script knowledge please refer to [Script->rfr](./script.md#rfr).

:::

## 2024.1.6.3  &#x20;

## 2024.1.6.2  &#x20;

::: hint Combined annotation support&#x20;

Support the annotation like the following

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface CustomRestController {

    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};
}
```

:::

## 2024.1.6  &#x20;

::: hint Multi Tab window&#x20;


Open `rapi` file to display the debugging interface and supports multi-Tab opening. More detail please refer [**Multi-tab window**](/en/guide/features/navigateCurrentMethodJson.md)

:::

::: hint Apis mark&#x20;


Added support for mark APIs in the Apis tab, marking them with red highlight dots, and added mark Api searche condation. Developers can mark the important APIs to make them more visible
:::

::: hint Url copy in Apis & Navigate&#x20;


Right-click option supports copying original URL
:::

::: hint Json path support&#x20;

![jsonPath](/img/2024.1.6/jsonPath.png)

Support json path search In `Body->Raw` and `Response->Pretty` tab, but this depends on [JSONPath](https://plugins.jetbrains.com/plugin/22044-jsonpath) plugin.
:::

## 2024.1.5.1  &#x20;

## 2024.1.5  &#x20;

::: hint Add library support in pre and post script&#x20;
![](/img/2024.1.5/addLibrary_en.png)

Add a button to allow adding script library to get the [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html) function.
:::

::: hint Type drop-down box optimization&#x20;
![](/img/2024.1.5/typeDropdown.png)

The drop-down box uses a more clear English type and uses different colors to distinguish different types.

:::

::: hint Original url display optimization&#x20;

![](/img/2024.1.5/originUrl.png)

Url in run tab, and APIs tab is displayed as the original URL, the path parameters will no longer replace.
:::

::: hint cUrl import optimization&#x20;
Url is the same, but the method type (Get, Post, etc.) is different, the binding pairs are optimized so that they can match correctly.
:::

## 2024.1.4  &#x20;

::: hint Apifox integration&#x20;

Apifox integration supports synchronization of APIs, environment variables, domain name information, etc. Just configure an `API key` and set the project mapping relationship to synchronize.
:::

::: hint AI support optimization&#x20;
![AI](/img/2024.1.4/ai_en.png)

In order to make AI's responses more accurate, we have made AI settings more refined and added 3 dimensions:
`Summary`、`Example question`、`Example question's answer`.
This design allows the AI to train a question and answer session so that the results are more in line with expectations.

We can try to express various possibilities of the question in the `Summary`, and the give an example in `Example question` and `Example question's answer`.

The following is a case of using AI to help optimize JSON messages. For more details, please refer to [AI](/en/guide/features/ai.md).

::: code-tabs

@tab Summary

```
You are an excellent JSON handler. Whenever a user sends you a JSON, you should respond to
the user in a fixed JSON formatand replace the values in the JSON fields with
human-readable content according to the meaning of the JSON keys. You will only modify
the values in the JSON without changing its structure.
```

@tab Example question

```json
{ "id": 1, "title": "title_ahska", "simpleDesc": "simpleDesc_ohga1" }
```

@tab Example question's answer

```json
{
  "id": 1,
  "title": "Harry Potter and the Philosopher's Stone",
  "simpleDesc": "Join Harry Potter on a thrilling adventure at Hogwarts School of Witchcraft and Wizardry as he uncovers dangerous secrets and saves the rewarding world."
}
```

:::

::: hint Change moduleHeader to apiHeader&#x20;

![Header](/img/2024.1.4/header.png)

1. The variable `moduleHeader` in the pre-script and post-script is changed to `apiHeader`.
2. The scope of the Header tab in the main interface has been changed to only face the API level.
   That is, each API can have different Headers. If you need a public Header, please go to [Project Level Header](/guide/features/projectValueConfig.md) setting.

:::

::: hint cURL import Optimization&#x20;
在去除域名后，如果 Url 与项目中方法的 Url 一致则自动绑定。
:::

## 2024.1.3 &#x20;

::: hint AI support optimization&#x20;


Added three forms of AI support: OpenAi, CodeGeeX, and Private deployment AI. Through prompt, selected text can be replaced with another text.

AI action has added the following tabs: `Body -> JSON`, `Body -> Form URL-Encoded -> Text`,`URL Params -> Text`
:::

## 2024.1.2.1 &#x20;

## 2024.1.2 &#x20;

::: hint Custom toolbar&#x20;

![customToolbar](/img/2024.1.2/customToolbar_en.png)

Using the custom toolbar option, select the functionalities you want to use and hide other function buttons. Then restart IDEA.
:::

## 2024.1.1 &#x20;

::: hint SearchEveryWhere support classname search&#x20;
![searchEveryWhere](/img/2024.1.1/searchEveryWhere_en.png)

Support enters the class name where the Url is located to search for all APIs under this class.

:::

::: hint Generate .fastRequest directory strategy&#x20;
When Project is opened by default, the .fastRequest directory will no longer be generated.
Only when you operate plugin, it will be generated.
:::

::: hint Ignore field name parse support&#x20;
![ignoreFiled](/img/2024.1.1/ignoreFiled_en.png)

Only need to add field names in the configuration to ignore target fields in the entity class.

:::

::: hint Apis and Navigate in popup&#x20;

You can quickly open the Apis and Navigate window to view information through shortcut keys.
When the window is open, press ESC to close the window.
:::

::: hint Manual update check&#x20;
![manualUpdateCheck](/img/2024.1.1/manualUpdateCheck.png)

Click to check for plugin updates
:::

::: hint SearchEveryWhere performance optimization&#x20;
The SearchEveryWhere search logic has been optimized to greatly improve the search performance of large projects.

We tested a project with 7000 APIs and the search response was very fast
:::

::: hint Content-Disposition support optimization&#x20;

Content-Disposition supports the following 2 formats, and also supports file name transcoding.

```
Content-Disposition:attachment; filename*=UTF-8''fastRequest%20.txt
Content-Disposition:attachment; filename=fastRequest.txt
```

:::

## 2023.2.3.1 &#x20;

## 2023.2.3 &#x20;

::: hint Sync API to Postman&#x20;
You only need to configure the token of Postman and the ID of the workspace to upload the API to Postman.Enjoy what fast request do not support.

To make the data synchronised to Postman more complete, it is recommended to select the `sync after API save` option in the Postman config of the plugin
then trigger the synchronisation by saving the API.

More details please refer [Postman sync](./features/postmanSync.md)
:::

::: hint Project name drop-down box automatically switches&#x20;
When generating parameters for APIs under different modules, if auto domain name is enabled, the project name drop-down box will automatically switch
:::

::: hint Environment refactoring&#x20;
![environment](/img/2023.2.3/environment_en.png)

Environment contains Current value and Initial value. Initial value can be shared by submitting config fine in `.fastRequest/config/fastRequestCurrentProjectEnvironment.json`.

:::

::: hint Project domain name addition optimization&#x20;

The module name is automatically recognized as a drop-down box option, and you can also customize it.

![projectName](/img/2023.2.3/projectName.png)
:::

::: hint Add @fastRequestParseIgnore support for ignore parse field&#x20;
To ignore parsing for entity field, you can add `@fast Request Parse Ignore` to the annotation. The original annotation `@parse Ignore` is still applicable.

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

:::

## 2023.2.2 &#x20;

::: hint Markdown doc template config&#x20;
Provides a custom format for local markdown and [Online API](./features/apiDocSync.md) doc.View [more detail](./features/markdownDocTemplateConfig.md)
:::

::: hint JSON5 support&#x20;
![json5](/img/2023.2.2/json5.png)
JSON5 format support for Request body, JSON field comment support

:::

## 2023.2.1.1&#x20;

## 2023.2.1&#x20;

::: hint UI predigestion&#x20;
![urlInputWithMethod](/img/2023.2.1/urlInputWithMethod.png)
Simplify the three horizontal components of the old version (method drop-down box, url input box,
and response drop-down box) into one, and remove the response drop-down box to increase simplicity.

:::

::: hint Support open API document in apis list&#x20;

![openDoc](/img/2023.2.1/openDoc.png)
:::

::: hint Put show apis into right click menu&#x20;

![openDoc](/img/2023.2.1/showApis.png)
:::

::: hint Add plugin version to statistics page&#x20;

![pluginVersion](/img/2023.2.1/pluginVersion.png)
:::

## 2023.1.9&#x20;

::: hint Quick add Environment variable support&#x20;

![quickAddEnvironmentVariable](/img/2023.1.9/quickAddEnvironmentVariable.png)
Click the "Add" button to select a line and add it to the environment variables, which can quickly save the variable to the current Env.
:::

::: hint Add、Delete、Modify support in Environment use view&#x20;
![environmentVariableEditRemoveModify](/img/2023.1.9/environmentVariableEditRemoveModify_en.png)
:::

::: hint Show apis trigger in Controller&#x20;
![showApis](/img/2023.1.9/showApis.gif)
Right-click in the Controller in any position to invoke `Generate`, choose`Show apis` , all APIs in the current controller can be displayed.

:::

::: hint Response adds Size property&#x20;
![responseSize.png](/img/2023.1.9/responseSize.png)
:::

## 2023.1.8&#x20;

::: hint Environment&#x20;
Add  nvironment variable support, [more detail](./features/environment.md)
:::

::: hint Method description length truncate&#x20;
![methodDescriptionLength](/img/2023.1.8/methodDescriptionLength_en.png)

You can truncate the method description process when the method name is too long, resulting in display or data saving.

Such as in `SearchEveryWhere`

:::

## 2023.1.7.1&#x20;

::: hint Add generate action in SearchEveryWhere&#x20;
![searchEveryWhere](/img/2023.1.7.1/searchEveryWhere.png)
After select Auto-generate icon, you can echo or automatically generate API-related URLs and parameter in SearchEveryWhere

:::

::: hint The main window pops up with configuration&#x20;
Add a config `Show main tool window when action trigger` in `Setting->Request Fast Request->Other config`.
:::

::: hint Param parse&#x20;
Parsing optimization for the input parameters with `@RequestBody Set<String>set`. By default, strings are enclosed in double quotation marks.

::: code-tabs#shell

@tab spring

```java
@PostMapping("test3")
public Set<String> test3(@RequestBody Set<String>set) {
    return set;
}
```

:::

## 2023.1.7&#x20;

::: hint New icon&#x20;
![toolwindowNew](/img/toolwindowNew.png)
:::

::: hint SearchEveryWhere enhance&#x20;
![searchEveryWhere](/img/2023.1.7/searchEveryWhere.png)
Add `module`、`methodType`、`library` condition
:::

::: hint Slack support&#x20;

If you have any question, please click [![Slack](https://img.shields.io/static/v1?label=Slack\&message=Restful%20Fast%20Request\&logo=slack\&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw) and join in.

We will help you solve your problems. Thanks!

:::

::: hint Compact http icon&#x20;

You can also change it in `setting -> Restful Fast Request -> Use compact http icon`

| Method |                   New Icon                    |                 Old Icon                  |
| :----: | :-------------------------------------------: | :---------------------------------------: |
|  Get   |    ![get\_dark](/img/http/new/get_dark.svg)    |    ![get\_dark](/img/http/get_dark.svg)    |
|  Post  |   ![post\_dark](/img/http/new/post_dark.svg)   |   ![post\_dark](/img/http/post_dark.svg)   |
| Delete | ![delete\_dark](/img/http/new/delete_dark.svg) | ![delete\_dark](/img/http/delete_dark.svg) |
|  Put   |    ![put\_dark](/img/http/new/put_dark.svg)    |    ![put\_dark](/img/http/put_dark.svg)    |
| Patch  |  ![patch\_dark](/img/http/new/patch_dark.svg)  |  ![patch\_dark](/img/http/patch_dark.svg)  |

:::

::: hint Duplicate field&#x20;
![fieldDup](/img/2023.1.7/fieldDup.png)
Support field line duplicate, mark key end with `-dup` for duplicate key, plugin will do not change key for collection field, developers need to modify manually.

:::

## 2023.1.6&#x20;

::: hint Header batch edit&#x20;

![batchEdit](/img/2023.1.6/batchEdit_en.png)

:::

## 2023.1.5&#x20;

::: hint Editor icon location&#x20;

![editorAction](/img/2023.1.5/editorAction.png)
Move from the original button where it is easy to block the text to the upper right corner of the editor

:::

::: caution SearchEveryWhere style confusion
This issue accompanies many versions and this version completely fixes it.

SearchEveryWhere, from now on, will not have the problem of the tab width increasing and causing the tab to not be fully displayed.
:::

## v2023.1.4.2&#x20;

## v2023.1.4.1&#x20;

## v2023.1.4&#x20;

::: hint Auto domain&#x20;
Automatically identify or add a default domain,---->[More detail](./features/autoDomain.md)
:::

## v2023.1.3.2&#x20;

[FAQ:API data lost after install 2023.1.3](./faq.md#s-apis-lost)

::: hint OpenAI API Optimising fields support&#x20;
![ai](/img/2023.1.3/ai.gif)
Replace some random fields with the [OpenAI API](https://platform.openai.com/docs/api-reference/chat)

:::

::: hint API and API storage file jumps&#x20;

![jumpToJsonData](/img/2023.1.3/jumpToJsonData.png)

![jump from apis](/img/2023.1.3/listJump2JsonData.png)
Implementation of the API and the saved json data for associative jumping

:::

::: hint Project config refresh&#x20;

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)
When the configuration file is updated, this function allows the UI to refresh the configuration as well.
:::

::: hint rfr.currentModuleName support&#x20;
Get the module name of the current API. Please see it in [script](./script.md#rfr).
:::

::: hint API doc sync supports custom branch&#x20;
![apiSyncSupportBranch](/img/2023.1.3/apiSyncSupportBranch.png)
Support custom branch, as in some cases the 'master' branch is protected and does not allow push

:::

::: hint Toolbar&#x20;

![htoolbar](/img/2023.1.3/htoolbar.png)

![vtoolbar](/img/2023.1.3/vtoolbar.png)

![toolbarSetting](/img/2023.1.3/toolbarSetting.png)

1. Add vertical toolbar support.
2. Button position adjustment
   :::

::: hint GET、POST、DELETE、PUT、PATCH icon&#x20;

![toolbar](/img/2023.1.3/apis.png)
Rounded corners and adjusted background colour for icons.
:::

## v2023.1.2&#x20;

::: hint Image render from response&#x20;

![imageRender](/img/2023.1.2/imageRender.png)
Automatically render images for requests that respond to image.
:::

::: hint Annual report&#x20;
![annualReport](/img/2023.1.2/annualReport_en.png)
Statistics for previous years of operation.
:::

::: hint Url parse logic&#x20;
Optimised the final result of url splicing against variables. For example, splicing several variables finally calculates a url

Plugin will calculate final url to `/test/a.htm`

```java
public class MyConstant {
  public static final String MODULE_NAME = "a";
  public static final String DYNAMIC_WEB_SUFFIX = ".htm";
  public static final String REPAYMENT_REPAY = MODULE_NAME + DYNAMIC_WEB_SUFFIX;
}
@PostMapping(MyConstant.REPAYMENT_REPAY)
  public String test2() {
  return "";
}
```

:::

::: hint cUrl import&#x20;

1. automatic parsing of query param into `Url params` tab page
2. json parsing optimization
3. Known bug fixes
   :::

::: hint Url special character parameter encoding&#x20;
We have done some optimization, no longer need to encode special characters in url params.
:::

::: hint Add send request trigger to url field&#x20;
Add `Enter` key listener to url field that can send the request.
:::

## v2023.1.1&#x20;

::: hint Project-level domain config&#x20;

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig_en.png "Domain config")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog_en.png "Domain list")
Supports fast config domain directly from the `yml` or `properties` file，for more detail, please see [ Project-level domain config](./features/projectLevelDomainConfig.md)
:::

::: hint Icon alignment&#x20;
The buttons have been categorised to make it easier for developers to find and understand the function of the corresponding button.
:::

::: hint Tool window icon in new UI&#x20;
The clarity of the tool window icons has been optimised under the new UI.
:::

## v2022.3.1&#x20;

::: hint Add @parseIgnore comment support for parse&#x20;

The following code, the plugin will ignore parsing this field. Please use `@parseIgnore`

```java
/**
 * xxx description
 * @parseIgnore
 */
private String someIgnoreField ;
```

:::

::: hint Add rfr.currentDomain in Script&#x20;
You can get the current domain link in the Script through `rfr.currentDomain`
:::

## v2022.2.9&#x20;

::: hint Global animate config support&#x20;
![animateConfig](/img/2022.2.9/animateConfig_en.png)
You can close animate in this global config, if close, icon animation  will stop
:::

## v2022.2.8&#x20;

::: hint Directory download file support&#x20;
When response contains `content-disposition:attachment`, click send will automatically adapt the download
:::

## v2022.2.7&#x20;

::: hint Api sync&#x20;

Online Api doc sync---->[More detail](./features/apiDocSync.md)
:::

:::tip Api doc show return type document
![returnValueDoc](/img/2022.2.7/returnValueDoc.png)
:::

::: hint Plugin update notification mechanism&#x20;
![upgradeNotice](/img/2022.2.7/upgradeNotice_en.png)

Change from an explicit dialog box to a notification, at the same time automatic updates have been changed to ==turn on==, if you want to turn it off, please go to the configuration page and turn it off manually.
But I recommend ==turning it on==, so that you can receive updates in time.

Note that the previous version of the update will still be a pop-up dialog, this version onwards will be a message notification.

:::

::: hint Common header&#x20;

![commonHeader](/img/2022.2.7/commonHeader.gif)
[---->More detail](./features/commonHeader.md)
:::

::: hint Full screen&#x20;

![fullScreen](/img/2022.2.7/fullScreen.png)
[---->More detail](./features/fullScreen.md)
:::

::: hint Comment preview&#x20;
![fullScreen](/img/2022.2.7/commentPreview.png)
:::

## v2022.2.6&#x20;

::: hint Clear params support&#x20;

![clear](/img/2022.2.6/clear.png)
clear all

![clearColumnValue](/img/2022.2.6/clearColumnValue.png)
batch clean column value
:::

::: hint Api comment preview&#x20;
![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
:::

::: hint Sort param column by key&#x20;
![sortColumn](/img/2022.2.6/sortColumn.png)
:::

## v2022.2.5.2&#x20;

## v2022.2.5.1&#x20;

## v2022.2.5&#x20;

::: hint Console support&#x20;

![console](/img/2022.2.5/console_en.png)
Console helps developers print some info you wanted

More info please see [script->console](./script.md#console)

:::

::: hint Add currentProjectName and currentEnvName property in build-in property&#x20;
You can use these 2 variables for some judgment

More info please see [script->Built-in variable->rfr](./script.md#rfr)
:::

::: hint Get description from javadoc for path and request param&#x20;

![parseDocDesc](/img/2022.2.5/parseDocDesc.png)
Parse parameter comments in the form of Javadoc, suitable for path parameters and request param is a parameter of non-entity class
:::

::: hint twitter&#x20;
If you have a Twitter account, please follow me, thank you

![](/img/twitter.png)

![twitterAction](/img/2022.2.5/twitterAction.png)
:::

## v2022.2.4.1&#x20;

## v2022.2.4&#x20;

::: hint New logo&#x20;

Developers can freely choose and switch the color you want in the drop-down box, and can make it moving

:::

::: hint Support parse date by DateTimeFormat annotation pattern&#x20;
![dateTimeFormat](/img/2022.2.4/dateTimeFormat.png)
:::

::: hint Support parse url of BaseController&#x20;
![baseController](/img/2022.2.4/baseController.png)
:::

::: hint Change auto update configurable&#x20;

![autoUpdate](/img/2022.2.4/autoUpdate.png)
If you don't need to receive automatic updates, you can turn it off and update it manually (recommend open)

:::

## v2022.2.3.1&#x20;

## v2022.2.3&#x20;

::: hint Script support&#x20;

![scriptProject](/img/2022.2.3/scriptProject_en.png)

![scriptModule](/img/2022.2.3/scriptModule_en.png)

Scripts allow developers to more flexibly, dynamically and easily modify some input parameters of the request process and the processing of responses.
Please refer to [Script](./script.md)
:::

::: hint One-click copy Url&#x20;
![copyUrl](/img/2022.2.3/copyUrl.png)
:::

::: hint Apply table cell value change when click send request&#x20;
Before ==2022.2.3==, when entering the parameters in the table, if the cursor is still inside the table, click the action button at this time, and in some scenarios, an error will be reported or the value cannot be modified, and the problem is solved in this version.

How the old version deals with it: [FAQ:After entering the parameters, the API call found that the parameters were invalid](./faq.md)
:::

## v2022.2.2&#x20;

::: hint Add stop API request function&#x20;
![stopApi](/img/2022.2.2/stopApi.gif)
:::

::: hint Batch export API doc&#x20;
![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)
:::

## v2022.2.1&#x20;

::: hint API grouping parameter save support&#x20;
Purpose: Save parameters of different combinations under one API

\==The original save operation will be classified into the Default group by default==

How to:After entering the parameters, click ==Save group param request==

![groupSave](/img/2022.2.1/groupSave_en.png)

**Toggle parameter**

The following is the query of the book list in 3 languages

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)
:::

::: hint Temporary request save support&#x20;

![tempSave](/img/2022.2.1/tempSave_en.png)

Purpose: Save any request that does not belong to this project for temporary invocation, not associated with the current project code

The Url of the Request saved by the temporary request must start with ==http== or ==https==

:::

::: hint cURL import&#x20;

![importByCurl](/img/2022.2.1/importByCurl_en.gif)

\==Note that if you need to bind a method, you need to place the cursor on the method name==
:::

::: hint Add Response Header in response&#x20;

![responseHeader](/img/2022.2.1/responseHeader.png)

:::

::: hint Add support for Url suffix&#x20;

![responseHeader](/img/2022.2.1/urlSuffix.png)

Purpose: Some requests need to add .do at the end of the url

:::

::: hint Optimized parameter parsing&#x20;
Optimized parsing of `List<Entity>`
:::

## v2022.1.10&#x20;

::: hint Add support for history request preview&#x20;
![history](/img/history.png)
Hidden skills: Double-click the record to display details
:::

::: hint Add support for description search in SearchEveryWhere&#x20;
![searchEveryWhereDescription](/img/searchEveryWhereDescription.png)
Support description search API
:::

::: hint Add request timeout settings&#x20;
![timeoutSetting](/img/timeoutSetting.png)
Default value: 60 seconds
:::

::: hint Optimization&#x20;

* Shortcut key conflict optimization
  * Added Shift to the three shortcut keys for opening windows
* Text editor character display cursor positioning optimization
  * When the text editor generates a string, it focuses on the first line by default

:::

## v2022.1.9&#x20;

::: hint Add shortcut keys for tool window jump&#x20;
![toolwindowShortcut](/img/toolwindowShortcut.png)
Provides shortcut keys to jump quickly, you can change it in `setting -> Keymap`
:::

::: hint Add auto add cookie support&#x20;
![autoStoreCookie](/img/autoStoreCookie.png)
Then automatically stored in == project global parameters ==
If enable this configuration,cookie will automatically store in ==Project Global Config== -> `cookies`,This configuration is enabled by default.
:::

::: hint Add support for html preview in response raw&#x20;

![rawHtmlPreview](/img/rawHtmlPreview.png)
The response of the html return type is no longer truncated in raw, and the html can be previewed at the same time
:::

::: hint Ignore field parsing optimizations&#x20;

:::

::: hint Generic parameter parsing support&#x20;
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

::: hint Improve project global config&#x20;
![projectConfigParam](/img/projectGlobalParam.png)
Added `URL Params`、`URL-Encoded Params`、`Cookies`
:::

::: note Optimization

* Interface optimization
  * Optimized tab spacing(narrow under windows)、background color
* Save API insertion order optimization
  * The post-saved API will be placed on top

:::

::: hint APIs list view optimization&#x20;
![apisList](/img/apisList.png)
The rendering of the APIs interface has been optimized,Newly saved api icons are automatically optimized by default,The API saved in history can be right-clicked to select and change
:::

## v2022.1.8&#x20;

::: hint Project-level global parameters support&#x20;
![projectConfigParam](/img/projectConfigParam.png)

Support global parameters within the project level, not affected by multiple modules.

Configure priority `api header > project header>global header`

:::

::: hint Quickly add to header parameters&#x20;
![add2projectConfig](/img/add2projectConfig.png)

![cookie2projectConfig](/img/cookie2projectConfig.png)

Change ~~add params to global header~~ to `add params to project global header`

Cookie is also like this

:::

## v2022.1.7&#x20;

::: hint Kotlin spring framework support&#x20;
![kotlinSupport](/img/kotlinSupport.png)
:::

::: hint Add SearchEveryWhere scope search strategy&#x20;
![searchEveryWhereStrategy](/img/searchEveryWhereStrategy.png)
The 2022.1.7 version can search for mapping in SpringBoot feign or controller by default

The default search strategy is to search based on annotations, and two options are provided. If you need to exclude interfaces similar in SpringBoot feign interface during the search process, you can remove the check of Interface to deal with it.
:::

::: note Add toggleUseSoftWrap support in editor

![toggleUseSoftWrap](/img/toggleUseSoftWrap.png)
:::

::: hint Add support for add to Global Header from response&#x20;

![add2GlobalHeader](/img/add2GlobalHeader.png)
It is recommended to use in multi-module projects, because Add to Headers can only be added to the current module project
:::

::: note Add support for navigating to the current method

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)
After you generate the url of the method, sometimes you will switch to another place in the code, and then want to start debugging the API method, you can use this function to quickly locate the code
:::

::: hint Optimize automatic binding projectName for saved api under multiple modules&#x20;
Optimized the need to manually switch the project drop-down box for the saved api echo under the multi-module
:::

## v2022.1.6&#x20;

::: hint Add support for one click to add cookie to header&#x20;

![addCookie2Header](/img/addCookie2Header.png)
For those that require cookie support for login, you can quickly add the cookie in the response to the Header to achieve this
:::

::: hint Add support for sharing API to Word and html&#x20;

![shareApi](/img/shareApi_en.png)
Please note that the API in word form is implemented in html, so don't feel strange, just export it
:::

## v2022.1.5&#x20;

::: hint Add support for automatically generating parameter descriptions&#x20;

![paramDescription](/img/paramDescription.png)
You can hide or show description by toggle click
:::

::: hint Add support for generate API documentation&#x20;

![shareDocButton](/img/shareDocButton.png)

![apiDocExample](/img/apiDocExample.png)
After generating parameters, you can generate a Markdown document directly by sharing

:::

::: hint Add support for single class API list preview&#x20;

![apiDocExample](/img/apiPreview.gif)
After focusing on the window, enter the keyword, and you can quickly search according to the path keyword of the API
:::

## v2022.1.4&#x20;

\==require idea 2021.2+==

::: hint SearchEveryWhere highlight optimization&#x20;

![help](/img/searchEveryWhereHighlight.png)
Highlight search keywords to speed up the search for the API you really want to find.At the same time, the javadoc corresponding to the api is displayed
:::

::: hint Add support for import and export APIs&#x20;
![exportImportApis](/img/exportImportApis.gif)
Using this function, you can easily share your existing APIs with other developers, or import to IDEA on other devices

More info **[Features->APIs import and export](./feature.md#apis-import-and-export)**
:::

::: hint Add support for run APIs&#x20;
![runInApiManagement](/img/runInApiManagement.png)
You can run your saved requests directly in the APIs tab

:::

::: hint Automatically generate parameter optionals&#x20;
![generateSwitch](/img/generateSwitch_en.png)
:::

::: hint Merged Send and Send and Download buttons&#x20;
![mergeRunAndDownload](/img/mergeRunAndDownload.png)
The buttons have been merged, because we usually use Send but not Send and Download, which reduces the number of toolbar buttons and looks simpler
:::

::: hint Swagger default value parsing support&#x20;
Added the parsing of the default value of swagger annotations, which is more user-friendly of input parameters

* @ApiParam(swagger2)
* @ApiImplicitParam(swagger2)
* @ApiModelProperty(swagger2)
* @Parameter(swagger3)
* @Schema(swagger3)

More info **[Features->swagger default value parsing support](./feature.md#swagger-default-value-parsing-support)**
:::

::: hint Optimize the user guide of features&#x20;
![help](/img/help.png)
We have added a ? option in different windows to display some guidelines. For first-time users, it's easier to operate

And with the iteration of the version, more prompt operation guidelines may be added in the future

:::

::: hint Url generation optimization&#x20;
Historical logic will only take the first url, that is, test1, considering that in actual use, it is possible that you need another url, so random support has been added.

The following method url will be randomly generated to **/url1/test1,/url1/test2,/url2/test1,/url2/test2** by click ::restfulFastRequest::

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

::: hint API Navigate rename to Navigate in tab&#x20;
In the case where the tool window is relatively small, the API Navigate will be hidden. In order to display more content in the smallest tool window as possible, the name is shorter.
:::

## v2.1.3&#x20;

* Add encode support for special symbol in parameter
* Add global headers to curl

## v2.1.2&#x20;

* Add support for export api to Postman
* Open the idea lazy loading API Navigate tree for the first time
* SearchEveryWhere show module
* Optimize URL parsing
* Optimize the display of api navigate tree
* Optimize the group when save api
* Global header params support

::: hint Optimize URL parsing&#x20;
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
public Integer testUrl(){
   return 1;
}
```

:::

::: hint Add support for export api to Postman&#x20;

![export2postman](/img/export2postman.gif)
:::

::: hint SearchEveryWhere show module&#x20;

![searchEveryWhereModule](/img/searchEveryWhereModule.png)
:::

::: hint Optimize the group when save api&#x20;

```
when saving the api,it will save to a group named controller's name

```

![save2ControllerGroup](/img/save2ControllerGroup.png)
:::

::: hint Global header params support&#x20;

![globalRequestHeader](/img/globalRequestHeader.png)
:::

## v2.1.1&#x20;

* SearchEveryWhere support
* Add a what's new button to view the latest version at any time
* Optimize the parsing of enum
* Add support for http redirect

::: hint SearchEveryWhere support&#x20;

```
example
/url
get /list         (used for search get method)
post /save        (used for search post method)
```

![searchEveryWhere](/img/searchEveryWhere.gif)
:::

::: hint Quick view of the latest version&#x20;
![whatsnewNotifcation](/img/whatsnewNotifcation.png)
:::

## v2.1.0.2&#x20;

* Joined the organization [**Dromara**](https://dromara.org/zh/)(An organization dedicated to native solutions for the
  microservice cloud)

## v2.1.0.1&#x20;

* Fix get param error

## v2.1.0&#x20;

* Fix post api parse error when using @RequestBody and @RequestParam
* Parsing support for nested classes
* Add support for url parse from the path property of the @RequestMapping in controller class level
* Added support for Api Tree scanning with @RequestMapping class

## v2.0.9&#x20;

* Fix "Slow operations are prohibited on EDT" for 2021.3+
* Fix add headers from response
* Add project-level configuration,env and project will not change while switch project
* Add format action icon to textEditor

::: hint Add headers from response&#x20;

```
if your api need token in header,you can do like this to add params to header from response
```

![format](/img/quickAddHeaders.gif)
:::

::: hint Add format action icon to textEditor&#x20;
![format](/img/format.gif)
:::

## v2.0.8.1&#x20;

* Fix conflicting error when searchEveryWhere
* Adjust position of toolbar action button
* remove unnecessary dependence,size 11.1M->5.9M

## v2.0.8&#x20;

* Fix npe when first add project/env
* Json tree response character rendering maximum limit
* Add support for filter by methodType in Api navigate
* Add support for ignoring parameter parsing
* Fix some EDT problem
* Send/sendDownload button support for custom shortcut keys
* Fix response text not show in 2021.3

::: hint send/sendDownload button support for custom shortcut keys&#x20;

```
shortcut is available anywhere to trigger the event,No longer need to focus on the tool window
```

![shortcutSendAndDownload](/img/shortcutSendAndDownload.png)
:::

::: hint Add support for filter by methodType in Api navigate&#x20;
![shortcutSendAndDownload](/img/methodFilter.png)
:::

## v2.0.7&#x20;

* Add support for api navigate tree
* Add support for automatic switching of Headers with project and environment switching
* Optimized Windows system. In some cases, downloading files cannot pop up the directory
* Optimize the parameter parse of @RequestParam
* Optimize tool window project and env drop-down components and layout
* Optimize the prompt message of curl copy
* Optimize the prompt message of curl regenerate
* Add confirm operation when delete project and env config
* Fix the order of json field output is disrupted
* Move send button to toolbar and support keyboard shortcut

::: hint API navigate tree&#x20;

```
Select the tree and enter keywords
Press Enter again or double-click the left mouse button to locate the API

Hover the mouse to display the doc of the api
```

![apinavi](/img/apinav.gif)
:::

::: hint Headers Automatic switching&#x20;

```
Scenes:Multi-module projects such as SpringBoot have different header parameters in different projects and different environments.
In order to quickly switch headers, header grouping is coming.
Operation method：
1.Modify in the headers group, constraint: the input value must be in standard json format
2.Switch the environment or project name directly, and then enter the corresponding key and value values in the headers form
```

![headerSwitch](/img/headerSwitch.gif)
:::

::: hint Optimize the parameter parse of @RequestParam&#x20;
@RequestParam parameters will be prioritized according to the alias\
`@RequestParam("nameAlias")List<String> nameList` param name will be nameAlias
:::

::: hint Move send button to toolbar and support keyboard shortcut&#x20;

```
send request: alt =
send and download: alt -
Prerequisites:Tool window needs to be focused
```

![toolbarSend](/img/toolbarSend.png)
:::

::: hint Optimize tool window project and env drop-down box and layout&#x20;

```
Icon p represents project
Icon e represents environment
```

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
:::

## v2.0.6&#x20;

* Fix "Light files should have PSI only in one project"
* Add support for json syntax check
* Add support for PATCH method
* Added support for saving the API to the corresponding module group
* Fix null value in response not output
* Add support for JAX-RS
* Display what's new

Without affecting the use, sometimes it will pop up "*Light files should have PSI only in one project"*.This has fixed in 2.0.6

::: hint json grammar check&#x20;
![json](/img/json_en.png)
:::

::: hint API group automatic association&#x20;
![apiGroup](/img/apiGroup_en.gif)

Module supports quick search
![apiGroup](/img/moduleSearch.gif)
:::

::: hint JAX-RS support&#x20;
![apiGroup](/img/jaxrs.gif)
:::

::: hint Show what's new&#x20;
Will only be shown once
![apiGroup](/img/whatsnew_en.png)
:::

## v2.0.5&#x20;

* JSON built-in editor support
* Fix checkbox hidden when change param by text
* Fix headers param show error when reshow the api
* Optimized the request progress bar display

## v2.0.4&#x20;

* Fix the interface confusion caused by the response content aisle
* Optimization of response JSON ultra long display
* Optimized icon size
* Migrate Chinese documents to Gitee

## v2.0.3&#x20;

* Add confirmation when deleting api
* Added the support of selecting all parameters and inverting selection
* Fix param parse for java.util.Set
* Fix parse like 【@RequestParam
* Fix the confusion of the quick add header interface

## v2.0.2&#x20;

* Fix that the A request is displayed to the B project in the case of multiple open projects
* Fix array and list param parse error decorate by @ResponseBody
* Fix List without generic parse error
* Add support for send and download
* Show progressbar while sending request
* Brand new document\[click the doc icon in the toolbar]
* Some optimization

## v2.0.1&#x20;

* rename from Fast Request to Restful Fast Request
* merge Json、Form URL-Encoded、Multipart to Body Tab to reduce the tool window width
* fix bug when modify config in global mode
* add get、post、delete、put icon to saved request
* saved request support more search strategy
* rename tab name from collection to APIs
* support parse java.time.YearMonth

## v2.0.0&#x20;

* support store request
* support params optional
* optimal iu
* fix some bugs
