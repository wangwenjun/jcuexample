package com.wangwenjun.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/19
 * QQ交流群:601980517，463962286
 ***************************************/
public class PhaserExample7 {

    /**
     * awaitAdvanceInterruptibly
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

/*        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
        thread.start();
        System.out.println("=======================");
        TimeUnit.SECONDS.sleep(10);

        thread.interrupt();
        System.out.println("===thread.interrupt===");*/

        /*final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(10);
                System.out.println("*****************");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("=======================");
        TimeUnit.SECONDS.sleep(10);
        thread.interrupt();
        System.out.println("===thread.interrupt===");*/


        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(10, 10, TimeUnit.SECONDS);
                System.out.println("*****************");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
