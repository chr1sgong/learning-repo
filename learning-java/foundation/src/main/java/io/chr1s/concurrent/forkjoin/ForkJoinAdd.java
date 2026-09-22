package io.chr1s.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinAdd extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long THRESHOLD = 10_000;

    public ForkJoinAdd(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinAdd(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return add();
        }
        ForkJoinAdd firstTask = new ForkJoinAdd(numbers, start, start + length / 2);
        firstTask.fork();

        ForkJoinAdd secondTask = new ForkJoinAdd(numbers, start + length/ 2, end);
        secondTask.fork();

        Long secondTaskResult = secondTask.compute();
        Long firstTaskResult = firstTask.join();

        return firstTaskResult + secondTaskResult;
    }

    private long add() {
        long result = 0;
        for (int i = start; i < end; ++i) {
            result += numbers[i];
        }
        return result;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000000000).toArray();
        long curr = System.currentTimeMillis();
        ForkJoinTask<Long> task = new ForkJoinAdd(numbers);
        long result = new ForkJoinPool().invoke(task);
        System.out.println("time cost: " + (System.currentTimeMillis() - curr));
        System.out.print(result);

        result = 0;
        curr = System.currentTimeMillis();
        for (int i = 0; i <= 1000000000; ++i) {
            result += i;
        }
        System.out.println("time cost: " + (System.currentTimeMillis() - curr));
        System.out.print(result);
    }
}
