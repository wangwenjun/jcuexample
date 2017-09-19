package com.wangwenjun.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/10
 * QQ交流群:601980517，463962286
 ***************************************/
public class ConditionExample2 {
    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    /**
     * 1.not use the Condition only use the lock? NO
     * 2.the producer get the lock but invoke await method and not jump out the lock statement block
     * why the consumer can get the lock still?
     * 3.not use the lock only use the condition? NO
     *
     * @param args
     */
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

    private static void buildData() {
        try {
            lock.lock();    //synchronized key word #monitor enter
            while (noUse) {
                condition.await();  //monitor.wait()
            }

            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signal();     //monitor.notify
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();      //synchronized end #monitor end
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }

            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
