package com.wangwenjun.juc.executors;

import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/27
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorServiceSummarize {

    public static void main(String[] args) throws InterruptedException {
        final List<Thread> threads = new ArrayList<>();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10, r -> {
            Thread t = new Thread(r);
            threads.add(t);
            return t;
        });


        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());
//        System.out.println(executorService.getActiveCount());

//        threads.stream().forEach(Thread::interrupt);
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());

        threads.stream().forEach(Thread::interrupt);
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));
        executorService.execute(() -> System.out.println("="));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threads.size());
    }
}
