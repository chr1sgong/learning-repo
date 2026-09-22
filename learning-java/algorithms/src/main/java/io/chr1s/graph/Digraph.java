package io.chr1s.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {

    private final int V;

    private int E;

    private Bag<Integer>[] adj;

    /**
     * 创建一幅含有V个顶点但没有边的有向图
     * @param V
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    /**
     * 从输入流in中读取一幅有向图
     * @param in
     */
    public Digraph(In in) {
        this(in.readInt());

        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            // 添加边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * 顶点总数
     * @return
     */
    public int V() {
        return this.V;
    }

    /**
     * 边的总数
     * @return
     */
    public int E() {
        return this.E;
    }

    /**
     * 向图中添加一条v -> w的边
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        this.E++;
    }

    /**
     * 有v指出的边所连接的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return this.adj[v];
    }

    /**
     * 该图的反向图
     * @return
     */
    public Digraph reverse() {
        Digraph R = new Digraph(this.V);
        for (int v = 0; v < V; v++) {
            for (int w : this.adj[v]) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString() {
        String s = V + "vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
