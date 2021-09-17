package leetcode.greedy;

import java.util.Arrays;

public class FindContentChildren {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;
        // 贪心策略，先排序，再遍历
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    public static void main(String[] args) {
        int res = findContentChildren(new int[]{1,2,3}, new int[]{3});
        System.out.println(res);
    }
}
