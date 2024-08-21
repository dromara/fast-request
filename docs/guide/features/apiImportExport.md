---
---

# APIs 导入导出

版本要求: <Badge text="2022.1.4.0" /> <Badge text="废弃" type="danger"/>

利用该功能，你可以非常方便得将自己已有的 APIs 分享给别的开发者，或者导入到其他设备上的 IDEA。

::: caution 注意点

- 导出的时候会新增一个名为 fastRequestCollection.xml 的 xml 文件，你不能重命名它， 默认导出到当前项目路径下。

- 导入的时候会做默认备份，并且会在.idea 文件夹下生成一个名为 fastRequestCollection-yyyyMMddHHmmssSSS.xml 的文件，
  如果是导入误操作，可以通过导入它来还原。

- 如果 fastRequestCollection.xml 不可见，点击 file->Reload All from Disk 来强制刷新。

:::

![exportImportApis](/img/exportImportApis.gif)
