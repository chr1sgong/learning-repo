package io.chr1s.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-04-21
 */
public class Solution127 {
    // word.length <= 10
    // wordList.length <= 5000
    // 5000 * 5000 * 10
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> adjMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            if (check(word, beginWord)) {
                set.add(word);
            }
        }
        adjMap.put(beginWord, set);
        for (int i = 0; i < wordList.size(); ++i) {
            String word1 = wordList.get(i);
            for (int j = i + 1; j < wordList.size(); ++j) {
                String word2 = wordList.get(j);
                if (check(word1, word2)) {
                    Set<String> set1 = adjMap.getOrDefault(word1, new HashSet<>());
                    set1.add(word2);
                    adjMap.put(word1, set1);

                    Set<String> set2 = adjMap.getOrDefault(word2, new HashSet<>());
                    set2.add(word1);
                    adjMap.put(word2, set2);
                }
            }
        }
        if (!adjMap.containsKey(endWord)) return 0;
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offerLast(beginWord);
        int res = 0;
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String curr = queue.pollFirst();
                if (curr.equals(endWord)) return res;
                Set<String> adjs = adjMap.getOrDefault(curr, new HashSet<>());
                for (String adj : adjs) {
                    if (!visited.contains(adj)) {
                        queue.offerLast(adj);
                    }
                }
            }
        }
        return 0;
    }

    private boolean check(String w1, String w2) {
        int len = w1.length();
        int cnt = 0;
        for (int i = 0; i < len; ++i) {
            if (w1.charAt(i) != w2.charAt(i)) {
                cnt++;
                if (cnt > 1) return false;
            }
        }
        return cnt == 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution127().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
