package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution216 {


    public List<List<Integer>> combinationSum3(int K, int N) {
       List<List<Integer>> res = new ArrayList<>();
       backtrack(1, K, N, new ArrayList<>(), res);
       return res;
    }

    private void backtrack(int currNum, int k, int n, List<Integer> curr, List<List<Integer>> res) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(curr));
        }
        if (k == 0 || n < 0) return;
        for (int i = currNum; i <= 9; ++i) {
            curr.add(i);
            backtrack(i + 1, k - 1, n - i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Solution216().combinationSum3(3, 7);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
