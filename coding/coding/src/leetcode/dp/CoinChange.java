package leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    // dp[i][j]  前i个硬币组成j， dp[i][j] = min(dp[i][j], dp[i][j - coin_i] + 1)  dp[n - 1][amount] => dp[j] = min(dp[j], dp[j - coin_i] + 1), dp[amount]
    // dp[amount] = Main.min(dp[amount - coinI], dp[amount - coinJ], dp[amount - coinK],) + 1
    // 递归数，三叉树
    // 求最少硬币数
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 遍历所有amount
        for (int i = 1; i <= amount; i ++) {
            for (int coin : coins) {
                // 如果面额超过要凑的钱数，跳过
                if (i < coin) {
                    continue;
                }
                // 当前面额选或不选
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    // 求组合数
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
