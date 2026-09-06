---
url: /en/guide/features/script.md
---
# Script

Scripts allow developers to more flexibly, dynamically and easily modify some input parameters of the request process and the processing of responses.
Please refer to [Script](../script.md).

![scriptProject](/img/2022.2.3/scriptProject_en.png)

![scriptModule](/img/2022.2.3/scriptModule_en.png)

## Reference local project classes directly&#x20;

Scripts automatically load the compiled output and runtime dependencies of the module containing the current API. You can import local project classes and call their methods directly:

```groovy
import com.example.YourClass

def value = YourClass.someMethod()
console.info(value)
```

Compile the project before running the script so that the target class and its dependencies are available. You no longer need to specify an absolute path to `target/classes` through `FrLocalClassLoader`.

You can use the code snippet shortcut to generate this code. For more details, see the [script example](../script.md#_4-reference-a-project-class-directly).
