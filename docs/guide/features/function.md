# 函数

<MyCarousel :imgList="['/img/2024.1.7/function.png','/img/2024.1.7/functionInBody.png','/img/2024.1.7/functionConfig.png']" />

路径：`项目级别配置 -> Functions`

使用 `{{$函数名}}` 形式来替换魔法值。==函数必须是无参数的==，否则不能正常工作。

输入`{{$`后按快捷键可以唤起函数自动补全。

开发者可以利用 jdk 中自带的类写属于自己定制化的函数，例如随机 5 个字符串

```java
public String randomString() {
    StringBuilder sb = new StringBuilder(5);
    for (int i = 0; i < 5; i++) {
        int randomIndex = RANDOM.nextInt(CHAR_NORMAL.length());
        sb.append(CHAR_NORMAL.charAt(randomIndex));
    }
    return sb.toString();
}
```

更可以使用 [java-faker](https://github.com/DiUS/java-faker) 库，定制各种类型的参数随机生成。

::: tip 如何设置代码补全快捷键
在 `Setting -> Keymap` 下的 `Main Menu->Code->Code Completion->Basic`
:::
