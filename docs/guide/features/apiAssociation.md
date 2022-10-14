# API 分组自动关联

版本要求: <Badge text="2.0.6" /> <Badge text="废弃" />

api 分组是为了将保存的 api 保存到不同分组以便区分不同的 api,当处于多模块的项目情况下,支持扫描项目中的 module,并且快速得将 module 添加到分组中,这种方式会将分组添加到 root 下

**v2.1.2 保存 api 的时候自动创建 module group**

![apiGroup](../../.vuepress/public/img/apiGroup.gif)

::: tip 特别说明

1. 如果没有创建 module 分组,那么保存请求的时候会将保存的请求放入 Default Group
2. 如果创建 module 分组,那么保存请求的时候会自动根据当前 API 所处于的 module 自动归类到对应的 Module Group
3. 当然你可以通过拉拽的方式自行移动 API 到对应的更小的 group,并不冲突

:::

模块支持快速搜索(光标焦点放在列表上输入关键字即可)

![moduleSearch](../../.vuepress/public/img/moduleSearch.gif)
