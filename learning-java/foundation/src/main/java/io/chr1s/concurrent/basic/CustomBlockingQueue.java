package io.chr1s.concurrent.basic;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue {

    private Queue<Integer> container;

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private volatile int capacity;

    public CustomBlockingQueue(int capacity) {
        container = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean put(int x) {
        lock.lock();
        try {
            while (container.size() == capacity) {
                System.out.println("队列已满");
                notFull.await();
            }
            System.out.println("往队列写" + x);
            container.offer(x);
            notEmpty.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            notEmpty.signalAll();
            return false;
        } finally {
            lock.unlock();
        }
        return true;
    }

    public int take() {
        lock.lock();
        try {
            while (container.isEmpty()) {
                System.out.println("队列为空");
                notEmpty.await();
            }
            int data = container.poll();
            System.out.println("从队列读: " + data);
            notFull.signalAll();
            return data;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            notFull.signalAll();
            throw new RuntimeException("异常");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        CustomBlockingQueue blockingQueue = new CustomBlockingQueue(10);

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i++) {
                    blockingQueue.put(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i++) {
                    System.out.println(blockingQueue.take());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        putThread.start();
        takeThread.start();
    }
}
