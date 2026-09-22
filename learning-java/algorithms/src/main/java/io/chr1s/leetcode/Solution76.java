package io.chr1s.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-04-02
 */
public class Solution76 {

    public String minWindowSubString(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        Map<Character, Integer> sMap = new HashMap<>();
        int required = tMap.size();
        int formed = 0;
        int left = 0;
        int right = 0;
        int start = -1;
        int minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            if (tMap.containsKey(c) && tMap.get(c).intValue() == sMap.get(c).intValue()) {
                formed++;
            }
            while (formed == required && left <= right) {
                if (start == -1 || right - left + 1 < minLen) {
                    start = left;
                    minLen = right - left + 1;
                }
                c = s.charAt(left);
                sMap.put(c, sMap.get(c) - 1);
                if (tMap.containsKey(c) && sMap.get(c) < tMap.get(c)) {
                    formed--;
                }
                left++;
            }

            right++;
        }
        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        System.out.println(new Solution76().minWindowSubString("ADOBECODEBANC", "ABC"));
        System.out.println(new Solution76().minWindowSubString("a", "a"));
    }
}
