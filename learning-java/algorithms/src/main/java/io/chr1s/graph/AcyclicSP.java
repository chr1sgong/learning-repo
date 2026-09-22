//package io.chr1s.graph;
//
//import edu.princeton.cs.algs4.Bag;
//import edu.princeton.cs.algs4.Stack;
//
//public class AcyclicSP {
//
//    private DirectedEdge[] edgeTo;
//
//    private double[] distTo;
//
//    public AcyclicSP(EdgeWeightedDigraph G, int s) {
//        this.edgeTo = new DirectedEdge[G.V()];
//        this.distTo = new double[G.V()];
//
//        for (int v = 0; v < G.V(); v++) {
//            distTo[v] = Double.POSITIVE_INFINITY;
//        }
//        distTo[s] = 0.0d;
//
//        Topological top = new Topological(G);
//        for (int v : top.order()) {
//            relax(G, v);
//        }
//    }
//
//    private void relax(EdgeWeightedDigraph G, int v) {
//
//    }
//
//    public double distTo(int v) {
//        return distTo[v];
//    }
//
//    public boolean hasPathTo(int v) {
//        return distTo[v] != Double.POSITIVE_INFINITY;
//    }
//
//    public Iterable<DirectedEdge> pathTo(int v) {
//        Stack<DirectedEdge> path = new Stack<>();
//        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
//            path.push(e);
//        }
//        return path;
//    }
//}
