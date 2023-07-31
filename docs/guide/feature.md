---
title: 功能
icon: tools
layout: RfrSlot
---

==不再维护==，[请点击这边的文档](./features/README.md)

[[toc]]

## 让图标动起来 <Badge text="2022.2.4"/>

开发者可以在下拉框里面自由选择并切换你想要的颜色，并且可以让它==动起来==(性能佳)

![newLogo](/img/2022.2.4/newLogo.gif)

![newLogoConfig](/img/2022.2.4/newLogoConfig.png)

## 调试 API 发送请求 <Badge text="2.0.0"/>

![example](/img/example.gif)

新版本中发送按钮在工具栏

![sendRequest](/img/sendRequest.png)

## SearchEveryWhere 支持 <Badge text="2.1.1" />

```
输入案例
/url              (查询指定url)
get /list         (指定get方式指定url)
post /save        (指定post方式指定url)
方法关键字          (查询指定描述)
```

![searchEveryWhere](/img/searchEveryWhere.gif)

## 针对响应是文件的 API <Badge text="2.0.2" type="error"/>

点击 Send and download

![example_download](/img/downloadFile.png)

## 脚本 <Badge text="2022.2.3+" type="error"/>

脚本可以让开发者更加灵活地、动态地、方便地修改请求过程的一些入参，以及响应的处理  
具体请参考-------> [脚本](./script.md)

![scriptProject](/img/2022.2.3/scriptProject.png)

![scriptModule](/img/2022.2.3/scriptModule.png)

## API 导出到 Postman <Badge text="2.1.2" type="error"/>

![export2postman](/img/export2postman.gif)

## API 列表 <Badge text="2.0.1" type="warning"/>

::: tip API 列表 name 取值:

1. 如果方法使用了 swagger 的@io.swagger.annotations.ApiOperation，则取该注解的 value 值
1. 如果没有 swagger 直接修饰，则取方法的 javaDoc 描述
1. 如果以上都没有则返回 New Request

API 的 name 值支持修改

:::

![api](/img/apis_hd.png)
![apiManager](/img/apiManager.png)

## 保存请求 <Badge text="2.0.0"/>

::: tip 特别说明

1. 保存的请求默认会放入*Default Group*中，支持拉拽放入别的组，当然最好是加入 module 分组，请查看 api 分组自动关联

1. API 取名:如果 api 使用了 swagger 注解`@ApiOperation("xxx")`，则 api 取名 xxx，如果没加 swagger 注解，则使用 javadoc 作为 api 的名称，否则将取名 New Request

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

## API 分组参数保存支持 <Badge text="2022.2.1"/>

目的:一个 API 下保存不同组合的参数

