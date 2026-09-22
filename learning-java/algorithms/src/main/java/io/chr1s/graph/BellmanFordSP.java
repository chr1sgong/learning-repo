//package io.chr1s.graph;
//
//import edu.princeton.cs.algs4.Queue;
//import edu.princeton.cs.algs4.Stack;
//
///**
// * Bellman-Ford算法实现。
// * 使用一条用来保存即将被放松的顶点的队列queue
// * 一个由顶点索引的boolean数组onQ[], 用来指示顶点是否已经存在于队列中，以防止将顶点重复插入队列。
// * 首先，将起点s加入队列中，然后进入一个循环，其中每次都从队列中取出一个顶点并将其放松。
// */
//public class BellmanFordSP {
//
//    /**
//     * 从起点到某个顶点的路径长度
//     */
//    private double[] distTo;
//
//    /**
//     * 从起点到某个顶点的最后一条边
//     */
//    private DirectedEdge[] edgeTo;
//
//    /**
//     * 该顶点是否在队列中
//     */
//    private boolean[] onQ;
//
//    /**
//     * 正在被放松的顶点
//     */
//    private Queue<Integer> queue;
//
//    /**
//     * relax()的调用次数
//     */
//    private int cost;
//
//    /**
//     * edgeTo[] 中的是否有负权重环
//     */
//    private Iterable<DirectedEdge> cycle;
//
//    public BellmanFordSP(EdgeWeightedGraph G, int s) {
//        this.distTo = new double[G.V()];
//        this.edgeTo = new DirectedEdge[G.V()];
//        this.onQ = new boolean[G.V()];
//        this.queue = new Queue<>();
//        for (int v = 0; v < G.V(); v++) {
//            distTo[v] = Double.POSITIVE_INFINITY;
//        }
//        distTo[s] = 0.0d;
//
//        queue.enqueue(s);
//        onQ[s] = true;
//        while (!queue.isEmpty() && !hasNegativeCycle()) {
//            int v = queue.dequeue();
//            onQ[v] = false;
//            relax(G, v);
//        }
//    }
//
//    private void relax(EdgeWeightedGraph G, int v) {
//
//    }
//
//    public double distTo(int v) {
//        return this.distTo[v];
//    }
//
//    public boolean hasPathTo(int v) {
//        return this.distTo[v] != Double.POSITIVE_INFINITY;
//    }
//
//    public Iterable<DirectedEdge> pathTo(int v) {
//        if (!hasPathTo(v)) return null;
//        Stack<DirectedEdge> stack = new Stack<>();
//        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
//            stack.push(e);
//        }
//        return stack;
//    }
//
//    private void findNegativeCycle() {
//        int V = edgeTo.length;
//        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
//        for (int v = 0; v < V; v++) {
//            if (edgeTo[v] != null) {
//                spt.addEdge(edgeTo[v]);
//            }
//        }
//        EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(spt);
//        cycle = cf.cycle();
//    }
//
//    public boolean hasNegativeCycle() {
//        return cycle != null;
//    }
//
//    public Iterable<Edge> negativeCycle() {
//        return cycle;
//    }
//}
