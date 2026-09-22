package io.chr1s.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC implements SCC {

    /**
     * 已访问过的顶点
     */
    private boolean[] marked;

    /**
     * 强连通分量的标识符
     */
    private int[] id;

    /**
     * 强连通分量的数量
     */
    private int count;

    public KosarajuSCC(Digraph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    @Override
    public int count() {
        return this.count;
    }

    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSCC scc = new KosarajuSCC(G);
        StdOut.println(scc.count + " components");
        Bag<Integer>[] components = (Bag<Integer>[])new Bag[scc.count()];
        for (int v = 0; v <  G.V(); v++) {
            if (components[scc.id(v)] == null) {
                components[scc.id(v)] = new Bag<>();
            }
            components[scc.id(v)].add(v);
        }
        for (Bag<Integer> component : components) {
            for (Integer v : component) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
