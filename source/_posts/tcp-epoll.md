---
title: tcp_epoll
date: 2019-08-24 18:11:32
tags: ['notes','']
---

epoll & more
<!--more-->

#### epoll

linuxIO多路复用的管理机制

- 支持一个进程打开大数目的socket描述符
- IO效率不随着FD数目增加而线性下降
- 使用mmap加速内核与用户空间的消息传递
- 内核微调

工作方式LT/ET

block和no-block socket

使用：

```C
#include <sys/epoll.h>
kdpfd = create_epoll(int maxfds);//close()
nfds = epoll_wait(kdpfd, events, maxevents, -1);
for( ; ; )
    {
        nfds = epoll_wait(epfd,events,20,500);
        for(i=0;i<nfds;++i)
        {
            if(events[i].data.fd==listenfd) /*有新的连接；我们可以注册多个FD,如果内核发现事件，就会载入events，如果有我们要的描述符也就是listenfd，说明某某套接字监听描述符所对应的事件发生了变化。每次最多监测20个fd数。*/
            {
                connfd = accept(listenfd,(sockaddr *)&clientaddr, &clilen); //accept这个连接
                ev.data.fd=connfd;
                ev.events=EPOLLIN|EPOLLET;//LT
                epoll_ctl(epfd,EPOLL_CTL_ADD,connfd,&ev); //将新的fd添加到epoll的监听队列中
            }
            else if( events[i].events&EPOLLIN ) //接收到数据，读socket,数据可读标志EPOLLIN
            {
                n = read(sockfd, line, MAXLINE)) < 0    //读
                ev.data.ptr = md;     //md为自定义类型，添加数据
                ev.events=EPOLLOUT|EPOLLET;
                epoll_ctl(epfd,EPOLL_CTL_MOD,sockfd,&ev);//修改标识符，等待下一个循环时发送数据，异步处理的精髓
            }
            else if(events[i].events&EPOLLOUT) //有数据待发送，写socket
            {
                struct myepoll_data* md = (myepoll_data*)events[i].data.ptr;    //取数据
                sockfd = md->fd;
                send( sockfd, md->ptr, strlen((char*)md->ptr), 0 );        //发送数据
                ev.data.fd=sockfd;
                ev.events=EPOLLIN|EPOLLET;
                epoll_ctl(epfd,EPOLL_CTL_MOD,sockfd,&ev); //修改标识符，等待下一个循环时接收数据
            }
            else
            {
                //其他的处理
            }
        }
    }
```