package com.wangwenjun.juc.executors;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/22
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorsExample2 {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newWorkStealingPool();
        /*Optional.of(Runtime.getRuntime().availableProcessors())
                .ifPresent(System.out::println);*/

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println("Thread " + Thread.currentThread().getName());
                    sleep(2);
                    return "Task-" + i;
                }
        ).collect(toList());

        executorService.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);


    }

    /**
     * sleep the specify seconds.
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
