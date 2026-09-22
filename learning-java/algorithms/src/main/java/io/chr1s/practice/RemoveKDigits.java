package io.chr1s.practice;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-04-20
 */
public class RemoveKDigits {

    // 1432219 k = 3
    private static String removeKDigits(String num, int k) {
//        num = trimLeadingZeros(num);
        if (num.length() <= k) {
            return "0";
        }
        if (k == 0) return num;
        Deque<Character> stack = new ArrayDeque<>();
        int deleted = 0;
        int i = 0;
        while (deleted < k && i < num.length()) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && deleted < k && stack.peek() > c) {
                stack.pop();
                deleted++;
            }
            if (stack.isEmpty() && c == '0') {
                deleted++;
            } else {
                stack.push(c);
            }
            i++;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        res.reverse();
        res.append(num.substring(i));
        String ans = res.substring(0, res.length() - (k - deleted));
        return trimLeadingZeros(ans);
    }

    private static String trimLeadingZeros(String num) {
        int i = 0;
        while (i < num.length() && num.charAt(i) == '0') i++;
        return num.substring(i);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1432219", 3));
        System.out.println(removeKDigits("10200", 1));
        System.out.println(removeKDigits("10", 2));
        System.out.println(removeKDigits("112", 1));
    }
}
