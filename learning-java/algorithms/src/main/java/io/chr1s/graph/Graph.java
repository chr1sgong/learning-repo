package io.chr1s.graph;

import edu.princeton.cs.algs4.In;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    /**
     * 顶点数
     */
    private final int V;

    /**
     * 边的数目
     */
    private int E;

    /**
     * 邻接表
     */
    private List<Integer>[] adj;

    /**
     * chuan
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (List<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    /**
     * 从输入流构建图
     * @param in
     */
    public Graph(In in) throws IOException {
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
     * 获取顶点书
     * @return
     */
    public int V() {
        return this.V;
    }

    /**
     * 获取边数
     * @return
     */
    public int E() {
        return this.E;
    }

    /**
     * 向图中添加一条边
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    /**
     * 获取和v相邻的所有顶点
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 对象字符串表示
     * @return
     */
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
