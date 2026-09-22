package io.chr1s.jvm;

public class VisibilityTest {

    private boolean flag = true;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

//    public static void main(String[] args) throws InterruptedException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (flag) {
//                    System.out.println("running");
//                }
//                System.out.println("stopped");
//            }
//        }).start();
//        Thread.sleep(1000);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                flag = false;
//            }
//        }).start();
//        while (true) {}
//    }
}
