package com.wangwenjun.juc.executors;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/21
 * QQ交流群:601980517，463962286
 ***************************************/
public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(20);
                        System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                })
        );

        List<Runnable> runnableList = null;
        try {
            runnableList = executorService.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("==============over=================");
        System.out.println(runnableList);
        System.out.println(runnableList.size());
        //other work.

        /**
         * shutdown
         *
         * 20 threads
         *    10 threads work
         *    10 idle
         *
         * shutdown invoked
         *
         * 1. 10 waiting to finished the work.
         * 2. 10 interruped the idle works.
         * 3. 20 idle threads will exist
         */

        /**
         * shutdownNow
         *
         * 10 threads queue elements 10
         * 10 running
         * 10 stored in the blocking queue.
         *
         * 1.return list<Runnable> remain 10 un handle runnable.
         * 2.interruped all of threads in the pool.
         *
         */

    }
}
