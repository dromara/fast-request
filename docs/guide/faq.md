---
title: FAQ
icon: faq
---

> 关键字含义
> <Badge text="S: 技巧" color="#087CFA" /> <Badge text="Q: 问题" color="#FE2857" /> <Badge text="A: 答案" color="#21D789" />

[[toc]]

## S: Url 错误

![urlError](/img/skill/urlError.png)

<Badge text="解决办法" type="tip" />
1. 配置正确的域名，并启用项目和环境2个下拉框
2. 设置正确的url，例如需要将url上的变量{id}替换，可以在Path Param tab中替换变量

## S: 快速定位

获取到窗口或者弹出框焦点以后，输入需要搜索的字母，快读定位，然后使用`↑`箭头或者`↓`箭头进行跳跃定位

例如:

![apiDocExample](/img/apiPreview.gif)

## S: 多文件上传

输入多个值并将字段类型设置为 file

![multiFileUpload](/img/skill/multiFileUpload.png)

## S: Body 中想直接传 text

在 header 中加入**Content-Type: text/plain**

考虑使用[常用头参数](./features/commonHeader.md)快速添加

## S: 没有 Controller，如何发送请求

参考[临时请求](./features/tempRequest.md)

## S: API文档同步

当你修改了你的接口参数，从APIs列表双击回来的api是你未更新前保存的API，此时你想保证在线API文档是你修改后的，
需要再点一下 <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg>(保存) 或者左侧图标 <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg> ,再进行同步。

## S: APIs回显

针对保存后的api，又进行了修改，那么你需要手动补参数并保存。如果你完全不想要之前的参数了，直接点击<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-regenerate"></use></svg>([重新生成](/guide/features/regenerate.md))

> 请确保你每次修改参数后点击`保存`<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg>按钮

## Q: 输入参数后调用 API 发现无效

**A:** 插件版本小于==2022.2.3==的在 table 控件编辑某个值的时候，需要在编辑完值后==先在空白处点一下==，再发送请求，该问题再 2022.2.3+版本得到修复

## Q: 为啥插件没反应

**A:** 请先完善配置，参考[快速开始](/guide/getstarted/start.md)，再点击图标

## Q: 点击图标后 idea 卡死

**A:** 你设计的实体类嵌套递归，插件不支持，比如说 A 实体类有 B 实体类的属性，B 实体类有 A 实体类的属性

```java
public class A{
    private B b;
    private int xx;
}
public class B{
    private A a;
    private String xx;
}
```

以上这种情况如果你不需要 B 属性，那么你可以在生成的时候手动给 B 加一个 static 属性

```java
public class A{
    private static B b;
    private int xx;
}
```
