---
title: 刷题总结DayANight
date: 2018-8-31 00:32:30
tags: [coding,Java,SQL,Shell]
---

RT!

<!--more-->

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

链表反转

```java
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
### 链表

反转,见面试总结

### LeetCode

#### 数据结构

#### SQL

- count(\*),count(1),count(列名) 

> count(\*)包含了所有的列，相当于行数，统计结果的时候不会忽略列值null*
>
> count(1)效果与count(*)相同
>
> count(列名)只包括列名那一列，统计结果的时候会忽略列值为空 null，为主键时执行快，不为主键时执行没有count(1)快 

- limit 1 offset 1，从第一条开始（offset1）取一条

  limit 2,1 从第二条开始去一条，即取第二条 

- rank,dense_rank(不跳过),row_number 

[leetcode197:Rising Temperature](https://leetcode.com/problems/rising-temperature/)

datediff函数

```mysql
#找出比前一天温度高的Id
select weather.Id
from weather join weather w on DATEDIFF(weather.RecordDate,w.RecordDate) = 1
and weather.Temperature > w.Temperature;
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



































