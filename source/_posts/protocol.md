---
layout: communication
title: protocol
date: 2019-07-31 22:16:26
tags: [notes,...]
---

communication protocol

<!--more-->

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

  **TCPBBR**：启动、排空、带宽探测、时延探测

- http2+tcp+tls广泛应用

  tcp和tls带来了两次握手时延

- QUIC

  quick **udp** internet connection（使用udp进行多路并发传输的协议），避免协议对操作系统和中间设备依赖问题？

  0-RTT、可插拔拥塞控制、多路复用(避免队头阻塞(tcp(sequence number)和tls(record)均会出现))、连接迁移(不再用四元组标识连接，用ID标识)、前向冗余纠错

严格递增的PacketNumber+StreamOffset(不变)保证数据有序可靠

Reneging，丢弃已接受并回SACK的内容

QUIC ACK Frame可以同时提供256个AckBlock，在丢包率较高的网络下，可以提升网络恢复速度，减少重传





