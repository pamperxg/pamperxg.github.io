package leetcode.slidewindow;

public class EqualSubstring {
    /**
     * 字符串尽可能相等
     * s = "abcd", t = "bcdf", maxCost = 3
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int start = 0, end = 0;
        int sum = 0, res = 0;
        // 开启滑动窗口end++
        while (end < n) {
            sum += diff[end];
            // 如果和超过预算，start++
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            // 更新长度
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
