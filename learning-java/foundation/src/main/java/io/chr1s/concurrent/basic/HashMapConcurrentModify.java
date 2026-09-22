package io.chr1s.concurrent.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapConcurrentModify {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<Integer> iterator = map.keySet().iterator();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (;;) {
                    if (iterator.hasNext()) {
                        int key = iterator.next();
                        System.out.print(map.get(key) + " ");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1000; i < 1100; i++) {
                    map.put(i, i);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
