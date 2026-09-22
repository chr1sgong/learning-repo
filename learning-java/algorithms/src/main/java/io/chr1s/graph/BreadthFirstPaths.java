package io.chr1s.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) throws IOException {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        int t = Integer.parseInt(args[2]);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(G, s);

        if (breadthFirstPaths.hasPathTo(t)) {
            for (int v : breadthFirstPaths.pathTo(t)) {
                StdOut.print(v);
                if (v != t) {
                    StdOut.print(" - ");
                }
            }
        }
    }
}
