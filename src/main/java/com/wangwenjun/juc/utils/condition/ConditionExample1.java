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
public class ConditionExample1 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                buildData();
            }
        }).start();
        for (int i = 0; i < 2; i++)
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
            condition.signalAll();     //monitor.notify
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

//            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
