package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution377 {


    public List<List<Integer>> combinationSum4(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int target, int startIdx, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (startIdx >= nums.length || target < 0) return;

        for (int i = 0; i < nums.length; ++i) {
            curr.add(nums[i]);
            backtrack(nums, target - nums[i], i, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Solution377().combinationSum4(new int[]{1,2,3}, 4);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
