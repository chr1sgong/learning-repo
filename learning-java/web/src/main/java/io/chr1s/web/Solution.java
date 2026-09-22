package io.chr1s.web;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-04-18
 */
public class Solution {

    public ListNode sum(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode res = null;
        int c = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode t1 = stack1.pop();
            ListNode t2 = stack2.pop();
            int v = t1.val + t2.val + c;
            ListNode node = new ListNode(v % 10);
            c = v / 10;
            node.next = res;
            res = node;
        }
        while (!stack1.isEmpty()) {
            ListNode t1 = stack1.pop();
            int v = t1.val + c;
            ListNode node = new ListNode(v % 10);
            c = v / 10;
            node.next = res;
            res = node;
        }
        while (!stack2.isEmpty()) {
            ListNode t2 = stack2.pop();
            int v = t2.val + c;
            ListNode node = new ListNode(v % 10);
            c = v / 10;
            node.next = res;
            res = node;
        }
        if (c > 0) {
            ListNode node = new ListNode(c);
            node.next = res;
            res = node;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        ListNode tail = l1;
        tail.next = new ListNode(2);
        tail = tail.next;
        tail.next = new ListNode(4);
        tail = tail.next;
        tail.next = new ListNode(3);
        tail = tail.next;
        ListNode l2 = new ListNode(5);
        tail = l2;
        tail.next = new ListNode(6);
        tail = tail.next;
        tail.next = new ListNode(4);

        print(l1);
        print(l2);
        print(new Solution().sum(l1, l2));
    }

    private static void print(ListNode l) {
        ListNode curr = l;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
