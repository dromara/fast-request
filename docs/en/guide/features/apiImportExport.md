# APIs import and export

Version Required: <Badge text="2022.1.4" />

Using this function, you can easily share your existing APIs with other developers or import them to IDEA on other devices.

::: danger Attention

- A new file named fastRequestCollection.xml will be added when exporting. You should not rename it. The plugin will export it to the current project path by default.

- When importing, it will do a default backup and generate a file named fastRequestCollection-yyyyMMddHHmmssSSS.xml under the .idea folder. If fastRequestCollection.xml is imported by mistake, it can be restored by importing it.

- Click file->Reload All from Disk to force a refresh to get fastRequestCollection.xml if it is not visible.

:::

![exportImportApis](/img/exportImportApis.gif)
