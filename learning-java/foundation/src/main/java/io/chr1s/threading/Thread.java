package io.chr1s.threading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2023-03-08
 */
public class Thread {
    private static AtomicInteger threadCount = new AtomicInteger();

    public void run() {
        System.out.println("Running Thread " + threadCount.incrementAndGet());
    }

    public void start() {
        start0();
    }

    private native void start0();


    public static void main(String[] args) {
        new Thread().start();
    }
}
