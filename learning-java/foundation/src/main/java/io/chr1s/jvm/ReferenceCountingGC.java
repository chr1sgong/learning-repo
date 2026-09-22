package io.chr1s.jvm;

import java.lang.ref.SoftReference;

/**
 * 引用计数算法垃圾收集测试
 * 启动参数：
 * java -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -classpath "/Users/chr1s/Workspace/github/learning-java/foundation/target/classes"
 * io.chr1s.jvm.ReferenceCountingGC
 */
public class ReferenceCountingGC {

    private Object instance = null;
    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占用内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objA;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC，objA和objB是否被回收？
        System.gc();
        System.out.println("finished");
    }
}
