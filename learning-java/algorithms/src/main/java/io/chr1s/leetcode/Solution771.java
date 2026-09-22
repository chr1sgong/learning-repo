package io.chr1s.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Jewels and Stones
 *
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-08
 */
public class Solution771 {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); ++i) {
            set.add(jewels.charAt(i));
        }
        int res = 0;
        for (int i = 0; i < stones.length(); ++i) {
            if (set.contains(stones.charAt(i))) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution771().numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(new Solution771().numJewelsInStones("z", "ZZ"));
    }
}
