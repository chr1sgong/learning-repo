package io.chr1s.interview;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public List<String> solute(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        backtrack(n, new StringBuilder(), 0, 0, res);
        return res;
    }

    private void backtrack(int n, StringBuilder curr, int left, int right, List<String> res) {
        if (curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }
        if (left < n) {
            curr.append("(");
            backtrack(n, curr, left + 1, right, res);
            curr.deleteCharAt(curr.length() - 1);
        }
        if (right < left) {
            curr.append(")");
            backtrack(n, curr, left, right + 1, res);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().solute(0));
        System.out.println(new Solution2().solute(3));
    }
}
