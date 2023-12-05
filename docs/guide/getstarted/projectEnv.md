---
title: 项目/环境/域名
icon: config2
---

::: caution 建议
建议使用[<ColorIcon icon="domainConfig" /> 项目级别域名配置](../features/projectLevelDomainConfig.md)，原来的配置将作为全配置配置，并且优先级小于项目级别配置。

使用项目级别配置的好处是当项目多的时候，下拉框选项将减少（排除了其他项目的配置)，只取本项目级别域名与全局域名配置的交集。
:::

设置映射关系，配置对应项目下对应环境的 url 前缀地址

![project env url](/img/projectEnvUrl.png)

::: note

- 图标 P 含义为 Project
- 图标 E 含义为 Environment

:::

![dropdownProject](/img/dropdownProject.png)

![dropdownEnv](/img/dropdownEnv.png)
