# 自动域名

版本要求: <Badge text="2023.1.4" />

接口左侧点击<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg>的时候，如果没有配置域名，则自动创建一个。

## 配置文件解析逻辑

1. 读取`application.yml`或者`bootstrap.yml`。如果找到配置`server.port`或者`server.servlet.context-path`，则取该配置。
2. 如果以上配置文件无法找到`server.port`或者`server.servlet.context-path`，则读取`spring.profiles.active`，作为变量`env`。接着寻找`application-env.yml`或者`bootstrap-env.yml`，寻找配置`server.port`或者`server.servlet.context-path`
3. properties文件逻辑类似
4. 以上配置无法找到则`port=8080，context-path=/`
5. 项目名称取自模块名
6. `spring.profiles.active`不支持Maven变量读取解析，例如`spring.profiles.active=@active.env@`


## 配置开关
![autoDomain](/img/2023.1.4/autoDomain.png)
