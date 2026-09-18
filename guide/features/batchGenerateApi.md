---
url: /guide/features/batchGenerateApi.md
---
# 批量生成 API&#x20;

批量生成 API 可以一次扫描一个 Controller 类，或一个包及其子包中的 Controller，并将发现的 API 生成后直接保存到 Fast Request。

## 按类批量生成

在 Java/Kotlin Controller 文件中的任意位置右键，选择 **批量生成并保存 API**。无需将光标放在类名或具体方法上。

该操作会生成并保存当前类中的全部 API。

![按类批量生成 API](/img/2026.1.1/batchGenerateClassApi.png)

## 按包批量生成

在 Project 视图中右键生产源码包，选择菜单顶部的 **批量生成并保存包内 API**。插件会递归扫描当前包及其子包中的 Java/Kotlin Controller；测试源码和非生产源码不会被包含。

![按包批量生成 API](/img/2026.1.1/batchGeneratePackageApi.png)

## 确认与冲突处理

执行前会展示本次作用范围、Controller 数量、API 数量、项目、环境和域名，并要求选择已有 API 的处理方式：

* **跳过已有 API**：只创建尚未保存的 API。
* **覆盖 Default 参数**：更新已有 API 的 Default 参数组。
* **取消**：不执行本次任务。

## 执行结果

任务在后台逐个处理 API，并显示进度。某个 API 生成、AI 优化或文档同步失败时，会记录该项错误并继续处理下一个 API，不会中断整批任务。

完成消息会汇总创建、更新、跳过和失败数量，并附带失败详情。

## AI 优化与在线文档

* 启用[生成 API 后自动优化参数](./aiParameterOptimization.md)后，每个 API 保存前都会进行 AI 参数智能优化；失败时回退到普通生成值。
* 启用[保存请求后自动同步在线文档](./onlineDocument.md)后，每个成功保存的 API 都会同步到在线文档，示例值使用最终保存结果。

::: tip
包内 API 较多时，建议先确认当前环境和域名配置，并根据是否需要保留已有参数选择合适的冲突策略。
:::
