---
url: /guide/features/script.md
---
# 脚本

脚本可以让开发者更加灵活地、动态地、方便地修改请求过程的一些入参，以及响应的处理\
具体请参考-------> [脚本](../script.md)

![scriptProject](/img/2022.2.3/scriptProject.png)

![scriptModule](/img/2022.2.3/scriptModule.png)

## 直接引用本地项目类&#x20;

Script 会自动加载当前 API 所属模块的编译输出和运行时依赖，因此可以直接导入本地项目类并调用类中的方法：

```groovy
import com.example.YourClass

def value = YourClass.someMethod()
console.info(value)
```

使用前请先编译项目，确保目标类及其依赖已生成。无需再通过 `FrLocalClassLoader` 指定 `target/classes` 的绝对路径。

可以点击代码片段快捷方式快速生成以上代码。更多用法请参考[脚本示例](../script.md#_4-直接引用项目中的类)。
