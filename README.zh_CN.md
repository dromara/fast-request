# Fast Request

![Version](http://phpstorm.espend.de/badge/16988/version)
![Downloads](http://phpstorm.espend.de/badge/16988/downloads)
![Downloads Last Month](http://phpstorm.espend.de/badge/16988/last-month)

**Fast Request**是一个基于springmvc的帮助你快速生成**url**和**参数**的插件.

你只需要按一下快捷键,插件就会给你方法的url和参数

目前插件对标的是[Paw](https://paw.cloud/)

如果你觉得本插件不错,请给Star,也欢迎提供优秀的PR

![example](./screenshot/example.gif)

- document
    * [中文文档](README.zh_CN.md)
    * [English Document](README.md)

QQ 群:754131222

## 1.安装

**插件市场安装**

- <kbd>Preferences(Settings)</kbd> > <kbd>Plugins</kbd> > <kbd>Browse repositories...</kbd> > <kbd>输入"Fast
  Request"</kbd> > <kbd>点击Install</kbd>

**手动安装:**

- 下载[`lastest plugin zip`][latest-release] -> <kbd>Preferences(Settings)</kbd> > <kbd>Plugins</kbd> > <kbd>Install
  plugin from disk...</kbd>

## 2.默认快捷键
可通过IDEA快捷键设置修改

|快捷键|作用域|说明|
| --- | --- | --- |
| <kbd> ctrl \ </kbd> | 方法(光标放上面就行) | 生成当前方法的url和请求参数 |

如果它不起作用，您可以在Keymap中搜索<kbd>Generate URL and Param</kbd>并更改快捷键

修改路径:<kbd>Preferences(Settings)</kbd> > <kbd>Keymap</kbd>

另外的方法:<kbd>Code(Toolbar)</kbd> > <kbd>Generate</kbd> > <kbd>Generate URL and Param</kbd>

## 3.配置及使用

### 3.1 公共配置
|配置名|描述|
| --- | --- |
|ProjectName|项目名,如user、order等,请确保必须先有一个project再添加env|
|Env|环境名,如local、develop、test、produce等|
|Domain|在表格中维护对应项目对应环境的域名|

![](./screenshot/commonConfig.png)

### 3.2 数据映射
|配置名|描述|
| --- | --- |
|Random String Length|随机出的字符串长度,默认为5|
|Custom Data Mapping|如果你想要一个类只解析自己想要的字段,那么你可以通过添加自定义的映射配置|
|Default Data Mapping|默认类型的关系映射,即类型转化为对应的值|

![](./screenshot/dataMapping.png)

#### 3.2.1 Custom Data Mapping
**Java Type**为对应的对象类型,必须是包含包名和类名,如`com.baomidou.mybatisplus.extension.plugins.pagination.Page`

**Default value**必须是json格式,如
```
{"size":10,"current":1}
```

## 4.小技巧
1. 参数Table支持值修改,修改了PathParamTable对应的url会想要的变化
2. Url Param、JSON、Form URL-Encoded Table支持参数修改,修改后的text tab也参数也会随之变化,在text框中点击**鼠标右键即可以复制参数**


## 5.类型ICON映射
图标和类型映射关系

|Icon|Type|
| --- | --- | 
|![](./screenshot/icon/array.svg)  |Array  |
|![](./screenshot/icon/object.svg) |Object |
|![](./screenshot/icon/number.svg) |Number |
|![](./screenshot/icon/string.svg) |String |
|![](./screenshot/icon/boolean.svg)|Boolean|

## 6.更新日志
- v1.0.0(2021.06.09)
  * support generate url and param
  * support custom domain
  * support custom params
  
## 7.支持作者
如果觉得插件很赞，为你节约了不少时间，那么就请作者喝杯咖啡吧~☕☕☕,非常感谢

| ![微信](./screenshot/pay/wechat.jpg) | ![支付宝](./screenshot/pay/alipay.png) |
| --- | --- |

## 8.后续计划
希望各位路人大佬帮忙一起建设后续的功能,提供好的建议以及好的PR!

* 支持发送请求 