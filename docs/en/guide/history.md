---
title: What's New
icon: changelog
---

::: important Announcement
[**Paid vs Free**](./versionCompare.md) ｜ [**Buy plugin**](./buy.md)

Issue report please refer to [GitHub](https://github.com/dromara/fast-request/issues)

Join and follow us to solve your question ❤️❤️❤️↓↓↓

| [![Slack](https://img.shields.io/static/v1?label=Slack&message=Restful%20Fast%20Request&logo=slack&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw) | [![twitter](https://img.shields.io/static/v1?label=Twitter&message=FastRequest666&logo=twitter&color=FC8D34)](https://twitter.com/FastRequest666) |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| ![slack](/img/slack.png =300x300)                                                                                                                                                                                        | ![twitter](/img/twitter.png =300x300)                                                                                                                     |

:::

> **Because developing version is still in development, the documentation of developing version may not be entirely accurate and is subject to change.**
> <br/><br/>
> important: <Badge text="Important function" type="important"/> feat: <Badge text="New function" type="tip"/>
> 
> perf: <Badge text="Optimization function" type="info"/> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> fix: <Badge text="Fix or Remove function" type="danger"/>

## 2024.1.9 <Badge text="Free trial" type="tip"/> <Badge text="Newest version" type="pink"/> <Badge text="Developing..." color="SandyBrown"/> <Badge text="IDEA 2023.3+" color="pink"/>
- <Badge text="OpenAPI export support" type="tip"/>
- <Badge text="Run switch tab configurable" type="tip"/>
- <Badge text="Response document" type="tip"/>
- <Badge text="Solon framework support" type="tip"/>
- <Badge text="File export" type="tip"/>

::: hint OpenAPI export support <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.9/openApiExport_en.png)

Support export [OpenAPI](https://spec.openapis.org/oas/latest.html) document of the current request.

:::

::: hint Run switch tab configurable <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.9/runSwitchTab.png)

You can run the API directly in the APIs tab without switching to the run tab, this is configurable.
This makes it possible to run one API on the APIs tab and then run the next.

:::

::: hint Response document <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.9/responseDocument.png)

In the response tab, you can view the field meaning.

:::

::: hint Solon Solon framework support <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.9/solon.png)

Support [Solon](https://solon.noear.org/) framework.

:::

::: hint File export <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.9/exportPath_en.png)

The default export directory, for example, markdown、html、word、openapi export can be customized in project-level config.

:::

## 2024.1.8.1 <Badge text="Free trial" type="tip"/> <Badge text="Newest version" type="pink"/> <Badge text="2024-11-28" color="SandyBrown"/> <Badge text="IDEA 2023.3+" color="pink"/>

- <Badge text="Fix the pre-script execution logic" type="danger"/>

## 2024.1.8 <Badge text="Free trial" type="tip"/> <Badge text="2024-11-12" color="SandyBrown"/> <Badge text="IDEA 2023.3+" color="pink"/>

- <Badge text="Header Preset" type="tip"/>
- <Badge text="Binary support" type="tip"/>
- <Badge text="Entity parse optimization" type="info"/>  
- <Badge text="Fix several known issue" type="danger"/>

::: hint Header Preset <Badge vertical="top" text="New feature" type="tip"/>
<MyCarousel :imgList="['/img/2024.1.8/headersPreset_en.png','/img/2024.1.8/headersPresetManage_en.png']" />

Header presets allow you to create different header groups, manage header groups, 
and add all preset headers easily by selecting the dropping down item.

:::

::: hint Binary support <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.8/binary.png)

Support binary upload.

:::

## 2024.1.7 <Badge text="Free trial" type="tip"/> <Badge text="2024-09-18" color="SandyBrown"/> <Badge text="IDEA 2023.3+" color="pink"/>

- <Badge text="Functions and environment code completion" type="important"/>
- <Badge text="JSON filter" type="tip"/>
- <Badge text="Common Header code completion" type="tip"/>
- <Badge text="@FeignClient scan support" type="tip"/>
- <Badge text="Default file path for multipart" type="tip"/>
- <Badge text="Error report support" type="tip"/>
- <Badge text="SearchEveryWhere result sorting optimization" type="info"/>
- <Badge text="Script interface optimition" type="info"/>
- <Badge text="Script grammar hints optimization" type="info"/>
- <Badge text="Map parse" type="info"/>
- <Badge text="Fix URL scan in jar is displayed as empty" type="danger"/>
- <Badge text="Fix several known issue" type="danger"/>

::: hint Functions and environment code completion <Badge vertical="top" text="Important feature" type="important"/>
<MyCarousel :imgList="['/img/2024.1.7/functionEn.png','/img/2024.1.7/functionInBodyEn.png','/img/2024.1.7/functionConfig.png']" />

Support environment value like `{{xx}}` and function value `{{$functionName}}` code completion

For more information about function support, see ----> [Function](/en/guide/features/function.md)
:::

::: hint JSON filter <Badge vertical="top" text="New feature" type="tip"/>
![jsonFilter](/img/2024.1.7/jsonFilterEn.png)

When there are many fields in JSON, but only part of them are needed, this feature can help filter the JSON fields.
:::

::: hint Common Header code completion <Badge vertical="top" text="New feature" type="tip"/>
![header](/img/2024.1.7/header.png)

Code completion is supported for some common header keys, such as `Authorization`
:::

::: hint @FeignClient scan support <Badge vertical="top" text="New feature" type="tip"/>
![feignClientSupport](/img/2024.1.7/feignClientSupport.png)

Support scan `org.springframework.cloud.openfeign.FeignClient`, optimized the path parse result
:::

::: hint Default file path for multipart <Badge vertical="top" text="New feature" type="tip"/>
![defaultMultipartFile](/img/2024.1.7/defaultMultipartFile.png)

Multipart type fields can be set default path by this setting.
:::

::: hint Error report support <Badge vertical="top" text="New feature" type="tip"/>
![errorReport](/img/2024.1.7/errorReport.png)

It is easier to report bugs, which allows authors to better fix and locate bugs.
:::

::: hint SearchEveryWhere result sorting optimization <Badge vertical="top" text="Optimization" type="info"/>
![searchEveryWhere](/img/2024.1.7/searchEveryWhere.png)

Optimized the collation for matching by URL path
:::

::: hint Script grammar hints optimization <Badge vertical="top" text="Optimization" type="info"/>
![scriptHint](/img/2024.1.7/scriptHint.png)

Need to click `Add Script library` first. After that, use built-in variables `rfr.request`、`rfr.response` to get code completion.

Old built-in variables `request` and `response` can still work, but the code completion hint is not friendly.

