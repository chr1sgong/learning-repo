package io.chr1s.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
    // [1,5,9]
    // [7,10]

    public int[] solution(int[] big, int[] small) {
        Set<Integer>  baseSet = new HashSet<>();
        for (int num : small) {
            baseSet.add(num);
        }
        System.out.println("baseSet size: " + baseSet.size());
        Integer startIdx = null;
        int[] res = null;
        Set<Integer> currSet = new HashSet<>();
        for (int i = 0; i < big.length; i++) {
            if (baseSet.contains(big[i])) {
                if (startIdx == null) startIdx = i;
                currSet.add(big[i]);
                System.out.println("currIdx: " + i + "->" + currSet.size());
                if (currSet.size() == baseSet.size()) {
                    if (res == null) {
                        res = new int[] {startIdx, i};
                    } else {
                        if (i - startIdx < res[1] - res[0]) {
                            res[0] = startIdx;
                            res[1] = i;
                        }
                    }

                    currSet.remove(big[startIdx]);
                    startIdx++;
                    while (startIdx <= i && !baseSet.contains(big[startIdx])) {
                        startIdx++;
                    }
                }
            }
        }
        System.out.println("res: [" + res[0] + "," + res[1] + "]");
        return res == null ? new int[0] : res;
    }

    public static void main(String[] args) {
        int[] res = new Solution().solution(new int[] {7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7}, new int[] {1,5,9});
    }
}
