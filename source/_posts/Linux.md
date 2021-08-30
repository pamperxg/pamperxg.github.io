###Linux

####对于一台新机器

- 创建新用户/用户组：

```shell
# cat /etc/passwd id/whoami w/who 
# 新建用户和用户组
# -m自动创建相应的目录
sudo useradd -m xxx
passwd xxx

groupadd yyy
usermod -G yyy xxx

groupdel yyy
userdel xxx

# 给用户添加root权限
ll /etc/sudoers
# chmod 700 /etc/sudoers
vim /etc/sudoers
# xxx ALL=(ALL:ALL) ALL
# chmod 400 /etc/sudoers

#切换用户
sudo su
su xxx

#切换用户后只显示$
#将/etc/passwd中用户的/bin/sh修改为/bin/bash

#修改linux的主机名
#修改/etc/hostname
#centos修改：/etc/sysconf/network
```

#### 系统结构

主要组成为：内核、shell、文件系统、应用程序

##### 内核

程序 -->系统调用接口(SCI ./linux/kernel) --> **内核** --> 硬件

- 内存管理(./linux/mm)

- 进程管理

  进程间通信：信号、管道、共享内存、信号量、套接字

  创建新进程：fork/exec/POSIX

- 驱动程序管理

- 文件系统(./linux/fs)

  SCI --> VFS --> ext3...(支持多种目前流行的文件系统) --> Buffer Cache --> DeviceDriver --> Physical Devices

  常用命令：

  ```shell
  #磁盘管理
  fdisk df du
  #目录管理
  cd pwd mkdir rmdir cp ls rm mv
  #内容查看
  cat tac(反序输出) more less head tail
  #权限控制
  chmod chown chgrp umask(权限掩码相关)
  #查找
  whick whereis locate find
  ```

  

- 网络管理

  由BSD套接字、网络协议层、网络设备驱动程序组成

##### Shell

用户命令解释器，用户 --> shell -->内核， eg.BASH





