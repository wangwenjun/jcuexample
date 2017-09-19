package com.wangwenjun.juc.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/21
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorsExample1 {
    public static void main(String[] args) throws InterruptedException {
//        useCachedThreadPool();
//        useFixedSizePool();
        useSinglePool();
    }

    /**
     * SingleThreadExecutor difference between one Thread.
     * <p>
     * Thread will die after finish work, but SingleThreadExecutor can always alive.
     * Thread can not put the submitted runnable to the cache queue but SingleThreadExecutor can do this.
     * <p>
     * <p>
     * new FinalizableDelegatedExecutorService
     * (new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()));
     * <p>
     * new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>())
     * =====
     * <p>
     * Executors.newFixedThreadPool(1);
     */
    private static void useSinglePool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 10).boxed().forEach(i -> executorService
                .execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " [" + i + "]");
                }));

        TimeUnit.SECONDS.sleep(1);
//        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     */
    private static void useFixedSizePool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 10).boxed().forEach(i -> executorService
                .execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " [" + i + "]");
                }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * <p>
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     */
    private static void useCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        executorService.execute(() -> System.out.println("=========="));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService
                .execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " [" + i + "]");
                }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
