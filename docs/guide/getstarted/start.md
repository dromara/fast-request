---
title: 快速开始
icon: start1
---

```flow
st=>start: 开始
cfg=>parallel: 配置
op1=>subroutine: 配置项目名
op2=>subroutine: 配置环境名
op20=>operation: 设置域名
op21=>parallel: 返回主界面
op3=>operation: 选择项目名
op4=>operation: 选择环境名
op5=>inputoutput: 点击方法左侧火箭R字图标
op6=>operation: 点击发送按钮
e=>end: 结束|future
st(bottom)->cfg(path1,right)->op1(bottom)->op20->op21(path1,right)->op3->op5
st(bottom)->cfg(path2,left)->op2(bottom)->op20->op21(path2,bottom)->op4->op5
op5->op6->e
```

::: tip 使用入门

1. 点击配置添加项目名 (如微信卡片) 和环境名 (如 local、dev)，如果插件版本 >= **2023.1.1**，请参考[<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-domainConfig"></use></svg> 项目级别域名配置](../features/projectLevelDomainConfig.md)，结合配置文件快速添加域名配置
2. 设置对应项目及环境的域名
3. 打开工具窗口(右上角位置)，选择当前项目想要启用的环境
4. 点击在方法左侧的 fastRequest 的图标 <FontIcon icon="restfulFastRequest" /> (老图标<FontIcon icon="restfulFastRequest1" />)
5. 点击发送请求按钮发送请求

:::

![](/img/howToUse.gif)

新 Logo icon <FontIcon icon="restfulFastRequest" />

![](/img/newLogoIcon.png)

@include(@src/contact.snippet.md)
