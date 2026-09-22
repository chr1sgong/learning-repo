package io.chr1s.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-07
 */
public class Solution1286 {

    class CombinationIterator {

        private List<String> combinations;
        private int currIdx;

        public CombinationIterator(String characters, int combinationLength) {
            List<String> res = new ArrayList<>();
            backtrack(characters, 0, combinationLength, new StringBuilder(), res);
            Collections.sort(res, (s1, s2) -> {
                int idx = 0;
                while (idx < s1.length()) {
                    if (s1.charAt(idx) < s2.charAt(idx)) return -1;
                    else if (s1.charAt(idx) > s2.charAt(idx)) return 1;
                    ++idx;
                }
                return 0;
            });
            combinations = res;
            currIdx = 0;
        }

        private void backtrack(String s, int startIdx, int len, StringBuilder sb, List<String> res) {
            if (sb.length() == len) {
                res.add(sb.toString());
                return;
            }
            if (startIdx >= s.length() || sb.length() > len) return;
            for (int i = startIdx; i < s.length(); ++i) {
                sb.append(s.charAt(i));
                backtrack(s, i + 1, len, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        public String next() {
            return combinations.get(currIdx++);
        }

        public boolean hasNext() {
            return currIdx >= combinations.size();
        }
    }

    public static void main(String[] args) {
        CombinationIterator iter = new Solution1286().new CombinationIterator("abc", 2);
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
    }


}
