package io.chr1s.concurrent.basic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞方法处理InterruptedException之直接抛出该异常
 */
public class TaskQueue {

    private static final int MAX_TASK = 100;

    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void putTask(Runnable task) throws InterruptedException {
        queue.put(task);
    }

    public void getTask() throws InterruptedException {
        queue.take();
    }
}
