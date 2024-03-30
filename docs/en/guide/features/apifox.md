# Apifox integration

Version required: <Badge text="2024.1.4+" />

![apifoxIntegration](/img/2024.1.4/apifoxIntegration_en.png)

## 1. API access token configuration

![apifoxGenerateApikey](/img/features/apifoxGenerateApikey_en.png)

Setting path: `Avatar -> Account Setting-> API Access Token`。

::: tip Expiration
Note that after the validity period of the Api key, the plugin's Apifox synchronization function will no longer work, 
so it is recommended to check `no Expriation` when creating the Api Access token.
:::

## 2. Mapping project

![apifoxConfigProject](/img/features/apifoxConfigProject_en.png)

After we have correctly set the Api access token, we need to make a mapping association between the IDEA project 
and the Apifox project. Click to refresh the project. When the project is loaded, click on the team option and 
check the target project.

## 3. Environment sync
![apifoxSyncEnvironment](/img/features/apifoxSyncEnvironment_en.png)

Environment sync function will synchronize all domain names, variables, and global parameters of the project to Apifox 
as service parameters, variables, and global parameters. It is recommended to execute this step before **API sync**


## 4. API sync
![apifoxSyncApi](/img/features/apifoxSyncApi_en.png)

To synchronize the API, you need to save the API first. It can be triggered manually or automatically. 
For automatic triggering, you need to check the `Sync after save` option in the Apifox sync setting.