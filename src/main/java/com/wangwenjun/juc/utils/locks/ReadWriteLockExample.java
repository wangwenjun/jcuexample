package com.wangwenjun.juc.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/9
 * QQ交流群:601980517，463962286
 ***************************************/
public class ReadWriteLockExample {

    /**
     * W W X
     * W R X
     * R W X
     * R R O
     */
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final static Lock readLock = readWriteLock.readLock();

    private final static Lock writeLock = readWriteLock.writeLock();

    private final static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(ReadWriteLockExample::read);
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReadWriteLockExample::read);
        thread2.start();


    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "=========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

}