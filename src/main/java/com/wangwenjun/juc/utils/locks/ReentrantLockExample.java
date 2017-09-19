package com.wangwenjun.juc.utils.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/9
 * QQ交流群:601980517，463962286
 ***************************************/
public class ReentrantLockExample {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        /*IntStream.range(0, 2).forEach(i -> new Thread() {
            @Override
            public void run() {
                needLockBySync();
            }
        }.start());*/
/*        Thread thread1 = new Thread(() -> testUnInterruptibly());
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> testUnInterruptibly());
        thread2.start();
        TimeUnit.SECONDS.sleep(1);


        thread2.interrupt();
        System.out.println("================");*/


        /*Thread thread1 = new Thread(() -> testTryLock());
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> testTryLock());
        thread2.start();*/


        Thread thread1 = new Thread(() -> testUnInterruptibly());
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> testUnInterruptibly());
        thread2.start();

        TimeUnit.SECONDS.sleep(1);
        Optional.of(lock.getQueueLength()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThreads()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(thread2)).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(thread1)).ifPresent(System.out::println);
        Optional.of(lock.isLocked()).ifPresent(System.out::println);
        //Optional.of(lock.getQueuedThreads()).ifPresent(System.out::println);


    }

    public static void testTryLock() {


        if (lock.tryLock()) {
            try {
                Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
                while (true) {

                }
            } finally {
                lock.unlock();
            }
        } else {
            Optional.of("The thread-" + Thread.currentThread().getName() + " not get lock.").ifPresent(System.out::println);
        }
    }


    public static void testUnInterruptibly() {

        try {
            lock.lockInterruptibly();
            Optional.of(Thread.currentThread().getName() + ":" + lock.getHoldCount()).ifPresent(System.out::println);
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void needLock() {
        try {
            lock.lock();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void needLockBySync() {
        synchronized (ReentrantLockExample.class) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
