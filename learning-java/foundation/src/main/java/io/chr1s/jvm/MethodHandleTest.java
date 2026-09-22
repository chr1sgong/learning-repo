package io.chr1s.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        /**
         * 无论obj指向的是哪中类型的实例，下面这句话都能正确地调用到println方法
         */
        getPrintlnMH(obj).invokeExact("chr1s");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        // MethodType代表"方法类型"，包含了方法的返回值（methodType()的第一个参数）和具体参数（methodType()的第二个以及以后的参数）
        MethodType methodType = MethodType.methodType(void.class, String.class);
        // lookup()方法来自于MethodHandles.lookup，这句话的作用是在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄
        // 因为这里调用的是一个虚方法，按照java语法的规则，方法的第一个参数是隐式的，代表方法的接收者，也就是this指向的对象，这个参数以前是放在
        // 参数列表中进行传递的，而现在提供了bindTo()方法来完成这件事情
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }
}
