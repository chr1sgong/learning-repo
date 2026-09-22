package io.chr1s.leetcode;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-22
 */
public class Solution1682 {

    public int longestPalindromeSubseq(String s) {
        int L = s.length();
        int[][][] dp = new int[L][L][27];

        for (int end = 1; end < L; ++end) {
            for (int start = end - 1; start >= 0; --start) {
                for (int i = 0; i <= 26; ++i) {
                    if (s.charAt(start) == s.charAt(end)) {
                        dp[start][end][i] = dp[start + 1][end - 1][s.charAt(start) - 'a'] + (i == s.charAt(start) - 'a' ? 0 : 2);
                    } else {
                        dp[start][end][i] = Math.max(dp[start + 1][end][i], dp[start][end - 1][i]);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= 26; ++i) {
            res = Math.max(res, dp[0][L - 1][i]);
        }
        return res;
    }

    public int lpsBU(String s) {
        Integer[][][] memo = new Integer[s.length()][s.length()][27];
        return lpsBU(s, 0, s.length() - 1, 26, memo);
    }

    private int lpsBU(String s, int start, int end, int prev, Integer[][][] memo) {
        if (start >= end) {
            return 0;
        }
        if (memo[start][end][prev] != null) return memo[start][end][prev];
        if (s.charAt(start) == s.charAt(end) && s.charAt(start) - 'a' != prev) {
            return memo[start][end][prev] = 2 + lpsBU(s, start + 1, end - 1, s.charAt(start) - 'a', memo);
        }
        int ans = Math.max(lpsBU(s, start + 1, end, s.charAt(start) - 'a', memo),
                lpsBU(s, start, end - 1, s.charAt(end) - 'a', memo));
        return memo[start][end][prev] = ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1682().longestPalindromeSubseq("aabbccbb"));
        System.out.println(new Solution1682().lpsBU("aabbccbb"));
    }
}
