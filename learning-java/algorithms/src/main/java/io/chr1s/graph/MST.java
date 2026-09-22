package io.chr1s.graph;

/**
 * 最小生成树API
 */
public interface MST {

    /**
     * 最小生成树的所有边
     * @return
     */
    Iterable<Edge> edges();

    /**
     * 最小生成树的权重
     * @return
     */
    double weight();
}
