package io.chr1s.concurrent.basic;

import java.lang.ref.WeakReference;

public class Reference {

    public static void main(String[] args) {
        WeakReference<Object> reference = new WeakReference<>(new Object());
        System.out.println(reference.get());
        System.gc();
        System.out.print(reference.get());
    }
}
