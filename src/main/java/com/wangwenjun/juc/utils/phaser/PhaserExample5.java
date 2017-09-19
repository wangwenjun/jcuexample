package com.wangwenjun.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/19
 * QQ交流群:601980517，463962286
 ***************************************/
public class PhaserExample5 {

    private final static Random random = new Random(System.currentTimeMillis());

    //arrive
    public static void main(String[] args) throws InterruptedException {
        /*final Phaser phaser = new Phaser();
        new Thread(phaser::arrive).start();

        TimeUnit.SECONDS.sleep(4);*/


        final Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser, i).start();
        }

        phaser.arriveAndAwaitAdvance();

        System.out.println("The phase 1 work finished done.");
    }


    private static class ArriveTask extends Thread {
        private final Phaser phaser;

        private ArriveTask(Phaser phaser, int no) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working.");
            PhaserExample5.sleep();
            System.out.println(getName() + " The phase one is running");
            phaser.arrive();


            PhaserExample5.sleep();

            System.out.println(getName() + " keep to do other thing.");

        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
