---
home: true
title: 主页
heroImage: /img/fastRequest.svg
heroText: Restful Fast Request
tagline: 为简化调试API而生
actions:
      - text: 🚀 快速上手
        link: /guide/getstarted/start
        type: primary
features:
- title: 基于原生
  icon: origin
  details: 集成IDEA工具,在线离线均可使用,不再需要额外的诸如Postman等API调试工具
  link: /guide/introduce.html
- title: 效率至上
  icon: effective
  details: 只需要简单配置即可生成方法的api请求，从而节省大量时间
  link: /guide/getstarted/start.html
- title: 功能丰富
  icon: features
  details: 一键生成url和参数、api导出到postman、SearchEveryWhere搜索、发送API请求、下载文件、存储历史请求,各种功能一应俱全
  link: /guide/feature.html
- title: 轻量、界面友好
  icon: code-box-fill
  details: 轻量,仅7M大小,并且提供一个非常直观的界面,好操作易上手

head:
  - - meta
    - name: keywords
      content: Restful Fast Request,idea插件,http client,Restful API
  - - meta
    - name: description
      content: Restful Fast Request 一个基于IDEA的类似postman的restful api工具包插件,可以根据已有的方法帮助您快速生成url和params,一个API调试工具+API管理工具,支持springmvc、springboot、java-rs

---

<a href="https://www.jetbrains.com"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" width = "10%" /></a>
<a href="https://www.jetbrains.com/idea"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg" width = "10%" /></a>

[![](https://badgen.net/badge/Github/fast-request/21D789?icon=github)](https://github.com/dromara/fast-request)
[![](https://img.shields.io/static/v1?label=Gitee&message=fast-request&color=FF318C&logo=gitee)](https://gitee.com/dromara/fast-request)
[![Jetbrains Plugins][plugin-img]][plugin]
![Version](https://img.shields.io/jetbrains/plugin/v/16988?logo=IntelliJ%20IDEA) ![Downloads](https://img.shields.io/jetbrains/plugin/d/16988?color=FE2857) ![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/r/rating/16988) [![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Restful%20Fast%20Request&logo=telegram&color=32CD32)](https://t.me/restful_fast_request)

<iframe frameborder="none" width="245px" height="48px" src="https://plugins.jetbrains.com/embeddable/install/16988"></iframe>

### 功能  :100:

* [x] Postman一样的操作界面
* [x] 一键生成url和参数
* [x] SearchEveryWhere支持
* [x] API导出到Postman
* [x] 发送请求调试API
* [x] 发送请求并下载文件
* [x] 域名定制及切换
* [x] 自定义参数解析
* [x] 字符串生成策略化
* [x] 保存API
* [x] 管理API(搜索过滤、修改API名称等)
* [x] 一键定位、运行历史保存的API到方法
* [x] API转curl
* [x] 自动将API关联到模块分组
* [x] 内置Json参数编辑器
* [x] API导航树
* [x] Headers分组
* [x] Swagger参数解析支持
* [x] 自动生成API字段的描述
* [x] 一键生成API文档
* [x] API一览表
* [x] 项目全局配置
* [x] HTML预览
* [x] 自动Cookie存储
* [x] 历史请求预览
* [x] API分组参数保存支持
* [x] 临时请求保存支持
* [x] url尾缀支持
* [x] ......


::: chart Fast Request功能玫瑰图

```json
{
  "type": "polarArea",
  "data": {
    "labels": ["易用性","Swagger", "Java", "Kotlin", "压测", "集成"],
    "datasets": [
      {
        "label": "My First Dataset",
        "data": [95, 90, 90, 70, 40, 60],
        "backgroundColor": [
          "rgb(255, 99, 132)",
          "rgb(75, 192, 192)",
          "rgb(255, 182, 193)",
          "rgb(255, 215, 0)",
          "rgb(54, 162, 235)",
          "rgb(0, 250, 154)"
        ]
      }
    ]
  }
}
```

:::
------

::: chart API工具对比

```json
{
  "type": "radar",
  "data": {
    "labels": ["易用性","Swagger", "Java", "Kotlin", "压测", "集成"],
    "datasets": [
      {
        "label": "Fast Request",
        "data": [95, 90, 90, 70, 40, 60],
        "fill": true,
        "backgroundColor": "rgba(0, 250, 154, 0.2)",
        "borderColor": "rgb(0, 250, 154)",
        "pointBackgroundColor": "rgb(0, 250, 154)",
        "pointBorderColor": "#fff",
        "pointHoverBackgroundColor": "#fff",
        "pointHoverBorderColor": "rgb(0, 250, 154)"
      },
      {
        "label": "Postman",
        "data": [80, 80, 80, 50, 90, 100],
        "fill": true,
        "backgroundColor": "rgba(54, 162, 235, 0.2)",
        "borderColor": "rgb(54, 162, 235)",
        "pointBackgroundColor": "rgb(54, 162, 235)",
        "pointBorderColor": "#fff",
        "pointHoverBackgroundColor": "#fff",
        "pointHoverBorderColor": "rgb(54, 162, 235)"
      }
    ]
  },
  "options": {
    "elements": {
      "line": {
        "borderWidth": 3
      }
    }
  }
}
```

:::


::: tip 注意  
请确保你的 IDEA版本 版本 >= 2020.3+

插件版本       | IDEA版本要求
------------- | -------------
2.0.0~2.1.3   | 2020.3+
2022.1.4+     | 2021.3+

当然请如果插件有更新，请确保更新成最新版本
:::


[comment]: <> (## 🧲 友情链接)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://hertzbeat.com/" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/hertzbeat.svg'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://gitee.com/dromara/easy-es" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/easy-es.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://gitee.com/dromara/TLog" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/tlog-logo.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://liteflow.yomahub.com/" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/lite-flow.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://gitee.com/dromara/sa-token" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/sa-token.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)



[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://gitee.com/dromara/hutool" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/hutool-logo.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://gitee.com/dromara/forest" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/forest-logo.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)


[comment]: <> (<span style="width:16%;  padding:15px 15px 15px 15px;display:inline-block">)

[comment]: <> (    <a href="https://jpom-docs.keepbx.cn/" target="_blank">)

[comment]: <> (        <img :src="$withBase&#40;'/img/link/jpom-logo.png'&#41;" class="no-zoom" style="height:40px;max-width:150px;margin: 10px;">)

[comment]: <> (    </a>)

[comment]: <> (</span>)

[plugin]: https://plugins.jetbrains.com/plugin/16988

[plugin-img]: https://img.shields.io/badge/plugin-Restful_Fast_Request-x.svg?logo=IntelliJ%20IDEA
