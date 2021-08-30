package leetcode.binarysearch;

public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int leftBound = leftBound(nums, target);
        int rightBound = rightBound(nums, target);
        return new int[]{leftBound, rightBound};
    }

    private int leftBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] >= target) {
                // 相等的时候收缩右边界
                r = m;
            } else {
                l = m + 1;
            }
        }
        // 检查右边界
        if (l == nums.length || nums[l] != target) return -1;
        return l;
    }

    private int rightBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] <= target) {
                // 相等时收缩左边界
                l = m + 1;
            } else {
                r = m;
            }
        }
        // 检查左边界
        if (l == 0 || nums[l - 1] != target) return -1;
        return l - 1;
    }
}
