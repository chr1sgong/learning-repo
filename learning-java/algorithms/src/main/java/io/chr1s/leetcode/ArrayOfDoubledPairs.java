package io.chr1s.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayOfDoubledPairs {

    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] temp = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = Math.abs(arr[i]);
            map.put(temp[i], map.getOrDefault(temp[i], 0) + 1);
        }
        Arrays.sort(temp, (o1, o2) -> 0);
        for (int i = 0; i < temp.length; i++) {
            int cnt = map.getOrDefault(temp[i], 0);
            if (cnt == 0) continue;
            int doubleCnt = map.getOrDefault(temp[i] * 2, 0);
            if (doubleCnt <= 0) return false;

            map.put(temp[i], cnt - 1);
            map.put(temp[i] * 2, doubleCnt - 1);
        }
        return true;
    }
}
