# Restful Fast Request

- [简体中文](./README.zh_CN.md)
- Version before v2022.1.4

<a href="https://www.jetbrains.com"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" width="10%" /></a>
<a href="https://www.jetbrains.com/idea"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg" width="10%" /></a>

[![Slack](https://img.shields.io/static/v1?label=Slack&message=Restful-Fast-Request&logo=slack&color=38B580)](https://join.slack.com/t/restfulfastrequest/shared_invite/zt-1we57vum8-TALhTHI2uNmPF2bx1NDyWw)
[![twitter](https://img.shields.io/static/v1?label=Twitter&message=FastRequest666&logo=twitter&color=FC8D34)](https://twitter.com/FastRequest666)
[![](https://badgen.net/badge/Github/fast-request/21D789?icon=github)](https://github.com/dromara/fast-request)
[![](https://img.shields.io/static/v1?label=Gitee&message=fast-request&color=FF318C&logo=gitee)](https://gitee.com/dromara/fast-request)
[![Jetbrains Plugins][plugin-img]][plugin]
![Version](https://img.shields.io/jetbrains/plugin/v/16988?logo=IntelliJ%20IDEA)
![Downloads](https://img.shields.io/jetbrains/plugin/d/16988?color=FE2857)
![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/r/rating/16988)
![GitHub](https://img.shields.io/github/license/dromara/fast-request?color=087CFA)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Restful%20Fast%20Request&logo=telegram&color=32CD32)](https://t.me/restful_fast_request)
[![OSCS Status](https://www.oscs1024.com/platform/badge/dromara/fast-request.svg?size=small)](https://www.oscs1024.com/project/dromara/fast-request?ref=badge_small)

[**Restful Fast Request**](https://plugins.jetbrains.com/plugin/16988) is the IDEA version of Postman,it is a powerful restful api toolkit plugin(http client) help you quickly generate url and params by exist method.`Plugin = API debug tool + API manager tool + API search tool`<br/>
It has a beautiful interface to compose requests,inspect server responses,store your api request and export api request.Plugin help you debug request just in Intellij frame

Compared with the HTTP Client, Restful Fast Request not only has the built-in functions of the HTTP Client, but also
provides a friendly, easy-to-understand and intuitive interface, which makes it more convenient and simple for users to
debug the API. At the same time, various types of parameters also provide different customization methods to make it
more flexible.And it integrates additional features such as search, Postman integration, Swagger support and so on.

> Supported framework
>
> > Spring framework (Spring MVC / Spring Boot)  
> > JAX-RS

> PURPOSE
>
> > The purpose of the plug-in is to simplify development and improve efficiency. Our vision is to become the best partner of IDEA, just like Contra's 1P, 2P, base-friend collocation, and double the efficiency.

# Document

- [中文文档](https://api-buddy.cn)
- [English Document](https://api-buddy.com/en)

## Features

- API navigate tree
- SearchEveryWhere for api
- APIs export to Postman
- Generate url and parameters in one click
- Send request to debug API(support to modify the generated parameters)
- Domain customization and switching
- Custom parameter parse
- String generation strategy
- Save api
- Management API (search filter, modify API name)
- One click location history save api to method
- Api to curl
- Automatically associate APIs to module groups
- Built-in Json parameter editor

> Send request
> ![example](./screenshot/example.gif)

> SearchEveryWhere for api
> ![example](./docs/.vuepress/public/img/searchEveryWhere.gif)

> Send and download
> ![example_download](./screenshot/downloadFile.gif)

> Api manage
> ![api manage](./screenshot/apis_hd.png)

> Api navigate
> ![apinav](./screenshot/apinav.gif)

> Api export to postman
> ![export2postman](./docs/.vuepress/public/img/export2postman.gif)

> Api list preview
> ![apiPreview](./docs/.vuepress/public/img/apiPreview.gif)

## Install

**using IDE plugin system**

- recommended <kbd>Preferences(Settings)</kbd> > <kbd>Plugins</kbd> > <kbd>Browse repositories...</kbd> > <kbd>type in"Restful Fast Request"</kbd> > <kbd>Install Plugin</kbd>

![](./screenshot/download.png)

Here’s the translated Markdown in English, with the links preserved as-is:

## Documentation Optimization
We welcome developers to help optimize the documentation content [api-buddy.com](api-buddy.com). All code for the documentation is located in the [docs](./docs) directory, implemented using the [vuepress-theme-hope](https://theme-hope.vuejs.press/zh/) theme.

[Recommended Environment](https://theme-hope.vuejs.press/get-started/env.html):

```
Node version: >20  
PNPM >= 10.5.2  
```  

To run the theme:

```
pnpm i  
pnpm docs:dev  
```  

After successful execution, you can check the access port in the logs (e.g., `localhost:8080`).

[latest-release]: https://github.com/dromara/fast-request/releases/latest
[plugin]: https://plugins.jetbrains.com/plugin/16988
[plugin-img]: https://img.shields.io/badge/plugin-Restful_Fast_Request-x.svg?logo=IntelliJ%20IDEA

## Contributors

[Mister-Hope](https://github.com/Mister-Hope), Author of [vuepress-theme-hope](https://vuepress-theme-hope.github.io/v2/)
