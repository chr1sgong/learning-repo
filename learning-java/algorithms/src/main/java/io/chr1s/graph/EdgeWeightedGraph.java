package io.chr1s.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图
 */
public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] edges;

    /**
     * 创建一幅含有V个顶点的空图
     * @param V
     */
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.edges = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            edges[v] = new Bag<>();
        }
    }

    /**
     * 从输入流中读取图
     * @param in
     */
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int edges = in.readInt();
        for (int i = 0; i < edges; i++) {
            Edge e = new Edge(in.readInt(), in.readInt(), in.readDouble());
            addEdge(e);
        }
    }

    /**
     * 图的顶点数
     * @return
     */
    public int V() {
        return this.V;
    }

    /**
     * 图的边数
     * @return
     */
    public int E() {
        return this.E;
    }

    /**
     * 向图中添加一条边
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.edges[v].add(e);
        this.edges[w].add(e);
        this.E++;
    }

    /**
     * 和v相关联的所有边
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v) {
        return edges[v];
    }

    /**
     * 图的所有边
     * @return
     */
    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }

    /**
     * 对象的字符串表示
     * @return
     */
    @Override
    public String toString() {
        return "EdgeWeightedGraph";
    }
}
