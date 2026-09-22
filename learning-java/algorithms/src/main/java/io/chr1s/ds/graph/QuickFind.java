package io.chr1s.ds.graph;

/**
 * QuickFind的思路：root中的value是index的根节点。将两个index相连的思路是使其中几个index及其所有的子节点的根节点的值设置为另外一个
 */
public class QuickFind implements UnionFind {

    private int[] root;

    public QuickFind(int size) {
        this.root = new int[size];
        for (int i = 0; i < size; ++i) {
            root[i] = i;
        }
    }

    @Override
    public int find(int x) {
        return root[x];
    }

    @Override
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            for (int i = 0; i < root.length; ++i) {
                if (root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        UnionFind uf = new QuickFind(10);
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
