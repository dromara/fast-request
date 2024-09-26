---
title: Script
icon: code-box-fill
---

**Function**: Developers can use Groovy scripts to customize some logic, which makes the API more flexible, such as dynamically encrypting and signing parameters and putting them in the header rather than directly writing dead.

**Language**: [Groovy](https://groovy-lang.org/)

The groovy syntax is almost identical to Java.

## Script flow chart

![](/img/script_en.svg)

## Test Project

[https://github.com/kings1990/fast-request-samples](https://github.com/kings1990/fast-request-samples)

## Built-in variable

::: caution Notice

Developers should pay attention to the fact that the built-in variable has been declared in the script edited by themselves instead of creating a new Request or Response object. Otherwise, it may cause unexpected errors.

:::

Plugin use [hutool](https://hutool.cn/) `cn.hutool.http.HttpRequest` to send request

### request <Badge text="2022.2.3️" type="tip"/> <Badge text="Deprecated" type="danger"/>

**Please use `rfr.request` instead.**

- Remark: Contains request-related information, such as url, header, body, parameters
- Type: cn.hutool.http.HttpRequest

Parameters can be dynamically adjusted by modifying the request value.

### response <Badge text="2022.2.3️" type="tip"/> <Badge text="Deprecated" type="danger"/>

**Please use `rfr.response` instead.**

- Remark: Contains response information
- Type: cn.hutool.http.HttpResponse

You can get the result of the response through the response value.

### rfr <Badge text="2022.2.3️" type="tip"/>

- Remark: Contains some properties related to plugin interaction

#### request <Badge text="2024.1.7️" type="tip"/>

- Remark: Contains request-related information, such as url, header, body, parameters
- Type: cn.hutool.http.HttpRequest

Parameters can be dynamically adjusted by modifying the request value.

#### response <Badge text="2024.1.7️" type="tip"/>

- Remark: Contains response information
- Type: cn.hutool.http.HttpResponse

#### rfr.projectHeader <Badge text="2022.2.3️" type="tip"/>

```
Remark: Contains project-level headers, which can be modified to dynamically set values in the UI
Type: java.util.LinkedHashMap
```

#### rfr.apiHeader <Badge text="2022.2.3️" type="tip"/>

```
Remark: Contains module level headers, which can be modified to dynamically set values in the UI
Type: java.util.LinkedHashMap
```

#### rfr.currentProjectName <Badge text="2022.2.5️" type="tip"/>

```
Remark: The project name of the item currently selected in the drop-down box
Type: java.lang.String
```

#### rfr.currentEnvName <Badge text="2022.2.5️" type="tip"/>

```
Remark: The name of the environment currently selected in the drop-down box
Type: java.lang.String
```

#### rfr.currentDomain <Badge text="2022.3.1️" type="tip"/>

```
Remark: Get the currently active domain link
Type: java.lang.String
```

#### rfr.currentModuleName <Badge text="2022.3.1️" type="tip"/>

```
Remark: Get the currently module name of the API
Type: java.lang.String
```

## Import third jar

::: tip Built-in Jar

```
com.alibaba:fastjson:1.2.78
cn.hutool:hutool-all:5.8.5
com.google.guava:guava:30.1.1-jre
```

The version of Jar will be updated from time to time. If you find a bug, please contact up to upgrade.

Developers can use the tools and methods provided by the above three Jars to reference directly in the script without relying on third-party Jar.

Go to [Demo](#demo) to learn how to get [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html).

:::

- [x] Import jar(maven style)

Downloading the jar may take extra time.

```groovy
@Grab("org.apache.commons:commons-lang3:3.12.0")
import org.apache.commons.lang3.StringUtils

String debug = request.header("debug")
if(StringUtils.isNotBlank(debug)){
    //some logic
}
```

- [x] Import jar(by local jar)

```groovy
//Note the file: prefix here
this.class.classLoader.addURL(new URL("file:/path/to/jar"))
def StringUtils = Class.forName("org.apache.commons.lang3.StringUtils").getDeclaredConstructor().newInstance()

String debug = request.header("debug")//get header
if(StringUtils.isNotBlank(debug)){
    //some logic
}
```

## Scope and order

**Scope**: `Project-level` and `Single API level`，`Project-level` needs to click [<ColorIcon icon="quanjucanshu" /> Project-level config](./features/projectValueConfig.md).

`Project-level` will effort all APIs in the project, and `Single API level` only affects one API.

Execute order：`Project-level`->`Single API level`

![scriptScope](/img/script_en.png)

## Console <Badge text="2022.2.5" type="tip"/>

The console helps developers print some info you want.

```groovy
console.info("info")
console.print("print info")
console.warn("warn")
console.success("success")
console.error("error")

```

![console](/img/2022.2.5/console_en.png)

## Demo

::: tip Code completion support

Add the following dependency to the project (if there is none), then you can use [Code completion](https://www.jetbrains.com/help/idea/auto-completing-code.html) for the plugin's core classes in the editor (quickly importing classes, getting method hints, etc.).

![](/img/2024.1.5/addLibrary.png)

:::

### 1. Sign parameter

Create a new xxx.groovy file in your local idea, paste the following code. After appropriate modification, it can ensure that the local can run normally and then paste the code into the script.

- <Badge text="Note that the final script needs to remove this line of code" type="danger"/>

~~HttpRequest request = HttpUtil.createPost("http://localhost:8081/book/add")~~

```groovy
import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.crypto.digest.DigestUtil
import cn.hutool.http.HttpRequest
import cn.hutool.http.HttpUtil

//Script logic begins
String body = StrUtil.str(request.bodyBytes(), CharsetUtil.CHARSET_UTF_8)
body = "xxxx"//just for test
String sign = DigestUtil.md5Hex(body)
request.header("sign",sign)
```

### 2. Use the response of a request as the Header parameter of the request.

Note that the logic of obtaining the token must be handled in conjunction with the data structure returned by the http response. For example, if response returns

```json
{
  "success": true,
  "code": 200,
  "data": {
    "token": "xxxxx"
  }
}
```

It needs to be written like this

`String token = JSON.parseObject(myResponse.body()).getJSONObject("data").getString("token")`

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

### 3. Set an environment variable

If response returns

```json
{
  "success": true,
  "code": 200,
  "data": {
    "token": "xxxxx"
  }
}
```

You can add this code in the post-script

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

## Note

- Groovy scripts are not sensitive to `;`, Java uses `;` as the end of the statement code, Groovy uses a newline to indicate the end of a code
- The built-in variable has been declared. For example, in Demo 2, if an additional request needs to be created, the variable name needs to be noted that it cannot be the same as the built-in variable.

## Script contribute :star2:

In order to make the script more powerful, community script are welcome, and developers can donate the script in comments

- Format

```groovy
/**
 * Author:Kings
 * Main Page:https://github.com/kings1990
 * Function:xxxxx
 */

//Script begin
....
```

![scriptDonate](/img/2022.2.3/scriptDonate_en.png)
