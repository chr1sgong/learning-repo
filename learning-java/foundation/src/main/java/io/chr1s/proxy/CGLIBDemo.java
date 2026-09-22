package io.chr1s.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-12
 */
public class CGLIBDemo implements MethodInterceptor {

//    private Object target;

//    public CGLIBDemo(Object target) {
//        this.target = target;
//    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("method start...");
        Object res = methodProxy.invokeSuper(o, objects);
        System.out.println("method end...");
        return res;
    }

    public static void main(String[] args) {
        SampleClass sampleClass = new SampleClass();
        sampleClass.sayHello();

        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        SampleClass proxy = (SampleClass) Enhancer.create(SampleClass.class, new CGLIBDemo());
        proxy.sayHello();
    }
}
