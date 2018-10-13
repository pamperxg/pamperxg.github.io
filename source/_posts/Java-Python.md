---
title: Java&Python
date: 2018-8-20 00:33:45
tags: [coding,Java,Python,语言基础知识]
---

Java和Python的一些基础知识总结

<!--more-->

多线程、多进程、同步、异步、阻塞、非阻塞

多线程和多进程最大的不同在于，多进程中，同一个变量，各自有一份拷贝存在于每个进程中，互不影响，而多线程中，所有变量都由所有线程共享，所以，任何一个变量都可以被任何一个线程修改，因此，线程之间共享数据最大的危险在于多个线程同时改一个变量，把内容给改乱了。 

同步才有阻塞和非阻塞之分

同步：执行一个操作后等待结果，然后再继续后面的操作

阻塞：进程给cpu传达一个任务后，一直等待cpu处理完成再进行后续操作

非阻塞：进程给cpu传达一个任务后，继续处理后续操作，隔段时间再询问之前操作是否完成，也称轮询

异步：执行一个操作后，可以去执行其他操作，等待通知再回来执行未完成操作 

### Java

##### 常用类

Collection：

 java.util.Collection(接口) --extends java.lang.Iterable ，每一个位置存一个元素，没有get方法，只能通过iterator() 遍历元素

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
  
Map：

java.util.Map(接口)      （key-value pair）

- **HashMap**
- **HashTable**【线程安全】
- **ConcurrentHashMap**【支持线程安全，且效率更高】
- **LinkedHashMap**【使用链表维护元素顺序(插入顺序、LRU顺序)】
- SortedMap
  - **TreeMap**

array：容量固定且无法动态改变
**Arrays**：操作array的类，拥有一组静态函数
```java
Integer[] arr = {1,2,3};
List list = Arrays.asList();
```
**Collections**：和Arrays一样是工具类
**包装类**：
- 对象型：Boolean、Character(object的直接子类)
- 数值型：Byte、Double、Short、Integer、Float
基本数据类型：boolean、byte、char、short、int、float、long、double
包装类和基本类型的转化是自动拆箱装箱的，java把一些基本类型的常用对象存储在缓存池中，调用valueOf() 方法直接使用缓存池中的对象
> valueOf() 和new 对象区别：new出来的对象虽然值一样但是不是同一个对象，所以x!=y

> java不能隐式执行下转型1.1字面是double类型，不能执行float f = 1.1，只能执行float f = 1.1f但使用+=可以执行隐式类型转换 

**String、StringBuilder、StringBuffer**:

> String声明为final不可变，其他两个是可变的，StringBuilder线程不安全
##### 常识梳理
**关键字**

> public,protected,default,private
>
> 全局，自身子类包内，包内，自身 

**参数传递方式**

> java的参数是以值传递的方式传入方法，而不是引用传递Dog dog = new Dog('a');中的dog是存储对象地址的指针，在将一个参数传入方法时，本质是将对象的地址以值得方式传递到形参中 。

**接口和抽象类**

> 抽象类(IS-A):包含抽象方法的类（也可以不包含），也可以拥有成员变量和成员方法,不能被实例化，需要继承抽象类才能实例化其子类【抽象事物，模板式设计】
>
> 抽象方法：只有声明没有具体实现，必须为public或者protected抽象类是为了继承而生的，子类必须实现父类的抽象方法，如果没有实现，也应该声明为抽象类 
>
> 接口(LIKE-A)：极度抽象，一般都不定义变量（public(方法) 、成员变量只能static final），所有的方法（public abstract）都不能有具体的实现【抽象行为，辐射式设计】(从java8开始接口也可以拥有默认的方法实现，因为不支持默认方法的接口维护成本太高，接口增加方法要修改所有实现了该方法的类)
>
> 只能继承一个抽象类，但是能实现多个接口 

> java中的接口有继承和实现两个概念，且支持多继承
>
> 接口可以继承另外一个接口，并获得父接口所有的方法和成员变量
>
> 接口也可以被一个类实现，实现接口的类需要实现接口及其父接口里的多有抽象方法（java1.8中增加了default关键字，允许接口里有非抽象方法，这些抽象方法可以不被接口的实现类实现）
>
> 为什么接口支持多继承而类不可以？
>
> 当继承多个类中有参数列表相同的方法时子类就会产生混淆，所以只能单继承，单继承限制了类的拓展性但是接口中的方法都为抽象方法，没有方法体，只有接口的实现类有方法的实现，所以不会产生混淆 

**泛型**

> 泛型：参数化类型
>
> 泛型类型逻辑上可以看成是多个不同的类型，实际上都是相同的基本类型？ 

**反射**

> 运行时动态加载 

**Object通用方法**

