package io.chr1s.ds.graph;

public interface UnionFind {

    int find(int x);

    void union(int x, int y);

    boolean connected(int x, int y);
}
