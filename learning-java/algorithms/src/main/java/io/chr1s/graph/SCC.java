package io.chr1s.graph;

public interface SCC {

    /**
     * 图中的强连通分量的总数
     * @return
     */
    int count();

    /**
     * 顶点v和w是否强连通
     * @param v
     * @param w
     * @return
     */
    boolean stronglyConnected(int v, int w);

    /**
     * v 所在的强连通分量的表标识符，0~count() - 1
     * @param v
     * @return
     */
    int id(int v);
}
