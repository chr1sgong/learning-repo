package io.chr1s.concurrent.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-01-18
 */
public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Task());
        System.out.println(future.get());
        executor.shutdown();
    }

    private static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在进行计算");
            Thread.sleep(2000);
            return 1;
        }
    }
}
