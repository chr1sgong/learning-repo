package io.chr1s.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST implements MST {
    /**
     * 最小生成树的顶点
     */
    private boolean[] marked;

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 横切边（包括失效的边）
     */
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        this.pq = new MinPQ<>();
        this.marked = new boolean[G.V()];
        this.mst = new Queue<>();

        // 假设G是连通的
        visit(G, 0);
        while (!pq.isEmpty()) {
            // 从pq中得到权重最小的边
            Edge e = pq.delMin();

            int v = e.either();
            int w = e.other(v);
            // 跳过失效的边
            if (marked[v] && marked[w]) continue;
            // 将边添加到树中
            mst.enqueue(e);
            // 将顶点(v 或 w)添加到树中
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double res = 0.0d;
        for (Edge e : edges()) res += e.weight();
        return res;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
