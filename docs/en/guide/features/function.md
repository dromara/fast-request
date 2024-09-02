# Function

<MyCarousel :imgList="['/img/2024.1.7/functionEn.png','/img/2024.1.7/functionInBodyEn.png','/img/2024.1.7/functionConfig.png']" />

Path：`Project Global Config -> Functions`

Use `{{$functionName}}` to replace the fixed value. ==Methods must be paramaterless==, otherwise it won't work.

Pressing the shortcut key after typing `{{$`, it will invoke the auto-completion action.

Developers can use the built-in api in the JDK to write own custom functions, such as 5-length random strings.

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

Also you can use [java-faker](https://github.com/DiUS/java-faker) library to customize various types of random parameters.

::: tip How to set Code completion shortcut
Under `Setting -> Keymap` ====> `Main Menu->Code->Code Completion->Basic`
:::
