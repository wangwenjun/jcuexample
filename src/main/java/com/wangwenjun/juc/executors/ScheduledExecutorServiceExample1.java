package com.wangwenjun.juc.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/27
 * QQ交流群:601980517，463962286
 ***************************************/
public class ScheduledExecutorServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
/*
        ScheduledFuture<?> future = executor.schedule(() -> System.out.println("===i will be invoked!"), 2, TimeUnit.SECONDS);


        System.out.println(future.cancel(true));
        */
/*

        ScheduledFuture<Integer> future = executor.schedule(() -> 2, 2, TimeUnit.SECONDS);
        System.out.println(future.get());
*/


/**
 * period 2 seconds execute a task.
 * but the task spend 5 seconds actually
 *
 * solution 1: (crontab/quartz/Control-M)
 *
 * period first policy
 *
 * 0:5
 * 2:5
 * 4:5
 *
 * solution 2:(JDK timer)
 *
 * 0:5
 * 5:5
 * 10:5
 * 15:5
 *
 */
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
    }
}