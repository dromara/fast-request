---
title: Teamwork support
icon: teamwork
---

# Teamwork support

Version Required: <Badge text="2023.1.3" type="error"/>

## Principle
Based on version control systems, share configurations and all API data.

## Features
<span class="badge" style="vertical-align: middle;background: #21D789">No deployment required</span><br/>
<span class="badge" style="vertical-align: middle;background: #087CFA">Privatisation</span><br/>
<span class="badge" style="vertical-align: middle;background: #FE2857">Unlimited developers</span><br/>
<span class="badge" style="vertical-align: middle;background: #FC801D">Unlimited APIs</span><br/>
<span class="badge" style="vertical-align: middle;background: #6B57FF">Unlimited projects</span><br/>
<span class="badge" style="vertical-align: middle;background: #3DEA62">Unlimited features</span><br/>
<span class="badge" style="vertical-align: middle;background: #FDB60D">Offline work</span><br/>
<span class="badge" style="vertical-align: middle;background: #21D789">Safe</span><br/>

## How to use
Based on the principle, so when we need to share or pull someone else's configuration or API, assuming we use Git as our version control system, we can just **push** to share configuration and API data, and **pull** to get someone else's committed configuration and API data.

### Update configuration
After pull, click the Refresh Project Configuration button to get the configuration submitted by other members.

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)

## Update API
After pull, click the API Refresh button to get the APIs submitted by other members.

![refreshProjectConfig](/img/2023.1.3/refreshAPI.png)

## Local file storage structure
By default, the plugin creates a **.fastRequest** directory in the project root, the API is stored in the **collections** directory, the configuration is stored in the **config** directory, the API is stored in the `Module->Class-Name->API` structure, each directory stores a **directory. json** file in each directory, which identifies that directory.
Other APIs are stored using the method's **full class name + method name.json**. This file may create conflicts in the version control system due to multiple modifications, which will eventually require the user to resolve the conflicts and satisfy the standard json as well as the data structure.

Note that if you want to see the API history data, you can restore it in conjunction with the version control system's file history, or you can restore it in conjunction with the local file history.

![teamDirectory](/img/2023.1.3/teamDirectory_en.png)