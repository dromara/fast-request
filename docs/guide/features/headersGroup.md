---
layout: RfrSlot
---

# Headers 分组

版本要求: <Badge text="2.0.7"/>

```
场景:SpringBoot等多模块项目不同项目、不同环境下头参数不同，为了快速自动切换headers，引入了header分组
操作方式：
1.在headers group里修改约束:输入的值必须是标准json格式
2.直接切换环境，然后再headers表格中输入对应的key、value值
```

![headerSwitch](/img/headerSwitch.gif)
