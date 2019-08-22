---
title: carryC
date: 2019-07-07 22:48:03
tags: [notes,...]
---

。。。

<!--more-->

```python
import sys
import os
import re

print(sys.argv[0])
print(os.getcwd())
print(os.path.abspath('.'))
print(os.path.abspath('..'))

def findtxt(path, ret):
    """Finding the *.txt file in specify path"""
    filelist = os.listdir(path)
    for filename in filelist:
        de_path = os.path.join(path, filename)
        if os.path.isfile(de_path):
            if de_path.endswith(".txt"): #Specify to find the txt file.
                ret.append(de_path)
        else:
            findtxt(de_path, ret)
            

txt_files = []
findtxt(root_path,txt_files)

def get_item(files_list):
    res = []
    for i in range(len(files_list)):
        file_items = []
        with open(files_list[i]) as f:
        #     print(f.read().split('\n'))
        #     print(type(f.read().split('\n')))
            file_items = f.read().split('\n')
            file_highlights = []
            for j in range(len(file_items)):
                file_highlights.append(file_items[j].split('.')[1])
            res.append(file_highlights)
    print(res)
    return res

res_func_list = get_item(txt_files)
```

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

UDP：（UserDatagramProtocol）

无连接、面向事务简单不可靠、速度快、适合传输量小的场景、资源消耗少、面向数据报、可以一对多

TCP：

面向连接、可靠、传输数据量大、速度较慢、面向字节流、点对点连接

**超时重传机制**和**数据应答机制**保证可靠传输

RTT-RoundTripTime

WindowSize

MSS-maximun segment size

ACK

FLOW-control：

​	滑动窗口：加快传输效率，避免每一次发送数据都要等待接收端确认再发送下一个数据，而是在等待确认的过程中继续发送数据，在窗口大小内都是可以接受的。

Congestion-Control：

​	慢开始，避免拥塞

​	快重传，快恢复

回调函数：

```C
//定义主函数，回调函数作为参数
function A(callback) {
    callback();  
    console.log('我是主函数');      
}

//定义回调函数
function B(){
    setTimeout("console.log('我是回调函数')", 3000);//模仿耗时操作  
}

//调用主函数，将函数B传进去
A(B);
```

QUIC协议：

0-RTT握手过程：

​	通信双方发起通信连接时，第一个数据包就可以携带有效业务数据。

​	DH算法

重传与恢复

安全性

observatory：utils.update_repository

```C
//unsigned short 最大为65535
#define NULL 0
#define MEN_OK 0
#define MEM_ERR 1

enum ENUM_STAT_ITEM
{
STAT_ITEM0,
STAT_ITEM1,
STAT_ITEM_BUTT
};

typedef struct tag_PERM_COUNT_STAT_INFO
{
    unsigned short stat_item;
    unsigned short number;
}_sPermCountStatInfo;

_sPermCountStatInfo pcsi[STAT_ITEM_BUTT] = 
{
    {STAT_ITEM0,16000},
    {STAT_ITEM1,50000},
};  //定义数组，数组中的元素均为_sPermCountStatInfo类型

unsigned long *pulStatDataBuffer = NULL;
unsigned short AllocPermMemory(void)
{
    unsigned short usBufferSize = 0;
    unsigned short usLoop = 0;

    for(usLoop=0;usLoop<STAT_ITEM_BUTT;usLoop++)
    {
        usBufferSize += pcsi[usLoop].number;
    }
    pulStatDataBuffer = (unsigned long *)malloc(sizeof(unsigned long) *usBufferSize);  //返回分配内存块起始位置的指针
    
    if(NULL == pulStatDataBuffer) //malloc内存后要判断是否成功
    {
        return MEM_ERR;
    }
    return MEN_OK;
}
```
64位的架构中，sizeof(char*)的值为：8

|length|32bit os|64bit os|
|:-:|:-:|:-:|
|指针|4|8|
|int|4|4|
|char|1|1|
|double|8|8|


```C
#include <stdio.h>
#define M(x,y,z) x*y+z
int main(void) { 
    int a=1,b=2,c=3;
	printf("%d\n",M(a+b,b+c,c+a)); //12 a+b*b+c+z
	return 0;
}
```
```C

#include <stdio.h>
int main(void) { 
    char acArr[] = "ABCDE";
    char *pcPtr;
    for(pcPtr=acArr;pcPtr<acArr+5;pcPtr++){
        printf("%s\n",pcPtr);
    }
}
/*ABCDE
BCDE
CDE
DE
E
*/
```
构成c程序的基本单位是函数

```C
#include <stdio.h>

int main()
{
    int a = 0,i ;
    for(i=0;i<5;i++){
        switch (i)
        {
        case 0:
        case 3:
            a+=2;
        case 1:
        case 2:
            a+=3;
        default:
            a+=5;
        }
    }
    printf("i=%d\n",a);
}
/*
case0
case3
case1
case2
default
case1
case2
default
case2
default
case3
case1
case2
default
default
i=41
*/
```
编译占用内存大小：
编译器四字节对齐，double占两个字节位

线性数据结构：
非线性数据结构：
数据元素之间存在一对一的线性关系

```C
union 
{
    struct
    {
        char a:1;
        char b:2;
        char c:3;
    }d;
    char e;
}f;
f.e = 1;
printf("%d",f.d.a);
//x86平台上，结果为-1
```

浮点型变量分为单精度（float型）、双精度（double型）、长双精度（long double型）3类，单精度浮点型小数点后面有效数字为6~7位和双精度浮点型小数点后面有效数字为15~16位，单精度为32位，双精度为64位，8位为一个字节。

线程占有的都是不共享的，如：栈，寄存器，状态，程序计数器
共享的有：堆，全局变量，静态变量
函数调用栈，内存在函数执行期间有效，由编译器自动分配回收
堆由程序显式分配和回收，不回收会发生内存泄漏
