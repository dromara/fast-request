---
title: 最新变化
icon: changelog
---

::: tip 公告

插件版本 2022.1.4+即氪金版(但是你可以免费试用 30 天后再决定是否值得氪金)，对比氪金版和免费有什么更强大的功能，只需要看更新日志>=2021.1.4+的，或者简单看一下以下的对比。

[**收费版 VS 免费版->**](./versionCompare.md)

购买**License**流程请参考[**购买插件**](./buy.md) 或者进入微信群里查看==群公告==

| ![微信](https://img.shields.io/static/v1?label=wechat&message=微信&logo=wechat&color=07C160) | [![twitter](https://img.shields.io/static/v1?label=Twitter&message=FastRequest666&logo=twitter&color=FC8D34)](https://twitter.com/FastRequest666) |
| -------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| ![wechat group](/img/wechatGroup.png)                                                        | ![twitter](/img/twitter.png)                                                                                                                      |
| 如果扫码失败请直接搜微信号==FastRequest99==                                                  | 欢迎关注官方 Twitter                                                                                                                              |

Issue 提报请到[_Github_](https://github.com/dromara/fast-request/issues)

---

feat: <Badge text="新功能" type="tip"/> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> perf: <Badge text="优化" type="info"/> <span>&nbsp;&nbsp;&nbsp;&nbsp;</span> fix: <Badge text="修复/移除" type="danger"/>

由于开发版本仍在开发中，开发版本的文档可能不完全准确，可能会发生变化。
:::

::: danger 重量级更新：团队协作支持
我们费了非常大的精力，对插件进行了重大的重构，将原先的**单机版**改成了支持**团队协作版**，通过文件版本管理系统，实现部分配置及所有API的**共享**。

但是可能也带来了一些bug，希望开发者能体谅！

同时我们也希望开发者们能在团队中推广并分享Restful Fast Request插件，再次非常感谢！

更多详情请看 -> [团队协作支持](./teamwork.md)

:::

## 2023.1.3 <Badge text="免费试用" type="tip"/>  <Badge text="开发中..." color="LightPink"/>
- <Badge text="重量级更新：团队协作支持" type="tip"/>
- <Badge text="API与存储文件跳转" type="tip"/>
- <Badge text="项目配置刷新" type="tip"/>
- <Badge text="垂直工具栏" type="tip"/>  
- <Badge text="Mapping为数组的时候URL随机生成" type="danger"/>

::: tip API与存储文件跳转
实现API以及底层保存的json数据进行关联跳转

![jumpToJsonData](/img/2023.1.3/jumpToJsonData.png)

![API列表跳转到json](/img/2023.1.3/listJump2JsonData.png)
:::

::: tip 项目配置刷新
配置文件更新后，UI实现项目级别配置更新

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)
:::

::: tip 垂直工具栏
API调试窗口默认为水平工具栏，但是垂直工具栏可以展示更多按钮。

![toolbar](/img/2023.1.3/toolbar.png)
:::



## 2023.1.2 <Badge text="免费试用" type="tip"/>  <Badge text="最新版" color="LigntGreen"/>
- <Badge text="图片响应渲染" type="tip"/>
- <Badge text="年度报告" type="tip"/>
- <Badge text="Url解析逻辑" type="info"/>
- <Badge text="cUrl导入" type="info"/>
- <Badge text="Url特殊字符编码" type="info"/>
- <Badge text="Url输入框回车事件" type="info"/>
- <Badge text="移除窗口插件标题" type="info"/>
- <Badge text="jdk11+的idea版本中Patch请求失败" type="danger"/>
- <Badge text="API名字保存错误" type="danger"/>

::: tip 图片响应渲染
针对响应是图片的请求，自动渲染出图片，适合验证码场景

![imageRender](/img/2023.1.2/imageRender.png)
:::

::: tip 年度报告
统计了历年操作的数据，你是否要来**PK**一把。此处省略1个狗头
![annualReport](/img/2023.1.2/annualReport.png)
:::

::: info Url解析逻辑
优化了url针对变量拼接的最终结果，例如几个变量拼接最终计算出一个url。

插件将最终计算出url=`/test/a.htm`
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

::: info cUrl导入
1. 自动解析query param进入`Url params` tab页
2. json解析优化
3. 已知bug修复
:::

::: info Url特殊字符编码
针对带query param的url中的参数值待特殊字符进行了处理，不需要用户再额外进行特殊编码再传参
:::

::: info Url输入框回车事件
url输入框点击回车，自动发送请求
:::


## 2023.1.1 <Badge text="免费试用" type="tip"/>
- <Badge text="Yml和Properties文件的域名解析支持" type="tip"/>
- <Badge text="项目级别域名配置" type="tip"/>
- <Badge text="图标排版" type="info"/>
- <Badge text="新UI工具窗口图标" type="info"/>
- <Badge text="项目级别头参数可选" type="info"/>
- <Badge text="Cookie取值" type="info"/>  
- <Badge text="基础类型Swagger默认值解析" type="danger"/>
- <Badge text="数组不解析" type="danger"/>

::: tip 项目级别域名配置
支持直接从`yml`和`properties`配置文件快速配置域名，更多详情请查看 [<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-domainConfig"></use></svg> 项目域名配置](./features/projectLevelDomainConfig.md)

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig.png "域名配置")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog.png "域名列表")
:::

