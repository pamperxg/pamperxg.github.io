---
title: Java&Python
date: 2018-8-20 00:33:45
tags: [coding,Java,Python,语言基础知识]
---

Java和Python的一些基础知识总结

<!--more-->

### Java

##### 常用类

``` java.util.Collection(接口)   (extends java.lang.Iterable)```，每一个位置存一个元素，没有get方法，只能通过```iterator()```遍历元素

- List【维护一组元素特定顺序】
  - **ArrayList**【查询快，基于arrays数组实现】
  - **LinkedList**【增删快，常用作队列】
  - **Vector**【实现同步，线程安全】
- Queue
  - **PriorityQueue**【基于堆结构实现】
- Set【每个元素唯一】
  - SortedSet
    - **TreeSet**【保持次序的set，基于红黑树实现，查找效率不如hashset】
  - **HashSet**【定义了hashcode，支持快速查找，不支持有序性操作，使用iterator遍历的结果可能是不确定的】
  - **LinkedHashSet**【hash加快查询，链表维护元素次序，只能顺序访问，可以快速增删】

```java.util.Map(接口)```        （key-value pair）

- **HashMap**
- **HashTable**【线程安全】
- **ConcurrentHashMap**【支持线程安全，且效率更高】
- **LinkedHashMap**【使用链表维护元素顺序(插入顺序、LRU顺序)】
- SortedMap
  - **TreeMap**

array：容量固定且无法动态改变

**Arrays**：操作array的类，拥有一组静态函数

``````
Integer[] arr = {1,2,3};
List list = Arrays.asList();
``````

**Collections**：和Arrays一样是工具类

**包装类**：

- 对象型：```Boolean、Character(object的直接子类)```
- 数值型：```Byte、Double、Short、Integer、Float```

基本数据类型：```boolean、byte、char、short、int、float、long、double```

包装类和基本类型的转化是自动拆箱装箱的，java把一些基本类型的常用对象存储在缓存池中，调用```valueOf()```方法直接使用缓存池中的对象

> ```valueOf()```和```new 对象```区别：new出来的对象虽然值一样但是不是同一个对象，所以x!=y

**String、StringBuilder、StringBuffer**:

> String声明为final不可变，其他两个是可变的，StringBuilder线程不安全

##### 常识梳理

**关键字**

**参数传递方式**

**接口和抽象类**

**泛型**

**Object通用方法**

**继承重写重载**

**虚拟机**

**Spring、SpringBoot**

---

### Python

#####**dict排序**

```tmp_list = sorted(tmp_list.items(),key = lambda d:d[1],reverse = True)```按value、反序

##### **交集并集差集**

```python
print(list(set(b).difference(set(a))))
print(list(set(b).union(set(a))))
print(list(set(b).intersection(set(a))))
```

##### **xrange、range**

##### **生成器**

#####**@property**

#####**@staticmethod、@classmethod**

#####**enumerate**

#####**多线程多进程**

##### 数据库连接

#####**pandas操作**

**loc、iloc、ix**