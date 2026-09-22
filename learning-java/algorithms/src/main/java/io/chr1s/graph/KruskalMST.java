package io.chr1s.graph;

import edu.princeton.cs.algs4.*;

/**
 * Kruskal 算法
 * 按照边的权重顺序（从小到大 ）来进行处理。
 * 将边加入到最小生成树中，加入的边不会与已经加入的边构成环
 * 知道树中含有V-1条边为止。
 *
 * Prim算法是一条边一条边地来构建最小生成树，每一步都为一棵树添加一条边。
 * Kruskal算法在构造最小生成树的时候也是一条边一条边地构造，不同之处是他寻找的边会连接森林中的两棵树
 */
public class KruskalMST implements MST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        this.mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // 从pq得到权重最小的边和它的顶点
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            // 忽略失效的边
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double res = 0.0d;
        for (Edge e : mst) {
            res += e.weight();
        }
        return res;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));
        KruskalMST kruskalMST = new KruskalMST(G);
        for (Edge e : kruskalMST.edges()) {
            StdOut.println(e);
        }
        StdOut.println(kruskalMST.weight());
    }
}
