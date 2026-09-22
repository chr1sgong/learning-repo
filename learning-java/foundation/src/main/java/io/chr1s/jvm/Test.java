package io.chr1s.jvm;

public class Test {

    private static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                System.out.println("-" + System.currentTimeMillis());
//                synchronized (this) {
//                    while (flag) {
//                        i++;
//                    }
//                }
                while (flag) {
                    synchronized (this) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                }
                System.out.println(i);
                System.out.println("stopped");
            }
        }).start();

        Thread.sleep(100);

        new Thread(() -> flag = false).start();
    }
}
