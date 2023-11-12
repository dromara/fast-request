# Postman 同步

版本要求: <Badge text="2023.2.3" />

## Api key
Api key 用于使用 Postman 在线 API 执行一些手工行为.例如**创建Collection**、**创建目录**、**创建 Request** 等行为.

打开 [https://postman.co/settings/me/api-keys](https://postman.co/settings/me/api-keys) 并登录,并执行创建.注意最好是创建一个没有有效期的Api key.

![postmanApiKey](/img/2023.2.3/postmanApiKey.png)

## WorkspaceId
Workspace id 是 Postman 工作空间的Id. 有2中策略来创建Workspace

1. 每个项目来区分创建1Workspace
2. 创建1个Workspace表示某个公司的,不同的Collection来表示公司下面不同的项目

---

**获取WorkspaceId的步骤如下↓↓↓**

a. 创建一个 Workspace,已有则忽略

![createWorkspace](/img/2023.2.3/createWorkspace.png)

b. 点击 Workspace,并点击Workspace的info图标(注意不是点击Workspace Settings)

![showWorkspaceId](/img/2023.2.3/showWorkspaceId.png)

c.复制得到Workspace id

![workspaceInfo](/img/2023.2.3/workspaceInfo.png)