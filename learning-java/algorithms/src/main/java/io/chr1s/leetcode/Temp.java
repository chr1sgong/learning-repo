package io.chr1s.leetcode;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-21
 */
public class Temp {
    // 可转换为01背包问题，具体如下：
    // 题目要求选出一部分数的和记为sa，求sa >= target时的最小值
    // 假定剩下的数的和为sb，所有数的和为sum，则sa + sb = sum => sb >= sum - target
    // 又sb越大，sa越小。将背包大小设置为sum - target, 能装入背白的最大值为sb，答案就是sum - sb了。
    public int solution(int[] prices, int target) {
        int sum = 0;
        for (int price : prices) sum += price;
        int bag = sum - target;
        int[][] dp = new int[prices.length][bag + 1];
        for (int i = prices[0]; i <= bag; ++i) {
            dp[0][i] = prices[0];
        }
        for (int i = 1; i < prices.length; ++i) {
            for (int j = prices[i]; j <= bag; ++j) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i]] + prices[i]);
            }

        }
        return sum - dp[prices.length - 1][bag];
    }

    public static void main(String[] args) {
        System.out.println(new Temp().solution(new int[]{18,20,22,21}, 30));
        System.out.println(new Temp().solution(new int[]{2,3,15,17,26}, 30));
        System.out.println(new Temp().solution(new int[]{20,20,30}, 30));
    }
}
