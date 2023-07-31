---
---

# Project 级别域名配置

作用：本功能可以让开发者一键添加域名配置

版本要求: <Badge text="2023.1.1+"/>

::: danger 建议
建议使用项目级别域名配置，原来的[<ColorIcon icon="tool" /> 管理配置](./settingManager.md)将作为全配置配置，并且优先级小于项目级别配置，建议项目域名以项目级别隔离而不要使用全局配置。

使用项目级别配置的好处是当项目多的时候，下拉框选项将减少（排除了其他项目的配置)，只取本项目级别域名与全局域名配置的交集。
:::

使用：选择项目中的`application.properties`或者`application.yml`，通过配置文件，点击图标<ColorIcon icon="restfulFastRequest" />，可以快捷添加配置。

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig.png "域名配置")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog.png "域名列表")

::: tip 变量来源

1. 针对带有环境名如`application-dev.properties`，插件将自动适配`-`与`.`之间的变量作为环境名。
2. 项目名来源于模块名
3. 域名=`http://localhost:port/context-path`

:::
