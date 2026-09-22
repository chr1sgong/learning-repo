package io.chr1s.leetcode;

public class ReverseKGroup {
    /**
     *
     * @param head ListNode类 
     * @param k int整型 
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
//        System.out.println(head == null ? null : head.val);
        if (head == null) return head;
        int cnt = 1;
        ListNode curr = head;
        while (cnt < k && curr != null) {
            cnt++;
            curr = curr.next;
        }
        if (curr == null) return head;
        ListNode temp = curr.next;
        curr.next = null;
        ListNode newHead = reverseKGroup(temp, k);
        while (head != null) {
            System.out.println(head.val);
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tail = head;
        tail.next = new ListNode(2);
        tail = tail.next;
        tail.next = new ListNode(3);
        tail = tail.next;
        tail.next = new ListNode(4);
        tail = tail.next;
        tail.next = new ListNode(5);
        tail = tail.next;
        ListNode res = new ReverseKGroup().reverseKGroup(head, 2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}