package io.chr1s.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;

/**
 * 在图中进行搜索
 */
public class SearchGraphDemo {

    private Graph graph;

    private int s;

    private boolean[] marked;

    public SearchGraphDemo(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        this.marked = new boolean[graph.V()];
        dfs(s);
    }

    /**
     * 判断是否和源点连通
     * @param v
     * @return
     */
    public  boolean marked(int v) {
        return marked[v];
    }

    /**
     * 找到与源点相邻的所有点
     * @param s
     */
    private void dfs(int s) {
        for (Integer v : graph.adj(s)) {
            if (marked[v]) continue;
            marked[v] = true;
            dfs(v);
        }
    }

    /**
     * 与源点连通的顶点总数
     * @return
     */
    public int count() {
        int res = 0;
        for (Integer integer : graph.adj(s)) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        SearchGraphDemo searchGraphDemo = new SearchGraphDemo(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (searchGraphDemo.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
        if (searchGraphDemo.count() != G.V()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");

        StdOut.print(G.toString());
    }
}
