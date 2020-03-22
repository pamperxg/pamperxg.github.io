---
layout: communication
title: protocol
date: 2019-07-31 22:16:26
tags: [notes,...]
---

communication protocol

<!--more-->

OSI七层模型和TCP/IP五层模型：

>应用层：为应用程序提供服务
>
>表示层：数据格式转化、数据加密
>
>会话层：建立、管理、维护会话
>
>传输层：建立、管理、维护端到端地链接
>
>网络层：IP选址以及路由
>
>数据链路层：提供介质访问和链路管理
>
>物理层

>应用层 HTTP
>
>传输层 TCP UDP
>
>网络层 IP ICMP
>
>数据链路层 ARP(直接和网络设备接口打交道)
>
>物理层

​    Linux中的BSD socket和INET socket：对应于七层模型中的表示层和会话层，重要的数据结构为struct socket、struct sock、proto。

​    Linux中的IP协议向下需要和网络设备接口打交道，向上需要提供传输和接收服务(路由服务)，是一个非常核心的协议。

​    Linux中网络接口模块为内核代码的/linux/net部分代码，其中tcp/ip协议的实现在/linux/net/ipv4部分。网络接口主要由网络设备接口、网络接口core模块、网络协议族、网络接口socket四部分组成，其中网络设备接口(linux/driver/net)负责与物理介质进行数据的交互；网络接口核心模块主要功能为屏蔽物理层介质，为网络协议提供统一的接口，负责把包送入合适的协议(主要管理文件为linux/net/core/dev.c)；网络协议层实现各种网络协议；网络接口Socket为用户提供的网络服务提供编程接口(linux/net/socket.c)。

​    linux/include/linux/socket.h中linuxBSDsocket定义了多至32中支持的协议族，其中PF_INET为tcp/ip协议族。在/linux/net/ipv4目录下，**af_inet.c**为主要的管理文件。


​	滑动窗口：加快传输效率，避免每一次发送数据都要等待接收端确认再发送下一个数据，而是在等待确认的过程中继续发送数据，在窗口大小内都是可以接受的。

- UDP：（UserDatagramProtocol）

  无连接、面向事务简单不可靠、速度快、适合传输量小的场景、资源消耗少、面向数据报、可以一对多

- TCP：

  面向连接、可靠、传输数据量大、速度较慢、面向字节流、点对点连接

  基于字节序号的SequenceNumber和ACK，确认消息的有序到达；重传也不会改变的SequenceNumber会导致TCP**重传歧义**问题。

  TCP连接的四元组标识：源IP、源端口、目的IP、目的端口

  **慢启动、拥塞避免、快速重传、快速恢复**（锯齿曲线）

  TCP头(60字节，其中标准头20字节，TCP Option40字节(TcpTimestampOption10字节，所以留给Sack仅30字节，又由于SackBlock为8字节，SackOption为2字节，所以TcpSackOption最大为3个))

  AckDelay，服务端接收到segment到ack中回复TimestampOption的时间，导致RTT计算误差

  RTT-RoundTripTime

  WindowSize

  MSS-maximun segment size

  ACK/SACK(告诉发送方接收到连续Segment范围)

  FLOW-control/Congestion-control

  CWND/RWND

  TCP中接收速率的计算，可以采用固定时间窗口的计算方式和数ACK数的计算方式，如果在存在ACK传输过程中丢失的情况下，后者的计算准确性更好。

  源码中接收速率的计算方法：

  ```c
  //tcp.c、tcp_rate.c
  static u64 tcp_compute_delivery_rate(const struct tcp_sock *tp)
  {
  	u32 rate = READ_ONCE(tp->rate_delivered);
  	u32 intv = READ_ONCE(tp->rate_interval_us);
  	u64 rate64 = 0;
  
  	if (rate && intv) {
  		rate64 = (u64)rate * tp->mss_cache * USEC_PER_SEC;
  		do_div(rate64, intv);
  	}
  	return rate64;
  }
  
  /* The bandwidth estimator estimates the rate at which the network
   * can currently deliver outbound data packets for this flow. At a high
   * level, it operates by taking a delivery rate sample for each ACK.
   *
   * A rate sample records the rate at which the network delivered packets
   * for this flow, calculated over the time interval between the transmission
   * of a data packet and the acknowledgment of that packet.
   *
   * Specifically, over the interval between each transmit and corresponding ACK,
   * the estimator generates a delivery rate sample. Typically it uses the rate
   * at which packets were acknowledged. However, the approach of using only the
   * acknowledgment rate faces a challenge under the prevalent ACK decimation or
   * compression: packets can temporarily appear to be delivered much quicker
   * than the bottleneck rate. Since it is physically impossible to do that in a
   * sustained fashion, when the estimator notices that the ACK rate is faster
   * than the transmit rate, it uses the latter:
   *
   *    send_rate = #pkts_delivered/(last_snd_time - first_snd_time)
   *    ack_rate  = #pkts_delivered/(last_ack_time - first_ack_time)
   *    bw = min(send_rate, ack_rate)
   *
   * Notice the estimator essentially estimates the goodput, not always the
   * network bottleneck link rate when the sending or receiving is limited by
   * other factors like applications or receiver window limits.  The estimator
   * deliberately avoids using the inter-packet spacing approach because that
   * approach requires a large number of samples and sophisticated filtering.
   *
   * TCP flows can often be application-limited in request/response workloads.
   * The estimator marks a bandwidth sample as application-limited if there
   * was some moment during the sampled window of packets when there was no data
   * ready to send in the write queue.
   */
  ```

  

  **TCPBBR**：启动、排空、带宽探测、时延探测(STARTUP、DRAIN、PROBE_BW、PROBE_RTT)

  ​	BBR算法不能感知丢包，只能看到bw和rtt。

  ​	pacing rate(cwnd窗口中的数据包以多大的时间间隔发送) & cwnd(当前TCP最多可以发送多少数据)

  ​	即时带宽：bw=delivered/interval_us

  ​	

- http2+tcp+tls广泛应用

  tcp和tls带来了两次握手时延

- QUIC

  quick **udp** internet connection（使用udp进行多路并发传输的协议），避免协议对操作系统和中间设备依赖问题？

  0-RTT、可插拔拥塞控制、多路复用(避免队头阻塞(tcp(sequence number)和tls(record)均会出现))、连接迁移(不再用四元组标识连接，用ID标识)、前向冗余纠错

严格递增的PacketNumber+StreamOffset(不变)保证数据有序可靠

Reneging，丢弃已接受并回SACK的内容

QUIC ACK Frame可以同时提供256个AckBlock，在丢包率较高的网络下，可以提升网络恢复速度，减少重传

