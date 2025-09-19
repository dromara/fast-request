---
title: FAQ
icon: faq
---

> 关键字含义
> <Badge text="S: 技巧" color="#087CFA" /> <Badge text="Q: 问题" color="#FE2857" /> <Badge text="A: 答案" color="#21D789" />
>
> ---
>
> 我们将持续更新开发者遇到的问题以及解决方案

[[toc]]

## S: 如何忽略 .fastRequest 目录

- 方式 1：在 IDEA 中不可见

有些开发者不希望看到`.fastRequest`目录，可以前往 idea 设置中的`File Types`，在`Ignore files and folders`中添加`.fastRequest`即可

![ignoreFastRequestDir](/img/faq/ignoreFastRequestDir.png)

- 方式 2：设置全局 gitignore

当你想要在 git 管理的项目中忽略`.fastRequest`目录，可以设置全局 gitignore 文件。

1. 创建全局 .gitignore 文件： 选择一个位置来存储全局 .gitignore 文件，例如在你的主目录中创建一个名为 .gitignore_global 的文件。

```shell
touch ~/.gitignore_global
```

2. 配置 Git 使用全局 .gitignore 文件： 使用 Git 命令将这个文件设置为全局 .gitignore 文件。

```shell
git config --global core.excludesFile ~/.gitignore_global
```

3. 编辑全局 .gitignore 文件： 在 ~/.gitignore_global 文件中添加你想要忽略的文件或目录模式。

```
.fastRequest
```

- 方式 3：针对已经提交到 git，但是后面想忽略该目录

``` shell
git rm --cached .fastRequest
git commit -m "rm ignore folder"
```
后面再参考方式 1 或者方式 2 进行忽略

## S: Slow operations are prohibited on EDT

报错`Slow operations are prohibited on EDT`相关错误。

在 SearchEveryWhere 中输入关键字 `ide.slow.operations.assertion` ,并将下图所示的关闭。

![slowOperationsConfig](/img/faq/slowOperationsConfig.png)

## S: 发送按钮被禁用,但是没有任何提示

需要手动开启通知消息。如果通知消息不开启,很多提示都不能被展现,所以需要配置 IDEA 的消息通知设置。其他情况类同处理。

需要前往 `Setting-> Appearance & Behavior->Nofications` 选中 `Display ballon notifications`和`Enable system notifications`. 如果`Don't ask again nofications`勾选了FastRequest,则需要移除条目。

![enableNotifications](/img/faq/enableNotifications.png)

## S: 超时时间设置

默认: 60 秒

点击插件设置按钮**管理配置**,设置ConnectTimeout和ReadTimeout的值。

![manageConfig](/img/manageConfig.png)

![timeout](/img/faq/timeout.png)

## S: 快捷添加域名

请参考 [<ColorIcon icon="domainConfig" /> 项目级别域名配置](./features/projectLevelDomainConfig.md)
(插件版本>=**2023.1.1**)

## S: Url 错误

![urlError](/img/skill/urlError.png)

<Badge text="解决办法" type="tip" />
1. 配置正确的域名，并启用项目和环境2个下拉框
2. 设置正确的url，例如需要将url上的变量{id}替换，可以在Path Param tab中替换变量

## S: 解析如何忽略某个字段

方式 1： 给字段使用如下 2 种注解中的一种

```
com.fasterxml.jackson.annotation.JsonIgnore

com.alibaba.fastjson.annotation.JSONField(serialize = false)
```

方式 2： 给字段增加 `static` 修饰符

方式 3：注释上加@fastRequestParseIgnore

```java
/**
 * xxx description
 * @fastRequestParseIgnore
 */
private String someIgnoreField ;
```

方式 4: 忽略字段名解析配置

![ignoreFiled](/img/2024.1.1/ignoreFiled.png)

只需要在配置中增加字段名，即可将实体类中的特定字段忽略生成

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

## S: 写脚本时得到提示

复制一下代码，完成脚本编辑后删除

```
import cn.hutool.http.HttpUtil

def request = HttpUtil.createGet("shouldremove")
def response = request.execute()
```

## S: API 文档同步

当你修改了你的接口参数，从 APIs 列表双击回来的 api 是你未更新前保存的 API，此时你想保证在线 API 文档是你修改后的，
需要再点一下 <ColorIcon icon="saveNew" />(保存) 或者左侧图标 <ColorIcon icon="restfulFastRequest" /> ,再进行同步。

