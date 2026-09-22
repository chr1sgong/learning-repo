package io.chr1s.graph;

public class DepthFirstSearchDemo {

    private boolean[] marked;

    private int count;

    public DepthFirstSearchDemo(Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph G, int s) {
        for (Integer v : G.adj(s)) {
            if (marked[v]) continue;
            marked[v] = true;
            count++;
            dfs(G, v);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return this.count;
    }
}
