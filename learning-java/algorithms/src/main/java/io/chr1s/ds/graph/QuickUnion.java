package io.chr1s.ds.graph;

/**
 * quick union的思路是root数组存的是index的parent节点
 */
public class QuickUnion implements UnionFind {

    private int[] root;

    public QuickUnion(int size) {
        this.root = new int[size];
        for (int i = 0; i < size; ++i) {
            root[i] = i;
        }
    }

    @Override
    public int find(int x) {
        int rootX = root[x];
        while (rootX != root[rootX]) {
            rootX = root[rootX];
        }
        return rootX;
    }

    @Override
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        UnionFind uf = new QuickUnion(10);
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);
        System.out.println(uf.connected(1, 5));
        System.out.println(uf.connected(5, 7));
        System.out.println(uf.connected(4, 9));
        uf.union(4, 9);
        System.out.println(uf.connected(4, 9));
    }
}
