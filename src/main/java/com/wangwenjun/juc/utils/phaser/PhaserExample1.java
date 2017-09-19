package com.wangwenjun.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/15
 * QQ交流群:601980517，463962286
 ***************************************/
public class PhaserExample1 {

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);

        phaser.register();

        phaser.arriveAndAwaitAdvance();
        System.out.println("all of worker finished the task.");

    }

    static class Task extends Thread {

        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The Worker [" + getName() + "] is working...");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();

        }
    }
}