建议：不需要提前在 Github、Gitee、Gitlab 创建仓库，插件自动会帮助创建，开发者只需要提供仓库名就行

## S: APIs 回显

针对保存后的 api，又进行了修改，那么你需要手动补参数并保存。如果你完全不想要之前的参数了，直接点击<ColorIcon icon="regenerate" />([重新生成](/guide/features/regenerate.md))

**请确保你每次修改参数后点击`保存`<ColorIcon icon="save" />按钮**

## S: 最佳视觉效果

调整工具窗口宽度至`610`+像素，达到最佳视觉效果

## S: 怀疑插件卡顿

<MyCarousel :imgList="['/img/faq/startCpuProfile.png','/img/faq/stopCpuProfile.png']" />

点击 Help 菜单选择 `Start CPU usage profile` -> `执行你认为卡顿的操作` -> `Stop CPU usage profile`。将生成出来的 jfr 文件发给我们分析

## Q: Origin等请求头失效

由于 `sun.net.www.protocol.http.HttpURLConnection` 设置了**受限标头**,以下一些请求头如果想使用,则需要给IDEA配置vm参数

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

点击 **help->Edit Custom Vm Options...**,在 **idea.vmoptions**(linux) 或者 **idea64.exe.vmoptions** (windows)中加入加入如下配置

```
-Dsun.net.http.allowRestrictedHeaders=true
```

## Q: 响应返回 Unexpected end of file from server

造成的原图大致如下

```
1. 网络连接丢失
2. 服务器决定关闭连接
3. 客户端和服务器之间的某些东西（nginx、路由器等）终止了请求
4. 服务端 api 需要代理但是 IDEA 没有配置,尤其是一些内网项目
```

IDEA配置代理: `Setting-> Appearance & Behavior->System Settings->HTTP Proxy`

## Q: Spring Get参数带数组/集合参数报 400错误

例如Url `http://localhost:8081/test?a[0].b[0].token=xxx&a[0].b[0].name=yyy`

加入如下配置

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

## Q: 操作按钮不可见

点击 `Options` 勾选 `Show Toolbar`

![](/img/faq/showToolbar.png)

## Q: 生成参数、跳转错乱

**A:** controller 代码里面不要出现方法名一样的方法

## Q: 左侧图标不显示

左侧图标<ColorIcon icon="restfulFastRequest" />不显示。

**A:** 打开配置 `setting->Editor->Gutter icons->show gutter icon`

**B:** 检查是否安装了 forestx 插件，该插件目前会引起 Fast Request 插件图标不展示

**C:** 复制该类内容删除该类并重新新建该类

## Q: 输入参数后调用 API 发现无效

**A:** 插件版本小于 2022.2.3 的在 table 控件编辑某个值的时候，需要在编辑完值后==先在空白处点一下==，再发送请求，该问题在 2022.2.3+版本得到修复

## Q: 为啥插件没反应

**A:** 请先完善配置，参考[快速开始](/guide/getstarted/start.md)，再点击图标

## Q: 点击图标后 idea 卡死

**A:** 你设计的实体类嵌套递归，插件不支持，比如说 A 实体类有 B 实体类的属性，B 实体类有 A 实体类的属性

```java
public class A {
    private B b;
    private int xx;
}
public class B {
    private A a;
    private String xx;
}
```

以上这种情况如果你不需要 B 属性，那么你可以在生成的时候手动给 B 加一个 static 属性

```java
public class A {
    private static B b;
    private int xx;
}
```

## S: APIs 丢失

非常抱歉，安装过 2023.1.3 版本的(已隐藏)会导致历史 apis 丢失，此时需要手动找回数据。==请不要回退插件版本==

0. 下载插件版本>=2023.1.3.2+的版本
1. 找到项目下的.idea 目录，右键并点击 Local history(本地历史记录)，找到关于`.idea/FastRequestCollection.xml`的变更日志(标题包含了 Deleting)，找到最后一个版本，把其中的内容拷贝到`.idea/fastRequest/fastRequestCollection.xml`中，
2. 重启 idea
3. 在 APIs tab 下点击图标<ColorIcon icon="dataTransfer" />并确认数据转移

![dataTransfer20231](/img/faq/dataTransfer202313.png)
