---
title: C_mix_a
date: 2019-08-24 18:26:48
tags: [notes,C,...]
---

C & ...

<!--more-->
vscode 插件：
>ident Rainbow
>code Runner
>vscode Icons
>beyond the horizon
>markdown preview enhanced
>braket pair colorizer


C回调函数：

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

---
windows 安装mingw
https://osdn.net/projects/mingw/downloads/68260/mingw-get-setup.exe/

在测试计划阶段制定*退出准则*
当发现的缺陷数量小于某指定标准的时候停止测试
测试计划中的测试完成准则的目的是为了确定什么时候结束测试

冒烟测试：
指软件修改后，对其关键的功能进行测试
用于确定是否需要让测试人员进入到新版本的测试工作中
为防止时间人力的浪费，在通过冒烟测试之后才进行全面测试阶段

黑盒测试：(等价类、边界值、决策树、状态转换、正交因子组合、错误猜测、探索性测试设计)
测试程序的功能和性能
白盒测试：(覆盖)
通过熟悉被测对象的内部工作机理选择测试数据；假定测试人员知道一个单元或程序的逻辑路径，应用编码知识检查程序的输出

静态分析：数据流分析、代码检视  (静态分析可以发现参数类型不匹配、没有被声明的变量、没有被调用过的函数等错误)
动态分析：等价类划分、用例测试、探索式测试、决策测试

等价类划分：
按给定条件的类型划分有效等价类和无效等价类
如：输入条件给定取值范围，可以确立一个有效等价类和两个无效等价类


数据组合覆盖测试设计：
||||
|:-:|:-:|:-:|
|EC|单一选择组合|每一个输入的每一个值在所有组合(用例)中至少出现一次|
|BC|基本选择组合|选取基本组合，在此基础上通过修改一个输入的取值创建新的组合|
|AC|全组合||
|OA|正交数组||
|N:wise|覆盖任意N个输入全组合||
全组合覆盖生成的用例会包含Pairwise组合覆盖生成的用例
虽然BC组合比EC组合的覆盖强度大，但是BC组合生成的用例未必包含EC组合生成的与用例


测试覆盖方法：

||||
|:-:|:-:|:-:|
|语句覆盖|一条语句(如：if)一次||
|判定覆盖|每一个分支覆盖，但是无法验证判定条件错误||
|条件覆盖|每个判定条件及其可能取值覆盖||
|判定-条件覆盖|覆盖判断条件及其本声判定结果||
|条件组合覆盖|||
|路径覆盖|覆盖程序中所有可能的路径，数量庞大||
判定-条件覆盖要求设计的测试用例使得判断中每个条件的所有可能至少出现一次，并且每个判断本身的判定结果也至少出现一次。



SFIT故障注入手段
软硬件BIST

**错误猜测**适合在系统化的测试设计之后应用

性能测试考虑因素：系统响应能力、系统吞吐量、系统可伸缩性、系统资源使用情况

单元测试测试范畴可包括：模块局部数据结构测试、模块边界条件测试、模块执行路径测试

软件可靠性和硬件可靠性：
软件不存在物理退化现象，而硬件的失效主要由于物理退化所致
由于软件内部逻辑相对硬件内部逻辑更加复杂，设计错误成为软件失效的主要原因
软件是唯一的，而两个硬件不可能绝对相同所以概率方法在硬件可靠性领域取得了很大成功。

---