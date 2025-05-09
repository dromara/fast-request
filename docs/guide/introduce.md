---
title: 简介
icon: restfulFastRequest
---

[![twitter](https://img.shields.io/static/v1?label=Twitter&message=FastRequest666&logo=twitter&color=FC8D34)](https://twitter.com/FastRequest666)
[![](https://badgen.net/badge/Github/fast-request/21D789?icon=github)](https://github.com/dromara/fast-request)
[![](https://img.shields.io/static/v1?label=Gitee&message=fast-request&color=FF318C&logo=gitee)](https://gitee.com/dromara/fast-request)
[![Jetbrains Plugins][plugin-img]][plugin]
![Version](https://img.shields.io/jetbrains/plugin/v/16988?logo=IntelliJ%20IDEA) ![Downloads](https://img.shields.io/jetbrains/plugin/d/16988?color=FE2857) ![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/r/rating/16988) [![Slack](https://img.shields.io/static/v1?label=Slack&message=Fast%20Request&logo=slack&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw)

[**::restfulFastRequest::estful ::rfr-f::ast ::restfulFastRequest::equest**](https://plugins.jetbrains.com/plugin/16988) 是一个类似于 Postman 的 IDEA 插件。它是一个强大的 restful api 工具包插件，可以根据已有的方法帮助您快速生成 url 和 params。
`Restful Fast Request = API调试工具 + API管理工具 + API搜索工具`。 它有一个漂亮的界面来完成请求、检查服务器响应、存储你的 api 请求和导出 api 请求。插件帮助你在 IDEA 界面内更快更高效得调试你的 API。

对比于 HTTP Client，Fast Request 不仅拥有 HTTP Client 内置的功能，还提供了友好易懂直观的界面，让使用者调试 API 的时候能够更加方便、简捷。同时各种类型参数也提供了不同的定制方式，更加灵活。而且集成了搜索、Postman 集成，Swagger 支持等额外的[功能](/guide/features/)。

::: important 宗旨

插件的宗旨是为简化开发、提高效率而生，我们的愿景是成为 IDEA 最好的搭档，就像魂斗罗中的 1P、2P，基友搭配，效率翻倍。
:::

## 支持的框架

- Spring体系框架 (Spring MVC / Spring Boot)
- JAX-RS
- Kotlin Spring体系框架
- Solon

## API 工具对比

::: chartjs

```json
{
  "type": "radar",
  "data": {
    "labels": ["易用性", "Swagger", "Java", "Kotlin", "压测", "集成"],
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

## 关于作者

```component VPCard
title: Kings
desc: <b>Make it easier...faster...stronger...</b><br/>
logo: /img/fastRequest.svg
link: https://github.com/kings1990
background: rgb(39 117 182 / 38%)
```

职务: 从事 java 软件开发。Fast Request 作者、设计师、星推官。[Bean Assistant](https://plugins.jetbrains.com/plugin/24576-bean-assistant--spring-code-helper)作者

<!-- @include: @src/contact.snippet.md -->

## 支持

<a href="https://www.jetbrains.com"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" width = "10%" /></a>
<a href="https://www.jetbrains.com/idea"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg" width = "10%" /></a>

[plugin]: https://plugins.jetbrains.com/plugin/16988
[plugin-img]: https://img.shields.io/badge/plugin-主页-x.svg?logo=IntelliJ%20IDEA
