---
title: 团队协作
icon: teamwork
---

版本要求: <Badge text="2023.1.3"/>

## 购买优惠
**团队人数>=6**，可联系作者，酌情打折。同时也欢迎各位开发者在团队中推荐Restful Fast Request，谢谢!

## 原理
基于版本控制系统，共享配置以及所有的API数据。

## 特性
<span class="badge" style="vertical-align: middle;background: #21D789">零部署</span><br/>
<span class="badge" style="vertical-align: middle;background: #087CFA">私有化</span><br/>
<span class="badge" style="vertical-align: middle;background: #FE2857">不限人数</span><br/>
<span class="badge" style="vertical-align: middle;background: #FC801D">不限API数</span><br/>
<span class="badge" style="vertical-align: middle;background: #6B57FF">不限项目数</span><br/>
<span class="badge" style="vertical-align: middle;background: #3DEA62">不限功能</span><br/>
<span class="badge" style="vertical-align: middle;background: #FDB60D">离线运转</span><br/>
<span class="badge" style="vertical-align: middle;background: #21D789">安全</span><br/>

## 如何使用
基于原理，所以当我们需要共享或者拉取别人的配置或者API时，假设我们利用Git作为我们的版本控制系统，我们只需要**push**即可共享配置和API数据，**pull**即可获取别人提交的配置和API数据。

### 更新配置
版本控制系统pull后，点击刷新项目配置按钮，即可获取其他成员提交的配置

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)

## 更新API
版本控制系统pull后，点击API刷新按钮，即可获取其他成员提交的API

![refreshProjectConfig](/img/2023.1.3/refreshAPI.png)

## 本地文件存储结构
插件会默认在项目根目录下创建一个 **.fastRequest** 的目录，API存储在 **collections** 目录下，配置存储在 **config** 目录下，API按照 `模块->类名->API` 的结构进行存储，每一个目录下存放一个 **directory.json** 文件，用于标识该目录。
其他API用方法的**类全名+方法名.json**进行存储，该文件可能会在版本控制系统中因为多方修改产生冲突，最终需要用户解决冲突，并满足标准的json以及数据结构。

注意如果想看API历史数据，可以结合版本控制系统的文件历史记录来还原，也可以结合本地文件历史记录来还原。

![teamDirectory](/img/2023.1.3/teamDirectory.png)