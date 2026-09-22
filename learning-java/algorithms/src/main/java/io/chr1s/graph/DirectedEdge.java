package io.chr1s.graph;

/**
 * 有向边
 */
public class DirectedEdge {

    /**
     * 边的起点
     */
    private final int v;
    /**
     * 边的终点
     */
    private final int w;
    /**
     * 边的权重
     */
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    /**
     * 指出边的顶点
     * @return
     */
    public int from() {
        return this.v;
    }

    /**
     * 边指向的顶点
     * @return
     */
    public int to() {
        return this.w;
    }

    /**
     * 对象的字符串表示
     * @return
     */
    @Override
    public String toString() {
        return String.format("%d->%d %.2f", this.v, this.w, this.weight);
    }
}
