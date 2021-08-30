##### docker安装

docker for windows：

启动系统的Hper-V和容器： win+x --> 应用和功能  --> 程序和功能 --> 启用或关闭windows功能 --> Hyper-V, 容器

问题：某些版本的系统中没有Hyper-V

解决办法：

以管理员身份运行如下脚本：

```shell
pushd "%~dp0"
dir /b %SystemRoot%\servicing\Packages\*Hyper-V*.mum >hyper-v.txt
for /f %%i in ('findstr /i . hyper-v.txt 2^>nul') do dism /online /norestart /add-package:"%SystemRoot%\servicing\Packages\%%i"
del hyper-v.txt
Dism /online /enable-feature /featurename:Microsoft-Hyper-V-All /LimitAccess /ALL
```

然后下载docker for windows。但是安装对windows版本有限制。

win10下ubuntu子系统：

```
C:\Users\Administrator\AppData\Local\Packages\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\LocalState\rootfs
```

