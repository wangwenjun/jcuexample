package com.wangwenjun.juc.executors;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class CompletableFutureExample3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future.thenRun(() -> System.out.println("done")).thenRunAsync(() -> System.out.println("done"));
//        future.thenAcceptAsync(System.out::println);

/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
                (Supplier<String>) () -> {
                    throw new RuntimeException("not get the data");
                }).handleAsync((s, t) -> {
            Optional.of(t).ifPresent(e -> System.out.println("Error"));
            return (s == null) ? 0 : s.length();
        });
        System.out.println(future.get());*/

//                .thenApply(String::length);
/*                .thenApplyAsync(s -> {
            try {
                System.out.println("==========");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===over==");
            return s.length();
        });


        /*future.whenComplete((v, t) -> {
            try {
                System.out.println("==========");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===over==");
        });
        System.out.println("4444");*/
        /*future.whenCompleteAsync((v, t) -> {
            try {
                System.out.println("==========");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===over==");
        });*/
        System.out.println(future.get());

        Thread.currentThread().join();
    }
}