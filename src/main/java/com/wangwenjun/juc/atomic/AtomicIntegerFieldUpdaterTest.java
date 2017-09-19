package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/13
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {

        final AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        final TestMe me = new TestMe();

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    final int MAX = 20;
                    for (int i = 0; i < MAX; i++) {
                        int v = updater.getAndIncrement(me);
                        System.out.println(Thread.currentThread().getName() + "=>" + v);
                    }
                }
            }.start();
        }
    }


    static class TestMe {

        volatile int i;
    }

}


