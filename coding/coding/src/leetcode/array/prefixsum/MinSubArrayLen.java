package leetcode.array.prefixsum;

public class MinSubArrayLen {
    /**
     * 7, [2,3,1,2,4,3]
     * start end sum
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int prefixSum = 0;
        int start = 0, end = 0;
        while (end < n) {
            prefixSum += nums[end];
            while (prefixSum >= target) {
                res = Math.min(res, end - start + 1);
                prefixSum -= nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
