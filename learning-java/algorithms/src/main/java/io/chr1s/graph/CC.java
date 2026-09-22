package io.chr1s.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;

public class CC {

    private boolean[] marked;

    private int[] id;

    private int count;

    public CC(Graph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                this.count++;
            }
        }
    }

    private void dfs(Graph G, int s) {
        if (marked[s]) return;
        marked[s] = true;
        id[s] = count;
        for (int v : G.adj(s))
        dfs(G, v);
    }

    /**
     * 判断顶点v和w是否连通
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 返回连通分量
     * @return
     */
    public int count() {
        return this.count;
    }

    /**
     * 查询v所在的连通分量的标识符(0 ~ count() - 1)
     * @param v
     * @return
     */
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws IOException {
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

        int M = cc.count;
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Bag<>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);
        }
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
