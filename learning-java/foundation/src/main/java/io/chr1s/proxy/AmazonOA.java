package io.chr1s.proxy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AmazonOA {

    public int[] combinePackage(int[] packageWeights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int currMax = packageWeights[packageWeights.length  - 1];
        int i = packageWeights.length - 2;
        while (i >= 0) {
            if (packageWeights[i] < currMax) {
                currMax += packageWeights[i];
                i--;
            } else {
                stack.push(currMax);
                currMax = packageWeights[i];
                i--;
            }
        }

        stack.push(currMax);

        int[] arr = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            arr[index++] = stack.pop();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] res = new AmazonOA().combinePackage(new int[]{2, 9, 10, 3, 7});
        for (int item : res) {
            System.out.print(item + " ");
        }
    }
}
