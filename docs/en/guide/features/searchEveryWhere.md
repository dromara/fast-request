# SearchEveryWhere

Search for APIs by URL, HTTP method, or API description.

```
Example:
/url              (search by url)
get /list         (search by get method and url)
post /save        (search by post method and url)
description keywords  (search by keywords)
```

![searchEveryWhere](/img/2023.1.7/searchEveryWhere.png)

## Search URLs with a context path <Badge text="2026.1.1" type="info"/>

When a search path contains prefixes such as a `context-path` or gateway path that are not declared in the Controller mapping, SearchEveryWhere automatically tries matching path suffixes against APIs in the project. No truncation configuration is required.

For example, searching for `/gateway/user-service/users/{id}` can match the Controller mapping `/users/{id}`. The same behavior applies when an HTTP method is included, such as `GET /gateway/user-service/users/{id}`, while method filtering is preserved.
