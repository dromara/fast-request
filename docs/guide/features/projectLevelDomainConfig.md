---
---

# Project 级别域名配置

作用：本功能可以让开发者一键添加域名配置

版本要求: <Badge text="2023.1.1+"/>

::: caution 建议
建议使用项目级别域名配置，原来的[<ColorIcon icon="tool" /> 管理配置](./settingManager.md)将作为全配置配置，并且优先级小于项目级别配置，建议项目域名以项目级别隔离而不要使用全局配置。

使用项目级别配置的好处是当项目多的时候，下拉框选项将减少（排除了其他项目的配置)，只取本项目级别域名与全局域名配置的交集。
:::

::: tip

1. 项目名: Api 对应的项目名称。如果开启了自动域名，单模块项目，项目名将自动设置为项目名词，多模块则为模块名。不建议手动替换
2. 环境名: 不同的环境，例如将本地命名为 local，开发环境命名为 dev，测试环境命名为 test,生产环境命名为 prod
3. 域名: 项目名与环境名下对应的域名

:::

## 自动域名 or 配置文件快速生成

1. 自动域名：参考此[文档](./autoDomain.md)，最后根据实际情况只需要修改域名值

2. 配置文件快速生成：选择项目中的`application.properties`或者`application.yml`，通过配置文件，点击图标<ColorIcon icon="restfulFastRequest" />，可以快捷添加配置。

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig.png "域名配置")

> 变量来源

```
a. 针对带有环境名如`application-dev.properties`，插件将自动适配`-`与`.`之间的变量作为环境名。
b. 项目名来源于模块名
c. 域名=`http://localhost:port/context-path`
```

## 2.手动配置域名

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog.png "域名列表")

点击项目级别域名手动输入对应的值