> equals和==：
>
> 基本类型没有equals方法，\==判断两个值是否相等引用类型equals判断引用的对象是否等价，\==判断两个实例是否引用同一个对象 
>
> 等价的两个实例散列值一定相同，但散列值相同的两个实例不一定等价，在覆盖equals方法时应该也覆盖hashCode方法，保证等价的实例有相同的hashcode 

```java
public class EqualExample{
        private int x;
        private int y;
        private int z;
        public EqualExample(int x,int y,int z){
                this.x = x;
                this.y = y;
                this.z = z;
}
@override
public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        EqualExample that = (EqualExample) o;
        if(x!=that.x) return false;
        if(y!=that.y) return false;
        return z = that.z;
}
@override
public int hashCode(){
}
}

EqualExample e1 = new EqualExample(1,1,1);
EqualExample e2 = new EqualExample(1,1,1);
syso(e1.equals(e2)); //true

HashSet<EqualExample> s1 = new HashSet<>();
s1.add(e1);
s1.add(e2);
syso(s1.size()) //2,没有重写hashcode方法
/*
toString()
clone()
protect方法，必须显示重写才能调用
必须先实现cloneable接口才能调用clone方法
**/
public class CloneExample{
        private int a;
        private int b;
        @override
        protected CloneExample clone() throws Exception{
                return (CloneExample)super.clone();
    }
}
CloneExample c1 = new CloneExample();
try{
        CloneExample c2 = c1.clone();
} catch (CloneNotSupportedException e){
        e.printStackTrace();
} //java.lang.CloneNotSupportedException: CloneTest

public class CloneExample implements Cloneable{
        private int a;
        private int b;

        @override
        protected CloneExample clone() throws Exception{
                return (CloneExample)super.clone();
    }
}
//深拷贝(引用不同对象)和浅拷贝(引用同一对象)
```

**封装继承重写重载**

> 封装
>
> 子类覆盖父类的方法，子类中该方法的访问级别不能低于父类，(里式替换原则：子类对象必须能够替换所有的父类对象)字段不能公有，使用共有的getter，setter进行修改 
>
> 重写：继承过程中
>
> 重载：同一个类中同名方法，只有返回值不同不算重载方法名相同，参数列表不同（不同参数类型，参数顺序或参数个数），方法的返回值类型和修饰符与方法重载没有任何关系。 

**初始化顺序**

> 在存在继承的情况下，初始化顺序为：
>
> 父类(静态变量，静态语句块)
>
> 子类(静态变量，静态语句块)
>
> 父类(实例变量，普通语句块)
>
> 父类(构造函数)
>
> 子类(实例变量，普通语句块)
>
> 子类(构造函数) 

**虚拟机**

> - **运行时数据区域**
>
>   **THREAD**
>
>   - **程序计数器**：
>
>     记录正在执行的虚拟机字节码指令地址 
>
>   - **虚拟机栈**：
>
>     每一个java方法在执行的同时会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等
>
>     每一个方法调用到完成的过程对应一个栈帧在JVM中入栈和出栈的过程
>
>     java -Xss=512M HackTheJava //指定大小 
>
>   - **本地方法栈**： 
>
>   **堆**：
>
>   所有的对象实例在这里分配内存，垃圾收集的主要区域，不需要连续内存，可动态扩展
>
>   新生代(Eden,From Survivor,To Survivor)
>
>   老年代
>
>   永久代
>
>   java -Xms=1M -Xmx=2M HackTheJava 
>
>   **方法区**：
>
>   存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据 
>
>   - **运行时常量池**：方法区的一部分 
>
> - **直接内存**：程序计数器、虚拟机栈、本地方法栈三个区域属于线程私有，只存在于线程的生命周期内，线程结束即消失，不需要垃圾收集

> 垃圾收集垃圾收集主要集中在堆和方法区 

> 类加载机制：运行期间动态加载 

**Spring、SpringBoot**

TODO

---

### Python

