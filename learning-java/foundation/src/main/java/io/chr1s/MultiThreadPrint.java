package io.chr1s;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-18
 */
public class MultiThreadPrint {

    private static AtomicInteger curr = new AtomicInteger(0);


    static class PrintThread extends Thread {
        private int value;

        public PrintThread(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            while (curr.get() < 30) {
//                System.out.println(curr.get());
                if (curr.get() % 3 == value) {
//                    System.out.print(Thread.currentThread());

                    print();
                    curr.getAndAdd(1);
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

    public static void main(String[] args) {
        PrintThread printAThread = new PrintThread(0);
        PrintThread printBThread = new PrintThread(1);
        PrintThread printCThread = new PrintThread(2);

        printAThread.start();
        printBThread.start();
        printCThread.start();
    }
}
