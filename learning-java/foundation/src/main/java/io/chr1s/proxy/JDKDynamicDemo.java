package io.chr1s.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-12
 */
public class JDKDynamicDemo implements InvocationHandler {

    private Object target;

    public JDKDynamicDemo(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy: " + proxy.getClass().getName());
        System.out.println("method start...");
        Object res = method.invoke(target, args);
        System.out.println("method end...");
        return res;
    }

    public static void main(String[] args) {
        SampleClass sampleClass = new SampleClass();
        sampleClass.sayHello();
        System.out.println("----------------------------");
        SampleInterface proxy = (SampleInterface) Proxy.newProxyInstance(SampleInterface.class.getClassLoader(), new Class[]{SampleInterface.class}, new JDKDynamicDemo(sampleClass));
        proxy.sayHello();
        System.out.println("proxy: " + proxy.getClass().getName());
    }
}