[_B 站教程_](https://www.bilibili.com/video/BV1zU4y1S7pC?share_source=copy_web&vd_source=c46db3e7c134b1948dabbea9717a72ac)

如何操作:输入参数后点击==分组请求保存==

==原保存操作将默认归类到 Default 分组==

![groupSave](/img/2022.2.1/groupSave_en.png)

**切换参数**

以下是 book 列表 3 种语言的查询

![apiParamGroup](/img/2022.2.1/apiParamGroup_en.gif)

## 临时请求保存支持 <Badge text="2022.2.1"/>

目的:项目中保存任意不属于本项目的请求，用于临时调用，跟当前项目代码不关联

临时请求保存的 Request 的 Url 必须以==http==或者==https==开头

![tempSave](/img/2022.2.1/tempSave_en.png)

## 重新生成请求 <Badge text="2.0.0" type="error"/>

::: tip 特别说明
如果你已经保存一个请求，但是想彻底重新修改其中的参数，那么你可以选择该操作
:::

![regenerate](/img/regenerate.png)

## CURL 拷贝 <Badge text="1.1.4" type="warning"/>

必须是先生成方法 url 及参数后，点击工具栏![curl](/img/icon/curl_dark.svg)

![curl](/img/curl.png)

## 快速添加 header <Badge text="2.0.0" />

如果你的请求需要一个 token 而 token 可以通过一个登陆接口得到，那么你可以不用每次手动添加，只需要访问一下登录接口再通过以下操作来处理

![fastAddToken](/img/fastAddToken.gif)

## API 分组自动关联 <Badge text="2.0.6" type="warning"/>

api 分组是为了将保存的 api 保存到不同分组以便区分不同的 api，当处于多模块的项目情况下，支持扫描项目中的 module，并且快速得将 module 添加到分组中，这种方式会将分组添加到 root 下

**v2.1.2 保存 api 的时候自动创建 module group**

![apiGroup](/img/apiGroup.gif)

::: tip 特别说明

1. 如果没有创建 module 分组，那么保存请求的时候会将保存的请求放入 Default Group
1. 如果创建 module 分组，那么保存请求的时候会自动根据当前 API 所处于的 module 自动归类到对应的 Module Group
1. 当然你可以通过拉拽的方式自行移动 API 到对应的更小的 group，并不冲突

:::

模块支持快速搜索(光标焦点放在列表上输入关键字即可)

![moduleSearch](/img/moduleSearch.gif)

## Json 语法检查 <Badge text="2.0.6" type="error"/>

右上角提供了 json 语法检查，如果输入有误会提示对应的错误  
当然也支持格式化等操作

![json](/img/json.png)

## API 导航树 <Badge text="2.0.7"/>

```
选中树输入关键字，再按回车或者鼠标左键双击即可定位到API

悬浮鼠标显示api的doc

API Navigate树默认是懒加载的，需要点击刷新按钮，同样每次新增了API你也需要刷新才能得到
懒加载有利于加快idea启动速度
```

![apinavi](/img/apinav.gif)

## Headers 分组 <Badge text="2.0.7"/>

```
场景:SpringBoot等多模块项目不同项目、不同环境下头参数不同，为了快速自动切换headers，引入了header分组
操作方式：
1.在headers group里修改约束:输入的值必须是标准json格式
2.直接切换环境，然后再headers表格中输入对应的key、value值
```

![headerSwitch](/img/headerSwitch.gif)

## APIs 导入导出 <Badge text="2022.1.4.0" />

利用改功能，你可以非常方便得将自己已有的 APIs 分享给别的开发者，或者导入到其他设备上的 IDEA

::: danger 注意点

- 导出的时候会新增一个名为 fastRequestCollection.xml 的 xml 文件，你不能重命名它， 默认导出到当前项目路径下

- 导入的时候会做默认备份，并且会在.idea 文件夹下生成一个名为 fastRequestCollection-yyyyMMddHHmmssSSS.xml 的文件，
  如果是导入误操作，可以通过导入它来还原

- 如果 fastRequestCollection.xml 不可见，点击 file->Reload All from Disk 来强制刷新

:::

![headerSwitch](/img/exportImportApis.gif)

## swagger 默认值解析支持 <Badge text="2022.1.4.0" />

以下是一些 example

优先级: swagger 配置的值 > 配置默认值

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

## API 自动生成注释 <Badge text="2022.1.5" />

字段注释需要符合标准注释规范，使用`/**描述*/`

可以通过点击隐藏或显示 Description

![paramDescription](/img/paramDescription.png)

## API 生成 Markdown 文档 <Badge text="2022.1.5" />

请注意 word 形式的 API 内部采用 html 来实现的，所以不要觉得奇怪，导出就行

Response Example 需要运行你的 API 后才会被显示

![shareDocButton](/img/shareApi.png)

![apiDocExample](/img/apiDocExample.png)

## API 一览表预览 <Badge text="2022.1.5" />

聚焦窗口后输入关键字，根据 API 的路径关键字可以快速搜索

![apiDocExample](/img/apiPreview.gif)

## 项目级别全局参数支持 <Badge text="2022.1.8" />

支持项目级别内的全局参数，不受多模块影响

配置优先级`api头 > 项目级别全局请求头 > 全局请求头`

![projectConfigParam](/img/projectConfigParam.png)

## cURL 导入 <Badge text="2022.2.1" />

![importByCurl](/img/2022.2.1/importByCurl.gif)

==请注意如果需要绑定方法，则需要将光标放置到方法名上==

## 当前方法定位 <Badge text="2022.1.7" />

当你生成好方法的 url 以后，又切换到代码中的别的地方，然后又想开始调试该 API 方法，可以通过此功能快速定位到代码

![navigate2CurrentMethod](/img/navigate2CurrentMethod.png)

## 历史请求预览 <Badge text="2022.1.10" />

隐藏技能:双击记录可以展示详情

![history](/img/history.png)

## 终止 API 请求功能 <Badge text="2022.2.2" />

![stopApi](/img/2022.2.2/stopApi.gif)

## 批量导出 API 文档 <Badge text="2022.2.2" />

![batchExportApiDoc](/img/2022.2.2/batchExportApiDoc.png)

## 一键拷贝 Url <Badge text="2022.2.3" />

![copyUrl](/img/2022.2.3/copyUrl.png)

## Raw 中 html 预览 <Badge text="2022.1.9" />

可以对 html 进行预览

![rawHtmlPreview](/img/rawHtmlPreview.png)

## Api 注释预览 <Badge text="2022.2.6" />

![showCommentConfig](/img/2022.2.6/showCommentConfig.png)
![showCommentInClass](/img/2022.2.6/showCommentInClass.png)
![showCommentInNavigate](/img/2022.2.6/showCommentInNavigate.png)
