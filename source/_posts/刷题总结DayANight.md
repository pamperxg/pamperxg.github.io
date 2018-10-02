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

### 链表

反转

### LeetCode

#### 数据结构



#### SQL



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



































