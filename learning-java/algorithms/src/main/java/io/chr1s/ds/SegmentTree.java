package io.chr1s.ds;

import java.util.Arrays;

/**
 * Representation of Segment Tree
 * 1. Leaf Nodes are the elements of the input array
 * 2. Each internal node represents some merging of the leaf nodes. The merging may be different for different problems.
 *
 * An array representation of tree is used to represent Segment Trees.
 * For each node at index i (0-based), the left child is at index 2 * i + 1, right child at 2 * i + 2,
 * and the parent is at (i - 1) / 2.
 *
 * Segment tree is not a complete binary. It is a full binary tree (every node has 0 or 2 children) and all levels are
 * filled except possibly the last level.
 *
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-01
 */
public class SegmentTree<E> {

    private E[] st;     // The array that stores segment tree nodes.
    private E[] data;   // raw data
    private Merge<E> merge;

    /**
     * Construct a segment tree from an array.
     * every time we divide the current segment into two halves, and call the same procedure on both halves,
     * and for each such segment, we store the internal node element.
     *
     * @param arr
     * @param
     */
    private SegmentTree(E[] arr) {

    }

    public SegmentTree(E[] arr, Merge<E> merge) {
        if (merge == null) {
            throw new IllegalArgumentException("merge is null");
        }
        this.merge = merge;
        int n = arr.length;
        data = Arrays.copyOf(arr, n);
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        st = (E[]) new Object[2 * (int) Math.pow(2, height) - 1];
        build(0, 0, n - 1);
    }

    private void build(int sIdx, int left, int right) {
        // leaves
        if (left == right) {
            st[sIdx] = data[left];
            return;
        }
        int mid = left + ((right - left) >> 1);
        int leftChildIdx = sIdx * 2 + 1;
        int rightChildIdx = sIdx * 2 + 2;
        build(leftChildIdx, left, mid);
        build(rightChildIdx, mid + 1, right);
        st[sIdx] = merge.merge(st[leftChildIdx], st[rightChildIdx]);
    }

    public E rangeQuery(int queryL, int queryR) {
        if (queryL > queryR || queryL < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("out of range");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int sIdx, int dL, int dR, int queryL, int queryR) {
        if (dL == queryL && dR == queryR) {
            return st[sIdx];
        }
        int leftChildIdx = leftChildIdx(sIdx);
        int rightChildIdx = rightChildIdx(sIdx);
        int dMid = getMidIdx(dL, dR);
        if (queryR <= dMid) { // 左半区
            return query(leftChildIdx, dL, dMid, queryL, queryR);
        } else if (queryL > dMid) { // 右半区
            return query(rightChildIdx, dMid + 1, dR, queryL, queryR);
        } else {  // 跨左右半区 queryL <= sMid < queryR
            E leftPart = query(leftChildIdx, dL, dMid, queryL, dMid);
            E rightPart = query(rightChildIdx, dMid + 1, dR, dMid + 1, queryR);
            return merge.merge(leftPart, rightPart);
        }
    }

    public void update(int idx, E e) {
        if (idx < 0 || idx >= data.length) {
            throw new IllegalArgumentException("out of range");
        }
        update(0, 0, data.length - 1, idx, e);
    }

    private void update(int sIdx, int dL, int dR, int idx, E e) {
        if (dL == dR) {
            st[sIdx] = e;
            return;
        }

        int leftChildIdx = leftChildIdx(sIdx);
        int rightChildIdx = rightChildIdx(sIdx);
        int dMid = getMidIdx(dL, dR);
        if (idx > dMid) {
            update(rightChildIdx, dMid + 1, dR, idx, e);
        } else {
            update(leftChildIdx, dL, dMid, idx, e);
        }
        st[sIdx] = merge.merge(st[leftChildIdx], st[rightChildIdx]);
    }

    private int getMidIdx(int dL, int dR) {
        return dL + ((dR - dL) >> 1);
    }

    private int leftChildIdx(int sIdx) {
        return sIdx * 2 + 1;
    }

    private int rightChildIdx(int sIdx) {
        return sIdx * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : st) {
            sb.append(e).append(", ");
        }
        return sb.substring(0, sb.length() - 1);
    }


    public static interface Merge<E> {
        E merge(E a, E b);
    }

    public static void main(String[] args) {
        Integer[] arr = {11, 22,33 ,4, 5, 6, 45, 74, 8};
        SegmentTree<Integer> st = new SegmentTree<>(arr, Integer::sum);
        System.out.println(st.rangeQuery(1, 4));
        st.update(2, 32);
        System.out.println(st.rangeQuery(1, 4));
    }

}
