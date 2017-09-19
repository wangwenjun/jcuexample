package com.wangwenjun.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/13
 * QQ交流群:601980517，463962286
 ***************************************/
public class AIFUTest {

    private volatile int i;

    private AtomicInteger j = new AtomicInteger();

    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class, "i");

    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue);
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) {
        AIFUTest test = new AIFUTest();
        test.update(10);
        System.out.println(test.get());
    }

}
