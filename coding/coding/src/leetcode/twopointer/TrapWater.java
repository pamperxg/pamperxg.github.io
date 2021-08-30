package leetcode.twopointer;

public class TrapWater {
    /**
     * [0,1,0,2,1,0,1,3,2,1,2,1]
     *
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int left = 0, right = height.length - 1;
        int lMax = height[left], rMax = height[right];
        // 双指针，每一个柱子可以接雨水 min(lMax, rMax) - height
        while (left < right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);
            if (lMax < rMax) {
                res += lMax - height[left];
                left++;
            } else {
                res += rMax - height[right];
                right--;
            }
        }
        return res;
    }
}
