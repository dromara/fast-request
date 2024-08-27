---
title: 脚本
icon: code-box-fill
headerDepth: 4
---

**脚本的作用**: 开发者可以利用 Groovy 脚本来自定义一些逻辑,从而使得 api 更加灵活,例如对参数进行动态加密签名,放入 header,而不是直接写死

**实现技术**: [Groovy](https://groovy-lang.org/)

看到 Groovy 不熟悉请==不要慌==,语法和 Java 几乎一致(Java 就是香,手动一个狗头)

## 脚本流程图

![](/img/script.svg)

## 测试项目

[https://github.com/kings1990/fast-request-samples](https://github.com/kings1990/fast-request-samples)

## 内置变量

::: caution 注意

开发者在自己编辑的脚本中,应该注意内置变量已被声明,而不是再去 new 一个 Request 或者 Response 对象,对象名是 request 或者 response,否则可能会造成不可预期的错误

:::

插件底层采用[hutool](https://hutool.cn/)的`cn.hutool.http.HttpRequest`来实现请求的发送

### request <Badge text="2022.2.3️" type="tip"/> <Badge text="废弃️" type="danger"/>

废弃: **使用 `rfr.request` 代替**

- 作用: 包含了请求相关的信息,例如 url、header、body、各种参数
- 类型: cn.hutool.http.HttpRequest

可以通过修改 request 值来动态调整参数

### response <Badge text="2022.2.3️" type="tip"/> <Badge text="废弃️" type="danger"/>

废弃: **使用 `rfr.response` 代替**

- 作用: 包含了请求响应的信息
- 类型: cn.hutool.http.HttpResponse

可以通过 response 值来获取响应的结果

### rfr <Badge text="2022.2.3️" type="tip"/>

- 作用: 包含了跟插件交互相关的一些属性,用于后期定制


#### rfr.request <Badge text="2024.1.7️" type="tip"/>

- 作用: 包含了请求相关的信息,例如 url、header、body、各种参数
- 类型: cn.hutool.http.HttpRequest

可以通过修改 request 值来动态调整参数

#### rfr.response <Badge text="2024.1.7️" type="tip"/>

- 作用: 包含了请求响应的信息
- 类型: cn.hutool.http.HttpResponse


#### rfr.projectHeader <Badge text="2022.2.3️" type="tip"/>

```
作用: 包含了项目级别头,可以通过修改来动态往UI里面设置值
类型: java.util.LinkedHashMap<String,String>
```

#### rfr.apiHeader <Badge text="2022.2.3️" type="tip"/>

```
作用: 包含了模块级别头,可以通过修改来动态往UI里面设置值
类型: java.util.LinkedHashMap<String,String>
```

#### rfr.currentProjectName <Badge text="2022.2.5️" type="tip"/>

```
作用: 当前下拉框选择的项目名
类型: java.lang.String
```

#### rfr.currentEnvName <Badge text="2022.2.5️" type="tip"/>

```
作用: 当前下拉框选择的环境名称
类型: java.lang.String
```

#### rfr.currentDomain <Badge text="2022.3.1️" type="tip"/>

```
作用: 当前作用的域名
类型: java.lang.String
```

#### rfr.currentModuleName <Badge text="2023.1.3️" type="tip"/>

```
作用: 当前API的模块名
类型: java.lang.String
```

#### rfr.environment <Badge text="2023.1.8" type="tip"/>

```
作用: 当前环境的变量
类型: java.util.LinkedHashMap<String,String>
```

## 引入第三方 Jar

::: tip 内置的 Jar

```
com.alibaba:fastjson:1.2.78
cn.hutool:hutool-all:5.8.5
com.google.guava:guava:30.1.1-jre
```

Jar 的版本会不定期更新,如果发现 bug 请联系作者要求作者升级

开发者可以利用以上 3 个 Jar 所提供的工具方法,直接在脚本中引用,不需要再依赖第三方的 Jar(hutool 就是香,已经加入许多工具类)

前往[Demo](#demo)查看如何获得[Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html)功能
:::

- [x] 引入 jar(maven 形式)

下载 jar 可能会需要花费额外的时间

```groovy
@Grab("org.apache.commons:commons-lang3:3.12.0")
import org.apache.commons.lang3.StringUtils

String debug = request.header("debug")
if(StringUtils.isNotBlank(debug)){
    //some logic
}
```

- [x] 引入 jar(通过本地 jar)

```groovy
//注意此处的file:前缀
this.class.classLoader.addURL(new URL("file:/path/to/jar"))
def StringUtils = Class.forName("org.apache.commons.lang3.StringUtils").getDeclaredConstructor().newInstance()

String debug = request.header("debug")//获取header
if(StringUtils.isNotBlank(debug)){
    //some logic
}
```

## 作用范围与顺序

分为`项目级别`和`单API级别`，项目全局作用需要点击 [<ColorIcon icon="quanjucanshu" /> 项目级别全局参数支持](./features/projectValueConfig.md) 进行配置

项目级别将作用项目中所有的 API，单 API 级别只作用于单个 API。

执行顺序：`项目级别`->`单API级别`

![scriptScope](/img/script.png)

## Console <Badge text="2022.2.5" type="tip"/>

Console 帮助开发者打印一些你想要的信息

```groovy
console.info("info")
console.print("print info")
console.warn("warn")
console.success("success")
console.error("error")

```

![console](/img/2022.2.5/console.png)

## Demo

::: tip Code completion支持

在项目中加入如下依赖(如果没有),即可在编辑器脚本中处理针对插件核心类的[Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html)功能(快速导入类(import)、得到方法提示等操作)

![](/img/2024.1.5/addLibrary.png)

:::

### 1. 签名参数

在你的本地新建一个 xxx.groovy 文件,粘贴以下代码,适当修改后,能保证本地可以正常运行,再把代码粘贴到脚本中

- <Badge text="注意最终脚本需要删除这行代码" type="danger"/>

~~HttpRequest request = HttpUtil.createPost("http://localhost:8081/book/add")~~

```groovy
import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.crypto.digest.DigestUtil
import cn.hutool.http.HttpRequest
import cn.hutool.http.HttpUtil

//脚本逻辑正式开始
String body = StrUtil.str(request.bodyBytes(), CharsetUtil.CHARSET_UTF_8)
body = "xxxx"//just for test
String sign = DigestUtil.md5Hex(body)
request.header("sign",sign)
```

### 2. 利用某一个请求的响应充当请求的 Header 参数

注意获取 token 的逻辑得结合接口返回的数据结构来处理。例如返回

```json
{
  "success": true,
  "code": 200,
  "data": {
    "token": "xxxxx"
  }
}
```

则需要这么写
`JSON.parseObject(myResponse.body()).getJSONObject("data").getString("token")`

```groovy
import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.crypto.digest.DigestUtil
import cn.hutool.http.HttpRequest
import cn.hutool.http.HttpResponse
import cn.hutool.http.HttpUtil
import com.alibaba.fastjson.JSON

HttpRequest myRequest = HttpUtil.createPost("http://localhost:8081/api/v1.0/login")
HttpResponse myResponse = myRequest.execute()
if(myResponse.isOk()){
    String token = JSON.parseObject(myResponse.body()).getString("token")
    request.header("token",token)
}
```

### 3. 设置一个Environment变量

假定响应报文格式

```json
{
  "success": true,
  "code": 200,
  "data": {
    "token": "xxxxx"
  }
}
```

在后置脚本中植入以下代码

```groovy
import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.crypto.digest.DigestUtil
import cn.hutool.http.HttpRequest
import cn.hutool.http.HttpResponse
import cn.hutool.http.HttpUtil
import com.alibaba.fastjson.JSON

HttpRequest myRequest = HttpUtil.createPost("http://localhost:8081/api/v1.0/login")
HttpResponse myResponse = myRequest.execute()
if(myResponse.isOk()){
    String token = JSON.parseObject(myResponse.body()).getJSONObject("data").getString("token")
    rfr.environment.put("token",token)
}
```

## 注意点

- Groovy 脚本对 ==;== 不敏感,Java 用;作为语句代码的结束,Groovy 用换行表示一句代码的结束
- 内置变量已被申明,例如 Demo2 中需要额外新建一个请求,变量命名需要注意不能与内置变量相同

## 脚本投稿 :star2:

为了将脚本做的更加强大,评论区开放社区脚本投递,欢迎将强大脚本通过**下方留言** 的形式上报,让更多人使用你开发的脚本

- 格式

```groovy
/**
 * 作者:Kings
 * 主页:https://github.com/kings1990
 * 功能:xxxxx
 */

//脚本内容
....
```

![scriptDonate](/img/2022.2.3/scriptDonate.png)
