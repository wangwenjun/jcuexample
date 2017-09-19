package com.wangwenjun.juc.executors;

import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
        //testAbortPolicy();
//        testDiscardPolicy();

//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }


    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 3; i++)
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
    }

    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++)
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
        System.out.println("=================================");
    }

    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 3; i++)
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");

            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("=================================");
    }


    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 3; i++)
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("I come from lambda.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("=================================");
    }

}
