package leetcode.binarysearch;

public class SplitArray {
    /**
     * nums=[7,2,5,10,8],m=2   18  分割数组的和的最大值最小
     * 分割数组和的最大值为 10 -> 32
     * 二分查找，数组按照当前查找最大和进行分割(如果分割数大于m，说明和应该更大)
     */
    public int splitArray(int[] nums, int m) {
        int l = 0, r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }
        while (l < r) {
            int mid = l + ((r - l) >> 2);
            if (checkSum(nums, mid, m)) {
                r = mid;   // 收缩右边界
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private boolean checkSum(int[] nums, int curSum, int m) {
        int splitNum = 1;
        int tmpSum = 0;
        for (int num : nums) {
            if (tmpSum + num <= curSum) {
                tmpSum += num;
            } else {
                splitNum++;
                tmpSum = num;
            }
        }
        // 相等时，收缩右边界，求左边界值
        return splitNum <= m;
    }
}
