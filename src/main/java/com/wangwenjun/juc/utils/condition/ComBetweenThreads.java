package com.wangwenjun.juc.utils.condition;

import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/10
 * QQ交流群:601980517，463962286
 ***************************************/
public class ComBetweenThreads {

    private static int data = 0;

    private static boolean noUse = true;

    private final static Object MONITOR = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                useData();
            }
        }).start();
    }


    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void buildData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data++;
            sleep(1);
            System.out.println("P=>" + data);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    private static void useData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sleep(1);
            System.out.println("C=>" + data);
            noUse = false;
            MONITOR.notifyAll();
        }
    }

}
