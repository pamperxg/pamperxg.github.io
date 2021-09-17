package leetcode.greedy;

import java.util.Arrays;

public class Candy {
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
//        for (int i = 0; i < n - 1; i++) {
//            if (ratings[i + 1] > ratings[i] && res[i + 1] <= res[i]) {
//                res[i + 1] = res[i] + 1;
//            }
//        }
//        for (int j = n - 1; j > 0; j--) {
//            if (ratings[j - 1] > ratings[j] && res[j - 1] <= res[j]) {
//                res[j - 1] = res[j] + 1;
//            }
//        }
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }
        for (int j = n - 1; j > 0; j--) {
            if (ratings[j - 1] > ratings[j]) {
                // 注意更新较大值
                res[j - 1] = Math.max(res[j - 1], res[j] + 1);
            }
        }
        return Arrays.stream(res).sum();
    }

    public static void main(String[] args) {
        int res = candy(new int[]{1,3,4,5,2});
        System.out.println(res);
    }
}
