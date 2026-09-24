---
url: /guide/features/aiParameterOptimization.md
---
# AI 参数智能优化&#x20;

::: tip
**建议开启自动优化，经过测试 DeepSeek deepseek-v4-flash 模型基本上花不了太多的 token**
:::

AI 参数智能优化会结合 API 路径、参数名、类型和字段说明，将随机生成的参数替换为更符合真实业务语义的示例值。优化结果会自动应用，同时支持撤销。

## 使用前配置

先在 **AI API Key 管理**中配置可用的 AI 服务、API Key 和模型。详细配置方式请参考 [AI](./ai.md)。

如果 API Key 无效、过期或 AI 服务配置不完整，错误消息中可以直接进入 AI 设置进行修正。

![AI 设置](/img/2026.1.1/aiSetting.png)

## 手动优化

以下参数编辑区支持 **AI 参数智能优化**：

* Path Param
* URL Param
* Form URL-Encoded
* Form-data
* Raw JSON 请求体
* RunMethod 参数

点击参数区域工具栏中的 AI 按钮即可优化当前参数。执行期间，窗口底部会显示进度条。

![AI 参数智能优化](/img/2026.1.1/aiParameterOptimization.png)

## 生成 API 后自动优化

在 **AI API Key 管理**中启用 **生成 API 后自动优化参数**。之后每次生成 API，插件都会先生成常规参数，再调用 AI 优化并自动应用结果。

自动优化不是一次性状态：连续生成不同 API，或再次生成当前 API，都会按本次生成结果重新触发。

## 自动应用与撤销

AI 优化完成后会弹出结果消息，点击 **撤销**可以恢复本次被 AI 修改的值。

撤销只恢复仍保持 AI 优化结果的字段。如果优化后又手工修改了某个字段，该字段不会被撤销覆盖，从而避免丢失后续编辑。

## 与批量生成联动

启用自动优化后，[批量生成 API](./batchGenerateApi.md)也会逐个优化参数：

![ai 自动优化](/img/2026.1.1/aiAutoOptimization.png)

* 单个 API 优化失败不会影响后续 API。
* 优化失败的 API 仍会保留普通生成的参数并继续保存。
* 完成消息会分别统计生成、AI 优化和在线文档同步结果。
* 批量优化结果同样支持一次撤销。

如果同时启用了[在线文档自动同步](./onlineDocument.md)，文档中的示例值将使用 AI 优化并保存后的最终结果。
