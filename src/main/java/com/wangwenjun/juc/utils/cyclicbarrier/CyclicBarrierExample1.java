package com.wangwenjun.juc.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/19
 * QQ交流群:601980517，463962286
 ***************************************/
public class CyclicBarrierExample1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("all of finished.");
            }
        });

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("T1 finished.");
                    cyclicBarrier.await();

                    System.out.println("T1 The other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("T2 finished.");
                    cyclicBarrier.await();
                    System.out.println("T2 The other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        cyclicBarrier.await();

        while (true) {
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(cyclicBarrier.getParties());
            System.out.println(cyclicBarrier.isBroken());
            TimeUnit.MILLISECONDS.sleep(1);
        }

    }
}
