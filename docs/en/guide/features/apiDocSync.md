# API document sync

Version required: <Badge text="2022.2.7+" />

## Introduction

Used to synchronize API documents to <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-github"></use></svg> Github、<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-gitee"></use></svg> Gitee、<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-gitlab"></use></svg> Gitlab,
and generate online documents and share them with other partners.

![apiSync](/img/2022.2.7/apiSync_en.png)

## Attention

- If there is no network in the intranet, please use self-host gitlab. gitlab only supports v4 and does not support other versions.
- If the configured repo does not exist in the cloud, a ==private== repo will be created by default.
- The project access has been 404. The solution is to set the repo as public or pull the partner to the repo to become a member when sharing.
- A project corresponds to synchronizing a repo. After determining a repo and synchronizing some documents, do not modify the repo later because history cannot be synchronized.

## Configuration

![apiSyncSetting](/img/2022.2.7/apiSyncSetting_en.png)

Note: The naming policy determines the name of the file synchronized to the cloud,The file name comes from the Javadoc or Swagger method naming annotation,If javadoc is checked,Please make sure that the Javadoc and Swagger method naming must be ==short==,
Otherwise, the file name in the cloud will be very long. In this case, consider checking the method name to synchronize

## Token

When check the permissions, you must ensure that the minimum permissions are checked to support the api sync and the token is within the valid time frame.

```
Github:must check 'repo'
Gitlab:must check 'api'
Gitee:must check 'projects'
```

![githubToken](/img/2022.2.7/githubToken.png)

![gitlabToken](/img/2022.2.7/gitlabToken.png)

![giteeToken](/img/2022.2.7/giteeToken.png)
