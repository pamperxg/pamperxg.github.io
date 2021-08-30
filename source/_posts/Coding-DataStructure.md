---
title: Coding_DataStructure
date: 2018-8-31 00:32:30
tags: [coding,Java,SQL,Shell]
---

Coding on leetcode & nowcoder

<!--more-->

### 排序算法

https://pamperxg.github.io/2018/08/17/Sorting-Algotithms/#more

---

```C
//remove element of linked list
struct ListNode{
    int val;
    struct ListNode *next;
};

struct ListNode* removeElements(struct ListNode* head,int val){
    while(head != NULL && head->val == val){
        head = head->next;
    }
    struct ListNode *current = head;
    while(current != NULL){
        struct ListNode *pNext = current->next;
        if(pNext != NULL && pNext->val == val){
            current->next = pNext->next;
            free(pNext);
        }
        else{
            current = current->next;
        }
    }
    return head;
}
```

```c
//孪生素数
//在定义变量时，*定义的是指针，&定义的是引用
//在使用时，*是解指针，&是取地址
#include "stdio.h"
int isPrime(int n)
{
    int i;
    if(n < 2)
        return 0;
    for(i=2;i*i<=n;i++)
        if(n%i==0)
            return 0;
    return 1;
}
int main()
{
    int a,b,flag=1;
    scanf("%d %d",&a,&b);
    for(;a+2<=b;a++)
        if(isPrime(a&&isPrime(a+2)))
            flag=0,printf(%d,%d\n,a,a+2);
    if(flag)
        printf("-1\n");
    return 0;
}
```


```c
//A+B
#define INT_MAX 2147483647
#define INT_MIN (-INT_MAX-1)

int reverseInt(int x){
    int result = 0;
    while(x != 0){
        int tmp = x % 10;
        if(result > INT_MAX/10 || result < INT_MIN/10) return 0;
        result = result*10 + tmp;    
    }
    return result;
}
```

### 树

```java
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}
```

前序遍历

```java
public List<Integer> preorderTraversal(TreeNode root){
    ArrayList<Integer> res = new ArrayList<>();
    if(root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.isEmpty()){
        root = stack.pop();
        res.add(root.val);
        if(root.right != null) stack.push(root.right);
        if(root.left != null) stack.push(root.left);
    }
    return res;
}
```

中序遍历

```java
public List<Integer> inorderTraversal(TreeNode root){
    ArrayList<Integer> res = new ArrayList<>();
    if(root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    while(root != null || !stack.isEmpty()){
        while(root.left != null){
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        res.add(root.val);
        root = root.right;
    }
    return res;
}
```

后序遍历

```java
public List<Integer> postorderTraversal(TreeNode root){
    LinkedList<Integer> res = new LinkedList<>();
    if(root == null) return res;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.isEmpty()){
        root = stack.pop();
        //链表擅长增删
        res.addFirst(root.val);
        if(root.left == null) stack.push(root.left);
        if(root.right == null) stack.push(root.right);
    }
    return res;
}
```

层序遍历

```java
public List<List<Integer>> levelorderTraversal(TreeNode root){
    List<List<Integer>> res = new ArrayList<>();
    if(root == null) return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while(!queue.isEmpty()){
        int cnt = q.size();
        List<Integer> sub = new ArrayList<>();
        while(cnt -- > 0){
            TreeNode tmp = q.poll();
            if(tmp.left != null) q.add(tmp.left);
            if(tmp.right != null) q.add(tmp.right);
            sub.add(tmp.val);
        }
        res.add(sub);
    }
    return res;
}
/*
队列的方法：
add offer
remove poll
element peek
区别：为空或者已满时是否抛出异常
* */
```

二分查找

```java
public class BinarySearch{
    public int binarySearch(int[] nums,int target){
        if(nums == null || nums.length == 0) return -1;
        int left=0,right=nums.length-1;
        while(left <= right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
}
```

给一个数组，除了两个数字只出现一次，其他的都出现两次，找出这两个数

```java
public void findNumsAppearedOnce(int[] nums,int num1[],int num2[]){
	int diff = 0;
    for(int val:nums)
        diff ^= val;
    diff &= -diff;    //取出最右边两个不同的数的不同位
    for(int val:nums){  //分成两组，两个不同的数字在不同组
        if((val & diff) == 0)
            num1[0] ^= val;
        else
            num2[0] ^= val;
    }
}
```

