---
url: /en/guide/features/ai.md
---
# AI

Version required:&#x20;

## 2026.1.1 AI Settings Update&#x20;

AI API Key Management now stores multiple AI configurations and lets you switch between them from a list. Each configuration has its own:

* Provider: OpenAI, Zhipu AI, DeepSeek, Gemini, or another compatible service.
* Name, API endpoint, API key, and model.
* Request timeout.
* Value language: follow the API context automatically, Chinese, or English.
* Option to run [AI Parameter Optimization](./aiParameterOptimization.md) automatically after API generation.
* Enabled state.

Only one configuration is active at a time. `[ON]` identifies the active item; enabling another item disables the previous one automatically.

Selecting a provider fills its default API endpoint and model. Select **Others** for a compatible custom service. **Get api key** opens the provider's API key page.

![AI Settings](/img/2026.1.1/aiSetting_en.png)

::: tip
When a request fails because an API key is invalid or expired, or the configuration is incomplete, use the action in the error notification to open AI settings directly.
:::
