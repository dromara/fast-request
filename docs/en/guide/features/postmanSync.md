# Postman Sync

Version required: <Badge text="2023.2.3" />

::: tip
To improve the data synchronized to postman, it is recommended to select `sync after api save` option in the postman config of the plugin
then trigger the synchronization by save the api.

It is also recommended to use this function to synchronize the API instead of exporting
:::

## Api key

API key is used to perform manual actions using the Postman Online API. These actions include **creating collections**, **creating directories**, **creating requests**, and more.

Open [https://postman.co/settings/me/api-keys](https://postman.co/settings/me/api-keys) and login, and then generate an API key. It is recommended to create an API key without an expiration date.

![postmanApiKey](/img/2023.2.3/postmanApiKey.png)

## WorkspaceId

The Workspace ID is the identifier for a Postman workspace. There are 2 strategies to create a workspace.

1. Create one workspace for each project.(Recommended)
2. Create one workspace to represent a company, and use different collections to represent different projects within the company.

---

**Get WorkspaceId using the following steps↓↓↓**

1. Create a workspace (Ignore if exist)

![createWorkspace](/img/2023.2.3/createWorkspace.png)

2. Click Workspace and click the Workspace info icon (Note that it is not click "Workspace Settings")

![showWorkspaceId](/img/2023.2.3/showWorkspaceId.png)

3. Copy Workspace id

![workspaceInfo](/img/2023.2.3/workspaceInfo.png)

## Initial value & Current value

Variable in Environment contains **Initial value** and **Current value**,**Initial value** is shared with your team,but **Current value** only store in your local.
Request will finally use **Current value**.

==**Restful Fast Request in fact sync the Initial value of Environment**==.

So, if the environment values are updated in the plugin and sync successfully to Postman,
when executing a request, Postman will not immediately use the synchronized values.
Instead, you will need to manually replace the values in the environment tab
or perform a complete replacement by clicking on "Reset All".

![workspaceInfo](/img/2023.2.3/resetEnvironmentValue.png)

## Pre and Post script

Due to language differences, the plugin does not synchronize pre-request and post-request scripts.
Therefore, when using the plugin, it is recommended to write common scripts in the [project-level config](./projectValueConfig.md).
This way, after synchronizing with Postman, you only need to write pre-request and post-request scripts at the collection level in Postman.

![postmanScript](/img/2023.2.3/postmanScript.png)

## Note

1. It is recommended to check the option "Sync to Postman after saving". If not checked, you need to save manually before triggering and then click on the Sync button.
2. For teamwork, if multiple identical collections appear in the same workspace, you only need to manually replace the collection ID in the configuration with one of them.
3. For teamwork, if multiple identical folders appear in the same collection, you can use the `pmFolderId` property in the `.fastRequest/collections/ROOT/moduleName/className/directory.json` file from one of them.
4. For teamwork, if the same API exists twice in Postman, you can ensure consistency by modifying the `pmRequestId` and `pmResponseId` of one of the data. You can use [<ColorIcon icon="storeData" /> Navigate current method stored data](./navigateCurrentMethodJson.md) to locate the stored file. The best way is to use Git to manage and commit the `rapi` files to keep the underlying storage data consistent.
