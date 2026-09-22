package io.chr1s.concurrent.basic;

public class SynchronizedTest {

    synchronized void test() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            Thread.sleep(10);
        }
    }
}
