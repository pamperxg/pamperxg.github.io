---
title: subArray-leetcode
date: 2020-03-22 20:35:45
tags: [coding, java]
---

subArray problems in leetcode.

<!--more-->

---


```java
//total number of subarray sum is k
import java.util.HashMap;
import java.util.Map;

public class subarraySum {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for(int num : nums) {
            sum += num;
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
```



---