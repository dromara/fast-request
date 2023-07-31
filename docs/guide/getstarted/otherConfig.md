---
title: 其他配置
icon: others1
---

控制器上的 url 固定变量可以由配置替换，例如类控制器上 url 写法如下:

```java
@RequestMapping("/api/${api-module}/user")
@Controller
public class XxxController(){
  //code ...
}
```

实际的 url 是 `/api/base/user`，那么可以通过以下配置来替换 `${api-module}` 变量

![other config](/img/otherConfig.png)
