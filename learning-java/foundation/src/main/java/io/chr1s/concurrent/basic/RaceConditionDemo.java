package io.chr1s.concurrent.basic;

public class RaceConditionDemo {

    public static void main(String[] args) {

        // 客户端线程数
        int numberOfThreads = args.length > 0 ? Short.parseShort(args[0]) : Runtime.getRuntime().availableProcessors();

        Thread[] workerThreads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }

        for (Thread ct : workerThreads) {
            ct.start();
        }
    }

    // 业务线程模拟
    static class WorkerThread extends Thread {

        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIdGenerator requestIdGen = RequestIdGenerator.getInstance();

            while (i-- > 0) {
                requestID = requestIdGen.nextID();
                processRequest(requestID);
            }
        }

        // 模拟请求处理
        private void processRequest(String requestID) {
            // 模拟请求耗时
            System.out.printf("%s got requestId: %s %n", Thread.currentThread().getName(), requestID);
        }
    }
}
