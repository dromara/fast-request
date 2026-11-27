---
url: /guide/features/ai.md
---
# AI

::: tip

1. 如果请求因 API Key 无效、过期或配置不完整而失败，可以直接通过错误消息中的操作打开 AI 设置。
2. 建议开启自动优化，经过测试 DeepSeek deepseek-v4-flash 模型基本上花不了太多的 token
   :::

AI API Key 管理界面支持保存多套 AI 配置，并从列表中快速切换。每套配置可以单独设置：

* 服务商：OpenAI、智谱 AI、DeepSeek、Gemini 或其他兼容服务。
* 配置名称、API 地址、API Key 和模型。
* 请求超时时间。
* 参数值语言：自动跟随 API 上下文、中文或 English。
* 是否在生成 API 后自动执行[AI 参数智能优化](./aiParameterOptimization.md)。
* 是否启用当前配置。

同一时间只会启用一套配置。列表中的 `[ON]` 表示当前生效项；启用另一项后，原配置会自动停用。

切换服务商时会自动填充对应的默认 API 地址和模型，也可以选择 **Others** 接入兼容的自定义服务。点击 **Get api key** 可以前往相应服务商的 API Key 页面。

![AI 设置](/img/2026.1.1/aiSetting.png)
