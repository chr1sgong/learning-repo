package io.chr1s.concurrent.basic;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程中断
 * @author gongqi
 * @since 2019-08-23
 */
public class ThreadInterrupt {

    private static final Object lock = new Object();

    /**
     * 线程调用sleep, 在sleep的过程中，如果其它线程通过interrupt()方法中断该线程，则sleep停止，并会立即响应中断，怎么实现的？
     */
    public void threadInterruptTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("now I'm going to sleep");
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    System.out.println("current time: " + new Date());
                    System.out.println("interrupted when sleeping");
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current time: " + new Date());
        thread.interrupt();
    }

    /**
     * 线程在等待锁的过程中被其它线程中断，此时线程不会响应中断，而是在获取锁后继续运行，此时需要检查中断标志位看其它线程是否有请求自己中断
     */
    public void threadSyncInterruptTest() {

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("current time: " + new Date());
                    System.out.println("got the lock");
                    System.out.println("my interrupt status: " + Thread.interrupted());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                        thread1.interrupt();
                        System.out.println("thread2 done");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread2.start();
        System.out.println("current time: " + new Date());

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
    }

    public static void main(String[] args) {
        ThreadInterrupt test = new ThreadInterrupt();
//        test.threadInterruptTest();
        test.threadSyncInterruptTest();
    }
}
