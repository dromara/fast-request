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

## S: Slow operations are prohibited on EDT

`Slow operations are prohibited on EDT` error.

Fill into the keywords `ide.slow.operations.assertion` in **SearchEveryWhere** and uncheck them like following.

![slowOperationsConfig](/img/faq/slowOperationsConfig.png)

## S: Send button is disabled, but there is no notification message

You need to manually turn on the notification message. If notification messages are not turned on, many messages cannot be displayed, so IDEA's message notification settings need to be configured. Other situations are handled similarly.

Go to `Setting-> Appearance & Behavior->Nofications` , check `Display ballon notifications` and `Enable system notifications`. If Fast Request is checked in `Don't ask again nofications`, this item needs to be removed.

![enableNotifications](/img/faq/enableNotifications.png)

## S: Timeout setting

Default: 60 s

Click the plug-in settings button **Manage Configuration** and set the values of Connect Timeout and Read Timeout.

![manageConfig](/img/manageConfig_en.png)

![timeout](/img/faq/timeout_en.png)

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

Way 3: Add `@fastRequestParseIgnore` in comment

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField;
```

Way 4: Ignore field name
![ignoreFiled](/img/2024.1.1/ignoreFiled_en.png)

Only need to add field names in the configuration to ignore target fields in the entity class.

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

## S: Best Visual Effects

Adjust tool window width to `610`+ pixel and will achieve the best visual effect

## S: How to ignore .fastRequest directory

Some developers don't want to `.fastRequest` directory exist in the project,
then you can go to idea settings `File Types`, add `.fastRequest` in `Ignore files and folders`

![ignoreFastRequestDir](/img/faq/ignoreFastRequestDir.png)

## Q: Header likes Origin not effect

Because the **restrictedHeaders** strategy of `sun.net.www.protocol.http.HttpURLConnection` . If you want to use the following headers, you need to configure vm parameters for IDEA

```java
private static final String[] restrictedHeaders = {
        /* Restricted by XMLHttpRequest2 */
        //"Accept-Charset",
        //"Accept-Encoding",
        "Access-Control-Request-Headers",
        "Access-Control-Request-Method",
        "Connection", /* close is allowed */
        "Content-Length",
        //"Cookie",
        //"Cookie2",
        "Content-Transfer-Encoding",
        //"Date",
        //"Expect",
        "Host",
        "Keep-Alive",
        "Origin",
        // "Referer",
        // "TE",
        "Trailer",
        "Transfer-Encoding",
        "Upgrade",
        //"User-Agent",
        "Via"
    };
```

Click **help->Edit Custom Vm Options...**,add the following config in **idea.vmoptions**(linux) or **idea64.exe.vmoptions**(windows)

```
-Dsun.net.http.allowRestrictedHeaders=true
```

## Q: Response return Unexpected end of file from server

```
1. Network connection was lost
2. The server decided to close the connection
3. Something in between the client and the server (nginx, router, etc) terminated the request
4. The server-side api requires a proxy but IDEA is not configured, especially for some intranet projects
```

How to set IDEA proxy: `Setting-> Appearance & Behavior->System Settings->HTTP Proxy`

## Q: Spring Get request with array/collection parameter reports 400 error

For example Url = `http://localhost:8081/test?a[0].b[0].token=xxx&a[0].b[0].name=yyy`

Add the following configuration:

```java
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }

}
```

## Q: Action buttons are not visible

Click `Options` and check `Show Toolbar`

![](/img/faq/showToolbar.png)

## Q: Generate parameters、jump error

**A:** Do not have methods with the same method name in the controller code

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
