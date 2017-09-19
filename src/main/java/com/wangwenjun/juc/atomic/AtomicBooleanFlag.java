package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/4
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicBooleanFlag {

    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                        //System.out.println("I am working.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("I am finished.");
            }
        }.start();

        Thread.sleep(5000);

        new Thread() {
            @Override
            public void run() {
                flag = false;
            }
        }.start();

    }
}