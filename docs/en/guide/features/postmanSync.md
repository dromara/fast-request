# Postman Sync

Version required: <Badge text="2023.2.3" />

## Api key
API key is used to perform manual actions using the Postman Online API. These actions include **creating collections**, **creating directories**, **creating requests**, and more.

Open [https://postman.co/settings/me/api-keys](https://postman.co/settings/me/api-keys) and login, and then generate an API key. It is recommended to create an API key without an expiration date.

![postmanApiKey](/img/2023.2.3/postmanApiKey.png)

## WorkspaceId

The Workspace ID is the identifier for a Postman workspace. There are 2 strategies to create a workspace.


1. Create one workspace for each project to differentiate.
2. Create one workspace to represent a company, and use different collections to represent different projects within the company.

---

**Get WorkspaceId using the following steps↓↓↓**

1. Create a workspace (Ignore if exist)

![createWorkspace](/img/2023.2.3/createWorkspace.png)

2. Click Workspace and click the Workspace info icon (Note that it is not click "Workspace Settings")

![showWorkspaceId](/img/2023.2.3/showWorkspaceId.png)

3. Copy Workspace id

![workspaceInfo](/img/2023.2.3/workspaceInfo.png)