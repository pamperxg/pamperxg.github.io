---
title: LeetCode
date: 2018-08-23 22:23:30
tags: [coding,Java,LeetCode]
---

。。。

<!--more-->

#### 双指针

```java
//input sorted array 167
public int[] twoSum(int[] nums,int target){
    int i = 0,j = nums.length - 1;
    while (i <= j) {
        int sum = nums[i] + nums[j];
        if (sum == target)
            return new int[]{i+1,j+1};
        else if (sum < target)
            i++;
        else
            j--;
    }
    return null;
}
```

```java
//sum of square numbers  633
public boolean judgeSquareSum(int c){
    int i = 0,j = (int) Math.sqrt(c);
    while (i <= j) {
        int squareSum = i*i + j*j;
        if (squareSum == c)
            return true;
        else if (squareSum < c)
            i++;
        else
            j--;
    }
    return false;
}
```

```java
//vowels  345
private static final HashSet<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
public String reverseVowels(String s){
    int i = 0,j = s.length() - 1;
    char[] result = new char[s.length()];
    while(i <= j){
        char ci = s.charAt(i);
        char cj = s.charAt(j);
        if (!vowels.contains(ci))
            result[i++] = ci;
        else if (!vowels.contains(cj))
            result[j--] = cj;
        else{
            result[i++] = cj;
            result[j--] = ci;
        }
    }
    return new String(result);
}
```

```java
//valid palindrome delete 1char 680
public boolean validPalindrome(String s){
    int i = -1,j = s.length();
    while (++i < --j){
        if (s.charAt(i) != s.charAt(j)){
            return isPalidrome(s,i,j-1) || isPalidrome(s,i+1,j);
        }
    }
}
private boolean isPalidrome(String s,int i,int j){
    while (i < j){
        if (s.charAt(i++) != s.charAt(j--))
            return false;
    }
    return true;
}
```

```java
//merge sorted array  88
public void merge(int[] nums1,int m,int[] nums2,int n){
    int index1 = m - 1,index2 = n - 1;
    int mergedIndex = m + n - 1;
    while (index1 >= 0 || index2 >= 0){
        if (index1 < 0)
            nums1[mergedIndex--] = nums2[index2--];
        else if (index2 < 0)
            nums1[mergedIndex--] - nums1[index1--];
        else if (nums1[index1] > nums2[index2])
            nums1[mergedIndex--] = nums1[index1--];
        else
            nums1[mergedIndex--] = nums2[index2--];
    }
}
```

```java
//linked list cycle  141
public boolean hasCycle(listNode head){
    if (head == null)
        return false;
    listNode l1 = head,l2 = head.next;
    while (l1 != null && l2 != null && l2.next != null){
        if (l1 == l2)
            return true;
        l1 = l1.next;
        l2 = l1.next.next;
    }
    return false;
}
```

```java
//最长子序列  524
public String findLongestWord(String s,List<String> d){
    String longestWord = "";
    for(String target:d){
        int l1 = longestWord.length(),l2 = target.length();
        if(l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0))
            continue;
        if(isValid(s,target))
            longestWord = target;
    }
    return longestWord;
}
private boolean isValid(String s,String target){
    int i = 0,j = 0;
    while(i < s.length() && j < target.length()){
        if(s.charAt(i) == target.charAt(j))
            j++;
        i++;
    }
    return j == target;
}
```

#### 排序

Kth Element问题，使用quicksort的partition函数实现，需先打乱数组

TopK Elements问题，堆排序求解

出现频率最多的K个数，桶排序

三向切分排

```java
public class ThreeWaySort{
    public void sortThreeWay(int[] nums){
        int zero = -1,one = 0,two = nums.length;
        while(one < two){
            if(nums[one] == 0)
                swap(nums,++zero,one++);
            else if(nums[one] == 2)
                swap(nums,one,--two);
            else 
                ++one;
        }
    }
    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```



#### 贪心思想

```java
//删除重叠区间个数
public class Interval{
    int start;
    int end;
    Interval(){start = 0;end = 0};
    Interval(int s,int e){
        this.start = s;
        this.end = e;
    }
}
public class EraseIntervals{
    public int eraseIntervals(Interval[] intervals){
        if(intervals.length == 0 || intervals == null)
            return 0;
        Arrays.sort(intervals,new Comparator<Interval>(){
            @Override
            public int compare(Interval o1,Interval o2){
                return o1.end - o2.end;
            }
        });
        //Arrays.sort(intervals,Comparator.comparingInt(o -> o.end));
        int cnt = 1;
        int end = intervals[0].end;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i].start<end)
                continue;
            cnt++;
            end = intervals[i].end;
        }
        return intervals.length - cnt;
    }
}
```

#### 二分

```java
public int BinarySearch(int[] nums,int target){
    int l = 0,h = nums.length - 1;
    while(l <= h){
        int mid = l + (h - l) / 2; 
        if(nums[mid] == target)
            return mid;
        else if(nums[mid] < target)
            l = mid + 1;
        else
            h = mid - 1;
    }
    return -1;
}
```

```java
//sqrt(x)
public class Sqrt{
    public int sqrt(int x){
        if(x <= 1)
            return x;
        int l = 1,h = x;
        while(l <= h){
            int mid = l + (h-l)/2;
            int sqrt = x/mid;
            if(sqrt == mid)
                return mid;
            else if(mid > sqrt)
                h = mid - 1;
            else
                l = mid + 1;
        }
        return h;   //循环中值后l比h大
    }
}
```

#### 搜索

```java
//bfs

```

