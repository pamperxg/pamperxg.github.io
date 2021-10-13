package leetcode.array.prefixsum;

public class NumberOfSubarrays {
    // 连续子数组中有k个奇数数字
    // nums = [1,1,2,1,1], k=3  2
    public int numberOfSubarrays(int[] nums, int k) {
        // 记录前缀和数量
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        int res = 0, prefixSum = 0;
        for (int num : nums) {
            prefixSum += num & 1;
            prefixCnt[prefixSum]++;
            if (prefixSum >= k) {
                res += prefixCnt[prefixSum - k];
            }
        }
        return res;
    }
}
