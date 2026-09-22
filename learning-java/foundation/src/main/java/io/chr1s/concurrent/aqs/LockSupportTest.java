package io.chr1s.concurrent.aqs;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i == 50) LockSupport.park();
            System.out.println(i);
        }
    }
}
