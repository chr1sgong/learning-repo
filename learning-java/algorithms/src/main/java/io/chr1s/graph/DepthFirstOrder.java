package io.chr1s.graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {

    private boolean[] marked;
    /**
     * 所有顶点的前序排列
     */
    private Queue<Integer> pre;
    /**
     * 所有顶点的后序排列
     */
    private Queue<Integer> post;
    /**
     * 所有顶点的逆后序排列
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        this.pre = new Queue<>();
        this.post = new Queue<>();
        this.reversePost = new Stack<>();

        this.marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return this.pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }

    public Iterable<Integer> reversePost() {
        return this.reversePost;
    }

}
