package io.chr1s;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-22
 */
public class MultiThreadPrintWithLock {

    private static volatile int state;
    private static Object lock = new Object();


    public static void main(String[] args) {
        PrintThread a = new PrintThread(0);
        PrintThread b = new PrintThread(1);
        PrintThread c = new PrintThread(2);
        a.start();
        b.start();
        c.start();
    }








    static class PrintThread extends Thread {
        private int value;
        public PrintThread(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            while (state < 30) {
                synchronized (lock) {
                    if (state % 3 == value) {
                        print();
                        state++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

        private void print() {
            if (value == 0) {
                System.out.println("A");
            } else if (value == 1) {
                System.out.println("B");
            } else {
                System.out.println("C");
            }
        }
    }
}
