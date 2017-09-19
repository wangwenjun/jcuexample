package com.wangwenjun.juc.executors;

import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26
 * QQ交流群:601980517，463962286
 ***************************************/
public class CompletionServiceExample1 {

    /**
     * Demo the Future defect.
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureDefect1();
        futureDefect2();
    }

    /**
     * No callback
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {

            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        System.out.println("=================");
        future.get();
    }

    private static void futureDefect2() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("The 10 finished");
                    return 10;
                },
                () -> {
                    sleep(20);
                    System.out.println("The 20 finished");
                    return 20;
                }
        );

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(service.submit(callableList.get(0)));
        futures.add(service.submit(callableList.get(1)));

        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
    }

    /**
     * sleep the specify seconds
     *
     * @param seconds
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}