package io.chr1s.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {

    /**
     * 符号名->索引
     */
    private ST<String, Integer> st;

    /**
     * 索引 -> 符号名
     */
    private String[] keys;

    /**
     * 图
     */
    private Graph G;


    /**
     * 根据filename指定的文件构造图
     * 使用delim来分隔顶点名
     * @param filename
     * @param delim
     */
    public SymbolGraph(String filename, String delim) {
        st = new ST<>();
        In in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        this.G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * 判断key是否是一个订单
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return st.contains(key);
    }

    /**
     * 计算key的索引值
     * @param key
     * @return
     */
    public int index(String key) {
        return st.get(key);
    }

    /**
     * 获取索引为v的顶点名
     * @param v
     * @return
     */
    public String name(int v) {
        return keys[v];
    }

    /**
     * 隐藏的Graph对象
     * @return
     */
    public Graph G() {
        return this.G;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Graph g = sg.G();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : g.adj(sg.index(source))) {
                StdOut.println("   " + sg.name(w));
            }
        }
    }
}
