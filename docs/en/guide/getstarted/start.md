---
title: Get started
icon: start1
---

```flow
st=>start: Start
cfg=>parallel: Config
op1=>subroutine: Add project name
op2=>subroutine: Add env name
op20=>operation: Set domain
op21=>parallel: Return main interface
op3=>operation: Select project
op4=>operation: Select env
op5=>inputoutput: Click R icon button on method left
op6=>operation: Click send button 
e=>end: End

st(bottom)->cfg(path1,right)->op1(bottom)->op20->op21(path1,right)->op3->op5
st(bottom)->cfg(path2,left)->op2(bottom)->op20->op21(path2,bottom)->op4->op5
op5->op6->e
```

::: tip How to  
* Step1: Click Manager config then add project name[example:wx card] and env[example:local、dev]  
* Step2: set target domain  
* Step3: open toolwindow(at the top-right corner),select project and env  
* Step4: click <i class="icon iconfont icon-restfulFastRequest"></i>(fastRequest icon) on method left(Old icon<i class="icon iconfont icon-restfulFastRequest1"></i>)
* Step5: click send button for sending request  
:::

![](../../../.vuepress/public/img/howToUse_en.gif)


New logo icon <i class="icon iconfont icon-restfulFastRequest"></i>

![](../../../.vuepress/public/img/newLogoIcon.png)

## Contacts 
Join and follow us to solve your question  ❤️❤️❤️

|[![twitter](https://img.shields.io/static/v1?label=Twitter&message=FastRequest666&logo=twitter&color=FC8D34)](https://twitter.com/FastRequest666)|[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Restful%20Fast%20Request&logo=telegram&color=28A8E8)](https://t.me/restful_fast_request)|
|------|------------- |
|![twitter](../../../.vuepress/public/img/twitter.png)|![telegram](../../../.vuepress/public/img/telegram.png)|






