package com.wangwenjun.juc.utils.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/19
 * QQ交流群:601980517，463962286
 ***************************************/
public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch {
        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                this.runnable.run();
            }
        }
    }

    public static void main(String[] args) {

        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
            public void run() {
                System.out.println("all of work finish done.");
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished work");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished work");
            }
        }.start();

    }
}
