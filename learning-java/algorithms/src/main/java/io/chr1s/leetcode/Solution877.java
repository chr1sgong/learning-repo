package io.chr1s.leetcode;

/**
 * Stone Game
 *
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-08
 */
public class Solution877 {

    public boolean stoneGame(int[] piles) {
        Integer[][][] cache = new Integer[piles.length][piles.length][2];
        return stoneGame(piles, 0, piles.length - 1, 0, cache) > 0;
    }

    private int stoneGame(int[] piles, int start, int end, int curr, Integer[][][] cache) {
        if (start > end)
            return 0;
        if (cache[start][end][curr] != null)
            return cache[start][end][curr];
        int res;
        if ((curr & 1) == 0) {
            res = Math.max(piles[start] + stoneGame(piles, start + 1, end, curr ^ 1, cache),
                    piles[end] + stoneGame(piles, start, end - 1, (curr ^ 1), cache));
        } else {
            res = Math.min(stoneGame(piles, start + 1, end, (curr ^ 1), cache) - piles[start],
                    stoneGame(piles, start, end - 1, (curr ^ 1), cache) - piles[end]);
        }
        cache[start][end][curr] = res;
        return res;
    }
    public static void main(String[] args) {
        System.out.println(new Solution877().stoneGame(new int[]{5,3,4,5}));
        System.out.println(new Solution877().stoneGame(new int[]{3,7,2,3}));
        System.out.println(new Solution877().stoneGame(new int[]{46,12,47,47,3,21,13,48,49,42,6,2,16,25,48,38,20,8,32,32,39,6,38,17,34,26,45,12,19,30,20,37,45,24,20,20,29,19,23,43,41,34,34,1,20,46,18,1,13,8}));
    }
}
