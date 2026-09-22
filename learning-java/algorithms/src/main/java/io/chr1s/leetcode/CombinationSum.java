package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int num) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 1; i < num; ++i) {
            List<Integer> curr = new ArrayList<>();
            curr.add(i);
            backtrack(i, curr, i, num, res);
        }
        return res;
    }

    private void backtrack(int start, List<Integer> curr, int currSum, int target, List<List<Integer>> res) {
        if (currSum == target) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (currSum > target) return;
        for (int i = start; i < target; ++i) {
            curr.add(i);
            backtrack(i, curr, currSum + i, target, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(5));
    }
}
