package com.wangwenjun.juc.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExecutorServiceExample5 {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        executorService.execute(() -> System.out.println("I will be process because of triggered the execute."));
        executorService.getQueue().add(() -> System.out.println("I am added directly into the queue"));

    }
}
