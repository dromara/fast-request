# Environment

版本要求: <Badge text="2023.1.8" />

Environment中可以设置存储一些变量，并且在其他地方使用`{{key}}`来替换固定值或者魔法值。

## 定义
Environment的Tab值取自项目域名配置中的`环境`，key需要满足正则表达式`^[a-zA-Z_$][a-zA-Z0-9_$]*$`

![environment](/img/2023.1.8/environment.png)

::: tip 小技巧
聚焦表格后，可以输入key值来快速定位行
:::


## 使用
在Value中直接输入`{{key}}`来替换固定值或者魔法值，当替换后，如果Environment中包含此key则会绿色高亮。鼠标悬浮500ms后可以显示当前的Environment值

![useEnvironmentVariable](/img/2023.1.8/useEnvironmentVariable.png)

也可以选中**单行**，并点击<ColorIcon icon="environment" />从列表从选择。

![chooseEnvironmentVariable](/img/2023.1.8/chooseEnvironmentVariable.png)

::: tip 小技巧
聚焦表格后，可以输入key值来快速定位行
:::

## 脚本
参考 [脚本 -> demo -> 设置一个Environment变量](../script.md#demo)

![environmentScript](/img/2023.1.8/environmentScript.png)