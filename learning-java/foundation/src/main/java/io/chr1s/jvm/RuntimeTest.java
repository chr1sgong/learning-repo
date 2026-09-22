package io.chr1s.jvm;

import java.io.IOException;

public class RuntimeTest {

    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.totalMemory() / 1024 / 1024);
        System.out.println(runtime.freeMemory() / 1024 / 1024);
        System.out.println(runtime.maxMemory() / 1024 / 1024);
        System.out.println(runtime.exec("echo hello"));
        String [] cmd = {"/bin/sh","","echo hello"};
        System.out.println(runtime.exec(cmd).isAlive());
    }
}