判断一棵树是否是另一棵树的子树

```java
public class IsSubTree{
    //t是否是s的子树
    public boolean isSubTree(TreeNode s,TreeNode t){
        if(s == null) return false;
        if(isSameTree(s,t)) return true;
        return isSubTree(s.left,t) || isSubTree(s.right,t);
    }
    private boolean isSameTree(TreeNode p,TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val)
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        return false;
    }
}
```

归并排序

```java
public class MergeSort<T extends Comparable<T>>{
    public void sort(T[] nums){
        sort(nums,0.nums.length-1);
    }
    public void sort(T[] nums,int l,int h){
        if(h < l) return;
        int m = l + (h-l)/2;
        sort(nums,l,m);
        sort(nums,m+1,h);
        merge(nums,l,m,h);
    }
    public void merge(T[] nums,int l,int m,int h){
        T[] tmp = (T[]) new Comparable[nums.length];
        for(int k=l;k<h;k++)
            tmp[k] = nums[k];
        int i=l,j=m+1;
        for(int k=l;k<h;k++){
            if(i>m)
                nums[k] = nums[j++];
            else if(j > h)
                nums[k] = nums[i++];
            else if(nums[i].compareTo(nums[j]) < 0)
                nums[k] = nums[i++];
            else
                nums[k] = nums[j++];
        }
    }
}
```

String To Int

考虑：判断空串、判断空格、判断符号、判断数字（判断溢出）,注意index<s.length()

```java
public class Atoi{
    public int myAtoi(String s){
        int index = 0;
        int sign = 1;
        int total = 0;
        //空串
        if(s.length() == 0) return 0;
        //空格
        while(index < s.length() && s.charAt(index) == ' ')
            index ++;
        //符号
        if(index < s.length() && (s.charAt(index) == '+') || s.charAt(index) == '-'){
            sign = s.charAt(index) == '+' ? 1:-1;
            index ++;
        }
        //数字
        while(index < s.length()){
            int digit = s.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;
            //判断溢出
            if(Integer.MAX_VALUE/10 < total || (Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE%10 < digit))
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            total = total*10 + digit;
            index ++;
        }
        return sign*total;
    }
}
```

求第k大的数(复杂度是多少？)

```java
/*
时间复杂度O(n)，空间复杂度O(1)
**/
public class FindKthLargestElem{    
    public int findKthLargestElem(int[] nums,int k){
        k = nums.length - k;
        int l=0,h=nums.length - 1;
        while(l <= h){
            int j = partition(nums,l,h);
            if(j == k) break;
            else if(j < k)
                l = j + 1;
            else
                h = j - 1;
        }
        return nums[k];
    }
    private int partition(int[] nums,int l,int h){
        int i=l,j=h+1;
        while(true){
            while((nums[++i] < nums[l]) && i<h);
            while((nums[--j] > nums[l]) && j>l);
            if(i >= j) break;
            swap(nums,i,j);
        }
        swap(nums,l,j);
        return j;
    }
    private void swap(int[] nums,int j,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```
```java
public class TwoSum{
    public int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int obj = target - nums[i];
            if(map.containsKey(obj))
                return new int[]{map.get(obj),i};
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No Solution!");
        //Throwable--Error/Exception[IllegalArgumentException]
    }
}

//排序，二分查找
public class ThreeSum{
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i>0 && nums[i]==nums[i-1])
                continue;
            int j=i+1,k=nums.length-1,target=-nums[i];
            while(j<k){
                if(nums[j]+nums[k]=target){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j<k && nums[j]==nums[j-1]) j++;
                    while(j<k && nums[k]==nums[k+1]) k--;
                }
                else if(nums[j]+nums[k] < target)
                    j++;
                else
                    k--;
            }
        }
        return res;
    }
}
```

### 链表

