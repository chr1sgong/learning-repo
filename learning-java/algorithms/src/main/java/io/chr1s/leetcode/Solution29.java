package io.chr1s.leetcode;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-08-04
 */
public class Solution29 {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        if (dividend == 0) {
            return 0;
        }

        boolean flag = false;
        if (dividend > 0) {
            dividend = -dividend;
            flag = !flag;
        }
        if (divisor > 0) {
            divisor = -divisor;
            flag = !flag;
        }

        int left = 1;
        int right = Integer.MAX_VALUE;
        int ans = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return flag ? -ans : ans;
    }

    public int divide2(int dividend, int divisor) {
        long a = dividend;
        long b = divisor;
        boolean neg = (a > 0 && b < 0) || (a < 0 && b > 0);
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }

        long left = 0;
        long right = a;
        while (left < right) {
//            l + r + 1 >> 1
            long mid = (left + right + 1) >> 1;
            if (multiply(b, mid) < a) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        long res = neg ? -left : left;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }

    private long multiply(long a, long b) {
        if (a < b) {
            return multiply(b, a);
        }
        if (b == 1) {
            return a;
        }
        if ((b & 1) == 1) {
            return a + multiply(a, b - 1);
        }
        return multiply(a, b >> 1) << 1;
    }


    private boolean quickAdd(int a, int b, int c) {
        int result = 0;
        int add = a;
        while (b != 0) {
            if ((b & 1) != 0) {
                if (result < c - add) {
                    return false;
                }
                result += add;
            }
            if (b != 1) {
                if (add < c - add) {
                    return false;
                }
                add += add;
            }
            b >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution29 s = new Solution29();
        System.out.println(s.divide(10, 3));
        System.out.println(s.divide2(10 ,3));
    }
}
