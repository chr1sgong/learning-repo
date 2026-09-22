package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-18
 */
public class Solution316 {


    public String removeDuplicateLetters(String s) {
        int L = s.length();
        boolean[] removed = new boolean[L];
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < L; ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                removed[i] = true;
                continue;
            }
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<>());
            list.add(i);
            map.put(s.charAt(i), list);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < L; ++i) {
            if (removed[i]) continue;
            List<Integer> list = map.get(s.charAt(i));
            for (int j : list) {
                if (j <= i) continue;
                if (s.charAt(i) < s.charAt(i + 1)) removed[j] = true;
                else {
                    removed[i] = true;
                }
            }
        }
        for (int i = 0; i < L; ++i) {
            if (!removed[i]) sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution316().removeDuplicateLetters("bcabc"));
    }
}