```java
//pre->curr->next
//curr<-next
public class RverseLinkedList{
    public ListNode reverseLinkedList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode reverseNodeRecurse(ListNode head){
        if(head = null || head.next == null) return null;
        ListNode p = reverseNodeRecurse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
public class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}

//(2->4->3)+(5->6->4)=(7->0->8)
public class AddTwoNum{
    public ListNode addTwoNums(ListNode l1,ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p=l1,q=l2,curr=dummyHead;
        int carry = 0;
        while(p!=null || q!=null){
            int x = p!=null ? p.val:0;
            int y = q!=null ? q.val:0;
            int sum = x+y+carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            if(p!=null) p=p.next;
            if(q!=null) q=q.next;
        }
        if(carry > 0)
            curr.next = new ListNode(carry);
        return dummyHead.next;
    }
}

public class GetIntersection{
    public ListNode getIntersection(ListNode headA,ListNode headB){
        ListNode l1=headA,l2=headB;
        while(l1 != l2){
            l1 = l1 != null ? l1.next:headB;
            l2 = l2 != null ? l2.next:headA;
        }
        return l1;
    }
}

public class RemoveNthFromEnd{
    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(n-- >= 0)
            fast = fast.next;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

### LeetCode

#### 数据结构

查找数在排序数组中插入位置

```python
sorted(lst + [target]).index(target)
len([x for x in lst if x < target])
bisect.bisect_right(lst, target) #bisect_left
```

#### SQL

执行顺序：

> 1.from
>
> 2.on
>
> 3.join
>
> 4.where
>
> 5.group by
>
> 6.with、聚合（avg，sum）
>
> 7.having
>
> 8.select
>
> 9.distinct
>
> 10.order by
>
> 11.limit

> 中间每一步都会生成一张虚拟表，如果需要的字段不在上一步的虚拟表中就会报错
>
> select中的别名不能用于select中，但是可以用于后面的步骤，比如order by

- count(\*),count(1),count(列名) 

> count(\*)包含了所有的列，相当于行数，统计结果的时候不会忽略列值null*
>
> count(1)效果与count(*)相同
>
> count(列名)只包括列名那一列，统计结果的时候会忽略列值为空 null，为主键时执行快，不为主键时执行没有count(1)快 

- rank,dense_rank(不跳过),row_number 

  窗口函数运行在having语句之后，order by之前。

  ```mysql
  select * rank() over (partition by xx order by yy desc)  as rnk from oo order by zz;
  ```

[leetcode176SecondHighestSalary](https://leetcode.com/problems/second-highest-salary/description/)

- limit 1 offset 1，从第一条开始（offset1）取一条
- limit 2,1 从第二条开始去一条，即取第二条 
- ifnull(xx,null) as xx

```mysql
select ifnull((select distinct Salary 
from Employee order by Salary desc limit 1,1),null) as SecondHighestSalary;
```

[leetcode177NthHighestSalary](https://leetcode.com/problems/nth-highest-salary/)

```mysql
create function getNthHighestSalary(N int) returns int
begin
declare M int;
set M=N-1;
	return(
        select distinct Salary from Employee order by Salary desc limit M,1
    );
end
```

[leetcode178:RankScore](https://leetcode.com/problems/rank-scores/)

```mysql
select Score,
(select count(*) from (select distinct Score s from Scores) tmp where s >= Score) Rank
from Scores order by Score desc;
```

[leetcode197:Rising Temperature](https://leetcode.com/problems/rising-temperature/)

datediff函数

```mysql
#找出比前一天温度高的Id
select weather.Id
from weather join weather w on DATEDIFF(weather.RecordDate,w.RecordDate) = 1
and weather.Temperature > w.Temperature;

select w1.Id
from Weather w1,Weather w2
where w1.Temperature > w2.Temperature
and to_days(w1.RecordDate)-to_days(w2.RecordDate)=1;
```

presto中的常用日期处理函数：

```mysql
date_diff('day',cast(xx_time as date),cast(yy_time as date)) as zz_time
date_trunc('year',t1.xx_time)
year(xx_time)>2017
```

#### Shell

[leetcode195:TenthLine](https://leetcode.com/problems/tenth-line/description/)

```shell
cnt=0
while read line && [ $cnt -le 10 ];do
	let 'cnt = cnt + 1'
	if [ $cnt -eq 10 ];then
		echo $line
		exit 0
	fi
done < file.txt

awk 'FNR == 10 {print}' file.txt
awk 'NR == 10' file.txt

sed -n 10p file.txt

tail -n+10 file.txt|head -1

head -10 file.txt | tail -1
```

---

##### 翻转数字

```java
// Base 10 case
ans = ans*10 + n % 10
n /= 10
    
//Base 2 case
ans = ans*2 + n % 2
    
//Bit operation
ans = (ans << 1) | (n & 1)
n >>= 1
```

