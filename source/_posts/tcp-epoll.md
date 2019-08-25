---
title: tcp_epoll
date: 2019-08-24 18:11:32
tags: ['notes','']
---

epoll & more
<!--more-->

##### epoll

文件描述符，指向文件的引用，指向内核为每个进程维护的记录表。本身不产生事件，但是其指向的文件发生改变会产生事件。

epoll是一种I/O事件通知机制
linux中的I/O事件的对象都可以用文件描述符(fd)来表示，可以是文件file、网络socket、进程间的pipe。fd会关联到内核缓冲区，当缓冲区可读时，会触发可读事件，当缓冲区可写时，会触发可写事件。其中触发机制分为水平触发和边缘触发，区别如下：

```C
//水平触发
ret = read(fd,buf,sizeof(buf));

//边缘触发
while(true)
{
    ret = read(fd,buf,sizeof(buf));
    if(ret == EAGAIN) break;
}
```
epoll是一种可以处理大批量文件描述符的IO多路复用机制。深层原理：TODO

epoll提供给用户的接口：

```C
int epoll_create(int size);
//返回一个fd，指向创建的struct eventpoll对象，该对象可以管理应用程序添加的或已就绪的时间
//int epoll_create1(int size);

int epoll_ctl(int epfd,int op,int fd,struct epoll_event *event);
//向epoll中添加，修改，删除感兴趣的事件，op的取值有EPOLL_CTL_ADD、EPOLL_CTL_MOD、EPOLL_CTL_DEL

int epoll_wait(int epfd,struct epoll_event * events,int maxevents,int timeout);
//

```

```C
//epoll_create创建的eventpoll对象
struct eventpoll{
    spinlock_t lock;
    struct mutex mtx;

    //收集在wait的应用程序，调用了epoll_wait()
    wait_queue_head_t wq;
    wait_queue_head_t poll_wait;

    //就绪的epitem
    struct list_head rdllist;
    struct rb_root rbr;

    struct epitem *ovflist;
    struct wakeup_source *ws;
    struct user_struct *user;

    //eventpool类型对象相应的文件对象
    struct file *file;
    int visited;
    struct list_head visited_list_link;
}

//事件对象
struct epoll_event {
    __u32 events;
    __u64 data;
}
```

---

```C
struct sockaddr_in{
    short sin_family;
    u_short sin_port;
    struct in_addr sin_addr;
    char sin_zero[8];
}
```

```C

#include <stdio.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
     int sockfd, newsockfd, portno, clilen;
     char buffer[256];
     struct sockaddr_in serv_addr, cli_addr;
     int n;
     if (argc < 2) {
         fprintf(stderr,"ERROR, no port provided\n");
         exit(1);
     }
     sockfd = socket(AF_INET, SOCK_STREAM, 0);
     if (sockfd < 0) 
        error("ERROR opening socket");
     bzero((char *) &serv_addr, sizeof(serv_addr));
     portno = atoi(argv[1]);
     serv_addr.sin_family = AF_INET;
     serv_addr.sin_addr.s_addr = INADDR_ANY;
     serv_addr.sin_port = htons(portno);
     if (bind(sockfd, (struct sockaddr *) &serv_addr,
              sizeof(serv_addr)) < 0) 
              error("ERROR on binding");
     listen(sockfd,5);
     clilen = sizeof(cli_addr);
     newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
     if (newsockfd < 0) 
          error("ERROR on accept");
     bzero(buffer,256);
     n = read(newsockfd,buffer,255);
     if (n < 0) error("ERROR reading from socket");
     printf("Here is the message: %s\n",buffer);
     n = write(newsockfd,"I got your message",18);
     if (n < 0) error("ERROR writing to socket");
     return 0; 
}

```

```C
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 

void error(char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;

    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[256];
    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }
    portno = atoi(argv[2]);
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");
    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno);
    if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0) 
        error("ERROR connecting");
    printf("Please enter the message: ");
    bzero(buffer,256);
    fgets(buffer,255,stdin);
    n = write(sockfd,buffer,strlen(buffer));
    if (n < 0) 
         error("ERROR writing to socket");
    bzero(buffer,256);
    n = read(sockfd,buffer,255);
    if (n < 0) 
         error("ERROR reading from socket");
    printf("%s\n",buffer);
    return 0;
}
```
