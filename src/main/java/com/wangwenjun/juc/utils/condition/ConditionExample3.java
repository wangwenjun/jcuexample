package com.wangwenjun.juc.utils.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/10
 * QQ交流群:601980517，463962286
 ***************************************/
public class ConditionExample3 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCE_COND = lock.newCondition();

    private final static Condition CONSUME_COND = lock.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) throws InterruptedException {

        IntStream.range(0, 6).boxed().forEach(ConditionExample3::beginProduce);
        IntStream.range(0, 13).boxed().forEach(ConditionExample3::beginConsume);
    }

    private static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                produce();
                sleep(2);
            }
        }, "P-" + i).start();
    }

    private static void beginConsume(int i) {
        new Thread(() -> {
            for (; ; ) {
                consume();
                sleep(3);
            }
        }, "C-" + i).start();
    }

    private static void produce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCE_COND.await();
            }

            System.out.println("PRODUCE_COND.getWaitQueueLength->" + lock.getWaitQueueLength(PRODUCE_COND));
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.addLast(value);

            CONSUME_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_COND.await();
            }

            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
