---
title: FAQ
icon: faq
---

> Keywords meaning
> <Badge text="S: Skill" color="#087CFA" /> <Badge text="Q: Question" color="#FE2857" /> <Badge text="A: Answer" color="#21D789" />
>
> ---
>
> We will constantly update the problems encountered by developers and the corresponding solutions.

[[toc]]

## S: Quickly add domain

Please refer [<ColorIcon icon="domainConfig" /> Project-level domain config](./features/projectLevelDomainConfig.md)
(Plugin version>=**2023.1.1**)

## S: APIs lost

So sorry to inform you that installing the 2023.1.3 version of (have been hidden) will cause loss of historical APIs, and you will need to manually retrieve the data. Here's what you need to do:

0. Download 2023.1.3.2+ version of plugin
1. Go to the .idea directory in your project, right-click and click on "Local history", find the change log about `.idea/FastRequestCollection.xml` (the title contains "Deleting"), and locate the last version. Copy the contents of this version and paste them into `.idea/fastRequest/fastRequestCollection.xml`
2. Restart IntelliJ IDEA.
3. Click on the icon <ColorIcon icon="dataTransfer" /> in the APIs tab and do transfer

![dataTransfer20231](/img/faq/dataTransfer202313.png)

## S: Url error

![urlError](/img/skill/urlError_en.png)

<Badge text="Solution" type="tip" />

1. Configure the correct domain name and enable the 2 drop-down boxes for the project and environment
2. Set the correct URL, for example, to replace the variable {id} on the URL, you can replace the variable in the Path Param tab

## S: How to ignore the field of the entity

Way 1: Use one of the following 2 annotations.

```
com.fasterxml.jackson.annotation.JsonIgnore

com.alibaba.fastjson.annotation.JSONField(serialize = false)
```

Way 2: Add the `static` modifier to the field.

Way 3: Add `@parseIgnore` in comment

```java
/**
 * xxx description
 * @parseIgnore
 */
private String someIgnoreField ;
```

## S: Quick locate

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

## S: Get code hints while writing scripts

Copy the code and delete it after finish the script

```
import cn.hutool.http.HttpUtil

def request = HttpUtil.createGet("shouldremove")
def response = request.execute()
```

## S: API doc sync

When you modify your method, the API returned from the APIs list is the API saved before you update it. At this time, if you want to ensure that the online API document is your modified one,
you need to click <ColorIcon icon="saveNew" />(save) or click <ColorIcon icon="restfulFastRequest" /> again and then sync api doc.

Recommendation: There is no need to create a repo in Github, Gitee, and Gitlab in advance. Plug-ins will automatically help create a repo. Developers only need to provide the repo name.

## S: APIs echo

For the saved API, you modify it again, and you need manually fill in the parameters and save <ColorIcon icon="saveNew" /> it. If you don't want the parameters you saved before at all, you can just click <ColorIcon icon="regenerate" />([Re generate](/guide/features/regenerate.md)).

> Please ensure that click `save button` <ColorIcon icon="saveNew" /> every time you modify api params.

# S: Best Visual Effects

Adjust tool window width to `610`+ pixel and will achieve the best visual effect

## Q: Action buttons are not visible

Click `Options` and check `Show Toolbar`
![](/img/faq/showToolbar.png)

## Q: Generate parameters、jump error

**A:** Do not have methods with the same method name in the controller code

## Q: Tab not displayed in SearchEveryWhere under new UI

**A:** Change the theme of IDEA to`Dark`. Steps：`Open SearchEveryWhere -> Fill in 'theme' -> Enter or confirm and switch the theme to Dark`. This is a bug caused by the IDEA theme.

![](/img/faq/searchEveryWhereError.png)

## Q: Left icon missing

Left icon <ColorIcon icon="restfulFastRequest" /> missing.

**A:** Open config `setting->Editor->Gutter icons->show gutter icon`

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
