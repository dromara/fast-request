---
title: Teamwork
icon: teamwork
---

Version Required: <Badge text="2023.1.3" type="error"/>

## Principle

Based on version control systems, share **configurations** and **API data**.

## Features

- <Badge bgColor="#fe2857">No deployment required</Badge>
- <Badge bgColor="#087CFA">Privatisation</Badge>
- <Badge bgColor="#FE2857">Unlimited developers</Badge>
- <Badge bgColor="#FC801D">Unlimited APIs</Badge>
- <Badge bgColor="#6B57FF">Unlimited projects</Badge>
- <Badge bgColor="#3DEA62">Unlimited features</Badge>
- <Badge bgColor="#FDB60D">Offline work</Badge>
- <Badge bgColor="#21D789">Safe</Badge>

## How to use

Based on the principle, so when we need to share or pull someone else's configuration or API, assuming we use Git as our version control system, we can just **push** to share configuration and API data, and **pull** to get someone else's committed configuration and API data.

### Update configuration

After pull, click the Refresh Project Configuration button to get the configuration submitted by other members.

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)

## Update API

After pull, for example `Git pull` ,click the API Refresh button to get the APIs submitted by other members.

![refreshProjectConfig](/img/2023.1.3/refreshAPI.png)

## Local file storage structure

By default, the plugin will create a **.fastRequest** directory in the root directory of the project. APIs are stored in the **collections** directory, and configurations are stored in the **config** directory. APIs are stored in the structure of `module->class name->API`, and each directory contains a **directory.json** file to identify the directory.
Other APIs are stored using the **methodName.rapi**, and conflicts may occur in this file due to multiple modifications in version control systems. Users need to resolve the conflicts and meet the standard JSON and data structures.

Note that if you want to view the historical data of APIs, you can use the file history records of the version control system to restore them, or you can use the local file history records to restore them.

![teamDirectory](/img/2023.1.3/teamDirectory_en.png)

## Transfer historical data.

By default, the plugin will perform a historical data migration. During the data migration (for data on versions <= 2023.1.3), you do not need to worry about historical data if your data has not been committed to the version control system. If someone else has already committed the API to the version control system, it will be overwritten. Note that if you want to view API historical data, you can restore it by combining the file history records of the version control system or the local file.

If you find that the data has not been migrated, you can also manually operate it.

![transferData](/img/2023.1.3/transferData.png)
