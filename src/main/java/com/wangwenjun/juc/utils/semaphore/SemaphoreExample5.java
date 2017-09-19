package com.wangwenjun.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/8
 * QQ交流群:601980517，463962286
 ***************************************/
public class SemaphoreExample5 {

    private final static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    batchHandle();
                }
            }.start();
        }
    }

    private static void batchHandle() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " get the semaphore.");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        System.out.println(Thread.currentThread().getName() + " OUT.");
    }
}