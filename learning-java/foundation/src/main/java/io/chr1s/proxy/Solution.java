package io.chr1s.proxy;

import java.util.*;

public class Solution {


    public static List<Integer> distanceFrom(TreeNode root, TreeNode input, int K) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        dfs(root, null, parentMap);
        // BFS
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int distance = 0;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(input);
        queue.add(input);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                if (distance == K) {
                    res.add(curr.val);
                    continue;
                }
                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.add(curr.left);
                    visited.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.add(curr.right);
                    visited.add(curr.right);
                }
                // add parent
                if (parentMap.containsKey(curr) && parentMap.get(curr) != null && !visited.contains(parentMap.get(curr))) {
                    queue.add(parentMap.get(curr));
                    visited.add(parentMap.get(curr));
                }
            }
            distance++;
        }
        return res;
    }

    private static void dfs(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {

        parentMap.put(node, parent);
        if (node.left != null) {
            dfs(node.left, node, parentMap);
        }
        if (node.right != null) {
            dfs(node.right, node, parentMap);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(20, null, null);
        root.left = new TreeNode(8, null, null);
        root.right = new TreeNode(22, null, null);
        root.left.left = new TreeNode(4, null, null);
        root.left.right = new TreeNode(12, null, null);
        root.left.right.left = new TreeNode(10, null, null);
        root.left.right.right = new TreeNode(14, null, null);

        List<Integer> res = distanceFrom(root, root.left, 2);
        System.out.println(res);
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
