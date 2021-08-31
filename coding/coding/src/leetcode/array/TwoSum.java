package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int gap = target - nums[i];
            if (map.containsKey(gap)) {
                return new int[]{i, map.get(gap)};
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

//    public static int[] twoSum2(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }
//        Arrays.sort(nums);
//        int i = 0, j = nums.length - 1;
//        while (i < j) {
//            if (nums[i] + nums[j] == target) {
//                return new int[]{map.get(nums[i]), map.get(nums[j])};
//            } else if (nums[i] + nums[j] < target) {
//                i++;
//            } else {
//                j--;
//            }
//        }
//        return new int[]{0, 0};
//    }
//
//    public static void main(String[] args) {
//        System.out.printf(Arrays.toString(twoSum2(new int[]{3,3}, 6)));
//    }
}