::: info 图标排版
对按钮进行了归类整理，让开发者更加容易找到和理解对应按钮的功能。
:::

::: info 新UI工具窗口图标
新UI下工具窗口图标清晰度进行了优化。
:::

## 2022.3.1 <Badge text="免费试用" type="tip"/>
- <Badge text="注释增加@parseIgnore用来忽略字段解析" type="tip"/>
- <Badge text="Script中增加rfr.currentDomain支持" type="tip"/>
- <Badge text="激活提示优化" type="info"/>
- <Badge text="Kotlin和Java混编导致Navigate加载api失败" type="danger"/>

::: tip 注释增加@parseIgnore用来忽略字段解析

以下的代码，插件会忽略解析该字段，请使用`@parseIgnore`

```java
/**
 * xxx description
 * @parseIgnore
 */
private String someIgnoreField ;
```
:::

::: tip Script中增加rfr.currentDomain支持
在脚本中可以通过`rfr.currentDomain`获取当前作用的域名
:::


## 2022.2.9 <Badge text="免费试用" type="tip"/>

- <Badge text="兼容IDEA2022.2.4" type="tip"/>
- <Badge text="全局动画配置" type="tip"/>
- <Badge text="多Cookie值错误" type="danger"/>
- <Badge text="导航Navigate tab加载错误" type="danger"/>

::: info 全局动画配置
你可以在此关闭全局动画，如果关闭了，图标将不再动

![animateConfig](/img/2022.2.9/animateConfig.png)
:::

## 2022.2.8 <Badge text="免费试用" type="tip"/>

- <Badge text="自动适配下载" type="info"/>
- <Badge text="@RequestParam支持name解析" type="info"/>
- <Badge text="检查更新报错" type="danger"/>

::: info 下载文件适配
当response加了`content-disposition:attachment`，点击send自动适配下载
:::

## v2022.2.7 <Badge text="免费试用" type="tip"/>

- <Badge text="Api文档同步" type="tip"/>
- <Badge text="Api文档显示返回值类型参数文档" type="tip"/>
- <Badge text="插件更新通知机制" type="tip"/>
- <Badge text="公共请求头" type="tip"/>
- <Badge text="全屏" type="tip"/>
- <Badge text="注释预览" type="info"/>
- <Badge text="模块头一直被选中" type="danger"/>
- <Badge text="解析bug" type="danger"/>
- <Badge text="body中传string参数报错" type="danger"/>

::: tip Api 文档同步
在线文档同步---->[详情](./features/apiDocSync.md)

![apiSync](/img/2022.2.7/apiSync.png)

![apiSyncSetting](/img/2022.2.7/apiSyncSetting.png)
:::

:::tip Api 文档显示返回值类型参数文档
![returnValueDoc](/img/2022.2.7/returnValueDoc.png)
:::

::: tip 插件更新通知机制
从显式的对话框改为通知，同时自动更新也修改为==打开==了，如果你想关闭，请到配置页面手动关闭它，但我建议==开启它==，这样子就可以及时接收到更新。

需要注意的是，前一个版本的更新依然会是弹窗，这个版本以后就是消息通知机制了

![upgradeNotice](/img/2022.2.7/upgradeNotice.png)
:::

::: tip 公共请求头

[---->详情](./features/commonHeader.md)
![commonHeader](/img/2022.2.7/commonHeader.gif)
:::

::: tip 全屏
---->[详情](./features/fullScreen.md)
![fullScreen](/img/2022.2.7/fullScreen.png)
:::

::: info 注释预览
![fullScreen](/img/2022.2.7/commentPreview.png)
:::

## v2022.2.6 <Badge text="免费试用" type="tip"/> <Badge text="最新版" type="info"/>