For more script knowledge please refer to [Script->rfr](./script.md#rfr).

:::

## 2024.1.6.3 <Badge text="Free trial" type="tip"/> <Badge text="2024-08-15" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Compatible with IDEA 2024.2+" type="info"/>

## 2024.1.6.2 <Badge text="Free trial" type="tip"/> <Badge text="2024-07-30" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Combined annotation support" type="tip"/>
- <Badge text="Message Optimization" type="info"/>
- <Badge text="After the pre-script is executed, the data not used in the current request" type="danger"/>

::: hint Combined annotation support <Badge vertical="top" text="New feature" type="tip"/>

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

## 2024.1.6 <Badge text="Free trial" type="tip"/> <Badge text="2024-06-24" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Multi Tab window" type="important"/>
- <Badge text="Apis mark" type="tip"/>
- <Badge text="Url copy in Apis & Navigate" type="tip"/>
- <Badge text="Json path support" type="tip"/>
- <Badge text="Url generation supports extends BaseController interface" type="info"/>
- <Badge text="Fixed some known bugs" type="danger"/>

::: hint Multi Tab window <Badge vertical="top" text="Important feature" type="important"/>
<MyCarousel :imgList="['/img/2024.1.6/multiTab.png','/img/2024.1.6/openInTabAction.png','/img/2024.1.6/openTabInApis_en.png']" />

Open `rapi` file to display the debugging interface and supports multi-Tab opening. More detail please refer [**Multi-tab window**](/en/guide/features/navigateCurrentMethodJson.md)

:::

::: hint Apis mark <Badge vertical="top" text="New feature" type="tip"/>
<MyCarousel :imgList="['/img/2024.1.6/mark1_en.png','/img/2024.1.6/mark2.png']" />

Added support for mark APIs in the Apis tab, marking them with red highlight dots, and added mark Api searche condation. Developers can mark the important APIs to make them more visible
:::

::: hint Url copy in Apis & Navigate <Badge vertical="top" text="New feature" type="tip"/>
<MyCarousel :imgList="['/img/2024.1.6/urlCopyInApis_en.png','/img/2024.1.6/urlCopyInNav_en.png']" />

Right-click option supports copying original URL
:::

::: hint Json path support <Badge vertical="top" text="New feature" type="tip"/>

![jsonPath](/img/2024.1.6/jsonPath.png)

Support json path search In `Body->Raw` and `Response->Pretty` tab, but this depends on [JSONPath](https://plugins.jetbrains.com/plugin/22044-jsonpath) plugin.
:::

## 2024.1.5.1 <Badge text="Free trial" type="tip"/> <Badge text="2024-05-16" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Entity parse" type="danger"/>

## 2024.1.5 <Badge text="Free trial" type="tip"/> <Badge text="2024-05-14" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Add library support in pre and post script" type="tip"/>
- <Badge text="Type drop-down box optimization" type="info"/>
- <Badge text="Original url display optimization" type="info"/>
- <Badge text="Apifox directory sync optimization" type="info"/>
- <Badge text="Url input style optimization" type="info"/>
- <Badge text="Parameter Tab automatic jump optimization" type="info"/>
- <Badge text="cUrl import optimization" type="info"/>
- <Badge text="Fix Api doc synchronization is successful but prompts an error" type="danger"/>

::: hint Add library support in pre and post script <Badge vertical="top" text="New feature" type="tip"/>
![](/img/2024.1.5/addLibrary_en.png)

Add a button to allow adding script library to get the [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html) function.
:::

::: hint Type drop-down box optimization <Badge vertical="top" text="Optimization" type="info"/>
![](/img/2024.1.5/typeDropdown.png)

The drop-down box uses a more clear English type and uses different colors to distinguish different types.

:::

::: hint Original url display optimization <Badge vertical="top" text="Optimization" type="info"/>

![](/img/2024.1.5/originUrl.png)

Url in run tab, and APIs tab is displayed as the original URL, the path parameters will no longer replace.
:::

::: hint cUrl import optimization <Badge vertical="top" text="Optimization" type="info"/>
Url is the same, but the method type (Get, Post, etc.) is different, the binding pairs are optimized so that they can match correctly.
:::

## 2024.1.4 <Badge text="Free trial" type="tip"/> <Badge text="2024-04-08" color="SandyBrown"/> <Badge text="IDEA 2022.3+" color="pink"/>

- <Badge text="Apifox integration" type="important"/>
- <Badge text="AI support optimization" type="important"/>
- <Badge text="Change moduleHeader to apiHeader" type="info"/>
- <Badge text="cURL import Optimization" type="info"/>
- <Badge text="Entity parse optimization" type="info"/>
- <Badge text="Compatible with IDEA 2024" type="info"/>
- <Badge text="API export file suffix error" type="danger"/>

::: hint Apifox integration <Badge vertical="top" text="Important feature" type="important"/>

<MyCarousel :imgList="['/img/2024.1.4/apifoxIntegration_en.png','/img/2024.1.4/apifoxConfig_en.png']" />

Apifox integration supports synchronization of APIs, environment variables, domain name information, etc. Just configure an `API key` and set the project mapping relationship to synchronize.
:::

::: hint AI support optimization <Badge vertical="top" text="Important feature" type="important"/>
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

::: hint Change moduleHeader to apiHeader <Badge vertical="top" text="Optimization" type="info"/>

![Header](/img/2024.1.4/header.png)

1. The variable `moduleHeader` in the pre-script and post-script is changed to `apiHeader`.
2. The scope of the Header tab in the main interface has been changed to only face the API level.
   That is, each API can have different Headers. If you need a public Header, please go to [Project Level Header](/guide/features/projectValueConfig.md) setting.

:::

::: hint cURL import Optimization <Badge vertical="top" text="Optimization" type="info"/>
在去除域名后，如果 Url 与项目中方法的 Url 一致则自动绑定。
:::

## 2024.1.3 <Badge text="Free trial" type="tip"/> <Badge text="2024-03-04" color="SandyBrown"/>

- <Badge text="AI support optimization" type="important"/>
- <Badge text="Performance optimization for large response results" type="info"/>
- <Badge text="Map type parse optimization" type="info"/>
- <Badge text="Download does not respond in Windows system" type="danger"/>

::: hint AI support optimization <Badge vertical="top" text="Important feature" type="important"/>
<MyCarousel :imgList="['/img/2024.1.3/aiManagenemt_en.png','/img/2024.1.3/aiPromptEdit_en.png','/img/2024.1.3/aiPromptChoose_en.png']" />

Added three forms of AI support: OpenAi, CodeGeeX, and Private deployment AI. Through prompt, selected text can be replaced with another text.

AI action has added the following tabs: `Body -> JSON`, `Body -> Form URL-Encoded -> Text`,`URL Params -> Text`
:::

## 2024.1.2.1 <Badge text="Free trial" type="tip"/> <Badge text="2024-01-24" color="SandyBrown"/>

- <Badge text="No response when click send and download" type="danger"/>

## 2024.1.2 <Badge text="Free trial" type="tip"/> <Badge text="2024-01-19" color="SandyBrown"/>

- <Badge text="Custom toolbar" type="important"/>

::: hint Custom toolbar <Badge vertical="top" text="Important feature" type="important"/>

![customToolbar](/img/2024.1.2/customToolbar_en.png)

Using the custom toolbar option, select the functionalities you want to use and hide other function buttons. Then restart IDEA.
:::

## 2024.1.1 <Badge text="Free trial" type="tip"/> <Badge text="2024-01-15" color="SandyBrown"/>

- <Badge text="SearchEveryWhere support classname search" type="important"/>
- <Badge text="Generate .fastRequest directory strategy" type="important"/>
- <Badge text="Ignore field name parse support" type="tip"/>
- <Badge text="Apis and Navigate in popup" type="tip"/>
- <Badge text="Manual update check" type="tip"/>
- <Badge text="Content-Disposition support optimization" type="info"/>
- <Badge text="SearchEveryWhere performance optimization" type="info"/>
- <Badge text="API loading performance in Navigate optimization" type="info"/>
- <Badge text="Markdown doc add return value doc" type="info"/>
- <Badge text="Collection generic parsing error" type="danger"/>

::: hint SearchEveryWhere support classname search <Badge vertical="top" text="Important feature" type="important"/>
![searchEveryWhere](/img/2024.1.1/searchEveryWhere_en.png)

Support enters the class name where the Url is located to search for all APIs under this class.

:::

::: hint Generate .fastRequest directory strategy <Badge vertical="top" text="Important feature" type="important"/>
When Project is opened by default, the .fastRequest directory will no longer be generated.
Only when you operate plugin, it will be generated.
:::

::: hint Ignore field name parse support <Badge vertical="top" text="New feature" type="tip"/>
![ignoreFiled](/img/2024.1.1/ignoreFiled_en.png)

Only need to add field names in the configuration to ignore target fields in the entity class.

:::

::: hint Apis and Navigate in popup <Badge vertical="top" text="New feature" type="tip"/>
<MyCarousel :imgList="['/img/2024.1.1/apis_en.png','/img/2024.1.1/navigate_en.png']" />
You can quickly open the Apis and Navigate window to view information through shortcut keys.
When the window is open, press ESC to close the window.
:::

::: hint Manual update check <Badge vertical="top" text="New feature" type="tip"/>
![manualUpdateCheck](/img/2024.1.1/manualUpdateCheck.png)

Click to check for plugin updates
:::

::: hint SearchEveryWhere performance optimization <Badge vertical="top" text="Optimization" type="info"/>
The SearchEveryWhere search logic has been optimized to greatly improve the search performance of large projects.

We tested a project with 7000 APIs and the search response was very fast
:::

::: hint Content-Disposition support optimization <Badge vertical="top" text="Optimization" type="info"/>

Content-Disposition supports the following 2 formats, and also supports file name transcoding.

```
Content-Disposition:attachment; filename*=UTF-8''fastRequest%20.txt
Content-Disposition:attachment; filename=fastRequest.txt
```

:::

## 2023.2.3.1 <Badge text="Free trial" type="tip"/> <Badge text="2023-12-20" color="SandyBrown"/>

- <Badge text="JSON5 support error" type="danger"/>

## 2023.2.3 <Badge text="Free trial" type="tip"/> <Badge text="2023-12-19" color="SandyBrown"/>

- <Badge text="Sync API to Postman" type="tip"/>
- <Badge text="Project name drop-down box automatically switches" type="tip"/>
- <Badge text="Environment refactoring" type="info"/>
- <Badge text="Project domain name addition optimization" type="info"/>
- <Badge text="Change API document sync trigger to API is saved" type="info"/>
- <Badge text="Add @fastRequestParseIgnore support for ignore parse field" type="info"/>
- <Badge text="SearchEveryWhere compatible idea2023.3" type="danger"/>
- <Badge text="Error when passing non-json in body param" type="danger"/>
- <Badge text="curl import parsing\' error" type="danger"/>

::: hint Sync API to Postman <Badge vertical="top" text="Important feature" type="important"/>
You only need to configure the token of Postman and the ID of the workspace to upload the API to Postman.Enjoy what fast request do not support.

To make the data synchronised to Postman more complete, it is recommended to select the `sync after API save` option in the Postman config of the plugin
then trigger the synchronisation by saving the API.

More details please refer [Postman sync](./features/postmanSync.md)
:::

::: hint Project name drop-down box automatically switches <Badge vertical="top" text="New feature" type="tip"/>
When generating parameters for APIs under different modules, if auto domain name is enabled, the project name drop-down box will automatically switch
:::

::: hint Environment refactoring <Badge vertical="top" text="Optimization" type="info"/>
![environment](/img/2023.2.3/environment_en.png)

Environment contains Current value and Initial value. Initial value can be shared by submitting config fine in `.fastRequest/config/fastRequestCurrentProjectEnvironment.json`.

:::

::: hint Project domain name addition optimization <Badge vertical="top" text="Optimization" type="info"/>

The module name is automatically recognized as a drop-down box option, and you can also customize it.

![projectName](/img/2023.2.3/projectName.png)
:::

::: hint Add @fastRequestParseIgnore support for ignore parse field <Badge vertical="top" text="Optimization" type="info"/>
To ignore parsing for entity field, you can add `@fast Request Parse Ignore` to the annotation. The original annotation `@parse Ignore` is still applicable.

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

:::

## 2023.2.2 <Badge text="Free trial" type="tip"/> <Badge text="2023-11-24" color="SandyBrown"/>

- <Badge text="Markdown doc template config" type="tip"/>
- <Badge text="JSON5 support" type="tip"/>
- <Badge text="Compatible with IDEA 2023.3" type="tip"/>
- <Badge text="History requests list show errors" type="danger"/>

::: hint Markdown doc template config <Badge vertical="top" text="New feature" type="tip"/>
Provides a custom format for local markdown and [Online API](./features/apiDocSync.md) doc.View [more detail](./features/markdownDocTemplateConfig.md)
:::

::: hint JSON5 support <Badge vertical="top" text="New feature" type="tip"/>
![json5](/img/2023.2.2/json5.png)
JSON5 format support for Request body, JSON field comment support

:::

## 2023.2.1.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Style optimization for Url textField in some themes" type="tip"/>
- <Badge text="Project level header save error" type="danger"/>

## 2023.2.1 <Badge text="Free trial" type="tip"/>

- <Badge text="UI predigestion" type="tip"/>
- <Badge text="Support open API document in apis list" type="tip"/>
- <Badge text="Auto domain" type="info"/>
- <Badge text="Map parse" type="info"/>
- <Badge text="Put show apis into right click menu" type="info"/>
- <Badge text="Add plugin version to statistics page" type="info"/>
- <Badge text="Many details optimized" type="info"/>
- <Badge text="Url and cUrl copy can not replace environment variable" type="danger"/>
- <Badge text="Curl import parsing error in form-urlencoded and -d forms" type="danger"/>

::: hint UI predigestion <Badge vertical="top" text="New feature" type="tip"/>
![urlInputWithMethod](/img/2023.2.1/urlInputWithMethod.png)
Simplify the three horizontal components of the old version (method drop-down box, url input box,
and response drop-down box) into one, and remove the response drop-down box to increase simplicity.

:::

::: hint Support open API document in apis list <Badge vertical="top" text="New feature" type="tip"/>

![openDoc](/img/2023.2.1/openDoc.png)
:::

::: hint Put show apis into right click menu <Badge vertical="top" text="Optimization" type="info"/>

![openDoc](/img/2023.2.1/showApis.png)
:::

::: hint Add plugin version to statistics page <Badge vertical="top" text="Optimization" type="info"/>

![pluginVersion](/img/2023.2.1/pluginVersion.png)
:::

## 2023.1.9 <Badge text="Free trial" type="tip"/>

- <Badge text="Quick add Environment variable support" type="tip"/>
- <Badge text="Add、Delete、Modify support in Environment use view" type="tip"/>
- <Badge text="Show apis trigger in Controller" type="tip"/>
- <Badge text="Response adds Size property" type="tip"/>
- <Badge text="API Name Saving Optimization" type="info"/>
- <Badge text="Input parameter trim optimization" type="info"/>
- <Badge text="Reduce JSON tree tab size from 5MB to 2MB" type="info"/>
- <Badge text="Remove Accept-Language header when send request" type="danger"/>
- <Badge text="cURL copy format error of form-data" type="danger"/>
- <Badge text="Logic error of domain configuration page in some the case" type="danger"/>

::: hint Quick add Environment variable support <Badge vertical="top" text="New feature" type="tip"/>

![quickAddEnvironmentVariable](/img/2023.1.9/quickAddEnvironmentVariable.png)
Click the "Add" button to select a line and add it to the environment variables, which can quickly save the variable to the current Env.
:::

::: hint Add、Delete、Modify support in Environment use view <Badge vertical="top" text="New feature" type="tip"/>
![environmentVariableEditRemoveModify](/img/2023.1.9/environmentVariableEditRemoveModify_en.png)
:::

::: hint Show apis trigger in Controller <Badge vertical="top" text="New feature" type="tip"/>
![showApis](/img/2023.1.9/showApis.gif)
Right-click in the Controller in any position to invoke `Generate`, choose`Show apis` , all APIs in the current controller can be displayed.

:::

::: hint Response adds Size property <Badge vertical="top" text="New feature" type="tip"/>
![responseSize.png](/img/2023.1.9/responseSize.png)
:::

## 2023.1.8 <Badge text="Free trial" type="tip"/>

- <Badge text="Environment" type="tip"/>
- <Badge text="Method description length truncate" type="info"/>
- <Badge text="Parse support for Instant" type="info"/>
- <Badge text="Delete api directory error in Windows system" type="danger"/>

::: hint Environment <Badge vertical="top" text="New feature" type="tip"/>
Add <ColorIcon icon="environment" /> nvironment variable support, [more detail](./features/environment.md)
:::

::: hint Method description length truncate <Badge vertical="top" text="Optimization" type="info"/>
![methodDescriptionLength](/img/2023.1.8/methodDescriptionLength_en.png)

You can truncate the method description process when the method name is too long, resulting in display or data saving.

Such as in `SearchEveryWhere`

:::

## 2023.1.7.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Add generate action in SearchEveryWhere" type="tip"/>
- <Badge text="The main window pops up with configuration" type="info"/>
- <Badge text="Param parse" type="info"/>
- <Badge text="Table dragging problem" type="info"/>

::: hint Add generate action in SearchEveryWhere <Badge vertical="top" text="New feature" type="tip"/>
![searchEveryWhere](/img/2023.1.7.1/searchEveryWhere.png)
After select Auto-generate icon, you can echo or automatically generate API-related URLs and parameter in SearchEveryWhere

:::

::: hint The main window pops up with configuration <Badge vertical="top" text="Optimization" type="info"/>
Add a config `Show main tool window when action trigger` in `Setting->Request Fast Request->Other config`.
:::

::: hint Param parse <Badge vertical="top" text="Optimization" type="info"/>
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

## 2023.1.7 <Badge text="Free trial" type="tip"/>

- <Badge text="New icon" type="tip"/>
- <Badge text="SearchEveryWhere enhance" type="tip"/>
- <Badge text="Slack support" type="tip"/>
- <Badge text="Compact http icon" type="tip"/>
- <Badge text="Duplicate field" type="tip"/>
- <Badge text="Optimize the insertion of script snippets" type="info"/>
- <Badge text="Optimize table and textarea" type="info"/>
- <Badge text="Optimize automatic domain parse" type="info"/>
- <Badge text="cUrl import" type="info"/>
- <Badge text="FullScreen" type="info"/>
- <Badge text="Params parse" type="info"/>
- <Badge text="Remove the .main from the module name of a Gradle project" type="info"/>
- <Badge text="SearchEveryWhere keyword search with spaces" type="danger"/>
- <Badge text="Optimizing numerous operational details" type="danger"/>
- <Badge text="Batch export api doc" type="danger"/>
- <Badge text="History request echo data" type="danger"/>

::: hint New icon <Badge vertical="top" text="New feature" type="tip"/>
![toolwindowNew](/img/toolwindowNew.png)
:::

::: hint SearchEveryWhere enhance <Badge vertical="top" text="New feature" type="tip"/>
![searchEveryWhere](/img/2023.1.7/searchEveryWhere.png)
Add `module`、`methodType`、`library` condition
:::

::: hint Slack support <Badge vertical="top" text="New feature" type="tip"/>

If you have any question, please click [![Slack](https://img.shields.io/static/v1?label=Slack&message=Restful%20Fast%20Request&logo=slack&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw) and join in.

We will help you solve your problems. Thanks!

:::

::: hint Compact http icon <Badge vertical="top" text="New feature" type="tip"/>

You can also change it in `setting -> Restful Fast Request -> Use compact http icon`

| Method |                   New Icon                    |                 Old Icon                  |
| :----: | :-------------------------------------------: | :---------------------------------------: |
|  Get   |    ![get_dark](/img/http/new/get_dark.svg)    |    ![get_dark](/img/http/get_dark.svg)    |
|  Post  |   ![post_dark](/img/http/new/post_dark.svg)   |   ![post_dark](/img/http/post_dark.svg)   |
| Delete | ![delete_dark](/img/http/new/delete_dark.svg) | ![delete_dark](/img/http/delete_dark.svg) |
|  Put   |    ![put_dark](/img/http/new/put_dark.svg)    |    ![put_dark](/img/http/put_dark.svg)    |
| Patch  |  ![patch_dark](/img/http/new/patch_dark.svg)  |  ![patch_dark](/img/http/patch_dark.svg)  |

:::

::: hint Duplicate field <Badge vertical="top" text="New feature" type="tip"/>
![fieldDup](/img/2023.1.7/fieldDup.png)
Support field line duplicate, mark key end with `-dup` for duplicate key, plugin will do not change key for collection field, developers need to modify manually.

:::

## 2023.1.6 <Badge text="Free trial" type="tip"/>

- <Badge text="Header batch edit" type="tip"/>
- <Badge text="Idea hang when debug in post script" type="danger"/>

::: hint Header batch edit <Badge vertical="top" text="New feature" type="tip"/>

![batchEdit](/img/2023.1.6/batchEdit_en.png)

:::

## 2023.1.5 <Badge text="Free trial" type="tip"/>

- <Badge text="Editor icon location" type="info"/>
- <Badge text="SearchEveryWhere style confusion" type="danger"/>
- <Badge text="URL variable replacing" type="danger"/>

::: hint Editor icon location <Badge vertical="top" text="Optimization" type="info"/>

![editorAction](/img/2023.1.5/editorAction.png)
Move from the original button where it is easy to block the text to the upper right corner of the editor

:::

::: caution SearchEveryWhere style confusion
This issue accompanies many versions and this version completely fixes it.

SearchEveryWhere, from now on, will not have the problem of the tab width increasing and causing the tab to not be fully displayed.
:::

## v2023.1.4.2 <Badge text="Free trial" type="tip"/>

- <Badge text="Domain edit error in project domain config" type="danger"/>
- <Badge text="headers group load error" type="danger"/>

## v2023.1.4.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Fix Auto domain of multi-module project" type="danger"/>

## v2023.1.4 <Badge text="Free trial" type="tip"/>

- <Badge text="Auto domain" type="tip"/>
- <Badge text="SearchEveryWhere supports searching URLs in jar" type="tip"/>
- <Badge text="Set project header error in Script" type="danger"/>

::: hint Auto domain <Badge vertical="top" text="New feature" type="tip"/>
Automatically identify or add a default domain,---->[More detail](./features/autoDomain.md)
:::

## v2023.1.3.2 <Badge text="Free trial" type="tip"/>

- <Badge text="Teamwork support" type="tip"/>
- <Badge text="OpenAI API Optimising fields support" type="tip"/>
- <Badge text="API and API storage file jumps" type="tip"/>
- <Badge text="Project config refresh" type="tip"/>
- <Badge text="rfr.currentModuleName support" type="tip"/>
- <Badge text="API doc sync supports custom branch" type="info"/>
- <Badge text="Toolbar" type="info"/>
- <Badge text="GET、POST、DELETE、PUT、PATCH icon" type="info"/>
- <Badge text="LocalDateTime、LocalDate、LocalTime parse" type="info"/>
- <Badge text="Response text not format in IDEA 2023.1" type="danger"/>
- <Badge text="Random generate url from array url" type="danger"/>
- <Badge text="Comment can not be generate in Kotlin" type="danger"/>

[FAQ:API data lost after install 2023.1.3](./faq.md#s-apis-lost)

::: hint OpenAI API Optimising fields support <Badge vertical="top" text="New feature" type="tip"/>
![ai](/img/2023.1.3/ai.gif)
Replace some random fields with the [OpenAI API](https://platform.openai.com/docs/api-reference/chat)

:::

::: hint API and API storage file jumps <Badge vertical="top" text="New feature" type="tip"/>

![jumpToJsonData](/img/2023.1.3/jumpToJsonData.png)

![jump from apis](/img/2023.1.3/listJump2JsonData.png)
Implementation of the API and the saved json data for associative jumping

:::

::: hint Project config refresh <Badge vertical="top" text="New feature" type="tip"/>

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)
When the configuration file is updated, this function allows the UI to refresh the configuration as well.
:::

::: hint rfr.currentModuleName support <Badge vertical="top" text="New feature" type="tip"/>
Get the module name of the current API. Please see it in [script](./script.md#rfr).
:::

::: hint API doc sync supports custom branch <Badge vertical="top" text="Optimization" type="info"/>
![apiSyncSupportBranch](/img/2023.1.3/apiSyncSupportBranch.png)
Support custom branch, as in some cases the 'master' branch is protected and does not allow push

:::

::: hint Toolbar <Badge vertical="top" text="Optimization" type="info"/>

![htoolbar](/img/2023.1.3/htoolbar.png)

![vtoolbar](/img/2023.1.3/vtoolbar.png)

![toolbarSetting](/img/2023.1.3/toolbarSetting.png)

1. Add vertical toolbar support.
2. Button position adjustment
   :::

::: hint GET、POST、DELETE、PUT、PATCH icon <Badge vertical="top" text="Optimization" type="info"/>

![toolbar](/img/2023.1.3/apis.png)
Rounded corners and adjusted background colour for icons.
:::

## v2023.1.2 <Badge text="Free trial" type="tip"/>

- <Badge text="Image render from response" type="tip"/>
- <Badge text="Annual report" type="tip"/>
- <Badge text="Url parse logic" type="info"/>
- <Badge text="cUrl Import" type="info"/>
- <Badge text="Url special character parameter encoding" type="info"/>
- <Badge text="Add send request trigger to url field" type="info"/>
- <Badge text="Remove title of window" type="info"/>
- <Badge text="Patch request error in jdk11+" type="danger"/>
- <Badge text="API name saved error" type="danger"/>

::: hint Image render from response <Badge vertical="top" text="New feature" type="tip"/>

![imageRender](/img/2023.1.2/imageRender.png)
Automatically render images for requests that respond to image.
:::

::: hint Annual report <Badge vertical="top" text="New feature" type="tip"/>
![annualReport](/img/2023.1.2/annualReport_en.png)
Statistics for previous years of operation.
:::

::: hint Url parse logic <Badge vertical="top" text="Optimization" type="info"/>
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

::: hint cUrl import <Badge vertical="top" text="Optimization" type="info"/>

1. automatic parsing of query param into `Url params` tab page
2. json parsing optimization
3. Known bug fixes
   :::

::: hint Url special character parameter encoding <Badge vertical="top" text="Optimization" type="info"/>
We have done some optimization, no longer need to encode special characters in url params.
:::

::: hint Add send request trigger to url field <Badge vertical="top" text="Optimization" type="info"/>
Add `Enter` key listener to url field that can send the request.
:::

## v2023.1.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Add support for quickly add domain from Yml or Properties config" type="tip"/>
- <Badge text="Add project-level domain config" type="tip"/>
- <Badge text="Icon alignment" type="info"/>
- <Badge text="Tool window icon in new UI" type="info"/>
- <Badge text="Make Project-level headers value optional" type="info"/>
- <Badge text="Cookie fetch" type="info"/>
- <Badge text="Swagger default value for basic type" type="danger"/>
- <Badge text="Array not parse" type="danger"/>

::: hint Project-level domain config <Badge vertical="top" text="New feature" type="tip"/>

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig_en.png "Domain config")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog_en.png "Domain list")
Supports fast config domain directly from the `yml` or `properties` file，for more detail, please see [<ColorIcon icon="domainConfig" /> Project-level domain config](./features/projectLevelDomainConfig.md)
:::

::: hint Icon alignment <Badge vertical="top" text="Optimization" type="info"/>
The buttons have been categorised to make it easier for developers to find and understand the function of the corresponding button.
:::

::: hint Tool window icon in new UI <Badge vertical="top" text="Optimization" type="info"/>
The clarity of the tool window icons has been optimised under the new UI.
:::

## v2022.3.1 <Badge text="Free trial" type="tip"/>

- <Badge text="Add @fastRequestParseIgnore comment support for parse" type="tip"/>
- <Badge text="Add rfr.currentDomain in Script" type="tip"/>
- <Badge text="Activation prompt optimization" type="info"/>
- <Badge text="Reload api error in navigate when using Kotlin and Java in one project" type="danger"/>

::: hint Add @parseIgnore comment support for parse <Badge vertical="top" text="New feature" type="tip"/>

The following code, the plugin will ignore parsing this field. Please use `@parseIgnore`

```java
/**
 * xxx description
 * @parseIgnore
 */
private String someIgnoreField ;
```

:::

::: hint Add rfr.currentDomain in Script <Badge vertical="top" text="New feature" type="tip"/>
You can get the current domain link in the Script through `rfr.currentDomain`
:::

## v2022.2.9 <Badge text="Free trial" type="tip"/>

- <Badge text="Compatible with IDEA 2022.2.4" type="tip"/>
- <Badge text="Global animate config support" type="tip"/>
- <Badge text="Cookie bug" type="danger"/>
- <Badge text="Navigate tab load error" type="danger"/>

::: hint Global animate config support <Badge vertical="top" text="New feature" type="tip"/>
![animateConfig](/img/2022.2.9/animateConfig_en.png)
You can close animate in this global config, if close, icon animation <ColorIcon icon="restfulFastRequest" /> will stop
:::

## v2022.2.8 <Badge text="Free trial" type="tip"/>

- <Badge text="Directory download file support" type="info"/>
- <Badge text="@RequestParam support name attribute" type="info"/>
- <Badge text="Error when check update" type="info"/>

::: hint Directory download file support <Badge vertical="top" text="New feature" type="tip"/>
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

::: hint Api sync <Badge vertical="top" text="New feature" type="tip"/>

<MyCarousel :imgList="['/img/2022.2.7/apiSync_en.png','/img/2022.2.7/apiSyncSetting_en.png']" />

Online Api doc sync---->[More detail](./features/apiDocSync.md)
:::

:::tip Api doc show return type document
![returnValueDoc](/img/2022.2.7/returnValueDoc.png)
:::

::: hint Plugin update notification mechanism <Badge vertical="top" text="New feature" type="tip"/>
![upgradeNotice](/img/2022.2.7/upgradeNotice_en.png)

Change from an explicit dialog box to a notification, at the same time automatic updates have been changed to ==turn on==, if you want to turn it off, please go to the configuration page and turn it off manually.
But I recommend ==turning it on==, so that you can receive updates in time.

Note that the previous version of the update will still be a pop-up dialog, this version onwards will be a message notification.

:::

::: hint Common header <Badge vertical="top" text="New feature" type="tip"/>

![commonHeader](/img/2022.2.7/commonHeader.gif)
[---->More detail](./features/commonHeader.md)
:::

::: hint Full screen <Badge vertical="top" text="New feature" type="tip"/>

![fullScreen](/img/2022.2.7/fullScreen.png)
[---->More detail](./features/fullScreen.md)
:::

::: hint Comment preview <Badge vertical="top" text="Optimization" type="info"/>
![fullScreen](/img/2022.2.7/commentPreview.png)
:::

## v2022.2.6 <Badge text="Free trial" type="tip"/>

- <Badge text="Clear params support" type="tip"/>
- <Badge text="Api comment preview" type="tip"/>
- <Badge text="Sort param column by key" type="tip"/>
- <Badge text="Api doc add required column" type="info"/>
- <Badge text="Long delay in checking for updates" type="info"/>
- <Badge text="Long delay when first open tool window" type="info"/>
- <Badge text="Multi print when use pre-script" type="danger"/>

::: hint Clear params support <Badge vertical="top" text="New feature" type="tip"/>

![clear](/img/2022.2.6/clear.png)
clear all

![clearColumnValue](/img/2022.2.6/clearColumnValue.png)
batch clean column value
:::

::: hint Api comment preview <Badge vertical="top" text="New feature" type="tip"/>
![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
:::

::: hint Sort param column by key <Badge vertical="top" text="New feature" type="tip"/>
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

::: hint Console support <Badge vertical="top" text="New feature" type="tip"/>

![console](/img/2022.2.5/console_en.png)
Console helps developers print some info you wanted

More info please see [script->console](./script.md#console)

:::

::: hint Add currentProjectName and currentEnvName property in build-in property <Badge vertical="top" text="New feature" type="tip"/>
You can use these 2 variables for some judgment

More info please see [script->Built-in variable->rfr](./script.md#rfr)
:::

::: hint Get description from javadoc for path and request param <Badge vertical="top" text="New feature" type="tip"/>

![parseDocDesc](/img/2022.2.5/parseDocDesc.png)
Parse parameter comments in the form of Javadoc, suitable for path parameters and request param is a parameter of non-entity class
:::

::: hint twitter <Badge vertical="top" text="New feature" type="tip"/>
If you have a Twitter account, please follow me, thank you

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

::: hint New logo <Badge vertical="top" text="New feature" type="tip"/>

<MyCarousel :imgList="['/img/2022.2.4/newLogo.gif','/img/2022.2.4/newLogoConfig.png','/img/2022.2.4/newLogo.png']"/>

Developers can freely choose and switch the color you want in the drop-down box, and can make it moving

:::

::: hint Support parse date by DateTimeFormat annotation pattern <Badge vertical="top" text="New feature" type="tip"/>
![dateTimeFormat](/img/2022.2.4/dateTimeFormat.png)
:::

::: hint Support parse url of BaseController <Badge vertical="top" text="New feature" type="tip"/>
![baseController](/img/2022.2.4/baseController.png)
:::

::: hint Change auto update configurable <Badge vertical="top" text="Optimization" type="info"/>

![autoUpdate](/img/2022.2.4/autoUpdate.png)
If you don't need to receive automatic updates, you can turn it off and update it manually (recommend open)

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

::: hint Script support <Badge vertical="top" text="New feature" type="tip"/>

![scriptProject](/img/2022.2.3/scriptProject_en.png)

![scriptModule](/img/2022.2.3/scriptModule_en.png)

Scripts allow developers to more flexibly, dynamically and easily modify some input parameters of the request process and the processing of responses.
Please refer to [Script](./script.md)
:::

::: hint One-click copy Url <Badge vertical="top" text="New feature" type="tip"/>
![copyUrl](/img/2022.2.3/copyUrl.png)
:::

::: hint Apply table cell value change when click send request <Badge vertical="top" text="Optimization" type="info"/>
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

::: hint Add stop API request function <Badge vertical="top" text="New feature" type="tip"/>
![stopApi](/img/2022.2.2/stopApi.gif)
:::

::: hint Batch export API doc <Badge vertical="top" text="New feature" type="tip"/>
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

::: hint API grouping parameter save support <Badge vertical="top" text="New feature" type="tip"/>
Purpose: Save parameters of different combinations under one API

==The original save operation will be classified into the Default group by default==

How to:After entering the parameters, click ==Save group param request==

![groupSave](/img/2022.2.1/groupSave_en.png)

**Toggle parameter**

The following is the query of the book list in 3 languages

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)
:::

::: hint Temporary request save support <Badge vertical="top" text="New feature" type="tip"/>

![tempSave](/img/2022.2.1/tempSave_en.png)

Purpose: Save any request that does not belong to this project for temporary invocation, not associated with the current project code

The Url of the Request saved by the temporary request must start with ==http== or ==https==

:::

::: hint cURL import <Badge vertical="top" text="New feature" type="tip"/>

![importByCurl](/img/2022.2.1/importByCurl_en.gif)

==Note that if you need to bind a method, you need to place the cursor on the method name==
:::

::: hint Add Response Header in response <Badge vertical="top" text="New feature" type="tip"/>

![responseHeader](/img/2022.2.1/responseHeader.png)

:::

::: hint Add support for Url suffix <Badge vertical="top" text="New feature" type="tip"/>

![responseHeader](/img/2022.2.1/urlSuffix.png)

Purpose: Some requests need to add .do at the end of the url

:::

::: hint Optimized parameter parsing <Badge vertical="top" text="Optimization" type="info"/>
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

::: hint Add support for history request preview <Badge vertical="top" text="New feature" type="tip"/>
![history](/img/history.png)
Hidden skills: Double-click the record to display details
:::

::: hint Add support for description search in SearchEveryWhere <Badge vertical="top" text="Optimization" type="info"/>
![searchEveryWhereDescription](/img/searchEveryWhereDescription.png)
Support description search API
:::

::: hint Add request timeout settings <Badge vertical="top" text="New feature" type="tip"/>
![timeoutSetting](/img/timeoutSetting.png)
Default value: 60 seconds
:::

::: hint Optimization <Badge vertical="top" text="Optimization" type="info"/>

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

::: hint Add shortcut keys for tool window jump <Badge vertical="top" text="New feature" type="tip"/>
![toolwindowShortcut](/img/toolwindowShortcut.png)
Provides shortcut keys to jump quickly, you can change it in `setting -> Keymap`
:::

::: hint Add auto add cookie support <Badge vertical="top" text="New feature" type="tip"/>
![autoStoreCookie](/img/autoStoreCookie.png)
Then automatically stored in == project global parameters ==
If enable this configuration,cookie will automatically store in ==Project Global Config== -> `cookies`,This configuration is enabled by default.
:::

::: hint Add support for html preview in response raw <Badge vertical="top" text="Optimization" type="info"/>

![rawHtmlPreview](/img/rawHtmlPreview.png)
The response of the html return type is no longer truncated in raw, and the html can be previewed at the same time
:::

::: hint Ignore field parsing optimizations <Badge vertical="top" text="Optimization" type="info"/>

- <Badge text="Ignore parsing annotated by `@JsonIgnore`、`@JSONField(serialize = false)` fields" type="info"/>
- <Badge text="Ignore parsing`transient` keyword-decorated fields" type="info"/>

:::

::: hint Generic parameter parsing support <Badge vertical="top" text="Optimization" type="info"/>
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

::: hint Improve project global config <Badge vertical="top" text="Optimization" type="info"/>
![projectConfigParam](/img/projectGlobalParam.png)
Added `URL Params`、`URL-Encoded Params`、`Cookies`
:::

::: note Optimization

- Interface optimization
  - Optimized tab spacing(narrow under windows)、background color
- Save API insertion order optimization
  - The post-saved API will be placed on top

:::

::: hint APIs list view optimization <Badge vertical="top" text="New feature" type="tip"/>
![apisList](/img/apisList.png)
The rendering of the APIs interface has been optimized,Newly saved api icons are automatically optimized by default,The API saved in history can be right-clicked to select and change
:::

## v2022.1.8 <Badge text="Free trial" type="warn"/>

- <Badge text="Project-level global parameters support" type="tip"/>
- <Badge text="Optimized response data quickly add to headers" type="info"/>
- <Badge text="Optimized cookies quickly added to the header" type="info"/>
- <Badge text="Postman export optimization" type="info"/>
- <Badge text="Fix navigate tree scan bug" type="danger"/>
- <Badge text="Fix some bug" type="danger"/>

::: hint Project-level global parameters support <Badge vertical="top" text="New feature" type="tip"/>
![projectConfigParam](/img/projectConfigParam.png)

Support global parameters within the project level, not affected by multiple modules.

Configure priority `api header > project header>global header`

:::

::: hint Quickly add to header parameters <Badge vertical="top" text="Optimization" type="info"/>
![add2projectConfig](/img/add2projectConfig.png)

![cookie2projectConfig](/img/cookie2projectConfig.png)

Change ~~add params to global header~~ to `add params to project global header`

Cookie is also like this

:::

## v2022.1.7 <Badge text="Free trial" type="warn"/>

- <Badge text="Kotlin spring framework support" type="tip"/>
- <Badge text="Add SearchEveryWhere scope search strategy" type="tip"/>
- <Badge text="Add toggleUseSoftWrap support in editor" type="tip"/>
- <Badge text="Add support for add to Global Header from response" type="tip"/>
- <Badge text="Add support for navigating to the current method" type="tip"/>
- <Badge text="Optimize automatic binding projectName for saved api under multiple modules" type="info"/>

::: hint Kotlin spring framework support <Badge vertical="top" text="New feature" type="tip"/>
![kotlinSupport](/img/kotlinSupport.png)
:::

::: hint Add SearchEveryWhere scope search strategy <Badge vertical="top" text="Optimization" type="info"/>
![searchEveryWhereStrategy](/img/searchEveryWhereStrategy.png)
The 2022.1.7 version can search for mapping in SpringBoot feign or controller by default

The default search strategy is to search based on annotations, and two options are provided. If you need to exclude interfaces similar in SpringBoot feign interface during the search process, you can remove the check of Interface to deal with it.
:::

::: note Add toggleUseSoftWrap support in editor

![toggleUseSoftWrap](/img/toggleUseSoftWrap.png)
:::

::: hint Add support for add to Global Header from response <Badge vertical="top" text="New feature" type="tip"/>

![add2GlobalHeader](/img/add2GlobalHeader.png)
It is recommended to use in multi-module projects, because Add to Headers can only be added to the current module project
:::

::: note Add support for navigating to the current method

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)
After you generate the url of the method, sometimes you will switch to another place in the code, and then want to start debugging the API method, you can use this function to quickly locate the code
:::

::: hint Optimize automatic binding projectName for saved api under multiple modules <Badge vertical="top" text="Optimization" type="info"/>
Optimized the need to manually switch the project drop-down box for the saved api echo under the multi-module
:::

## v2022.1.6 <Badge text="Free trial" type="warn"/>

- <Badge text="Add support for one click to add cookie to header" type="tip"/>
- <Badge text="Add support for sharing API to Word and html" type="tip"/>
- <Badge text="Fix bug for `Basic Authorization` don't carry Basic" type="danger"/>
- <Badge text="Removes the drag function in APIs tab" type="danger"/>

::: hint Add support for one click to add cookie to header <Badge vertical="top" text="New feature" type="tip"/>

![addCookie2Header](/img/addCookie2Header.png)
For those that require cookie support for login, you can quickly add the cookie in the response to the Header to achieve this
:::

::: hint Add support for sharing API to Word and html <Badge vertical="top" text="New feature" type="tip"/>

![shareApi](/img/shareApi_en.png)
Please note that the API in word form is implemented in html, so don't feel strange, just export it
:::

## v2022.1.5 <Badge text="Free trial" type="warn"/>

- <Badge text="Add support for automatically generating parameter descriptions" type="tip"/>
- <Badge text="Add support for generate API documentation" type="tip"/>
- <Badge text="Add support for single class API list preview" type="tip"/>
- <Badge text="Remove text/html in Accept param in default header" type="danger"/>
- <Badge text="Fix some bugs" type="danger"/>

::: hint Add support for automatically generating parameter descriptions <Badge vertical="top" text="New feature" type="tip"/>

![paramDescription](/img/paramDescription.png)
You can hide or show description by toggle click
:::

::: hint Add support for generate API documentation <Badge vertical="top" text="New feature" type="tip"/>

![shareDocButton](/img/shareDocButton.png)

![apiDocExample](/img/apiDocExample.png)
After generating parameters, you can generate a Markdown document directly by sharing

:::

::: hint Add support for single class API list preview <Badge vertical="top" text="New feature" type="tip"/>

![apiDocExample](/img/apiPreview.gif)
After focusing on the window, enter the keyword, and you can quickly search according to the path keyword of the API
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

::: hint SearchEveryWhere highlight optimization <Badge vertical="top" text="Optimization" type="info"/>

![help](/img/searchEveryWhereHighlight.png)
Highlight search keywords to speed up the search for the API you really want to find.At the same time, the javadoc corresponding to the api is displayed
:::

::: hint Add support for import and export APIs <Badge vertical="top" text="New feature" type="tip"/>
![exportImportApis](/img/exportImportApis.gif)
Using this function, you can easily share your existing APIs with other developers, or import to IDEA on other devices

More info **[Features->APIs import and export](./feature.md#apis-import-and-export)**
:::

::: hint Add support for run APIs <Badge vertical="top" text="New feature" type="tip"/>
![runInApiManagement](/img/runInApiManagement.png)
You can run your saved requests directly in the APIs tab

:::

::: hint Automatically generate parameter optionals <Badge vertical="top" text="Optimization" type="info"/>
![generateSwitch](/img/generateSwitch_en.png)
:::

::: hint Merged Send and Send and Download buttons <Badge vertical="top" text="Optimization" type="info"/>
![mergeRunAndDownload](/img/mergeRunAndDownload.png)
The buttons have been merged, because we usually use Send but not Send and Download, which reduces the number of toolbar buttons and looks simpler
:::

::: hint Swagger default value parsing support <Badge vertical="top" text="New feature" type="tip"/>
Added the parsing of the default value of swagger annotations, which is more user-friendly of input parameters

- @ApiParam(swagger2)
- @ApiImplicitParam(swagger2)
- @ApiModelProperty(swagger2)
- @Parameter(swagger3)
- @Schema(swagger3)

More info **[Features->swagger default value parsing support](./feature.md#swagger-default-value-parsing-support)**
:::

::: hint Optimize the user guide of features <Badge vertical="top" text="New feature" type="tip"/>
![help](/img/help.png)
We have added a ? option in different windows to display some guidelines. For first-time users, it's easier to operate

And with the iteration of the version, more prompt operation guidelines may be added in the future

:::

::: hint Url generation optimization <Badge vertical="top" text="Optimization" type="info"/>
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

::: hint API Navigate rename to Navigate in tab <Badge vertical="top" text="Optimization" type="info"/>
In the case where the tool window is relatively small, the API Navigate will be hidden. In order to display more content in the smallest tool window as possible, the name is shorter.
:::

## v2.1.3 <Badge text="Free" type="info"/>

- Add encode support for special symbol in parameter
- Add global headers to curl

## v2.1.2 <Badge text="Free" type="info"/>

- Add support for export api to Postman
- Open the idea lazy loading API Navigate tree for the first time
- SearchEveryWhere show module
- Optimize URL parsing
- Optimize the display of api navigate tree
- Optimize the group when save api
- Global header params support

::: hint Optimize URL parsing <Badge vertical="top" text="New feature" type="tip"/>
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

::: hint Add support for export api to Postman <Badge vertical="top" text="New feature" type="tip"/>

![export2postman](/img/export2postman.gif)
:::

::: hint SearchEveryWhere show module <Badge vertical="top" text="New feature" type="tip"/>

![searchEveryWhereModule](/img/searchEveryWhereModule.png)
:::

::: hint Optimize the group when save api <Badge vertical="top" text="New feature" type="tip"/>

```
when saving the api,it will save to a group named controller's name

```

![save2ControllerGroup](/img/save2ControllerGroup.png)
:::

::: hint Global header params support <Badge vertical="top" text="New feature" type="tip"/>

![globalRequestHeader](/img/globalRequestHeader.png)
:::

## v2.1.1 <Badge text="Free" type="info"/>

- SearchEveryWhere support
- Add a what's new button to view the latest version at any time
- Optimize the parsing of enum
- Add support for http redirect

::: hint SearchEveryWhere support <Badge vertical="top" text="New feature" type="tip"/>

```
example
/url
get /list         (used for search get method)
post /save        (used for search post method)
```

![searchEveryWhere](/img/searchEveryWhere.gif)
:::

::: hint Quick view of the latest version <Badge vertical="top" text="New feature" type="tip"/>
![whatsnewNotifcation](/img/whatsnewNotifcation.png)
:::

## v2.1.0.2 <Badge text="Free" type="info"/>

- Joined the organization [**Dromara**](https://dromara.org/zh/)(An organization dedicated to native solutions for the
  microservice cloud)

## v2.1.0.1 <Badge text="Free" type="info"/>

- Fix get param error

## v2.1.0 <Badge text="Free" type="info"/>

- Fix post api parse error when using @RequestBody and @RequestParam
- Parsing support for nested classes
- Add support for url parse from the path property of the @RequestMapping in controller class level
- Added support for Api Tree scanning with @RequestMapping class

## v2.0.9 <Badge text="Free" type="info"/>

- Fix "Slow operations are prohibited on EDT" for 2021.3+
- Fix add headers from response
- Add project-level configuration,env and project will not change while switch project
- Add format action icon to textEditor

::: hint Add headers from response <Badge vertical="top" text="New feature" type="tip"/>

```
if your api need token in header,you can do like this to add params to header from response
```

![format](/img/quickAddHeaders.gif)
:::

::: hint Add format action icon to textEditor <Badge vertical="top" text="New feature" type="tip"/>
![format](/img/format.gif)
:::

## v2.0.8.1 <Badge text="Free" type="info"/>

- Fix conflicting error when searchEveryWhere
- Adjust position of toolbar action button
- remove unnecessary dependence,size 11.1M->5.9M

## v2.0.8 <Badge text="Free" type="info"/>

- Fix npe when first add project/env
- Json tree response character rendering maximum limit
- Add support for filter by methodType in Api navigate
- Add support for ignoring parameter parsing
- Fix some EDT problem
- Send/sendDownload button support for custom shortcut keys
- Fix response text not show in 2021.3

::: hint send/sendDownload button support for custom shortcut keys <Badge vertical="top" text="New feature" type="tip"/>

```
shortcut is available anywhere to trigger the event,No longer need to focus on the tool window
```

![shortcutSendAndDownload](/img/shortcutSendAndDownload.png)
:::

::: hint Add support for filter by methodType in Api navigate <Badge vertical="top" text="New feature" type="tip"/>
![shortcutSendAndDownload](/img/methodFilter.png)
:::

## v2.0.7 <Badge text="Free" type="info"/>

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

::: hint API navigate tree <Badge vertical="top" text="New feature" type="tip"/>

```
Select the tree and enter keywords
Press Enter again or double-click the left mouse button to locate the API

Hover the mouse to display the doc of the api
```

![apinavi](/img/apinav.gif)
:::

::: hint Headers Automatic switching <Badge vertical="top" text="New feature" type="tip"/>

```
Scenes:Multi-module projects such as SpringBoot have different header parameters in different projects and different environments.
In order to quickly switch headers, header grouping is coming.
Operation method：
1.Modify in the headers group, constraint: the input value must be in standard json format
2.Switch the environment or project name directly, and then enter the corresponding key and value values in the headers form
```

![headerSwitch](/img/headerSwitch.gif)
:::

::: hint Optimize the parameter parse of @RequestParam <Badge vertical="top" text="New feature" type="tip"/>
@RequestParam parameters will be prioritized according to the alias  
`@RequestParam("nameAlias")List<String> nameList` param name will be nameAlias
:::

::: hint Move send button to toolbar and support keyboard shortcut <Badge vertical="top" text="New feature" type="tip"/>

```
send request: alt =
send and download: alt -
Prerequisites:Tool window needs to be focused
```

![toolbarSend](/img/toolbarSend.png)
:::

::: hint Optimize tool window project and env drop-down box and layout <Badge vertical="top" text="New feature" type="tip"/>

```
Icon p represents project
Icon e represents environment
```

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
:::

## v2.0.6 <Badge text="Free" type="info"/>

- Fix "Light files should have PSI only in one project"
- Add support for json syntax check
- Add support for PATCH method
- Added support for saving the API to the corresponding module group
- Fix null value in response not output
- Add support for JAX-RS
- Display what's new

Without affecting the use, sometimes it will pop up "_Light files should have PSI only in one project"_.This has fixed in 2.0.6

::: hint json grammar check <Badge vertical="top" text="New feature" type="tip"/>
![json](/img/json_en.png)
:::

::: hint API group automatic association <Badge vertical="top" text="New feature" type="tip"/>
![apiGroup](/img/apiGroup_en.gif)

Module supports quick search
![apiGroup](/img/moduleSearch.gif)
:::

::: hint JAX-RS support <Badge vertical="top" text="New feature" type="tip"/>
![apiGroup](/img/jaxrs.gif)
:::

::: hint Show what's new <Badge vertical="top" text="New feature" type="tip"/>
Will only be shown once
![apiGroup](/img/whatsnew_en.png)
:::

## v2.0.5 <Badge text="Free" type="info"/>

- JSON built-in editor support
- Fix checkbox hidden when change param by text
- Fix headers param show error when reshow the api
- Optimized the request progress bar display

## v2.0.4 <Badge text="Free" type="info"/>

- Fix the interface confusion caused by the response content aisle
- Optimization of response JSON ultra long display
- Optimized icon size
- Migrate Chinese documents to Gitee

## v2.0.3 <Badge text="Free" type="info"/>

- Add confirmation when deleting api
- Added the support of selecting all parameters and inverting selection
- Fix param parse for java.util.Set
- Fix parse like 【@RequestParam
- Fix the confusion of the quick add header interface

## v2.0.2 <Badge text="Free" type="info"/>

- Fix that the A request is displayed to the B project in the case of multiple open projects
- Fix array and list param parse error decorate by @ResponseBody
- Fix List without generic parse error
- Add support for send and download
- Show progressbar while sending request
- Brand new document\[click the doc icon in the toolbar\]
- Some optimization

## v2.0.1 <Badge text="Free" type="info"/>

- rename from Fast Request to Restful Fast Request
- merge Json、Form URL-Encoded、Multipart to Body Tab to reduce the tool window width
- fix bug when modify config in global mode
- add get、post、delete、put icon to saved request
- saved request support more search strategy
- rename tab name from collection to APIs
- support parse java.time.YearMonth

## v2.0.0 <Badge text="Free" type="info"/>

- support store request
- support params optional
- optimal iu
- fix some bugs
