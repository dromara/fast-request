# Postman 同步

版本要求: <Badge text="2023.2.3" />

## Api key
Api key 用于使用 Postman 在线 API 执行一些手工行为.例如**创建Collection**、**创建目录**、**创建 Request** 等行为.

打开 [https://postman.co/settings/me/api-keys](https://postman.co/settings/me/api-keys) 并登录,并执行创建.注意最好是创建一个没有有效期的Api key.

![postmanApiKey](/img/2023.2.3/postmanApiKey.png)

## WorkspaceId
Workspace id 是 Postman 工作空间的Id. 有2中策略来创建Workspace

1. 每个项目来区分创建1个Workspace(推荐)
2. 创建1个Workspace表示某个公司的,不同的Collection来表示公司下面不同的项目

---

**获取WorkspaceId的步骤如下↓↓↓**

a. 创建一个 Workspace,已有则忽略

![createWorkspace](/img/2023.2.3/createWorkspace.png)

b. 点击 Workspace,并点击Workspace的info图标(注意不是点击Workspace Settings)

![showWorkspaceId](/img/2023.2.3/showWorkspaceId.png)

c.复制得到Workspace id

![workspaceInfo](/img/2023.2.3/workspaceInfo.png)

## Initial value 与 Current value
Environment 中变量的值包含 **Initial value** 和 **Current value**,**Initial value**是云端共享的,与之对应的**Current value**仅保留在本地.
Postman中实际Request使用的是**Current value**.

但是,==**Restful Fast Request在对 Environment 同步的时候同步的是 Initial value**==.

所以如果插件中更新了 Environment 值,并且同步到了 Postman ,此时在 Postman 中执行 Request 并不会立刻使用同步的值,
而是需要在 Environment 中手工替换或者全部替换(点击Reset All).

![workspaceInfo](/img/2023.2.3/resetEnvironmentValue.png)

## 前后置脚本
因为语言的不同,插件并不会同步前后置脚本.所以在使用插件的时候最好把一些共用的脚本写在[项目级别配置](./projectValueConfig.md)中的前后置脚本,这样子也只需要在同步到Postman以后,
在Postman中的Collection级别下写前后置脚本.

![postmanScript](/img/2023.2.3/postmanScript.png)
