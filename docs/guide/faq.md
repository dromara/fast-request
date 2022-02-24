---
title: FAQ
icon: faq
---

[[toc]]

## 为什么要创建Module group
1. 如果没有创建module分组,那么保存请求的时候会将保存的请求放入Default Group    
2. 如果创建module分组,那么保存请求的时候会自动根据当前API所处于的module自动归类到对应的Module Group  
3. 当然你可以通过拉拽的方式自行移动API到对应的更小的group,并不冲突

## 为啥插件没反应
答:请优先按照第一章节介绍的使用步骤配置相关的配置,再点击图标

## 点击图标后idea卡死 
答:你设计的实体类嵌套递归,插件不支持,比如说A实体类有B实体类的属性,B实体类有A实体类的属性
``` java
public class A{
    private B b;
    private int xx;
}
public class B{
    private A a;
    private String xx;
}
```
以上这种情况如果你不需要B属性,那么你可以在生成的时候手动给B加一个static属性
``` java
public class A{
    private static B b;
    private int xx;
}
```


## 保存API图标旁重新生成的作用
旨在重置生成的参数,会清空之前API的参数,但是不包含保存动作.  
如果你的API已保存,想重新换一下参数,那么你可以通过点击重新生成按钮,记得一定要再次保存,否则还是会保留原有的参数.  

相当于你操作一个文件,清空文件内容但未保存,那么看到的将还是原来修改前的文件  





## 控制台挡住工具窗口
答:目前官方在工具窗口和控制台同时可见时,不支持工具窗口的显示优先于控制台,所以你只能通过调整[视图模式](https://www.jetbrains.com/help/idea/viewing-modes.html)
来控制,或者通过快捷键来快速隐藏和显示控制台(快捷键:view->toolWindow->run/debug可见快捷键)，使得工具窗口中的内容全部可见。当然你可以点击隐藏Request部分来看Response