- <Badge text="一键清除参数" type="tip"/>
- <Badge text="API注释预览" type="tip"/>
- <Badge text="参数列根据key排序" type="tip"/>
- <Badge text="Api文档导出新增required列" type="info"/>
- <Badge text="优化内网检查更新延迟较长" type="info"/>
- <Badge text="第一次打开工具窗口有短暂卡顿现象" type="info"/>
- <Badge text="前置脚本多次打印" type="danger"/>

::: tip 一键清除参数
全局清空
![clear](/img/2022.2.6/clear.png)

批量清空列值
![clearColumnValue](/img/2022.2.6/clearColumnValue.png)
:::

::: tip API 注释预览
![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
:::

::: tip 参数列根据 key 排序
![sortColumn](/img/2022.2.6/sortColumn.png)
:::

## v2022.2.5.2 <Badge text="免费试用" type="tip"/>

- <Badge text="后置脚本无法清空" type="danger"/>

## v2022.2.5.1 <Badge text="免费试用" type="tip"/>

- <Badge text="Console语法报错" type="danger"/>

## v2022.2.5 <Badge text="免费试用" type="tip"/>

- <Badge text="添加Console控制台" type="tip"/>
- <Badge text="添加打印语法支持" type="tip"/>
- <Badge text="内置变量rfr添加currentProjectName、currentEnvName属性" type="tip"/>
- <Badge text="从Javadoc读取注释" type="tip"/>
- <Badge text="@RequestPart支持" type="tip"/>
- <Badge text="新增推特" type="tip"/>
- <Badge text="后置脚本在请求成功但响应是401不执行" type="danger"/>

::: tip Console 支持
Console 帮助开发者打印一些你想要的信息

更多信息请看-----> [script->console](./script.md#console)

![console](/img/2022.2.5/console.png)

:::

::: tip 内置变量 rfr 添加 currentProjectName、currentEnvName 属性
可以使用这 2 个变量用于一些判断

更多信息请看-----> [script->内置变量->rfr](./script.md#rfr)
:::

::: tip 从 Javadoc 读取注释
读取 Javadoc 形式的参数注释，适用于 path 参数以及 request param 是非实体类的参数

![console](/img/2022.2.5/parseDocDesc.png)
:::

::: tip 新增推特
有推特账号的请关注我，感谢

![](/img/twitter.png)

![twitterAction](/img/2022.2.5/twitterAction.png)
:::

## v2022.2.4.1 <Badge text="免费试用" type="tip"/>

- <Badge text="修复小屏EUAL协议无法显示同意按钮" type="danger"/>

## v2022.2.4 <Badge text="免费试用" type="tip"/>

- <Badge text="全新的Logo" type="tip"/>
- <Badge text="支持通过注释解析日期" type="tip"/>
- <Badge text="支持解析BaseController" type="tip"/>
- <Badge text="自动更新可配置化" type="info"/>
- <Badge text="参数编码传参优化" type="info"/>
- <Badge text="Map不带泛型解析错误" type="danger"/>
- <Badge text="后置脚本删除添加头信息失败" type="danger"/>

::: tip 全新的 Logo
开发者可以在下拉框里面自由选择并切换你想要的颜色，并且可以让它==动起来==(性能佳)

![newLogo](/img/2022.2.4/newLogo.gif)

![newLogoConfig](/img/2022.2.4/newLogoConfig.png)

![newLogo](/img/2022.2.4/newLogo.png)
:::

::: tip 支持通过 DateTimeFormat 注释解析日期
![dateTimeFormat](/img/2022.2.4/dateTimeFormat.png)
:::

::: tip 支持解析 BaseController
![baseController](/img/2022.2.4/baseController.png)
:::

::: info 自动更新可配置化
如果你不需要接收自动更新，则可以关闭，通过手动的形式更新(建议开启)
![autoUpdate](/img/2022.2.4/autoUpdate.png)
:::

## v2022.2.3.1 <Badge text="免费试用" type="tip"/>

- <Badge text="修复参数无法清除的bug" type="danger"/>

## v2022.2.3 <Badge text="免费试用" type="tip"/>

- <Badge text="前置脚本" type="tip"/>
- <Badge text="后置脚本" type="tip"/>
- <Badge text="一键拷贝Url" type="tip"/>
- <Badge text="兼容IDEA2021.2.1+" type="info"/>
- <Badge text="RequestParam defaultValue属性解析" type="info"/>
- <Badge text="点击按钮时触发表格单元格值更改" type="info"/>
- <Badge text="关闭RFR工具窗口后点击火箭第一次url无法生成" type="danger"/>
- <Badge text="Jax-rs PATCH支持" type="danger"/>

::: tip 脚本支持
脚本可以让开发者更加灵活地、动态地、方便地修改请求过程的一些入参，以及响应的处理  
具体请参考-------> [脚本](./script.md)

![scriptProject](/img/2022.2.3/scriptProject.png)

![scriptModule](/img/2022.2.3/scriptModule.png)
:::

::: tip 一键拷贝 Url
![copyUrl](/img/2022.2.3/copyUrl.png)
:::

::: info 点击按钮时触发表格单元格值更改
==2022.2.3 版本之前==在输入表格中的参数时候，若光标还在表格内部，此时去点击操作按钮，某些场景下会报错或者值无法被修改，该问题在此版本得到解决

老版本如何处理: [FAQ:输入参数后调用 API 发现无效](./faq.md)
:::

## v2022.2.2 <Badge text="免费试用" type="warn"/>

- <Badge text="终止API请求功能" type="tip"/>
- <Badge text="批量导出API文档" type="tip"/>
- <Badge text="导出到Postman添加注释" type="tip"/>
- <Badge text="响应数据量巨大情况下卡顿优化" type="info"/>
- <Badge text="某些场景下的体验和提示" type="info"/>
- <Badge text="新UI及EAP下SearchEveryWhere报错" type="danger"/>
- <Badge text="Multipart修改数字类型参数报错" type="danger"/>
- <Badge text="某些情况下历史请求删除操作报错" type="danger"/>

::: tip 终止 API 请求功能
![stopApi](/img/2022.2.2/stopApi.gif)
:::

::: tip 批量导出 API 文档
![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)
:::

## v2022.2.1 <Badge text="免费试用" type="warn"/>

- <Badge text="API分组参数保存支持" type="tip"/>
- <Badge text="临时请求保存支持" type="tip"/>
- <Badge text="cURL导入支持" type="tip"/>
- <Badge text="Response Header展示" type="tip"/>
- <Badge text="添加对Url尾缀支持" type="tip"/>
- <Badge text="优化快捷键" type="info"/>
- <Badge text="优化解析" type="info"/>
- <Badge text="修复Word导出bug" type="danger"/>

::: tip API 分组参数保存支持
目的:一个 API 下保存不同组合的参数

[B 站教程](https://www.bilibili.com/video/BV1zU4y1S7pC?share_source=copy_web&vd_source=c46db3e7c134b1948dabbea9717a72ac)

==原保存操作将默认归类到 Default 分组==

如何操作:输入参数后点击==分组请求保存==

![groupSave](/img/2022.2.1/groupSave.png)

**切换参数**

以下是 book 列表 3 种语言的查询

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)
:::

::: tip 临时请求保存支持
目的:保存任意不属于本项目的请求，用于临时调用，跟当前项目代码不关联

临时请求保存的 Request 的 Url 必须以==http==或者==https==开头

![tempSave](/img/2022.2.1/tempSave.png)

:::

::: tip cURL 导入支持

![importByCurl](/img/2022.2.1/importByCurl.gif)

==请注意如果需要绑定方法，则需要将光标放置到方法名上==
:::

::: tip Response Header 展示

![responseHeader](/img/2022.2.1/responseHeader.png)

:::

::: tip 添加对 Url 尾缀支持

目的:有些请求需要在 url 结尾加上.do

![responseHeader](/img/2022.2.1/urlSuffix.png)

:::

::: info 优化解析
优化了`List<Entity>`的解析
:::

## v2022.1.10 <Badge text="免费试用" type="warn"/>

- <Badge text="添加对历史请求预览的支持" type="tip"/>
- <Badge text="超时时间自定义设置" type="tip"/>
- <Badge text="SearchEveryWhere支持方法注释搜索" type="info"/>
- <Badge text="快捷键冲突优化" type="info"/>
- <Badge text="文本编辑器字符显示光标定位优化" type="info"/>
- <Badge text="Curl输出格式优化" type="info"/>
- <Badge text="优化@Consumes(APPLICATION_JSON)不生效问题(JAX-RS)" type="info"/>

::: tip 添加对历史请求预览的支持
隐藏技能:双击记录可以展示详情
![history](/img/history.png)
:::

::: tip 超时时间自定义设置
默认: 60 秒
![timeoutSetting](/img/timeoutSetting.png)
:::

::: info SearchEveryWhere 支持方法注释搜索
支持注释搜索 API
![searchEveryWhereDescription](/img/searchEveryWhereDescription.png)
:::

::: info 优化

- <Badge text="快捷键冲突优化" type="info"/>
  - <Badge text="将打开创建的三个快捷键统一加上了Shift" type="info"/>
- <Badge text="文本编辑器字符显示光标定位优化" type="info"/>
  - <Badge text="文本编辑器生成字符串的时候默认聚焦到第一行" type="info"/>

:::

## v2022.1.9 <Badge text="免费试用" type="warn"/>

- <Badge text="工具窗口跳转增加快捷键" type="tip"/>
- <Badge text="添加自动添加Cookie支持" type="tip"/>
- <Badge text="响应raw中增加html预览" type="tip"/>
- <Badge text="APIs列表视图优化" type="info"/>
- <Badge text="忽略字段解析优化" type="info"/>
- <Badge text="泛型参数解析支持" type="info"/>
- <Badge text="完善项目全局参数" type="info"/>
- <Badge text="修复编辑参数时同时按删除参数导致报错" type="info"/>
- <Badge text="界面优化" type="info"/>
- <Badge text="保存API插入顺序优化" type="info"/>
- <Badge text="优化了导出预览" type="info"/>

::: tip 工具窗口跳转增加快捷键
提供了快捷键可以快速跳转，可以在`setting->Keymap`中修改
![toolwindowShortcut](/img/toolwindowShortcut.png)
:::

::: tip 添加自动添加 Cookie 支持
开启此配置，cookie 再自动存入==项目全局参数==中的`cookies`中，此配置默认开启
![autoStoreCookie](/img/autoStoreCookie.png)
:::

::: tip 响应 raw 中增加 html 预览
raw 中不再截断 html 返回类型的响应，同时可以对 html 进行预览

![rawHtmlPreview](/img/rawHtmlPreview.png)
:::

::: info 忽略字段解析优化

- <Badge text="忽略解析使用`@JsonIgnore`、`@JSONField(serialize = false)`注解的字段" type="info"/>
- <Badge text="忽略解析`transient`关键字修饰的字段" type="info"/>

:::

::: info 泛型参数解析支持
支持此类泛型参数解析

```java
@Data
public class CommonDTO<T，R> implements Serializable {
    private String accessToken;
    private String appKey;
    private String clientId;
    private T data;
    private List<R> dataList;
}

@PostMapping(value = "/apply")
public Integer loanApply(@RequestBody CommonDTO<Book， TestDTO> req){
    return 1;
}
```

:::

::: info 完善项目全局参数
加入了`URL Params`、`URL-Encoded Params`、`Cookies`
![projectConfigParam](/img/projectGlobalParam.png)
:::

::: info 优化

- 界面优化
  - 优化了 tab 间距(windows 下不正常)、背景颜色、个别突出的 tab 颜色等
- 保存 API 插入顺序优化
  - 后保存的放在最上面

:::

::: info APIs 列表视图优化
渲染进行了优化，新保存的 api 图标会自动优化，历史保存的 api 可以右键进行选择变更
![apisList](/img/apisList.png)
:::

## v2022.1.8 <Badge text="免费试用" type="warn"/>

- <Badge text="项目级别全局参数支持" type="tip"/>
- <Badge text="优化响应数据快速添加到头" type="info"/>
- <Badge text="优化cookie快速添加到头" type="info"/>
- <Badge text="Postman导出优化" type="info"/>
- <Badge text="修复navigate树扫描bug" type="info"/>
- <Badge text="修复了一些bug" type="danger"/>

::: tip 项目级别全局参数支持
支持项目级别内的全局参数，不受多模块影响

配置优先级`api头 > 项目级别全局请求头 > 全局请求头`

![projectConfigParam](/img/projectConfigParam.png)
:::

::: info 快速添加到头参数

原响应结果从添加到 ~~全局请求头~~ 变更为添加到 ==项目全局请求头==，cookie 也是如此

![add2projectConfig](/img/add2projectConfig.png)

![cookie2projectConfig](/img/cookie2projectConfig.png)
:::

## v2022.1.7 <Badge text="免费试用" type="warn"/>

- <Badge text="Kotlin spring系列框架支持" type="tip"/>
- <Badge text="新增SearchEveryWhere范围搜索策略" type="tip"/>
- <Badge text="编辑器ToggleUseSoftWrap支持" type="tip"/>
- <Badge text="响应数据一键添加Global Header支持" type="tip"/>
- <Badge text="添加导航到当前方法的支持" type="tip"/>
- <Badge text="优化多模块下保存的API回显自动绑定项目" type="info"/>

::: tip Kotlin spring 系列框架支持
添加了 Kotlin 语言下，Spring 系列框架支持
![kotlinSupport](/img/kotlinSupport.png)
:::

::: tip 新增 SearchEveryWhere 范围搜索策略
2022.1.7 版本默认可以搜索到 feign 或者 controller 中的 mapping

默认搜索策略是根据注解来搜索的，再提供 2 种选项，如果在搜索过程中需要排除类似 feign 中的接口，可以通过去除 Interface 的勾选来处理.
![searchEveryWhereStrategy](/img/searchEveryWhereStrategy.png)
:::

::: tip 编辑器 ToggleUseSoftWrap 支持

![toggleUseSoftWrap](/img/toggleUseSoftWrap.png)
:::

::: tip 响应数据一键添加 Global Header 支持
多模块项目下建议使用，因为 Add to Headers 只能添加到当前模块项目下

![add2GlobalHeader](/img/add2GlobalHeader.png)
:::

::: tip 添加导航到当前方法的支持
当你生成好方法的 url 以后，又切换到代码中的别的地方，然后又想开始调试该 API 方法，可以通过此功能快速定位到代码

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)
:::

::: info 优化多模块下保存的 API 回显自动绑定项目
优化了**多模块**下下针对保存的 api 回显需要**手动切换项目下拉框**
:::

## v2022.1.6 <Badge text="免费试用" type="warn"/>

- <Badge text="添加一键添加Cookie到Header的支持" type="tip"/>
- <Badge text="添加API分享Word和html的支持" type="tip"/>
- <Badge text="修复了Basic authorization没有携带Basic字符串" type="danger"/>
- <Badge text="移除了APIs标签下的拉拽功能" type="danger"/>

::: tip 添加一键添加 Cookie 到 Header 的支持

针对登录需要 Cookie 支持的，可以将响应中的 Cookie 快速添加到 Header 来实现

![addCookie2Header](/img/addCookie2Header.png)
:::

::: tip 添加 API 分享 Word 和 html 的支持

请注意 word 形式的 API 内部采用 html 来实现的，所以不要觉得奇怪，导出就行

![shareApi](/img/shareApi.png)
:::

[comment]: <> (::: note 添加 YouTrack 跟踪 bug 支持)

[comment]: <> (Fast Request 接通了 YouTrack Issue 自动上报的功能，直接点击上报 bug 即可反馈问题，无需手动再复制粘贴到 Github 提报 bug)

[comment]: <> (Fast Request YouTrack 官方网址: [https://darkings.youtrack.cloud/issues]&#40;https://darkings.youtrack.cloud/issues&#41;)

[comment]: <> (![youtrackSubmit]&#40;/img/youtrackSubmit.png&#41;)

[comment]: <> (::: )

## v2022.1.5 <Badge text="免费试用" type="warn"/>

- <Badge text="添加对自动生成参数描述的支持" type="tip"/>
- <Badge text="添加一键生成API文档的支持" type="tip"/>
- <Badge text="添加单个类的API整体预览" type="tip"/>
- <Badge text="移除了Accept头上的text/html" type="danger"/>
- <Badge text="修复了一些bug" type="danger"/>

::: tip 添加对自动生成参数描述的支持

可选择性的对描述进行隐藏

![paramDescription](/img/paramDescription.png)
:::

::: tip 添加一键生成 API 文档的支持

点击生成参数后，可通过分享直接生成 Markdown 文档

![shareDocButton](/img/shareDocButton.png)

![apiDocExample](/img/apiDocExample.png)

:::

::: tip 添加单个类的 API 整体预览

聚焦窗口后输入关键字可快速定位

![apiDocExample](/img/apiPreview.gif)
:::

## v2022.1.4 <Badge text="免费试用" type="warn"/>

==idea 版本 2021.3+==

- <Badge text="APIs导入导出支持" type="tip"/>
- <Badge text="APIs支持直接运行" type="tip"/>
- <Badge text="API请求增加超时设置" type="tip"/>
- <Badge text="url生成优化之多url随机生成" type="tip"/>
- <Badge text="自动生成参数可选化" type="tip"/>
- <Badge text="swagger注解默认值参数解析支持" type="tip"/>
- <Badge text="兼容idea 2022.1" type="info"/>
- <Badge text="SearchEveryWhere高亮优化" type="info"/>
- <Badge text="对Send和Send and Download按钮进行了合并" type="info"/>
- <Badge text="APIs界面优化" type="info"/>
- <Badge text="Tab页API Navigate重命名Navigate" type="info"/>
- <Badge text="使用引导上的细节优化" type="info"/>
- <Badge text="修复了Light files should have PSI only in one project" type="danger"/>

::: info SearchEveryWhere 高亮优化
高亮展示搜索关键字，加快真实想要查找的 API 的查找速度，同时展示 api 对应的 javadoc
![help](/img/searchEveryWhereHighlight.png)
:::

::: tip APIs 导入导出支持
增加对 APIs 的导入导出支持，利用改功能，你可以非常方便得将自己已有的 APIs 分享给别的开发者，或者导入到其他设备上的 IDEA

![exportImportApis](/img/exportImportApis.gif)

更多详情请看 **[功能->APIs 导入导出](./feature.md#apis导入导出)**
:::

::: tip APIs 支持直接运行
你可以在 APIs tab 页直接运行你保存的请求

![runInApiManagement](/img/runInApiManagement.png)
:::

::: info 自动生成参数可选化
![generateSwitch](/img/generateSwitch.png)
:::

::: tip swagger 注解默认值参数解析支持
增加了 swagger 注解默认值的解析，该功能对于入参的传递更加人性化

- @ApiParam(swagger2)
- @ApiImplicitParam(swagger2)
- @ApiModelProperty(swagger2)
- @Parameter(swagger3)
- @Schema(swagger3)

更多详情请看 **[功能->swagger 默认值解析支持](./feature.md#swagger默认值解析支持)**
:::

::: info 对 Send 和 Send and Download 按钮进行了合并
对按钮进行了合并，因为常见的操作都是非下载操作，减少了工具栏按钮个数，看上去更加简捷
![mergeRunAndDownload](/img/mergeRunAndDownload.png)
:::

::: info 使用引导上的细节优化
我们在不同的操作窗口，增加?选项用来展示一些注意事项及操作指引，对于初次使用的用户，操作门槛更低

并且随着版本的迭代，后续可能会加入更多的提示操作指引

![help](/img/help.png)
:::

::: info url 解析优化

历史逻辑只会取第一个 url 即 test1，考虑到实际使用中，有可能你需要的是另外一个 url，所以添加了随机支持

以下 demo，url 将随着点击<FontIcon icon="restfulFastRequest" />随机生成 **/url1/test1,/url1/test2,/url2/test1,/url2/test2**

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

::: info Tab 页 API Navigate 重命名 Navigate  
在工具窗口比较小的情况下，API Navigate 会被隐藏，为了在尽可能小的工具窗口展示更多内容，所以命名更加简短
:::

## v2.1.3

- 参数特殊符号编码支持
- curl 加入 global headers

## v2.1.2

- 添加对导出 api 到 Postman 的支持
- 首次打开 idea 懒加载 API Navigate 树
- SearchEveryWhere module 标识
- Url 解析优化
- API navigate tree 展示优化
- API 保存分组优化
- 全局请求头支持

::: tip Url 解析优化
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
```

:::

::: tip 添加对导出 api 到 Postman 的支持

![export2postman](/img/export2postman.gif)
:::

::: tip SearchEveryWhere module 标识

![searchEveryWhereModule](/img/searchEveryWhereModule.png)
:::

::: tip API 保存分组优化

```
保存api的时候，api将会保存到对应的请求指定到控制器名所在的分组(更加直观)
```

![save2ControllerGroup](/img/save2ControllerGroup.png)
:::

::: tip 全局请求头支持

![globalRequestHeader](/img/globalRequestHeader.png)
:::

## v2.1.1

- SearchEveryWhere 支持
- 增加一个最新按钮可以随时查看最新版本
- 优化枚举的解析
- 增加对 http 重定向的支持

::: tip SearchEveryWhere support

```
输入案例
/url              (查询指定url)
get /list         (指定get方式指定url)
post /save        (指定post方式指定url)
```

![searchEveryWhere](/img/searchEveryWhere.gif)
:::

::: info 最新版本快速查看
![whatsnewNotifcation](/img/whatsnewNotifcation.png)
:::

## v2.1.0.2

- 加入了组织[**Dromara**](https://dromara.org/zh/)(致力于微服务云原生解决方案的组织)

## v2.1.0.1

- 修复 Get 参数错误

## v2.1.0

- 修复 POST 形式的 API 中@RequestBody、@RequestParam 混合使用参数解析导致请求 400 异常
- 对嵌套类的解析支持
- 增加支持对控制器类级别@RequestMapping(path="/xxx")path 的解析
- Api tree 针对带@RequestMapping 类的扫描支持

## v2.0.9

- 2021.3+版本修复"Slow operations are prohibited on EDT"
- 修复快速从 response 添加 Headers
- 添加项目级别的配置，切换项目 env 和 project 不受变化
- 文本编辑器添加了快捷格式化按钮

::: tip 从 response 添加 Headers

```
如果你的api需要再header里面塞入token，你可以像这样子从响应里面快速加参数塞入headers
```

![format](/img/quickAddHeaders.gif)
:::

::: tip 文本编辑器添加了快捷格式化按钮
![format](/img/format.gif)
:::

## v2.0.8.1

- 修复 searchEveryWhere 冲突警告
- 调整低版本 idea 工具栏操作按钮至工具栏顶部位置
- 移除多余依赖，插件从 11.1M 降至 5.9M

## v2.0.8

- 修复初次添加项目或环境 npe
- json 树响应字符渲染最大限制
- 添加 Api navigate 对 methodType 的支持
- 添加对忽略参数解析的支持
- 线程导致的 EDT 问题
- send/sendDownload 支持自定义快捷键
- 修复 2021.3 响应不显示

::: tip send/sendDownload 支持自定义快捷键

```
快捷键在任意位置点击均可触发，不再需要聚焦到工具窗口
```

![shortcutSendAndDownload](/img/shortcutSendAndDownload.png)
:::

::: tip 添加 Api navigate 对 methodType 的支持

![shortcutSendAndDownload](/img/methodFilter.png)
:::

## v2.0.7

- 添加 API 导航树
- 添加 Headers 随项目和环境切换自动切换的支持
- 优化 windows 系统某些情况下下载文件无法弹出目录
- 优化@RequestParam 的参数解析
- 优化工具窗口 project 和 env 下拉组件及布局
- curl 拷贝提示优化
- regenerate 提示优化
- 删除 project 和 env 配置增加确认操作
- 修复 json 字段输出循序被打乱
- 将发送请求按钮至工具栏同时支持快捷键

::: tip API 导航树

```
选中树输入关键字，再按回车或者鼠标左键双击即可定位到API

悬浮鼠标显示api的doc
```

![apinavi](/img/apinav.gif)
:::

::: tip Headers 自动切换

```
场景:SpringBoot等多模块项目不同项目、不同环境下头参数不同，为了快速自动切换headers，引入了header分组
操作方式：
1.在headers group里修改约束:输入的值必须是标准json格式
2.直接切换环境，然后再headers表格中输入对应的key、value值
```

![headerSwitch](/img/headerSwitch.gif)
:::

::: tip 优化@RequestParam 的参数解析
@RequestParam 注解的参数将优先按照别名  
`@RequestParam("nameAlias")List<String> nameList` 输入参数将变成 nameAlias  
:::

::: tip 将发送请求按钮至工具栏同时支持快捷键

```
send request: alt =
send and download: alt -
前提条件:工具窗口需要被聚焦
```

![toolbarSend](/img/toolbarSend.png)
:::

::: tip 优化工具窗口 project 和 env 下拉组件及布局

```
图标p代表project
图标e代表environment
```

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
:::

## v2.0.6

- Fix "Light files should have PSI only in one project"
- 添加对 json 的语法支持
- 添加对 PATCH 的支持
- 添加 API 保存时候存入对应的模块分组的支持
- 修复响应中 null 值不输出的问题
- 添加对 JAX-RS 的支持
- 最新功能展示

在不影响使用的情况下，有时候会经常弹出*Light files should have PSI only in one project*.2.0.6 版本得到修复

::: tip json 语法支持
![json](/img/json.png)
:::

::: tip 添加 API 保存时候存入对应的模块分组的支持
![apiGroup](/img/apiGroup.gif)

同时模块支持快速搜索
![apiGroup](/img/moduleSearch.gif)
:::

::: tip JAX-RS 的支持
![apiGroup](/img/jaxrs.gif)
:::

::: tip 最新功能展示  
只会展示一次
![apiGroup](/img/whatsnew.png)
:::

## v2.0.5

- JSON 内置编辑器支持
- 修复全选参数框隐藏问题
- 修复 API 回显 Headers 参数异常
- 优化了请求进度条显示

## v2.0.4

- 修复因 response 内容过道导致的界面错乱问题
- Response json 超长显示优化
- 优化图标尺寸
- 中文文档迁移至 gitee

## v2.0.3

- 删除 API 的时候增加了确认
- 添加了参数全选反选的功能
- 修复诸如【@RequestParam(value="address[]") Set address】的参数解析
- 修复快速添加 headers 界面错乱

## v2.0.2

- 修复项目多开情况下 A 请求显示到 B 项目
- 修复@ResponseBody 修饰的数组与集合解析问题
- 修复 List 参数无泛型解析问题
- 添加对文件下载的支持
- 发送请求时展示进度条
- 全新的 document，点击工具栏 doc 图标
- 一些优化

## v2.0.1

- rename from Fast Request to Restful Fast Request
- Json、Form URL-Encoded、Multipart Tab 合并减小工具窗口宽度
- 修复全局配置下修改配置报错
- 将 get、post、delete、put 图标添加到保存的请求
- 保存的请求支持更多搜索策略
- tab 重命名 collection->APIs
- YearMonth 解析支持

## v2.0.0

- 支持请求的存储
- 支持参数可选
- 优化了 UI
- 修复了一些 bug
