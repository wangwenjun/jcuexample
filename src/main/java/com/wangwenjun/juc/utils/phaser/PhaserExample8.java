package com.wangwenjun.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/19
 * QQ交流群:601980517，463962286
 ***************************************/
public class PhaserExample8 {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(3);

        new Thread(phaser::arriveAndAwaitAdvance).start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(phaser.isTerminated());

        phaser.forceTermination();
        System.out.println(phaser.isTerminated());
    }
}
