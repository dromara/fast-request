# Apifox 集成

版本要求: <Badge text="2024.1.4+" />

![apifoxIntegration](/img/2024.1.4/apifoxIntegration.png)

## 1. 配置 API access token

![apifoxGenerateApikey](/img/features/apifoxGenerateApikey.png)

参考 Apifox 官方文档 [Apifox 开放 API](https://apifox.com/help/openapi), 获取 API access token 的功能路径：`头像-账号设置- API 访问令牌`。

::: tip 有效期
需要注意的是，过了 Api access token的有效期，插件关于 Apifox 同步的功能将全部失效，所以建议在创建 Api access token 的时候设置为`无限期`
:::

## 2. 映射项目

![apifoxConfigProject](/img/features/apifoxConfigProject.png)

当我们正确设置好 Api access token 之后，需要对 IDEA 项目与 Apifox 的项目进行一个映射关联，点击刷新项目，当项目加载完毕以后，点开团队选项，勾选对应的项目即可。

## 3. 同步 Environment

![apifoxSyncEnvironment](/img/features/apifoxSyncEnvironment.png)

同步 Environment 会将项目的不同环境的域名、变量、全局参数全部同步至 Apifox，作为 Apifox 的服务参数、变量以及全局参数。这一步建议放在 API 同步之前**先执行**

## 4. 同步 API

![apifoxSyncApi](/img/features/apifoxSyncApi.png)

同步 API 需要先将 API 进行保存，可以手动触发，也可以自动触发，自动触发需要在 Apifox 同步设置中勾选`保存 API 后同步 Apifox`选项。

## 5.视频教程

[Bilibili视频教程](https://www.bilibili.com/video/BV1pM4m1Q7c5)
