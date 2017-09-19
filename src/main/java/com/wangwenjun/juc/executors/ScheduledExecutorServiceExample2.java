package com.wangwenjun.juc.executors;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/27
 * QQ交流群:601980517，463962286
 ***************************************/
public class ScheduledExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
//        testScheduleWithFixedDelay();
//        test1();
        test2();
    }

    private static void testScheduleWithFixedDelay() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        final AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() -> {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (interval.get() == 0) {
                        System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
                    } else {
                        System.out.printf("The actually spend [%d]\n", currentTimeMillis - interval.get());
                    }
                    interval.set(currentTimeMillis);
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                1, 2, TimeUnit.SECONDS);
    }

    private static void test1() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        final AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() ->
        {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
            } else {
                System.out.printf("The actually spend [%d]\n", currentTimeMillis - interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 1, 2, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1200);
        executor.shutdown();
        System.out.println("==over==");

    }

    private static void test2() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        final AtomicLong interval = new AtomicLong(0L);
        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(() -> {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (interval.get() == 0) {
                        System.out.printf("The first time trigger task at %d\n", currentTimeMillis);
                    } else {
                        System.out.printf("The actually spend [%d]\n", currentTimeMillis - interval.get());
                    }
                    interval.set(currentTimeMillis);
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                1, 2, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1200);
        executor.shutdown();
        System.out.println("==over==");
    }

}
