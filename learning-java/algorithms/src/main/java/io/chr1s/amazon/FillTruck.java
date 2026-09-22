package io.chr1s.amazon;

import java.util.PriorityQueue;

public class FillTruck {

    int maxUnit(int[][] boxTypes, int truckSize) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(boxTypes[i2][1], boxTypes[i1][1]));
        for (int i = 0; i < boxTypes.length; ++i) {
            pq.offer(i);
        }
        int res = 0;
        while (!pq.isEmpty() && truckSize > 0) {
            int idx = pq.poll();
            int d = Math.min(truckSize, boxTypes[idx][0]);
            truckSize -= d;
            res += boxTypes[idx][1] * d;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FillTruck().maxUnit(new int[][]{{1,3}, {2,2}, {3,1}}, 4));
        System.out.println(new FillTruck().maxUnit(new int[][]{{5,10}, {2,5}, {4,7}, {3,9}}, 10));

    }
}
