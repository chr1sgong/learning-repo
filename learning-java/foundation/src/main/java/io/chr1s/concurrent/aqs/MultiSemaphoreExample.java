package io.chr1s.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MultiSemaphoreExample {

    private static final int THREAD_COUNT = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < THREAD_COUNT; ++i) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 获取许可
                    semaphore.acquire(3);
                    test(threadNum);
                    // 释放许可
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("finished");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.out.println(threadNum);
        Thread.sleep(1000);
    }
}
