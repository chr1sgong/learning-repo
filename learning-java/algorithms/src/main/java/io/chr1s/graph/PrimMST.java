package io.chr1s.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * Prim算法的即时版本。
 * 思路：
 * 我们感兴趣的只是连接树顶点和非树顶点中权重最小的边。
 * 当我们将顶点v添加到树中时，对于每个非树顶点w产生的变化只可能是是的w到最小生成树的距离更近了。
 * 因此，我们不需要在优先队列中保存所有从w到树顶点的边，而值需要保存其中权重最小的那条。
 * 在将v添加到树中后检查是否需要更新这条权重最小的边（v-w的权重可能更小），只需要遍历v的邻接链表就能实现。
 *
 * 实现：
 * 使用两个顶点索引的数组edgeTo[]和distTo[]
 * 如果顶点v不在树中，但至少含有一条边和树相连，那么edgeTo[v]是将v和树链接的最短边，distTo[v]为这条边的权重。
 * 所有上述顶点v都保存在一条索引优先队列中，索引v关联的值是edgeTo[v]的边的权重
 */
public class PrimMST implements MST {

    /**
     * 距离树最近的边
     */
    private Edge[] edgeTo;

    /**
     * distTo[w] = edgeTo[w].weight()
     */
    private double[] distTo;

    /**
     * 如果v在树中则为true
     */
    private boolean[] marked;

    /**
     * 有效的横切边
     */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        this.edgeTo = new Edge[G.V()];
        this.distTo = new double[G.V()];
        this.marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());

        // 用顶点0和权重0初始化pq
        distTo[0] = 0.0d;
        pq.insert(0, distTo[0]);
        while (!pq.isEmpty()) {
            // 将最近的顶点添加到树中
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 将顶点v添加到树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                // 连接w和树的最佳边Edge变为e
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        Bag<Edge> res = new Bag<>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] != null) {
                res.add(edgeTo[v]);
            }
        }
        return res;
    }

    @Override
    public double weight() {
        double res = 0.0d;
        for (Edge e : edges()) res += e.weight();
        return res;
    }

    public static void main(String[] args) {

        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));

        PrimMST primMST = new PrimMST(G);

        for (Edge e : primMST.edges()) {
            StdOut.println(e);
        }
        StdOut.println(primMST.weight());
    }
}
