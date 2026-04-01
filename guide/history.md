---
url: /guide/history.md
---
::: important 公告

[**收费版 VS 免费版**](./versionCompare.md)

购买**License**流程请参考[**购买插件**](./buy.md)，Issue 提报请到[*Github*](https://github.com/dromara/fast-request/issues)

| ![微信技术群](https://img.shields.io/static/v1?label=wechat\&message=微信技术群\&logo=wechat\&color=07C160) | [![twitter](https://img.shields.io/static/v1?label=Twitter\&message=FastRequest666\&logo=twitter\&color=FC8D34)](https://twitter.com/FastRequest666) |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![wechat group](/img/wechatGroup.png)                                                           | ![twitter](/img/twitter.png)                                                                                                             |
| 如果扫码失败请直接搜微信号==FastRequest99==                                                              | 欢迎关注官方 Twitter                                                                                                                              |

:::

> **由于开发版本仍在开发中，开发版本的文档可能不完全准确，因此可能会发生变化。**
>
> important:      feat:       perf:       fix:&#x20;

## 2025.1.8   &#x20;

## 2025.1.7  &#x20;

:::hint 支持本地模式开关&#x20;

![](/img/2025.1.7/globalLocalMode.png)

全局本地模式会忽略 `.fastRequest` 目录并且让它不可见，与 [如何忽略.fastRequest 目录](./faq.md#s-%E5%A6%82%E4%BD%95%E5%BF%BD%E7%95%A5-fastrequest-%E7%9B%AE%E5%BD%95) 中的方式 1 一致。

![](/img/2025.1.7/localMode.png)

如果是 git 管理的项目，建议使用项目级别本地模式。

:::

:::hint 域名生成支持读取变量&#x20;

![](/img/2025.1.7/pomScanProp.png)

域名自动生成支持 pom 变量扫描

:::

:::hint 列表字段支持快速查找定位&#x20;

![](/img/2025.1.7/fieldSpeedSearch.png)

支持在列表中快速查找定位字段 key，输入关键字后再按方向键可以快速定位到对应的字段。此功能在 `Headers` 、`Path Params`、`URL Params`、`Form URL-Encoded`、`Form-data` 等 tab 中支持。

:::

:::hint 字段支持启用快捷过略&#x20;

![](/img/2025.1.7/enabledFieldList.png)

此功能可以在针对字段过多的情况下，快速查阅启用的字段定位字段的值。

:::

:::hint 支持快捷键快速修改&#x20;

![](/img/2025.1.7/shortcuts.png)

支持快速定位和修改插件快捷键

:::

## 2025.1.6   &#x20;

:::hint 搜索 URL 支持片段截断&#x20;

![](/img/2025.1.6/searchUrlTruncate.png)

当项目中有 context-path 或者 网关等 url 前缀片段时，在项目全局参数配置中加入截断片段，SearchEveryWhere 搜索时会自动截断片段，搜索出对应的结果。
:::

## 2025.1.5.1  &#x20;

## 2025.1.5  &#x20;

## 2025.1.4  &#x20;

:::hint Script 支持调用本地项目 Class&#x20;

![](/img/2025.1.4/script.png)

可以点击代码片段快捷方式快速生成代码。

```groovy
def XxxUtil = new FrLocalClassLoader("path/to/classes").loadClass("some.package.XxxUtil")
```

SpringBoot 项目编译后会在模块 target 目录下生成一个 classes 目录。使用 `FrLocalClassLoader` 可以加载这个目录下的类,并且可以直接调用类中的方法。

注意需要将 `path/to/classes` 替换成 classes 目录的绝对路径，将 `some.package.XxxUtil` 替换成你要调用的类的全名。

:::

## 2025.1.3  &#x20;

::: hint Api预览支持父类 api 的扫描&#x20;
![](/img/2025.1.3/apiPreviewSuperClass.png)

Api预览支持父类 api 的扫描，支持在Api预览中生成父类 API 相关信息的生成，支持保存

:::

::: hint 快捷忽略字段解析&#x20;


针对一些全局都不希望生成参数的字段，可以通过选择并快速添加到忽略字段解析配置。更多字段忽略字段的方法，请查看[字段解析](/guide/features/parseStrategy)

:::

::: hint Apis 配置化可以不跳转到方法&#x20;
![](/img/2025.1.3/apisJumpMethodConfig.png)

通过该配置，在执行 API 的时候可以使代码跳转不再执行，可以有效地避免已保存的 API 执行的时候打开多个代码标签页。

:::

::: hint 批量同步 API 到 Apifox&#x20;
![](/img/2025.1.3/apifoxBatchSync.png)

选择目录后，即可将目录下的 API 批量同步到 Apifox 中。

:::

## 2025.1.2  &#x20;

::: hint 默认不开启非核心功能&#x20;
![](/img/2025.1.2/customToolbar.png)

默认将禁用非核心功能，可以通过可配置化自定义勾选开启需要的非核心功能。重启后将生效。

:::

::: hint 修复API一览表未展示&#x20;
![](/img/2025.1.2/apiPreview.png)

修复了前几个版本 API 一览表未展示

:::

## 2025.1.1   &#x20;

::: hint OpenAPI 批量导出&#x20;
![](/img/2025.1.1/openApiBatch.png)

支持保存的 API 的 [OpenAPI](https://spec.openapis.org/oas/latest.html) 批量导出

:::

## 2024.1.9  &#x20;

::: hint OpenAPI 导出支持&#x20;
![](/img/2024.1.9/openApiExport_en.png)

可以将 API 导出成 [OpenAPI](https://spec.openapis.org/oas/latest.html) 格式。

:::

::: hint Solon 框架解析支持&#x20;
![](/img/2024.1.9/solon.png)

支持国产 [Solon](https://solon.noear.org/) 框架的解析。

:::

::: hint 运行后切换 Tab 可配置化&#x20;
![](/img/2024.1.9/runSwitchTab.png)

可以在 APIs 列表直接运行 API 并且不切换到运行界面，可配置化。这使得在 APIs 页面运行一个 API 以后可以再运行下一个 API。

:::

::: hint 响应文档展示支持&#x20;
![](/img/2024.1.9/responseDocument.png)

可以在 Response 页签下查看响应的字段含义。

:::

::: hint 文件导出默认目录支持自定义&#x20;
![](/img/2024.1.9/exportPath.png)

文件导出例如将 API 导出成 markdown 、html、word、openapi 等文档的默认目录地址支持自定义。

:::

## 2024.1.8.1  &#x20;

## 2024.1.8  &#x20;

::: hint Header 预设&#x20;


Header 预设支持创建不同的 Header 分组，管理各自的请求头，通过下拉选择轻松快捷得添加预设的 Header 参数。

:::

::: hint Binary 支持&#x20;
![](/img/2024.1.8/binary.png)

支持 Binary 格式上传

:::

## 2024.1.7  &#x20;

::: hint 函数与变量支持代码补全&#x20;


支持变量 `{{xx}}` 与 `{{$函数名}}`的代码补全

函数支持更多信息请参考---->[函数](/guide/features/function.md)
:::

::: hint JSON filter支持&#x20;
![jsonFilter](/img/2024.1.7/jsonFilter.png)

当 JSON 字段较多，但是只需要一部分的时候，可以通过该功能对 JSON进行字段过滤
:::

::: hint Header 常用值支持代码补全&#x20;
![header](/img/2024.1.7/header.png)

对于一些例如 `Authorization`、`token` 的请求头支持代码补全
:::

::: hint @FeignClient 扫描&#x20;
![feignClientSupport](/img/2024.1.7/feignClientSupport.png)

支持对 `org.springframework.cloud.openfeign.FeignClient` 的扫描，优化 path 路径解析结果
:::

::: hint 文件默认值支持&#x20;
![defaultMultipartFile](/img/2024.1.7/defaultMultipartFile.png)

Multipart 类型字段可以通过该配置设置默认文件路径
:::

::: hint 异常上报支持&#x20;
![errorReport](/img/2024.1.7/errorReport.png)

更加方便得上报错误，这可以使得作者可以更好得修复和定位 bug。
:::

::: hint SearchEveryWhere 结果排序优化&#x20;
![searchEveryWhere](/img/2024.1.7/searchEveryWhere.png)

按照Url 路径优化了匹配的排序规则，将匹配度高的排在前面
:::

::: hint Script 语法提示优化&#x20;

![scriptHint](/img/2024.1.7/scriptHint.png)

前提是需要点击安装依赖 lib，使用`rfr.request`、`rfr.response` 替换以获得代码补全提示。

原先的内置变量 `request`、`response` 依然可以使用，但是代码补全提示不友好。

更多内置变量请参考 [Script->rfr](./script.md#rfr)
:::

## 2024.1.6.3  &#x20;

## 2024.1.6.2  &#x20;

::: hint 组合注解支持&#x20;

针对如下类型的自定义 RestController 注解在 Controller 类中的扫描支持

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

::: hint 多 Tab 窗口&#x20;


`rapi`文件打开展现调试界面，支持多 Tab 打开。更多请查阅[**多 Tab 窗口**](/guide/features/navigateCurrentMethodJson.md)

:::

::: hint Apis 标记&#x20;


增加了对 Apis 标签页中对 Api 的标记支持，使其标记红色高亮圆点，并增加了标记 Api 搜索。 可将一些常用的 Api 标记，便于后续的查找。
:::

::: hint Apis 与 Navigate 标签页 Url 复制&#x20;


右键选项支持复制原始 Url
:::

::: hint Json path&#x20;

![jsonPath](/img/2024.1.6/jsonPath.png)

在 `Body->Raw` 和 `Response->Pretty` tab下支持 Json 路径查找，但是这依赖于 [JSONPath](https://plugins.jetbrains.com/plugin/22044-jsonpath) 插件.
:::

## 2024.1.5.1  &#x20;

## 2024.1.5  &#x20;

::: hint 脚本增加 Add script library支持&#x20;

![](/img/2024.1.5/addLibrary.png)

提示脚本库增加 library 支持，使得具备 [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html) 功能
:::

::: hint 类型下拉框优化&#x20;
![](/img/2024.1.5/typeDropdown.png)

下拉框使用更加明确的英文类型，并使用不同颜色区分不同的类型
:::

::: hint 原始url展示优化&#x20;
![](/img/2024.1.5/originUrl.png)

调试界面 Url 输入框和 APIs 界面 url 展示成原始 url，不对路径参数进行替换。
:::

::: hint cUrl 同一 url 匹配 method type优化&#x20;
针对匹配到的 url 一样，但是方法的类型(Get、Post等)不一样的绑定配对进行了优化，使得他们可以正确的匹配。
:::

## 2024.1.4  &#x20;

::: hint Apifox 集成&#x20;

Apifox集成，支持同步 API、环境变量、域名信息等。只要配置一个 `API key`，并设定项目映射关系即可进行同步。

更多信息请点击 [Apifox 集成](/guide/features/apifox.md)

[Bilibili视频教程](https://www.bilibili.com/video/BV1pM4m1Q7c5)

:::

::: hint AI支持优化&#x20;
![AI](/img/2024.1.4/ai.png)

为了将 AI 的回复更加精准，我们将 AI 的 设置更加精细化，加入了3个维度，加入了`概述`、`提问范例`、`提问范例结果`。此设计即让 AI 先训练一次问答，从而使得结果更加符合预期

我们可以在`概述`中尽量表述问题的各种可能，并在`提问范例`和`提问范例结果`中给于`概述`中描述的样本。

以下是一个让 AI 帮忙优化 JSON 报文的案例,更多详情请参考 [AI](/guide/features/ai.md)

::: code-tabs

@tab 概述

```
你是一位优秀的 JSON 处理程序。每当用户向您发送 JSON 时，您都应该以固定的 JSON 格式响应用户，并根据
JSON 键的含义将 JSON 字段中的值替换为人类可读的内容。您只需修改 JSON 中的值，而无需更改其结构。
```

@tab 提问范例

```
{"id":1,"title":"title_ahska","simpleDesc":"simpleDesc_ohga1","content":"content_yujga"}
```

@tab 提问范例结果

```
{"id":1,"title":"书画标题","simpleDesc":"简介内容","content":"整体内容"}
```

:::

::: hint 变更 moduleHeader 为 apiHeader&#x20;

![Header](/img/2024.1.4/header.png)

1. 前置、后置脚本中的变量 `moduleHeader` 变更 `为apiHeader`。
2. 主界面 Header 选项卡作用域变更为仅面向 API 级别，
   即每个 API 可以拥有不同的 Header，如果需要公共 Header，请前往[项目级别 Header](/guide/features/projectValueConfig.md) 设置。

:::

::: hint cURL导入优化&#x20;
在去除域名后，如果 Url 与项目中方法的 Url 一致则自动绑定。
:::

## 2024.1.3 &#x20;

::: hint AI支持优化&#x20;


增加了 OpenAi、CodeGeeX、自定义三种形式的 AI 支持，通过可配置化的指令，可将选中的文本替换成目标文本。

可以在 `Body -> JSON`, `Body -> Form URL-Encoded -> Text`,`URL Params -> Text` 3个 Tab 下操作。
:::

## 2024.1.2.1 &#x20;

## 2024.1.2 &#x20;

::: hint 自定义工具栏&#x20;

![customToolbar](/img/2024.1.2/customToolbar.png)

使用自定义工具栏，选择自己需要使用的功能，将其他功能按钮进行隐藏，设置完成需要重启 IDEA
:::

## 2024.1.1 &#x20;

::: hint SearchEveryWhere 支持类名搜索&#x20;

![searchEveryWhere](/img/2024.1.1/searchEveryWhere.png)

输入 Url 所在的类名，即可搜索该类下面的所有 API
:::

::: hint .fastRequest目录生成策略&#x20;
默认打开 Project 不再生成.fastRequest目录，只有操作插件才生成
:::

::: hint 忽略字段名解析支持&#x20;
![ignoreFiled](/img/2024.1.1/ignoreFiled.png)

只需要在配置中增加字段名，即可将实体类中的特定字段忽略生成
:::

::: hint Apis,Navigate弹框打开支持&#x20;

可以通过快捷键快速打开 Apis 和 Navigate 窗口查看信息, 窗口打开的情况下再按 ESC 可以关闭窗口
:::

::: hint 手动更新检测&#x20;
![manualUpdateCheck](/img/2024.1.1/manualUpdateCheck.png)

点击检查插件更新
:::

::: hint SearchEveryWhere性能优化&#x20;
优化了SearchEveryWhere搜索逻辑，大大提生大项目的搜索性能。我们测试了拥有 7000个 API 的项目，搜索响应非常快
:::

::: hint Content-Disposition支持优化&#x20;

文件下载支持针对一下 2 种格式的支持，同时支持文件名转码

```
Content-Disposition:attachment; filename*=UTF-8''fastRequest%20.txt
Content-Disposition:attachment; filename=fastRequest.txt
```

:::

## 2023.2.3.1 &#x20;

## 2023.2.3 &#x20;

::: hint API同步至Postman&#x20;
仅需配置Postman的token和对应workspace的ID即可将API云上传到Postman.享受fast request不支持的功能.

由于历史原因,历史保存的持久化数据丢失了一部分字段,为了能够将同步至postman的数据更完整,
建议在插件postman配置中勾上保存后同步postman选项,通过保存操作来触发同步.

更多详情参考[Postman 同步](./features/postmanSync.md)
:::

::: hint 自动域名切换项目名下拉框自动切换&#x20;
不同 Module 下的 API 生成参数的时候，如果开启了自动域名,自动切换项目名下拉框
:::

::: hint Environment重构&#x20;
![environment](/img/2023.2.3/environment.png)

Environment区分为本地值(Current value)和共享值(Initial value)

Initial value可以通过提交 `.fastRequest/config/fastRequestCurrentProjectEnvironment.json` 实现共享
:::

::: hint 项目域名添加优化&#x20;

![projectName](/img/2023.2.3/projectName.png)

自动识别 module 名称作为下拉框选项,也可以自定义输入.

:::

::: hint 忽略字段使用@fastRequestParseIgnore&#x20;
针对实体类字段忽略解析,可以在注释中添加`@fastRequestParseIgnore`来实现,原来的注解`@parseIgnore`依然适用

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

:::

## 2023.2.2 &#x20;

::: hint Markdown文档模板配置&#x20;
提供了导出文档和[Api在线文档](./features/apiDocSync.md)的自定义格式实现.查看[更多详情](./features/markdownDocTemplateConfig.md)
:::

::: hint JSON5支持&#x20;
![json5](/img/2023.2.2/json5.png)

请求体JSON5格式支持,json字段注释支持

:::

## 2023.2.1.1&#x20;

## 2023.2.1&#x20;

::: hint UI简化&#x20;
![urlInputWithMethod](/img/2023.2.1/urlInputWithMethod.png)

将老版本横向的3个组件(method下拉框,url输入框,响应下拉框)简化为1个,并且移除响应下拉框.增加简捷度

:::

::: hint api文档支持保存和打开&#x20;

![openDoc](/img/2023.2.1/openDoc.png)
:::

::: hint apis放入到右键菜单&#x20;

![openDoc](/img/2023.2.1/showApis.png)
:::

::: hint 统计页面加入了版本号&#x20;

![pluginVersion](/img/2023.2.1/pluginVersion.png)
:::

## 2023.1.9&#x20;

::: hint 快捷添加Environment变量&#x20;
![quickAddEnvironmentVariable](/img/2023.1.9/quickAddEnvironmentVariable.png)

选中一行需要添加到环境变量的点击添加按钮，可以快捷将该变量保存到当前Env中

:::

::: hint Environment使用界面增删改支持&#x20;
![environmentVariableEditRemoveModify](/img/2023.1.9/environmentVariableEditRemoveModify.png)
:::

::: hint Show apis快捷触发&#x20;
![showApis](/img/2023.1.9/showApis.gif)

在Controller中任意位置右键唤起 `Generate`，选择`Show apis` 即可展示当前控制器中的所有API
:::

::: hint Response新增Size属性&#x20;
![responseSize.png](/img/2023.1.9/responseSize.png)
:::

::: hint Api名称保存优化&#x20;
详看Bilibili视频讲解 -> [IDEA插件Fast Request之API命名机制](https://www.bilibili.com/video/BV1wu4y1d7c4)
:::

## 2023.1.8&#x20;

::: hint Environment&#x20;
引入了  nvironment变量，[详情](./features/environment.md)
:::

::: hint 方法描述截断&#x20;
![methodDescriptionLength](/img/2023.1.8/methodDescriptionLength.png)

针对方法名过于长，导致显示或者数据保存的时候方法描述过程，可以进行截断设置。例如`SearchEveryWhere`

:::

## 2023.1.7.1&#x20;

::: hint SearchEveryWhere增加自动生成策略&#x20;
![searchEveryWhere](/img/2023.1.7.1/searchEveryWhere.png)

选择自动生成后，即可回显或者自动生成API相关url及参数信息

:::

::: hint 主窗口弹出配置化&#x20;
在`设置->Request Fast Request->其他配置中`增加了配置项`动作触发时显示主工具窗口`
:::

::: hint 参数解析&#x20;
针对入参为`@RequestBody Set<String>set`的解析优化，默认给字符串加双引号

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

::: hint 全新扁平化图标&#x20;
![toolwindowNew](/img/toolwindowNew.png)
:::

::: hint SearchEveryWhere 加入过滤条件&#x20;
![searchEveryWhere](/img/2023.1.7/searchEveryWhere.png)

支持`module`、`methodType`、`library`搜索

:::

::: hint 精简版 http 图标&#x20;

同时支持在 `setting -> Restful Fast Request -> 使用精简http图标` 修改

| 方法名 |                    新图标                     |                  老图标                   |
| :----: | :-------------------------------------------: | :---------------------------------------: |
|  Get   |    ![get\_dark](/img/http/new/get_dark.svg)    |    ![get\_dark](/img/http/get_dark.svg)    |
|  Post  |   ![post\_dark](/img/http/new/post_dark.svg)   |   ![post\_dark](/img/http/post_dark.svg)   |
| Delete | ![delete\_dark](/img/http/new/delete_dark.svg) | ![delete\_dark](/img/http/delete_dark.svg) |
|  Put   |    ![put\_dark](/img/http/new/put_dark.svg)    |    ![put\_dark](/img/http/put_dark.svg)    |
| Patch  |  ![patch\_dark](/img/http/new/patch_dark.svg)  |  ![patch\_dark](/img/http/patch_dark.svg)  |

:::

::: hint 字段拷贝&#x20;
![fieldDup](/img/2023.1.7/fieldDup.png)

支持字段行拷贝，key用`-dup`结尾标注。集合场景不变更key，开发者需要自行处理修改下标
:::

## 2023.1.6&#x20;

::: hint Header 批量编辑&#x20;

![batchEdit](/img/2023.1.6/batchEdit.png)

:::

## 2023.1.5&#x20;

::: hint 编辑器图标位置&#x20;
![editorAction](/img/2023.1.5/editorAction.png)

从原先按钮容易挡住文本的位置移至编辑器右上角

:::

::: caution SearchEveryWhere 样式错乱
这个问题伴随了很多版本，该版本彻底修复了。SearchEveryWhere 不会出现 Tab 宽度变大而导致 Tab 显示不全
:::

## 2023.1.4.2&#x20;

## 2023.1.4.1&#x20;

## 2023.1.4&#x20;

::: hint 自动域名&#x20;
自动识别或增加一个默认的域名,---->[详情](./features/autoDomain.md)
:::

## 2023.1.3.2&#x20;

[FAQ:安装过 2023.1.3 版本的数据丢失](./faq.md#s-apis%E4%B8%A2%E5%A4%B1)

::: hint OpenAI API 接口优化字段支持&#x20;
![AI](/img/2023.1.3/ai.gif)

利用[OpenAI 接口](https://platform.openai.com/docs/api-reference/chat)，替换随机字段值。注意由于 API 是非结构向数据，所以返回的内容可能会有误差。

:::

::: hint API 与存储文件跳转&#x20;

![jumpToJsonData](/img/2023.1.3/jumpToJsonData.png)

![API列表跳转到json](/img/2023.1.3/listJump2JsonData.png)

实现 API 以及底层保存的 json 数据进行关联跳转
:::

::: hint 项目配置刷新&#x20;

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)

配置文件更新后，UI 实现项目级别配置更新

:::

::: hint rfr.currentModuleName 支持&#x20;
[脚本](./script.md#rfr)中用于获取当前 API 所属模块支持
:::

::: hint Api 文档同步支持自定义分支&#x20;

![apiSyncSupportBranch](/img/2023.1.3/apiSyncSupportBranch.png)

支持特定分支，因为有些情况下 master 被保护不允许 push
:::

::: hint 工具栏&#x20;

![htoolbar](/img/2023.1.3/htoolbar.png)

![vtoolbar](/img/2023.1.3/vtoolbar.png)

![toolbarSetting](/img/2023.1.3/toolbarSetting.png)

1. 垂直工具栏支持
2. 按钮位置调整和折叠

:::

::: hint GET、POST、DELETE、PUT、PATCH 图标&#x20;

![toolbar](/img/2023.1.3/apis.png)

对图标进行了圆角处理，并且调整了背景色

:::

## 2023.1.2&#x20;

::: hint 图片响应渲染&#x20;

![imageRender](/img/2023.1.2/imageRender.png)

针对响应是图片的请求，自动渲染出图片，适合验证码场景

:::

::: hint 年度报告&#x20;
![annualReport](/img/2023.1.2/annualReport.png)

统计了历年操作的数据，你是否要来**PK**一把。此处省略 1 个狗头
:::

::: hint Url 解析逻辑&#x20;
优化了 url 针对变量拼接的最终结果，例如几个变量拼接最终计算出一个 url。

插件将最终计算出 url=`/test/a.htm`

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

::: hint cUrl 导入&#x20;

1. 自动解析 query param 进入`Url params` tab 页
2. json 解析优化
3. 已知 bug 修复
   :::

::: hint Url 特殊字符编码&#x20;
针对带 query param 的 url 中的参数值待特殊字符进行了处理，不需要用户再额外进行特殊编码再传参
:::

::: hint Url 输入框回车事件&#x20;
url 输入框点击回车，自动发送请求
:::

## 2023.1.1&#x20;

::: hint 项目级别域名配置&#x20;

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig.png "域名配置")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog.png "域名列表")

支持直接从`yml`和`properties`配置文件快速配置域名，更多详情请查看 [ 项目域名配置](./features/projectLevelDomainConfig.md)
:::

::: hint 图标排版&#x20;
对按钮进行了归类整理，让开发者更加容易找到和理解对应按钮的功能。
:::

::: hint 新 UI 工具窗口图标&#x20;
新 UI 下工具窗口图标清晰度进行了优化。
:::

## 2022.3.1&#x20;

::: hint 注释增加@fastRequestParseIgnore 用来忽略字段解析&#x20;

以下的代码，插件会忽略解析该字段，请使用`@fastRequestParseIgnore`

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

:::

::: hint Script 中增加 rfr.currentDomain 支持&#x20;
在脚本中可以通过`rfr.currentDomain`获取当前作用的域名
:::

## 2022.2.9&#x20;

::: hint 全局动画配置&#x20;
![animateConfig](/img/2022.2.9/animateConfig.png)

你可以在此关闭全局动画，如果关闭了，图标将不再动

:::

## 2022.2.8&#x20;

::: hint 下载文件适配&#x20;
当 response 加了`content-disposition:attachment`，点击 send 自动适配下载
:::

## v2022.2.7&#x20;

::: hint Api 文档同步&#x20;

在线文档同步---->[详情](./features/apiDocSync.md)

:::

::: hint Api 文档显示返回值类型参数文档&#x20;
![returnValueDoc](/img/2022.2.7/returnValueDoc.png)
:::

::: hint 插件更新通知机制&#x20;
![upgradeNotice](/img/2022.2.7/upgradeNotice.png)

从显式的对话框改为通知，同时自动更新也修改为==打开==了，如果你想关闭，请到配置页面手动关闭它，但我建议==开启它==，这样子就可以及时接收到更新。

需要注意的是，前一个版本的更新依然会是弹窗，这个版本以后就是消息通知机制了

:::

::: hint 公共请求头&#x20;

![commonHeader](/img/2022.2.7/commonHeader.gif)

[---->详情](./features/commonHeader.md)

:::

::: hint 全屏&#x20;

![fullScreen](/img/2022.2.7/fullScreen.png)

[---->详情](./features/fullScreen.md)

:::

::: hint 注释预览&#x20;
![fullScreen](/img/2022.2.7/commentPreview.png)
:::

## v2022.2.6&#x20;

::: hint 一键清除参数&#x20;

![clear](/img/2022.2.6/clear.png)
全局清空

![clearColumnValue](/img/2022.2.6/clearColumnValue.png)
批量清空列值
:::

::: hint API 注释预览&#x20;
![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
:::

::: hint 参数列根据 key 排序&#x20;
![sortColumn](/img/2022.2.6/sortColumn.png)
:::

## v2022.2.5.2&#x20;

## v2022.2.5.1&#x20;

## v2022.2.5&#x20;

::: hint Console 支持&#x20;
![console](/img/2022.2.5/console.png)

Console 帮助开发者打印一些你想要的信息

更多信息请看-----> [script->console](./script.md#console)

:::

::: hint 内置变量 rfr 添加 currentProjectName、currentEnvName 属性&#x20;
可以使用这 2 个变量用于一些判断

更多信息请看-----> [script->内置变量->rfr](./script.md#rfr)
:::

::: hint 从 Javadoc 读取注释&#x20;

![console](/img/2022.2.5/parseDocDesc.png)

读取 Javadoc 形式的参数注释，适用于 path 参数以及 request param 是非实体类的参数

:::

::: hint 新增推特&#x20;
有推特账号的请关注我，感谢

![](/img/twitter.png)

![twitterAction](/img/2022.2.5/twitterAction.png)
:::

## v2022.2.4.1&#x20;

## v2022.2.4&#x20;

::: hint 全新的 Logo&#x20;

:::

::: hint 支持通过 DateTimeFormat 注释解析日期&#x20;
![dateTimeFormat](/img/2022.2.4/dateTimeFormat.png)
:::

::: hint 支持解析 BaseController&#x20;
![baseController](/img/2022.2.4/baseController.png)
:::

::: hint 自动更新可配置化&#x20;
如果你不需要接收自动更新，则可以关闭，通过手动的形式更新(建议开启)
![autoUpdate](/img/2022.2.4/autoUpdate.png)
:::

## v2022.2.3.1&#x20;

## v2022.2.3&#x20;

::: hint 脚本支持&#x20;
![scriptProject](/img/2022.2.3/scriptProject.png)

![scriptModule](/img/2022.2.3/scriptModule.png)

脚本可以让开发者更加灵活地、动态地、方便地修改请求过程的一些入参，以及响应的处理\
具体请参考-------> [脚本](./script.md)

:::

::: hint 一键拷贝 Url&#x20;
![copyUrl](/img/2022.2.3/copyUrl.png)
:::

::: hint 点击按钮时触发表格单元格值更改&#x20;
\==2022.2.3 版本之前==在输入表格中的参数时候，若光标还在表格内部，此时去点击操作按钮，某些场景下会报错或者值无法被修改，该问题在此版本得到解决

老版本如何处理: [FAQ:输入参数后调用 API 发现无效](./faq.md)
:::

## v2022.2.2&#x20;

::: hint 终止 API 请求功能&#x20;
![stopApi](/img/2022.2.2/stopApi.gif)
:::

::: hint 批量导出 API 文档&#x20;
![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)
:::

## v2022.2.1&#x20;

::: hint API 分组参数保存支持&#x20;
目的:一个 API 下保存不同组合的参数

[B 站教程](https://www.bilibili.com/video/BV1zU4y1S7pC?share_source=copy_web\&vd_source=c46db3e7c134b1948dabbea9717a72ac)

\==原保存操作将默认归类到 Default 分组==

如何操作:输入参数后点击==分组请求保存==

![groupSave](/img/2022.2.1/groupSave.png)

**切换参数**

以下是 book 列表 3 种语言的查询

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)
:::

::: hint 临时请求保存支持&#x20;
目的:保存任意不属于本项目的请求，用于临时调用，跟当前项目代码不关联

临时请求保存的 Request 的 Url 必须以==http==或者==https==开头

![tempSave](/img/2022.2.1/tempSave.png)

:::

::: hint cURL 导入支持&#x20;

![importByCurl](/img/2022.2.1/importByCurl.gif)

\==请注意如果需要绑定方法，则需要将光标放置到方法名上==
:::

::: hint Response Header 展示&#x20;

![responseHeader](/img/2022.2.1/responseHeader.png)

:::

::: hint 添加对 Url 尾缀支持&#x20;

![responseHeader](/img/2022.2.1/urlSuffix.png)

目的:有些请求需要在 url 结尾加上.do

:::

::: hint 优化解析&#x20;
优化了`List<Entity>`的解析
:::

## v2022.1.10&#x20;

::: hint 添加对历史请求预览的支持&#x20;

![history](/img/history.png)

隐藏技能:双击记录可以展示详情

:::

::: hint 超时时间自定义设置&#x20;

![timeoutSetting](/img/timeoutSetting.png)

默认: 60 秒

:::

::: hint SearchEveryWhere 支持方法注释搜索&#x20;

![searchEveryWhereDescription](/img/searchEveryWhereDescription.png)

支持注释搜索 API
:::

::: hint 优化&#x20;

:::

## v2022.1.9&#x20;

::: hint 工具窗口跳转增加快捷键&#x20;

![toolwindowShortcut](/img/toolwindowShortcut.png)

提供了快捷键可以快速跳转，可以在`setting->Keymap`中修改

:::

::: hint 添加自动添加 Cookie 支持&#x20;

![autoStoreCookie](/img/autoStoreCookie.png)

开启此配置，cookie 再自动存入==项目全局参数==中的`cookies`中，此配置默认开启
:::

::: hint 响应 raw 中增加 html 预览&#x20;

![rawHtmlPreview](/img/rawHtmlPreview.png)

raw 中不再截断 html 返回类型的响应，同时可以对 html 进行预览

:::

::: hint 忽略字段解析优化&#x20;

:::

::: hint 泛型参数解析支持&#x20;
支持此类泛型参数解析

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

::: hint 完善项目全局参数&#x20;

![projectConfigParam](/img/projectGlobalParam.png)

加入了`URL Params`、`URL-Encoded Params`、`Cookies`

:::

::: hint 优化&#x20;

* 界面优化
  * 优化了 tab 间距(windows 下不正常)、背景颜色、个别突出的 tab 颜色等
* 保存 API 插入顺序优化
  * 后保存的放在最上面

:::

::: hint APIs 列表视图优化&#x20;

![apisList](/img/apisList.png)

渲染进行了优化，新保存的 api 图标会自动优化，历史保存的 api 可以右键进行选择变更

:::

## v2022.1.8&#x20;

::: hint 项目级别全局参数支持&#x20;

![projectConfigParam](/img/projectConfigParam.png)

支持项目级别内的全局参数，不受多模块影响

配置优先级`api头 > 项目级别全局请求头 > 全局请求头`

:::

::: hint 快速添加到头参数&#x20;

![add2projectConfig](/img/add2projectConfig.png)

![cookie2projectConfig](/img/cookie2projectConfig.png)

原响应结果从添加到 ~~全局请求头~~ 变更为添加到 ==项目全局请求头==，cookie 也是如此

:::

## v2022.1.7&#x20;

::: hint Kotlin spring 系列框架支持&#x20;

![kotlinSupport](/img/kotlinSupport.png)

添加了 Kotlin 语言下，Spring 系列框架支持

:::

::: hint 新增 SearchEveryWhere 范围搜索策略&#x20;

![searchEveryWhereStrategy](/img/searchEveryWhereStrategy.png)

2022.1.7 版本默认可以搜索到 feign 或者 controller 中的 mapping

默认搜索策略是根据注解来搜索的，再提供 2 种选项，如果在搜索过程中需要排除类似 feign 中的接口，可以通过去除 Interface 的勾选来处理.

:::

::: hint 编辑器 ToggleUseSoftWrap 支持&#x20;

![toggleUseSoftWrap](/img/toggleUseSoftWrap.png)
:::

::: hint 响应数据一键添加 Global Header 支持&#x20;

![add2GlobalHeader](/img/add2GlobalHeader.png)

多模块项目下建议使用，因为 Add to Headers 只能添加到当前模块项目下

:::

::: hint 添加导航到当前方法的支持&#x20;

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)

当你生成好方法的 url 以后，又切换到代码中的别的地方，然后又想开始调试该 API 方法，可以通过此功能快速定位到代码

:::

::: hint 优化多模块下保存的 API 回显自动绑定项目&#x20;
优化了**多模块**下下针对保存的 api 回显需要**手动切换项目下拉框**
:::

## v2022.1.6&#x20;

::: hint 添加一键添加 Cookie 到 Header 的支持&#x20;

![addCookie2Header](/img/addCookie2Header.png)

针对登录需要 Cookie 支持的，可以将响应中的 Cookie 快速添加到 Header 来实现

:::

::: hint 添加 API 分享 Word 和 html 的支持&#x20;

![shareApi](/img/shareApi.png)

请注意 word 形式的 API 内部采用 html 来实现的，所以不要觉得奇怪，导出就行

:::

[comment]: <> "::: note 添加 YouTrack 跟踪 bug 支持"

[comment]: <> "Fast Request 接通了 YouTrack Issue 自动上报的功能，直接点击上报 bug 即可反馈问题，无需手动再复制粘贴到 Github 提报 bug"

[comment]: <> "Fast Request YouTrack 官方网址: [https://darkings.youtrack.cloud/issues](https://darkings.youtrack.cloud/issues)"

[comment]: <> "![youtrackSubmit](/img/youtrackSubmit.png)"

[comment]: <> "::: "

## v2022.1.5&#x20;

::: hint 添加对自动生成参数描述的支持&#x20;

![paramDescription](/img/paramDescription.png)

可选择性的对描述进行隐藏

:::

::: hint 添加一键生成 API 文档的支持&#x20;

![shareDocButton](/img/shareDocButton.png)

![apiDocExample](/img/apiDocExample.png)

点击生成参数后，可通过分享直接生成 Markdown 文档

:::

::: hint 添加单个类的 API 整体预览&#x20;

![apiDocExample](/img/apiPreview.gif)

聚焦窗口后输入关键字可快速定位

:::

## v2022.1.4&#x20;

\==idea 版本 2021.3+==

::: hint SearchEveryWhere 高亮优化&#x20;

![searchEveryWhereHighlight](/img/searchEveryWhereHighlight.png)

高亮展示搜索关键字，加快真实想要查找的 API 的查找速度，同时展示 api 对应的 javadoc

:::

::: hint APIs 导入导出支持&#x20;

![exportImportApis](/img/exportImportApis.gif)

增加对 APIs 的导入导出支持，利用改功能，你可以非常方便得将自己已有的 APIs 分享给别的开发者，或者导入到其他设备上的 IDEA

更多详情请看 **[功能->APIs 导入导出](./feature.md#apis导入导出)**
:::

::: hint APIs 支持直接运行&#x20;

![runInApiManagement](/img/runInApiManagement.png)

你可以在 APIs tab 页直接运行你保存的请求
:::

::: hint 自动生成参数可选化&#x20;
![generateSwitch](/img/generateSwitch.png)
:::

::: hint swagger 注解默认值参数解析支持&#x20;
增加了 swagger 注解默认值的解析，该功能对于入参的传递更加人性化

* @ApiParam(swagger2)
* @ApiImplicitParam(swagger2)
* @ApiModelProperty(swagger2)
* @Parameter(swagger3)
* @Schema(swagger3)

更多详情请看 **[功能->swagger 默认值解析支持](./feature.md#swagger默认值解析支持)**
:::

::: hint 对 Send 和 Send and Download 按钮进行了合并&#x20;

![mergeRunAndDownload](/img/mergeRunAndDownload.png)
对按钮进行了合并，因为常见的操作都是非下载操作，减少了工具栏按钮个数，看上去更加简捷

:::

::: hint 使用引导上的细节优化&#x20;
![help](/img/help.png)
我们在不同的操作窗口，增加?选项用来展示一些注意事项及操作指引，对于初次使用的用户，操作门槛更低

并且随着版本的迭代，后续可能会加入更多的提示操作指引

:::

::: hint url 解析优化&#x20;

历史逻辑只会取第一个 url 即 test1，考虑到实际使用中，有可能你需要的是另外一个 url，所以添加了随机支持

以下 demo，url 将随着点击::restfulFastRequest::随机生成 **/url1/test1,/url1/test2,/url2/test1,/url2/test2**

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

支持变量计算，以下 demo 将生成 **/url/public/test1**

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

::: hint Tab 页 API Navigate 重命名 Navigate&#x20;
在工具窗口比较小的情况下，API Navigate 会被隐藏，为了在尽可能小的工具窗口展示更多内容，所以命名更加简短
:::

## v2.1.3&#x20;

* 参数特殊符号编码支持
* curl 加入 global headers

## v2.1.2&#x20;

* 添加对导出 api 到 Postman 的支持
* 首次打开 idea 懒加载 API Navigate 树
* SearchEveryWhere module 标识
* Url 解析优化
* API navigate tree 展示优化
* API 保存分组优化
* 全局请求头支持

::: hint Url 解析优化&#x20;
支持以下 example 的解析

不再需要单独配置 url replace config
:::

::: code-tabs

@tab 场景 1: url 是类常量引用

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

@tab 场景 2: value 是一个数组

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

::: hint 添加对导出 api 到 Postman 的支持&#x20;

![export2postman](/img/export2postman.gif)
:::

::: hint SearchEveryWhere module 标识&#x20;

![searchEveryWhereModule](/img/searchEveryWhereModule.png)
:::

::: hint API 保存分组优化&#x20;

```
保存api的时候，api将会保存到对应的请求指定到控制器名所在的分组(更加直观)
```

![save2ControllerGroup](/img/save2ControllerGroup.png)
:::

::: hint 全局请求头支持&#x20;

![globalRequestHeader](/img/globalRequestHeader.png)
:::

## v2.1.1&#x20;

* SearchEveryWhere 支持
* 增加一个最新按钮可以随时查看最新版本
* 优化枚举的解析
* 增加对 http 重定向的支持

::: hint SearchEveryWhere support&#x20;

```
输入案例
/url              (查询指定url)
get /list         (指定get方式指定url)
post /save        (指定post方式指定url)
```

![searchEveryWhere](/img/searchEveryWhere.gif)
:::

::: hint 最新版本快速查看&#x20;
![whatsnewNotifcation](/img/whatsnewNotifcation.png)
:::

## v2.1.0.2&#x20;

* 加入了组织[**Dromara**](https://dromara.org/zh/)(致力于微服务云原生解决方案的组织)

## v2.1.0.1&#x20;

* 修复 Get 参数错误

## v2.1.0&#x20;

* 修复 POST 形式的 API 中@RequestBody、@RequestParam 混合使用参数解析导致请求 400 异常
* 对嵌套类的解析支持
* 增加支持对控制器类级别@RequestMapping(path="/xxx")path 的解析
* Api tree 针对带@RequestMapping 类的扫描支持

## v2.0.9&#x20;

* 2021.3+版本修复"Slow operations are prohibited on EDT"
* 修复快速从 response 添加 Headers
* 添加项目级别的配置，切换项目 env 和 project 不受变化
* 文本编辑器添加了快捷格式化按钮

::: hint 从 response 添加 Headers&#x20;

```
如果你的api需要再header里面塞入token，你可以像这样子从响应里面快速加参数塞入headers
```

![format](/img/quickAddHeaders.gif)
:::

::: hint 文本编辑器添加了快捷格式化按钮&#x20;
![format](/img/format.gif)
:::

## v2.0.8.1&#x20;

* 修复 searchEveryWhere 冲突警告
* 调整低版本 idea 工具栏操作按钮至工具栏顶部位置
* 移除多余依赖，插件从 11.1M 降至 5.9M

## v2.0.8&#x20;

* 修复初次添加项目或环境 npe
* json 树响应字符渲染最大限制
* 添加 Api navigate 对 methodType 的支持
* 添加对忽略参数解析的支持
* 线程导致的 EDT 问题
* send/sendDownload 支持自定义快捷键
* 修复 2021.3 响应不显示

::: hint send/sendDownload 支持自定义快捷键&#x20;

```
快捷键在任意位置点击均可触发，不再需要聚焦到工具窗口
```

![shortcutSendAndDownload](/img/shortcutSendAndDownload.png)
:::

::: hint 添加 Api navigate 对 methodType 的支持&#x20;

![shortcutSendAndDownload](/img/methodFilter.png)
:::

## v2.0.7&#x20;

* 添加 API 导航树
* 添加 Headers 随项目和环境切换自动切换的支持
* 优化 windows 系统某些情况下下载文件无法弹出目录
* 优化@RequestParam 的参数解析
* 优化工具窗口 project 和 env 下拉组件及布局
* curl 拷贝提示优化
* regenerate 提示优化
* 删除 project 和 env 配置增加确认操作
* 修复 json 字段输出循序被打乱
* 将发送请求按钮至工具栏同时支持快捷键

::: hint API 导航树&#x20;

```
选中树输入关键字，再按回车或者鼠标左键双击即可定位到API

悬浮鼠标显示api的doc
```

![apinavi](/img/apinav.gif)
:::

::: hint Headers 自动切换&#x20;

```
场景:SpringBoot等多模块项目不同项目、不同环境下头参数不同，为了快速自动切换headers，引入了header分组
操作方式：
1.在headers group里修改约束:输入的值必须是标准json格式
2.直接切换环境，然后再headers表格中输入对应的key、value值
```

![headerSwitch](/img/headerSwitch.gif)
:::

::: hint 优化@RequestParam 的参数解析&#x20;
@RequestParam 注解的参数将优先按照别名\
`@RequestParam("nameAlias")List<String> nameList` 输入参数将变成 nameAlias\
:::

::: hint 将发送请求按钮至工具栏同时支持快捷键&#x20;

```
send request: alt =
send and download: alt -
前提条件:工具窗口需要被聚焦
```

![toolbarSend](/img/toolbarSend.png)
:::

::: hint 优化工具窗口 project 和 env 下拉组件及布局&#x20;

```
图标p代表project
图标e代表environment
```

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
:::

## v2.0.6&#x20;

* Fix "Light files should have PSI only in one project"
* 添加对 json 的语法支持
* 添加对 PATCH 的支持
* 添加 API 保存时候存入对应的模块分组的支持
* 修复响应中 null 值不输出的问题
* 添加对 JAX-RS 的支持
* 最新功能展示

在不影响使用的情况下，有时候会经常弹出*Light files should have PSI only in one project*.2.0.6 版本得到修复

::: hint json 语法支持&#x20;
![json](/img/json.png)
:::

::: hint 添加 API 保存时候存入对应的模块分组的支持&#x20;
![apiGroup](/img/apiGroup.gif)

同时模块支持快速搜索
![apiGroup](/img/moduleSearch.gif)
:::

::: hint JAX-RS 的支持&#x20;
![apiGroup](/img/jaxrs.gif)
:::

::: hint 最新功能展示&#x20;
只会展示一次
![apiGroup](/img/whatsnew.png)
:::

## v2.0.5&#x20;

* JSON 内置编辑器支持
* 修复全选参数框隐藏问题
* 修复 API 回显 Headers 参数异常
* 优化了请求进度条显示

## v2.0.4&#x20;

* 修复因 response 内容过道导致的界面错乱问题
* Response json 超长显示优化
* 优化图标尺寸
* 中文文档迁移至 gitee

## v2.0.3&#x20;

* 删除 API 的时候增加了确认
* 添加了参数全选反选的功能
* 修复诸如【@RequestParam(value="address\[]") Set address】的参数解析
* 修复快速添加 headers 界面错乱

## v2.0.2&#x20;

* 修复项目多开情况下 A 请求显示到 B 项目
* 修复@ResponseBody 修饰的数组与集合解析问题
* 修复 List 参数无泛型解析问题
* 添加对文件下载的支持
* 发送请求时展示进度条
* 全新的 document，点击工具栏 doc 图标
* 一些优化

## v2.0.1&#x20;

* rename from Fast Request to Restful Fast Request
* Json、Form URL-Encoded、Multipart Tab 合并减小工具窗口宽度
* 修复全局配置下修改配置报错
* 将 get、post、delete、put 图标添加到保存的请求
* 保存的请求支持更多搜索策略
* tab 重命名 collection->APIs
* YearMonth 解析支持

## v2.0.0&#x20;

* 支持请求的存储
* 支持参数可选
* 优化了 UI
* 修复了一些 bug
