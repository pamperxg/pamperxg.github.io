package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /**
     * 先进行排序
     * 跳过重复  i > 0 && nums[i] == nums[i - 1];
     *         j < k && nums[j] == nums[j-1], j++;
     *         j < k && nums[k] == nums[k+1], k--
     * 三个指针位置 -nums[i], j = i + 1, k = nums.length - 1
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int sum = -nums[i], j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == sum) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (nums[j] + nums[k] < sum) {
                    j ++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}
