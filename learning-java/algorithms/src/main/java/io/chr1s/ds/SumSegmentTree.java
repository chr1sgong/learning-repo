package io.chr1s.ds;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-30
 */
public class SumSegmentTree {
    int[] st; // array that stores segment tree nodes

    public SumSegmentTree(int[] arr) {
        int n = arr.length;
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        st = new int[maxSize];
        build(arr, 0, n - 1, 0);
    }

    // top-down
    private int build(int[] arr, int ss, int se, int sIdx) {
        if (ss == se) {
            st[sIdx] = arr[ss];
            return st[sIdx];
        }
        int mid = getMid(ss, se);
        st[sIdx] = build(arr, ss, mid, sIdx * 2 + 1) + build(arr, mid + 1, se, sIdx * 2 + 2);
        return st[sIdx];
    }

    private int getMid(int start, int end) {
        return start + (end - start) / 2;
    }

    private int getSum(int[] nums, int rs, int re) {
        int n = nums.length;
        if (rs < 0 || re >= n || rs > re) {
            throw new IllegalArgumentException();
        }
        return doGetSum(0, n - 1, rs, re, 0);
    }

    private int doGetSum(int ss, int se, int rs, int re, int sIdx) {
        // 当前节点区间
        if (rs <= ss && re >= se) {
            return st[sIdx];
        }
        // 非法区间
        if (se < rs || ss > re) {
            return 0;
        }
        // 计算中间区间
        int mid = getMid(ss, se);
        return doGetSum(ss, mid, rs, re, 2 * sIdx + 1) + doGetSum(mid + 1, se, rs, re, 2 * sIdx + 2);
    }

    public void update(int[] arr, int idx, int newVal) {
        if (idx < 0 || idx >= arr.length) {
            throw new IllegalArgumentException();
        }
        int diff = newVal - arr[idx];
        arr[idx] = newVal;
        doUpdate(0, arr.length - 1, idx, diff, 0);
    }

    // top-down
    private void doUpdate(int ss, int se, int idx, int diff, int sIdx) {
        if (idx < ss || idx > se) {
            return;
        }
        st[sIdx] = st[sIdx] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            doUpdate(ss, mid, idx, diff, 2 * sIdx + 1);
            doUpdate(mid + 1, se, idx, diff, 2 * sIdx + 2);
        }
    }

    public static void main(String[] args) {
        int[] arr = {11, 22,33 ,4, 5, 6, 45, 74, 8};
        SumSegmentTree st = new SumSegmentTree(arr);
        for (int i = 0; i < st.st.length; ++i) {
            System.out.print(st.st[i] + ", ");
        }
        System.out.println();
        System.out.println(st.getSum(arr, 1, 4));
        st.update(arr, 2, 32);
        System.out.println(st.getSum(arr, 1, 4));
    }
}
