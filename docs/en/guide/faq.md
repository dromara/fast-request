---
title: FAQ
icon: faq
---

> Keywords meaning
> <Badge text="S: Skill" color="#087CFA" /> <Badge text="Q: Question" color="#FE2857" /> <Badge text="A: Answer" color="#21D789" />

[[toc]]

## S: Url error

![urlError](/img/skill/urlError_en.png)

<Badge text="Solution" type="tip" />

1. Configure the correct domain name and enable the 2 drop-down boxes for the project and environment
2. Set the correct URL, for example, to replace the variable {id} on the URL, you can replace the variable in the Path Param tab

## S: Fast position

After getting the focus of the window or pop-up box, enter the letters to position, then use the `↑` arrow or the `↓` arrow for jump position

For example:

![apiDocExample](/img/apiPreview.gif)

## S: Multi-file upload

Enter multiple values and set the field type to file

![multiFileUpload](/img/skill/multiFileUpload.png)

## S: Pass text/plain param in body

Please add **Content-Type: text/plain** in the header

Consider using add header by [`Common header`](./features/commonHeader.md)

## S: No controller, how to send a request

Refer to [Temporary request](./features/tempRequest.md)

## S: API doc sync

When you modify your method, the API returned from the APIs list is the API saved before you update it. At this time, if you want to ensure that the online API document is your modified one,
you need to click <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg>(save) or click <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg> again and then sync api doc.

Recommendation: There is no need to create a repo in Github, Gitee, and Gitlab in advance. Plug-ins will automatically help create a repo. Developers only need to provide the repo name.

## S: APIs echo

For the saved API, you modify it again, and you need manually fill in the parameters and save <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg> it. If you don't want the parameters you saved before at all, you can just click <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-regenerate"></use></svg>([Re generate](/guide/features/regenerate.md)).

> Please ensure that click `save button` <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg> every time you modify api params.

## Q: After entering the parameters, the API call found that the parameters were invalid

**A:** Plugin version less than ==2022.2.3==, when editing a value in the table, you need to ==click in the blank space== after editing the value, then send the request. The bug has been fixed in the 2022.2.3+ version.

## Q: Why the plugin doesn't respond

**A:** Please configure the relevant configuration according to the steps introduced in the first chapter first, and then click the icon.

## Q: Idea freezes after clicking the fastRequest icon

**A:** :The entity class you designed is nested and recursive, the plugin does not support.

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

If you don't need the B property in the above case, then you can manually add a static keywords to property B when generating

```java
public class A{
    private static B b;
    private int xx;
}
```
