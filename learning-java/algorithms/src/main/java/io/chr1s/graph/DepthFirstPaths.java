package io.chr1s.graph;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths implements Paths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;

    /**
     * 在G中找出所有起点为s的路径
     * @param G
     * @param s
     */
    public DepthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        for (int w : G.adj(v)) {
            if (marked[w]) continue;
            marked[w] = true;
            edgeTo[w] = v;
            dfs(G, w);
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v] = true;
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Deque<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) throws IOException {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Paths search = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
            }
            StdOut.println();
        }
    }


}
