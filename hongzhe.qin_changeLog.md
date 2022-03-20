ower: 你好,

	我试图下载下该项目进行启动,之前和你在钉钉群里面沟通过.

	你讲过直接在GradleTask 中运行 Intellij 下的 runIdl脚本即可.

    但是运行下来发现会有GBK编码IDEA不识别现象,详细报错如下:

		![image-20220320160635912](https://gitee.com/deligense/images/raw/master/img/202203201606001.png)



   出现的地方还挺多的(下图为我已经处理了一部分):

![image-20220320160811231](https://gitee.com/deligense/images/raw/master/img/202203201608303.png)



出现该问题原因经过查询是由于这些文件在我本地IDE 环境中 为UTF-8 编码,  最初以为我的IDEA和你的IDEA版本不一致导致的. 后面发现,应该是你的项目问题,这些出了问题的文件为UTF-8,其他文件为GBK 编码. 而且,这些出问题的文件中都有中文 方式的注释,因此导致该问题 详细内容见下图;



图1: 出问题的文件编码



![image-20220320161550670](https://gitee.com/deligense/images/raw/master/img/202203201615978.png)



图2: 其他没有问题的的文件



![image-20220320161637178](https://gitee.com/deligense/images/raw/master/img/202203201616482.png)



我不太理解,为什么项目中会出现两种编码.



解决该问题有两个办法:



第一: 

File->Setting->File Encodings

在下图位置添加对应错误文件的编码方式



![image-20220320162033885](https://gitee.com/deligense/images/raw/master/img/202203201620959.png)



第二: 直接转换



![image-20220320162107425](https://gitee.com/deligense/images/raw/master/img/202203201621471.png)

用这里把文件编码转换,也会添加图1 的形式.



第二个问题:  isBlank 不识别, 这个是因为jdk11,同样是jdk11的版本,有些低版本的jdk11 还没有isBlank ,升级即可.