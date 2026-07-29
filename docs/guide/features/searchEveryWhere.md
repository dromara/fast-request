---
---

# SearchEveryWhere

支持通过 URL、HTTP 方法或接口描述快速搜索 API。

```
输入案例
/url              (查询指定url)
get /list         (指定get方式指定url)
post /save        (指定post方式指定url)
方法关键字          (查询指定描述)
```

![searchEveryWhere](/img/2023.1.7/searchEveryWhere.png)

## 搜索带 context-path 的 URL <Badge text="2026.1.1" type="info"/>

当搜索路径包含 `context-path`、网关路径等前缀，而 Controller 中声明的路径不包含这些前缀时，SearchEveryWhere 会自动尝试使用路径后缀匹配项目中的 API，无需额外配置截断片段。

例如，搜索 `/gateway/user-service/users/{id}` 时，可以匹配 Controller 中声明的 `/users/{id}`。带 HTTP 方法搜索同样支持该能力，例如 `GET /gateway/user-service/users/{id}`，并且仍会按照 HTTP 方法过滤结果。
