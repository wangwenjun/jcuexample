package com.wangwenjun.juc.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class CompletableFutureExample1 {
    public static void main(String[] args) throws InterruptedException {

/*        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //
        while (!future.isDone()) {

        }
        System.out.println("DONE");*/

        //Unit
/*        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("DONE"));
        System.out.println("========i am not blocked=========");

        Thread.currentThread().join();*/

/*        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) () -> get()).collect(toList());

        executorService.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(CompletableFutureExample1::display);*/


        IntStream.range(0, 10).boxed()
                .forEach(i -> CompletableFuture.supplyAsync(CompletableFutureExample1::get)
                        .thenAccept(CompletableFutureExample1::display)
                        .whenComplete((v, t) -> System.out.println(i + " DONE"))
                );
        Thread.currentThread().join();
    }

    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + "display will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "display execute done " + data);
    }

    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " get will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "get execute done " + value);
        return value;
    }
}
