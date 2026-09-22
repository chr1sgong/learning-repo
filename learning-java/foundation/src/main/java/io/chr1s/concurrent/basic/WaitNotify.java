package io.chr1s.concurrent.basic;

public class WaitNotify {

    public static void main(String[] args) {

        Object lock = new Object();

        final boolean[] flag = new boolean[]{true};

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        if (flag[0]) {
                            System.out.println(i * 2 + 1);
                            flag[0] = false;
                            lock.notify();
                            if (i < 49) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        if (!flag[0]) {
                            System.out.println(i * 2 + 2);
                            flag[0] = true;
                            lock.notify();
                            if (i < 49) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
