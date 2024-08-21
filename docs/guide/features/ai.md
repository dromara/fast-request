---
---

# AI

版本要求: <Badge text="2024.1.4+" />

## 1.功能

![AI](/img/2024.1.4/ai.png)

为了将 AI 的回复更加精准，我们将 AI 的 设置更加精细化，加入了3个维度，加入了`概述`、`提问范例`、`提问范例结果`。此设计即让 AI 先训练一次问答，从而使得结果更加符合预期

我们可以在`概述`中尽量表述问题的各种可能，并在`提问范例`和`提问范例结果`中给于`概述`中描述的样本。

以下是一个让 AI 帮忙优化 JSON 报文的指令案例

::: code-tabs

@tab 概述

```
你是一位优秀的 JSON 处理程序。每当用户向您发送 JSON 时，您都应该以固定的 JSON 格式响应用户，并根据
JSON 键的含义将 JSON 字段中的值替换为人类可读的内容。您只需修改 JSON 中的值，而无需更改其结构。
```

@tab 提问范例

```
{"id":1,"title":"title_ahska","simpleDesc":"simpleDesc_ohga1","content":"content_yujga"}
```

@tab 提问范例结果

```
{
  "id": 1,
  "title": "哈利·波特与魔法石",
  "simpleDesc": "哈利·波特在霍格沃茨魔法学校展开惊心动魄的冒险，揭开危险的秘密并拯救有益的世界。"
}

```

:::

::: tip
编辑器中选中的值需要用 **${SELECTION}** 来替换，完整例子如下。

**${SELECTION}** 可以通过指令设置框中的{}图标快速生成
:::

![](/img/features/aiPromptExample.png)

## 2. Api key 获取与配置

1. [OpenAI](https://platform.openai.com/docs/api-reference/chat) 需要在生成后粘贴进 Api key 输入框
2. [CodeGeeX](https://codegeex.cn/) 只需要点击 Get api key 登录后即可自动补全
3. Custom OpenAI 即私有化部署的 OpenAI 服务，参考第 1 种方式获取 Api key

![](/img/2024.1.3/aiManagenemt.png)

## 3. 触发 AI 指令

![](/img/features/aiPromptTrigger.png)

选中编辑器中的内容以后，再点击指令。
