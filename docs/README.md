---
home: true
title: 主页
heroImage: /img/logo.gif
heroImageDark: /img/logoDark.gif
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
  details: 一键生成url和参数、脚本、api导出到postman、SearchEveryWhere搜索、发送API请求、下载文件、存储历史请求,各种功能一应俱全
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

<div style="height:130px"></div>

[![](https://badgen.net/badge/Github/fast-request/21D789?icon=github)](https://github.com/dromara/fast-request)
[![](https://img.shields.io/static/v1?label=Gitee&message=fast-request&color=FF318C&logo=gitee)](https://gitee.com/dromara/fast-request)
[![Jetbrains Plugins][plugin-img]][plugin]
![Version](https://img.shields.io/jetbrains/plugin/v/16988?logo=IntelliJ%20IDEA) 

![Downloads](https://img.shields.io/jetbrains/plugin/d/16988?color=FE2857) ![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/r/rating/16988) [![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Restful%20Fast%20Request&logo=telegram&color=32CD32)](https://t.me/restful_fast_request)


<div >
  <a href="https://www.jetbrains.com"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" width = "10%" /></a>
  <a href="https://www.jetbrains.com/idea"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg" width = "10%" /></a>
</div>

<div style="height:30px"></div>

### 功能  :100:

* [x] Postman一样的操作界面
* [x] Groovy前后置脚本支持
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

<div style="height:100px"></div>

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

<div style="height:100px"></div>

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

<div style="height:50px"></div>

::: tip 注意  
请确保你的 IDEA版本 版本 >= 2020.3+

插件版本       | IDEA版本要求
------------- | -------------
2.0.0~2.1.3   | 2020.3+
2022.1.4+     | 2021.2+

当然请如果插件有更新，请确保更新成最新版本
:::

<div style="height:50px"></div>

## 🧲 Dromara 成员项目
<div>
    <a href="https://hutool.cn/" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/hutool-logo.png')" title="🍬小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。">
    </a>
    <a href="https://sa-token.dev33.cn/" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/sa-token.png')" title="一个轻量级 java 权限认证框架，让鉴权变得简单、优雅！">
    </a>
    <a href="https://gitee.com/dromara/liteFlow" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/lite-flow.png')" title="轻量，快速，稳定，可编排的组件式流程引擎">
    </a>
    <a href="https://hertzbeat.com/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/hertzbeat-logo.png')" title="易用友好的云监控系统">
    </a>
    <a href="http://forest.dtflyx.com/" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/forest-logo.png')" title="Forest能够帮助您使用更简单的方式编写Java的HTTP客户端" nf>
    </a>
    <a href="https://easy-es.cn/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/easy-es.png')" title="🚀傻瓜级ElasticSearch搜索引擎ORM框架">
    </a>
    <a href="https://gitee.com/dromara/northstar" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/northstar-logo.png')" title="Northstar盈富量化交易平台">
    </a>
    <a href="https://gitee.com/dromara/TLog" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/tlog-logo.png')" title="一个轻量级的分布式日志标记追踪神器，10分钟即可接入，自动对日志打标签完成微服务的链路追踪">
    </a>
    <a href="https://gitee.com/dromara/hmily" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/hmily-logo.png')" title="高性能一站式分布式事务解决方案。">
    </a>
    <a href="https://gitee.com/dromara/Raincat" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/raincat-logo.png')" title="强一致性分布式事务解决方案。">
    </a>
    <a href="https://gitee.com/dromara/myth" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/myth-logo.png')" title="可靠消息分布式事务解决方案。">
    </a>
    <a href="https://cubic.jiagoujishu.com/" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/cubic-logo.png')" title="一站式问题定位平台，以agent的方式无侵入接入应用，完整集成arthas功能模块，致力于应用级监控，帮助开发人员快速定位问题">
    </a>
    <a href="https://jpom.io/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/jpom-logo.png')" title="一款简而轻的低侵入式在线构建、自动部署、日常运维、项目监控软件">
    </a>
    <a href="https://su.usthe.com/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/sureness-logo.png')" title="面向 REST API 的高性能认证鉴权框架">
    </a>
    <a href="https://www.jeesuite.com/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/mendmix-logo.png')" title="开源分布式云原生架构一站式解决方案">
    </a>
    <a href="https://gitee.com/dromara/koalas-rpc" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/koalas-logo.png')" title="企业生产级百亿日PV高可用可拓展的RPC框架。">
    </a>
    <a href="https://dynamictp.cn/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/dynamictp-logo.png')" title="🔥🔥🔥 基于配置中心的轻量级动态可监控线程池">
    </a>
    <a href="https://www.x-easypdf.cn" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/easypdf.png')" title="一个用搭积木的方式构建pdf的框架（基于pdfbox）">
    </a>
    <a href="https://www.herodotus.cn/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/dante-cloud2.png')" title="Dante-Cloud 是一款企业级微服务架构和服务能力开发平台。">
    </a>
    <a href="https://maxkey.top/" target="_blank" style="width:15%; height:40px;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/maxkey-logo.png')" title="业界领先的身份管理和认证产品">
    </a>
    <a href="https://async.sizegang.cn/" target="_blank" style="width:15%;  padding:10px 10px 10px 10px;display:inline-block">
        <img style="height:40px;" :src="$withBase('/img/link/gobrs-async.png')" title="🔥 配置极简功能强大的异步任务动态编排框架">
    </a>
</div>

[plugin]: https://plugins.jetbrains.com/plugin/16988

[plugin-img]: https://img.shields.io/badge/plugin-Restful_Fast_Request-x.svg?logo=IntelliJ%20IDEA
