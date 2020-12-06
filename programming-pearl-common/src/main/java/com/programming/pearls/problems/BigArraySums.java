package com.programming.pearls.problems;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Uninterruptibles;

/**
 * @author dufeng <dufeng@kuaishou.com>
 * Created on 2020-12-06
 */
public class BigArraySums {

    /**
     * 来个最简单的，直接全加一遍
     * 每一次相加模拟1ms休眠
     */
    public static long addBigAll(int[] data) {
        long sum = 0L;
        for (int i : data) {
            sum = compute(i, sum);
        }
        return sum;
    }

    public static long compute(long i, long j) {
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.MILLISECONDS);
        return i + j;
    }

    public static long addBigAllWithMultiThreads(int[] data) {
        int pageNum = Runtime.getRuntime().availableProcessors();
        ExecutorService executors = Executors.newFixedThreadPool(pageNum);
        int eachPage = data.length / pageNum;
        List<Future<Long>> futures = Lists.newArrayList();
        for (int j = 0; j < pageNum; j++) {
            final int curPage = j;
            futures.add(executors.submit(() -> {
                long res = 0L;
                int end = curPage == pageNum - 1 ? data.length : (curPage + 1) * eachPage;
                for (int i = curPage * eachPage; i < end; i++) {
                    res = compute(data[i], res);
                }
                return res;
            }));
        }
        return futures.stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (Throwable t) {
                        t.printStackTrace();
                        Throwables.throwIfUnchecked(t);
                    }
                    return 0L;
                }).reduce((l1, l2) -> l1.longValue() + l2.longValue()).get();
    }

    public static long forJoinCompute(int[] data) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Long> resTask = forkJoinPool.submit(new ComputeTask(data, 0, data.length));
        try {
            return resTask.get();
        } catch (Throwable t) {
            Throwables.throwIfUnchecked(t);
        }
        return 0L;
    }

    private static class ComputeTask extends RecursiveTask<Long> {
        private int[] data;
        private int from;
        private int to;

        public ComputeTask(int[] data, int from, int to) {
            this.data = data;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from <= 1) {
                long res = 0L;
                for (int i = from; i < to; i++) {
                    res = BigArraySums.compute(res, data[i]);
                }
                return res;
            } else {
                int mid = (from + to) / 2;
                ComputeTask left = new ComputeTask(data, from, mid);
                ComputeTask right = new ComputeTask(data, mid, to);
                //提交左边的任务
                left.fork();
                // 右边的任务直接利用当前线程计算，节约开销
                Long rightResult = right.compute();
                // 等待左边计算完毕
                Long leftResult = left.join();
                // 返回结果
                return leftResult + rightResult;
            }
        }
    }
}
