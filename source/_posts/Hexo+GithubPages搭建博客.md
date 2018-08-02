---
title: Hexo+GithubPages搭建博客
date: 2018-08-02 21:17:37
tags: [notes,Github Pages,Hexo]
---
​	Ctrl CV 即可搭建个人博客。。。

<!--more-->

# 一、安装Node.js、Hexo

​	Ubuntu、Mac、Win下的安装都大同小异

```bash
$ sudo apt-get install node.js
$ sudo apt-get install npm   #一种包管理工具
$ sudo npm install -g hexo

$ node -v
$ hexo -v  #输出相应的版本即安装成功
```
# 二、Git相关配置

## 1、创建一个**username.github.io**的repository

## 2、配置ssh key

- 打开bash

```bash
$ ssh-keygen -t rsa -C "xxxx@xx.xx" 
# 一路回车
```

- 打开用户目录下的*~/.ssh/*目录，目录下文件id_rsa为私钥，id_rsa.pub为公钥。复制id_rsa.pub里面的内容
- 打开Github->Settings->SSH and GPG keys->new SSH key，取好别名，粘贴秘钥，回到bash

```bash
$ ssh -T git@github.com    #如果输出hello。。。字样，表示配置成功
```

若同一电脑需要用到多个sshkey，则还需如下操作：

```bash
$ ssh-keygen -t rsa -f ~/.ssh/id_rsa.xx -C "xxx@xx.xx"   #生成另外一个带别名的key
$ ssh-add ~/.ssh/id_rsa.xx  #添加新的ssh key到ssh agent，其默认只读id_rsa
$ touch config  #创建多个key的配置文件
```

已同时配置gitlab和github的key为例，config文件中写入如下信息

```reStructuredText
Host github
HostName github.com
User git
IdentityFile ~.ssh/id_rsa.github

Host gitlab
HostName gitlab.com
User git
IdentityFile ~.ssh/id_rsa.gitlab
```

编写完成后同样可以```ssh -T xxx```检查是否配置成功

## 3、Git常规信息配置

```bash
$ git config --list     #查看config信息
$ git config --global user.name "xxx"
$ git config --global user.email "xxx@xxx.xx"
```

# 三、搭建博客

​	Hexo搭建博客后部署到Github，如果我们需要把我们原始文件也存到Github便于我们后续在不同电脑更改，理论上我们需要一个库部署，一个库来存储我们的原始文件。这里我们通过建立不同的分支来实现。

```bash
$ git clone git@github.com:xxx/xxx.github.io.git
$ git branch hexo
$ git checkout hexo
```

​	我们可以把hexo分支设置为默认分支，打开项目的settings->Branches->Default Branch更改即可。因为后续更改只会发生在hexo分支，master分支仅起部署作用。

接着，我们操作Hexo：

于hexo分支下，

```bash
$ hexo init
$ npm install hexo-deployer-git
$ hexo g  #生成静态网页
$ hexo s  #本地预览http://localhost:4000/
```

博客的配置文件为_config.yml，关于部署的配置如下：

```
deploy:
  type: git
  repository: git@github.com:xxx/xxx.github.io.git
  branch: master
```

其他配置参考注释。。

重新生成部署：

```bash
$hexo clean
$ hexo g
$ hexo d
```

此时，打开<https://xxx.github.io/>即可访问博客。

 # 四、主题配置

​	配置文件_config.yml中有主题配置选项theme，将心仪的主题clone到themes文件夹下，修改配置重新部署即可应用主题，主题基本配置同样主题文件夹下的 _config.yml配置。

​	本博客采用[yilia](https://github.com/litten/hexo-theme-yilia)主题






























