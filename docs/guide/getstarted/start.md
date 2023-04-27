---
title: 快速开始
icon: start1
---

```mermaid
flowchart TB
A[开始] -->B(配置)
B -->D[配置环境名]
B -->E[配置环境名]
D --> F[设置域名]
E --> F[设置域名]
F-->G[返回主界面]
G-->H[选择项目名]
G-->I[选择环境名]
H-->J[点击方法左侧火箭R字图标]
I-->J[点击方法左侧火箭R字图标]
J-->K[点击发送按钮]
```

::: tip 使用入门

1. 点击配置添加项目名 (如微信卡片) 和环境名 (如 local、dev)，如果插件版本 >= **2023.1.1**，请参考[<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-domainConfig"></use></svg> 项目级别域名配置](../features/projectLevelDomainConfig.md)，结合配置文件快速添加域名配置
2. 设置对应项目及环境的域名
3. 打开工具窗口(右上角位置)，选择当前项目想要启用的环境
4. 点击在方法左侧的 fastRequest 的图标 <FontIcon icon="restfulFastRequest" /> (老图标<FontIcon icon="restfulFastRequest1" />)
5. 点击发送请求按钮发送请求

:::

![](/img/howToUse.gif)

新 Logo icon <FontIcon icon="restfulFastRequest" />

![](/img/newLogoIcon.png)

<!-- @include: @src/contact.snippet.md -->
