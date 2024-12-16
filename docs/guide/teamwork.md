---
title: 团队协作
icon: teamwork
---

版本要求: <Badge text="2023.1.3"/>

## 原理

基于版本控制系统，共享**部分配置**以及**API 数据**。

## 特性

- <Badge bgColor="#fe2857">零部署</Badge>
- <Badge bgColor="#087CFA">私有化</Badge>
- <Badge bgColor="#FE2857">不限人数</Badge>
- <Badge bgColor="#FC801D">不限 API 数</Badge>
- <Badge bgColor="#6B57FF">不限项目数</Badge>
- <Badge bgColor="#3DEA62">不限功能</Badge>
- <Badge bgColor="#FDB60D">离线运转</Badge>
- <Badge bgColor="#21D789">安全</Badge>

## 如何使用

基于原理，所以当我们需要共享或者拉取别人的配置或者 API 时，假设我们利用 Git 作为我们的版本控制系统，我们只需要**push**即可共享配置和 API 数据，**pull**即可获取别人提交的配置和 API 数据。

### 更新配置

版本控制系统 pull 后，例如 `Git pull`，点击刷新项目配置按钮，即可获取其他成员提交的配置

![refreshProjectConfig](/img/2023.1.3/refreshProjectConfig.png)

## 更新 API

版本控制系统 pull 后，例如 `Git pull`，点击 API 刷新按钮，即可获取其他成员提交的 API

![refreshProjectConfig](/img/2023.1.3/refreshAPI.png)

## 本地文件存储结构

插件会默认在项目根目录下创建一个 **.fastRequest** 的目录，API 存储在 **collections** 目录下，配置存储在 **config** 目录下，API 按照 `模块->类名->API` 的结构进行存储，每一个目录下存放一个 **directory.json** 文件，用于标识该目录。
其他 API 用方法的**方法名.rapi**进行存储，该文件可能会在版本控制系统中因为多方修改产生冲突，最终需要用户解决冲突，并满足标准的 json 以及数据结构。

![teamDirectory](/img/2023.1.3/teamDirectory.png)

## 历史数据转移

插件默认会进行一次历史数据转移，数据转移的时候(版本<=2023.1.3 的数据)，如果你的数据在**版本管理系统**上未提交，则不需要关心历史数据。
如果版本管理系统其他人已提交 API，则会进行覆盖。 注意如果想看 API 历史数据，可以结合**版本控制系统**的文件**历史记录**来还原，也可以结合**本地文件历史记录**来还原。

如果发现数据未转移，也可手动操作！

![transferData](/img/2023.1.3/transferData.png)

## 文件权限

如果发现因为`Permission denied`导致的错误，执行以下指令

::: code-tabs#shell

@tab Linux

```bash
cd [current project root dir]
chmod -R 777 .fastRequest/*
```

@tab Windows

```bash
cd [current project root dir]
takeown /f /r /d n .fastRequest/*
```

:::
