package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution39 {


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int startIdx, int target, List<Integer> curr, List<List<Integer>> result) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if (startIdx >= nums.length) return;
        for (int i = startIdx; i < nums.length; ++i) {
            curr.add(nums[i]);
            backtrack(nums, i,target - nums[i], curr, result);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Solution39().combinationSum(new int[]{2,3,6,7}, 7);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