[pythonGitbook](https://eastlakeside.gitbooks.io/interpy-zh/content/)

**list、tuple、dict、set**

- list：列表，链表，有序，可索引，“[]”

  ```python
  append(y)、extend(L)、count(y)、index(y)、insert(i,y)、pop(y)、remove(y)、reverse()、sort()
  #y--元素，L--列表
  ```

- tuple：元组，对象集合，可索引，不能修改，“()”

  > 不可变，能保证代码的安全性
  >
  > len()，tuple[y]
  >
  > 同样可以索引切片连接重复

- dict：字典，key-value组合，无序，（空间换时间），“{}”

  > 不允许一个键对应多个值

  ```python
  dict.keys(),dict.values(),dict.items()
  hash(obj) ##-->可作为key
  del,dict.pop,clear
  sorted(dict.items(),key = lambda d:d[0])
  dict.get()
  dict.setdefault()
  {}.fromkeys(('a','b'),True)  #{'a':True,'b':True}
  ```

- set：集合，无序，元素自动去重，“set([])”

  ```python
  add,update,remove
  discard与remove #【如果删除的元素不在集合内，discard不会报错】
  #|(联合),&(交集),-(差集),^(差分)
  ```

**collections**

- defaultdict(不需要检查key是否存在，减少keyerror的发生)
- Counter
- deque：双端队列
- namedtuple：可以像字典一样访问，但是是不可变的

**dict排序**

```python
tmp_list = sorted(tmp_list.items(),key = lambda d:d[1],reverse = True)  ##按value、反序
```

**交集并集差集**

```python
print(list(set(b).difference(set(a))))
print(list(set(b).union(set(a))))
print(list(set(b).intersection(set(a))))

#检查是否有重复元素
duplicates = set([x for x in some_list if some_list.count(x) > 1])
```

**xrange、range**

xrange返回一个迭代器，range返回一个list

**生成器**

**@property**

将一个函数定义为特性，直接obj.name使用，遵循统一访问的原则 ，相当与get set？

```python
class Stu(object):
    @property #相当于get方法，调用简单方便可控
    def score(self):
        return self._score
    @score.setter #set方法，
    def score(self,value):
        if not isinstance(value,int):
            raise ValueError('must be int')
        if value<0 or value>100:
            raise ValueError('must 0<x<100')
            self._score = value
```

**@staticmethod、@classmethod**

```python
class Date(object):
    day = 0
    month = 0
    year = 0
    def __init__(self,day=0,month=0,year=0):
        self.day = day
        self.month = month
        self.year = year
    @classmethod #尝试一种另类的构造函数，python缺乏重载的特性
    def from_string(cls,date_as_string):
        day,month,year = map(int,date_as_string.split('-'))
        date1 = cls(day,month,year)
        return date1
    @staticmethod #不需要访问类
    def is_date_valid(date_as_string):
        day,month,year = map(int,date_as_string.split('-'))
        return day<=31 and month<=12 and year<=2019
if __name__ == '__main__':
    s = Stu()
    s.score = 60
    print(s.score)
    # s.score = 999
    date2 = Date.from_string('06-08-2018')
    is_date = Date.is_date_valid('06-08-2019')

    print(date2.day)
    print(is_date)
```

**enumerate**

```python
for i in range(len(list1)):
    print (i,list1[i])
#enumerate的用法
for index,item in enumerate(list1):
    print (index,item)
#enumerate还可以指定索引的起始值
for index,item in enumerate(list1,1):
    print(index,item)
#简单的统计文件行数的方法
count = len(open(filepath,'r').readlines())

count=0
for index,line in enumerate(open(filepath,'r')):
    count+=1
```

**多线程多进程**

> Python的线程虽然是真正的线程，但解释器执行代码时，有一个GIL锁：Global Interpreter Lock，任何Python线程执行前，必须先获得GIL锁，然后，每执行100条字节码，解释器就自动释放GIL锁，让别的线程有机会执行。这个GIL全局锁实际上把所有线程的执行代码都给上了锁，所以，多线程在Python中只能交替执行，即使100个线程跑在100核CPU上，也只能用到1个核。不能多线程并发。 

```python
import argparse
import os,time,random
from multiprocessing import Process,Pool,cpu_count,Queue
import subprocess
import threading

# parser = argparse.ArgumentParser()
# parser.add_argument('integer',type=int,help='display an integer')
# args = parser.parse_args()
# print(args.integer)

# parser.add_argument("--square",help="display a square of a given number",type=int)
# parser.add_argument("--cubic",help="display a cubic of a given number",type=int)
#
# args = parser.parse_args()
#
# if args.square:
#     print(args.square**2)
# if args.cubic:
#     print(args.cubic**3)

def run_proc(name):
    print('Run child process %s (%s)...' %(name,os.getpid()))

def long_time_task(name):
    print('Run child process %s (%s)...' %(name,os.getpid()))
    start = time.time()
    time.sleep(random.random()*3)
    end = time.time()
    print('Task %s runs %.2f seconds' % (name,(end-start)))

def write(q):
    print('Process to write: %s' % os.getpid())
    for value in ['A','B','C']:
    print('put %s queue ' % value)
    q.put(value)
    time.sleep(random.random())
def read(q):
    print('Process to read: %s' % os.getpid())
    while True:
        value = q.get(True)
    print('get %s from queue' % value)

def loop():
    print('thread %s is running' % threading.current_thread().name)
    n = 0
    while n<5:
        n = n+1
        print('thread %s >>> %s' % (threading.current_thread().name,n))
        time.sleep(1)
        print('thread %s ended' % threading.current_thread().name)
if __name__ == "__main__":
    #进程，进程池
    print(cpu_count())
    print('Parent process %s' % os.getpid())
    # p = Process(target=run_proc,args=('test',))
    p = Pool(32)
    for i in range(33):
        p.apply_async(long_time_task,args=(i,))
        # print('Child process start')
        # p.start()
        # p.join()
        # print('Child process end')
        print('waiting for all subprocess done')
        p.close()
        p.join()
        print('All subprocess done')

#进程执行
r = subprocess.call(['nslookup','https://pamperxg.github.io'])
print('Exit code:',r)

#进程间通信
q = Queue()
pw = Process(target=write,args=(q,))
pr = Process(target=read,args=(q,))
pw.start()
pr.start()
pw.join()
pr.terminate()

#线程
t = threading.Thread(target=loop,name='LoopThread')
t.start()
t.join()
print('thread %s ended' % threading.current_thread().name)
```

**数据库连接**

```python
def ConnectDataBase(self,db_name,user_name,password):
        conn = mdb.connect(host='88.88.88.88',port=3306,user=user_name,passwd=password,\
                           db=db_name,charset='utf8')
        cursor = conn.cursor()
        return (conn,cursor)
```

**pandas操作**

```python
#选取指定列进行操作
df.loc[(df.Cabin.notnull()),'Cabin'] = 'Yes'  #选取指定列替换为yes
df.loc[df.Team.isin(['England','Italy','Russia']),['Team','Shooting Accuracy']] #选取满足条件的列
```

**loc、iloc、ix**

> loc：在行标签上进行索引，包括start和end
>
> iloc：在行标签位置上进行索引，不包括end
>
> ix：在行标签上索引，索引不到就到位置上索引（如果index非全部整数）,不包括end
>
> > .ix is deprecated. Please use
> > .loc for label based indexing or
> > .iloc for positional indexing

**map、filter、reduce**

```python
items = [1,2,3,4]
squared = list(map(lambda x:x**2,items)) #map python3返回迭代器，python2返回列表

less_than_zero = filter(lambda x:x<0,number_list) #同python3返回迭代器，python2返回列表

#对一个列表进行计算并且返回结果
product = reduce((lambda x,y:x*y),[1,2,3,4]) #24
```

**网络相关**：

应用层：为特定应用程序提供数据传输服务，http，dns

运输层：进程间的通用数据传输服务，TCP、UDP

网络层：主机间的数据传输服务

数据链路层：为同一链路的主机提供服务

物理层：怎样在传输媒体上传输数据比特流 

表示层

会话层 

TCP/UDP：---  应用层：http，dns                  

​		    ---   运输层：tcp，udp                  

​                    ---   网际层 ：IP                  

   		    ---   网络接口层： 

> UDP是无连接的，支持一对一，一对多，多对一，多对多
>
> TCP是面向连接的，只能一对一

序号，确认号，数据偏移，确认ACK，同步SYN，中止FIN，窗口 

*TCP三次握手，四次挥手：*

- 服务端处于listen状态，等待客户端的连接请求

-  客户端向服务端发送请求报文，SYN=1，ACK=0，初始序号x 

- 服务端收到请求报文后，若同意连接，向客户端发送确认报文，SYN=1,ACK=1，确认号ack=x+1,初始序号y 

- 客户端收到确认报文后，继续向服务端发出确认，确认号ack=y+1，序号为x+1 

- 服务端收到客户端确认后，连接建立

  > 防止失效连接请求到达服务器
  >
  > 如果客户端发送的连接请求在网络中滞留，滞留时间超过客户端超时重传时间后，客户端会重新请求连接，延时的请求还是会到达服务端，如果不进行三次握手，服务器就会打开两个连接，有三次握手就会忽略滞留连接请求的连接确认，不进行三次握手，所以不会再次打开连接。 

---

- 客户端发送连接释放报文，FIN=1 

- 服务端收到后发出确认，此时TCP处于**半关闭状态**，服务器端能向客户端发送数据，反之不行/ CLOSE_WAIT 

- 服务端不再需要连接时，发送连接释放报文，FIN=1 

- 客户端收到后发出确认，进入**TIME_WAIT**状态，等待2MSL(最大报文存活时间)后释放连接 

- 服务端收到客户端确认后释放连接 

  > CLOSE_WAIT:状态让服务端发送完数据
  >
  > TIME_WAIT:确保确认报文能到达服务端，若未到达，服务端会再次发送释放报文。。                   
  >
  > ​		     等待本连接产生的所有报文都从网络中消失，下一个新连接不会受到旧的请求报文 
  >
  > 可靠传输：超时重传 

