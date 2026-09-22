package io.chr1s.ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-23
 */
public class SkipList<K extends Comparable<K>, V> {

    private static final int MAX_LEVEL = 32;

    private SkipNode<K, V> head;
    private int level;
    private Random random;

    public SkipList() {
        this.random = new Random();
        this.head = new SkipNode<>(null, null); // 哑结点
    }

    public SkipNode<K, V> search(K key) {
        SkipNode<K, V> curr = head;
        while (curr != null) {
            int temp = compareKey(curr.key, key);
            if (temp == 0) return curr;
            else if (curr.next == null || compareKey(curr.next.key, key) > 0) {
                curr = curr.down;
            } else {
                curr = curr.next;
            }
        }
        return null;
    }

    // 1. find the key
    // 2. delete it and return the value
    public V delete(K key) {
        SkipNode<K, V> curr = head;
        V val = null;
        while (curr != null) {
            if (curr.next == null) {
                curr = curr.down;
            } else {
                int temp = compareKey(curr.next.key, key);
                if (temp == 0) {
                    val = curr.next.value;
                    curr.next = curr.next.next;
                    curr = curr.down; // 继续向下删. 这一步很重要
                } else if (temp < 0) {
                    curr = curr.next;
                } else {
                    curr = curr.down;
                }
            }
        }
        return val;
    }

    public void add(SkipNode<K, V> node) {
        K key = node.key;
        SkipNode<K, V> existed = search(key);
        if (existed != null) {
            existed.value = node.value;
            return;
        }
        // 存储向下的节点，这些节点可能在右侧插入
        Deque<SkipNode<K, V>> deque = new ArrayDeque<>();
        SkipNode<K, V> team = head;
        while (team != null) {
            if (team.next == null) {
                team = team.down;
            } else if (compareKey(team.next.key, key) > 0) {
                deque.push(team);
                team = team.down;
            } else {
                team = team.next;
            }
        }
        // 当前层数，从第一层添加
        int level = 1;
        SkipNode<K, V> downNode = null;
        while (!deque.isEmpty()) {
            // 在该层插入node
            team = deque.pop(); // 抛出待插入的左侧节点
            SkipNode<K, V> nodeTeam = new SkipNode<>(node.key, node.value);
            nodeTeam.down = downNode;  // 垂直方向
            downNode = nodeTeam;
            if (team.next == null) {
                team.next = nodeTeam;
            } else {
                nodeTeam.next = team.next;
                team.next = nodeTeam;
            }

            if (level > MAX_LEVEL) {
                break;
            }
            double num = random.nextDouble();
            if (num > 0.5) {
                break;
            }
            level++;
//            if (level > highLevel) {
//                highLevel = level;
//                SkipNode<K, V> highLevelNode = new SkipNode<>(null, null);
//                highLevelNode.down = head;
//                head = highLevelNode;
//                deque.push(head);
//            }
        }

    }

    private int compareKey(K k1, K k2) {
        if (k1 == null) return -1;
        return k1.compareTo(k2);
    }


    static class SkipNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private SkipNode<K, V> next;
        private SkipNode<K, V> down;

        public SkipNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
