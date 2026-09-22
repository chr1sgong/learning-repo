package io.chr1s.ds.graph;

public class UnionFindByRank implements UnionFind {

    private int[] root;
    private int[] rank;

    public UnionFindByRank(int size) {
        this.root = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; ++i) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 路径压缩版。每次沿节点往根节点递归调用的时候，将当前节点的父节点直接设置为根节点
     * @param x
     * @return
     */
    @Override
    public int find(int x) {
        int rootX = root[x];
        if (x == rootX) {
            return x;
        }
        root[x] = find(rootX);
        return root[x];
    }

    @Override
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootX] = rootY;
                rank[rootY] += 1;
            }
        }
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFindByRank(10);
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
