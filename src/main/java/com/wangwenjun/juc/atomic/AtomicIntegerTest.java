package com.wangwenjun.juc.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicIntegerTest {


    /**
     * 1.kejianxing
     * 2.sequence
     * <p>
     * 3.no atomic
     */
//    private static volatile int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {

/*        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;
                    *//**
         * value = value+1
         * (1) get value from main memory to local memory
         * (2) add 1 => x
         * (3) assign the value to x
         * (4) flush to main memory
         *//*
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());*/


        final AtomicInteger value = new AtomicInteger();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + ":" + v);
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + ":" + v);
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + ":" + v);
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());
    }
}