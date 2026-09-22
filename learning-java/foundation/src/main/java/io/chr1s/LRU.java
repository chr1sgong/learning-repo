package io.chr1s;

import java.util.*;


public class LRU {
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */

    private Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public int[] LRU (int[][] operators, int k) {
        // write code here
        this.capacity = k;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            int[] operator = operators[i];
            if (operator[0] == 1) {
                // set
                set(operator[1], operator[2]);
            } else {
                // get
                res.add(get(operator[1]));
            }
        }
        int[] output = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            output[i] = res.get(i);
        }
        return output;
    }

    private void set(int key, int val) {
        Node node = map.get(key);
        if (node != null) {
            node.val = val;
            top(node);
        } else {
            node = new Node(key, val);
            map.put(key, node);
            addNode(node);
        }
    }

    private int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        else {
            top(node);
            return node.val;
        }
    }

    private void top(Node node) {
        Node last = node.last;
        Node next = node.next;
        if (last == null) return;
        last.next = next;
        if (next != null) {
            next.last = last;
        } else {
            tail = last;
        }
        node.next = head;
        head.last = node;
        node.last = null;
        head = node;
    }

    private void addNode(Node node) {
        if (size == capacity) {
            removeLast();
        }
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.last = node;
            head = node;
        }
        map.put(node.key, node);
        size++;
    }

    private void removeLast() {
        Node node = tail;
        Node last = node.last;
        if (last == null) {
            tail = null;
        } else {
            last.next = null;
            tail.last = null;
            tail = last;
        }
        map.remove(node.key);
        size--;
    }

    public static class Node {

        public int key;

        public int val;

        public Node next;

        public Node last;

        public Node(int val, Node next, Node last) {
            this.val = val;
            this.next = next;
            this.last = last;
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

