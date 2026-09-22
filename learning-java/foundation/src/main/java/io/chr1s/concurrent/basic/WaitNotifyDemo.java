package io.chr1s.concurrent.basic;

public class WaitNotifyDemo {

    public static void main(String[] args) {

        Object lock = new Object();

        final boolean[] flag = new boolean[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        while (flag[0]) {
                            try {
                                lock.wait();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        System.out.println(i * 2 + 1);
                        flag[0] = true;
                        lock.notify();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        while (!flag[0]) {
                            try {
                                lock.wait();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        System.out.println(i * 2 + 2);
                        flag[0] = false;
                        lock.notify();
                    }
                }
            }
        }).start();
    }
}
