package com.wangwenjun.juc.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/3
 * QQ交流群:601980517，463962286
 ***************************************/
public class CompletableFutureExample5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        getNow();
//        complete();
//        testJoin();
//        completeExceptionally();
//        obtrudeException();

        CompletableFuture<String> future = errorHandle();
        future.whenComplete((v, t) -> System.out.println(v));
        Thread.currentThread().join();
    }

    private static CompletableFuture<String> errorHandle() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("======i will be still process...");
            return "HELLO";
        });

        future1.thenApply(s -> {
            Integer.parseInt(s);
            System.out.println("===========keep move=====");
            return s + " WORLD";
        }).exceptionally(Throwable::getMessage).thenAccept(System.out::println);

        return future1;
    }

    private static void obtrudeException() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("======i will be still process...");
            return "HELLO";
        });
        future.obtrudeException(new Exception("i am error."));
        System.out.println(future.get());
    }

    private static void completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            sleep(5);
            System.out.println("======i will be still process...");
            return "HELLO";
        });
        sleep(1);
        future.completeExceptionally(new RuntimeException());
        System.out.println(future.get());
    }

    private static void testJoin() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("======i will be still process...");
            return "HELLO";
        });
        String result = future.join();
        System.out.println(result);
    }

    private static void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("======i will be still process...");
            return "HELLO";
        });
        sleep(1);
        boolean finished = future.complete("WORLD");
        System.out.println(finished);
        System.out.println(future.get());
    }

    private static void getNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            return "HELLO";
        });

        String result = future.getNow("WORLD");

        System.out.println(result);
        System.out.println(future.get());
    }


    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
