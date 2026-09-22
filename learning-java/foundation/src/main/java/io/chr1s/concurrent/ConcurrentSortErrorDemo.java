package io.chr1s.concurrent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-10-21
 */
public class ConcurrentSortErrorDemo {

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 10; i >= 0; i--) {
            list.add(i);
        }
        System.out.println("before concurrent sort: " + list);
        // 10个线程并发排序, 模拟并发访问
        for (int i = 0; i < 9; i++) {
            new Thread(() -> list.sort(Comparator.comparing(Integer::valueOf))).start();
        }
        // 等待排序跑完
        Thread.sleep(3000L);
        System.out.println("after concurrent sort: " + list);
    }
}
