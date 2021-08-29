package leetcode.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class CheckSubArraySum {
    /**
     * nums = [23,2,4,6,7],k=6,是否存在子数组和是k的倍数
     * [5, 1, 0, 1, 2]
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        // 前两个数前缀和为0，符合前缀和为0，长度大于2
        prefixSumMap.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < n; i ++) {
            // (pre[j] - pre[i]) % k = 0, => pre[j]%k = pre[i]%k
            remainder = (remainder + nums[i]) % k;
            if (prefixSumMap.containsKey(remainder)) {
                int preIdx = prefixSumMap.get(remainder);
                if (i - preIdx >= 2) {
                    return true;
                }
            } else {
                prefixSumMap.put(remainder, i);
            }
        }
        return false;
    }
}
