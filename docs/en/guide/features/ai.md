# AI

Version required: <Badge text="2024.1.4+" />

## 1. Introduce

![AI](/img/2024.1.4/ai_en.png)

In order to make AI's responses more accurate, we have made AI settings more refined and added 3 dimensions:
`Summary`、`Example question`、`Example question's answer`.
This design allows the AI to train a question and answer session so that the results are more in line with expectations.

We can try to express various possibilities of the question in the `Summary`, and the give an example in `Example question` and `Example question's answer`.

The following is a case of using AI to help optimize JSON messages

::: code-tabs

@tab Summary

```
You are an excellent JSON handler. Whenever a user sends you a JSON, you should respond to
the user in a fixed JSON formatand replace the values in the JSON fields with
human-readable content according to the meaning of the JSON keys. You will only modify
the values in the JSON without changing its structure.
```

@tab Example question

```json
{ "id": 1, "title": "title_ahska", "simpleDesc": "simpleDesc_ohga1" }
```

@tab Example question's answer

```json
{
  "id": 1,
  "title": "Harry Potter and the Philosopher's Stone",
  "simpleDesc": "Join Harry Potter on a thrilling adventure at Hogwarts School of Witchcraft and Wizardry as he uncovers dangerous secrets and saves the rewarding world."
}
```

:::

::: tip
The value selected in the editor needs to be replaced with **${SELECTION}**. The complete example is as following.

**${SELECTION}** can be quickly generated through the {} icon in the editor.

:::

![](/img/features/aiPromptExample_en.png)

## 2. Api key configuration

1. [OpenAI](https://platform.openai.com/docs/api-reference/chat) needs to be pasted into the Api key field after generation in web page.
2. [CodeGeeX](https://codegeex.cn/) need to click `Get api key` and log in. Plugin will complete the api key automatically.
3. Custom OpenAI refer to the first method to obtain the API key.

![](/img/2024.1.3/aiManagenemt_en.png)

## 3. Trigger AI prompt

![](/img/features/aiPromptTrigger.png)

After selecting the content in the editor, click the prompt line.
