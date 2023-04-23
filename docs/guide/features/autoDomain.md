# 自动域名

版本要求: <Badge text="2023.1.4" />

## 触发点
方法左侧点击<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg>的时候，如果没有配置域名，则自动创建一个。

## 项目名取值
自动生成的配置项目名取自当前API的模块名。

为什么呢？因为比方说项目下有2个模块`Module-1`、`Module-2`的API，如果`Module-1`的域名已存储，在`Module-2`下的API点击方法左侧点击<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg>的时候，
**老版本**做不到自动切换项目名。

==建议后期不要手动添加项目名==，因为插件无法找到映射关系，这容易导致API保存的时候本该是用`Module-2`的域名，结果用了`Module-1`的域名。

所以自动域名的另外一个好使是==API域名的自动切换==，如果没有则自动生成，如果有需改改进，开发者只需要修改域名值即可！插件将自动适配!


## 配置文件解析逻辑

1. 读取`application.yml`或者`bootstrap.yml`。如果找到配置`server.port`或者`server.servlet.context-path`，则取该配置。
2. 如果以上配置文件无法找到`server.port`或者`server.servlet.context-path`，则读取`spring.profiles.active`，作为变量`env`。接着寻找`application-env.yml`或者`bootstrap-env.yml`，寻找配置`server.port`或者`server.servlet.context-path`
3. properties文件逻辑类似
4. 以上配置无法找到则`port=8080，context-path=/`
5. 项目名称取自模块名
6. `spring.profiles.active`不支持Maven变量读取解析，例如`spring.profiles.active=@active.env@`
7. 针对历史已保存的api，插件不做自动域名处理；针对新的API，如果开关开启，将自动创建域名

## 配置开关
![autoDomain](/img/2023.1.4/autoDomain.png)


