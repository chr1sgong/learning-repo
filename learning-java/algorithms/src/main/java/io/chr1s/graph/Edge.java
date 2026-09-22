package io.chr1s.graph;

public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    /**
     * 用于初始化的构造函数
     * @param v
     * @param w
     * @param weight
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 边的权重
     * @return
     */
    public double weight() {
        return this.weight;
    }

    /**
     * 边两端的顶点之一
     * @return
     */
    public int either() {
        return this.v;
    }

    /**
     * 另一个顶点
     * @return
     */
    public int other(int vertex) {
        if (vertex == v) return this.w;
        else if (vertex == w) return this.v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else return 0;
    }

    /**
     * 对象的字符串表示
     * @return
     */
    @Override
    public String toString() {
        return String.format("%d-%d %.2f", this.v, this.w, this.weight);
    }
}
