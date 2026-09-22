package io.chr1s.ds;

/**
 * 树状数组用于高效求数组前缀和的同时实现单点更新
 *
 * 数组下标从1开始计算
 * 对于长度为n的数组nums，定义树状数组tree[], 使得树状数组的节点i满足tree[i] = nums[i] + nums[i - 1] + ... + nums[i - 2 ^ k + 1]
 * 其中，k为i的二进制位的最右边一个为1的bit位右边的0的个数, 记作2 ^ k = lowbit(i)
 * 例如，假设i = 13, 13的二进制表示为1101, lowbit(13) = 0001，因此tree[13] = nums[13]
 * 再例如i = 12, 12的二进制表示为1100, lowbit(12) = 0100, tree[12] = nums[12] + nums[11] + nums[10] + nums[9]
 * 再例如i = 10, 10的二进制表示为1010，lowbit(10) = 0010，tree[12] = nums[10] + nums[9]
 * 再例如i = 11, 11的二进制表示为1011，lowbit(11) = 0001, tree[11] = nums[11]
 * 再例如i = 8, 8的二进制表示为0100, lowbit(8) = 0000, tree[8] = nums[8] + nums[7] + nums[6] + ... + nums[1]
 *
 * 可以发现，树状数组中，下标为奇数的节点与原数组相同(叶子节点)；下标为偶数的节点存储的是从当前下标到下标为i - 2^k + 1的值的和（中间节点）。
 * 从底向上看，下标中最后一个1后面的0的个数，表示树的高度，因此下标为奇数的节点都为叶子节点；下标为xxx10形式的节点为倒数第二层节点，依此类推
 *
 *
 * Binary Indexed Tree is represented as an array. Each node of the BIT stores the sum of some
 * elements of the input array. The size of the BIT is equal to the size of the input array.
 *
 * Binary representation of each index. i.e 13 = 2 ^ 3 + 2 ^ 2 + 2 ^ 0
 * We can divide the array in binary ranges
 *
 *
 *
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-03-01
 */
public class BIT {

    private int[] data;
    private int count;

    public BIT(int capacity) {
        this.data = new int[capacity + 1];
        this.count = 0;
    }

    public BIT(int[] arr) {
        this.data = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; ++i) {
            add(i, arr[i - 1]);
        }
    }

    // index is 1-based
    public void add(int index, int val) {
        while (index < data.length) {
            data[index] += val;
            index += lowBit(index);
        }
    }

    /**
     * return the sum of the sub-array in [0,...,index]
     *
     * @param index
     * @return
     */
    public int prefixSum(int index) {
        int sum = 0;
        while (index >= 1) {
            sum += data[index];
            index -= lowBit(index);
        }
        return sum;
    }

    public int rangeSum(int from, int to) {
        return prefixSum(to) - prefixSum(from - 1);
    }

    /**
     * update indexed value to target val
     * @param index
     * @param delta
     */
    public void updateDelta(int index, int delta) {
        index += 1;
        while (index <= data.length) {
            data[index] += delta;
            index += lowBit(index);
        }
    }

    private int lowBit(int n) {
        return n & -n;
    }

    public static void main(String[] args) {
        BIT bit = new BIT(new int[]{3,2,1,4,0});
        System.out.println(bit.prefixSum(2));
    }
}
