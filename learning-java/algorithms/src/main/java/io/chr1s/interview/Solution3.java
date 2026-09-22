package io.chr1s.interview;

import java.util.Deque;
import java.util.LinkedList;

public class Solution3 {

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        boolean left2right = true;
//        List<TreeNode> currLevel = new ArrayList<>();
//        currLevel.add(root);
        Deque<TreeNode> currLevel = new LinkedList<>();
        currLevel.offerLast(root);
        while (!currLevel.isEmpty()) {
            Deque<TreeNode> nextLevel = new LinkedList<>();
            int size = currLevel.size();
            if (left2right) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = currLevel.pollFirst();
                    System.out.print(node.val + " ");
                    if (node.left != null) {
                        nextLevel.offerLast(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.offerLast(node.right);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = currLevel.pollLast();
                    System.out.print(node.val + " ");
                    if (node.right != null) {
                        nextLevel.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.offerFirst(node.left);
                    }
                }
            }
            currLevel = nextLevel;
            left2right = !left2right;

            System.out.println();
        }
    }

    //     1
    //   3   2
    // 6        4
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(6);
        root.right.right = new TreeNode(4);
        new Solution3().traverse(root);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}