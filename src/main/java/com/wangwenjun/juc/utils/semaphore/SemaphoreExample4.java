package com.wangwenjun.juc.utils.semaphore;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/7
 * QQ交流群:601980517，463962286
 ***************************************/
public class SemaphoreExample4 {
    public static void main(String[] args) throws InterruptedException {
        final MySemaphore semaphore = new MySemaphore(5);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    semaphore.drainPermits();
                    System.out.println(semaphore.availablePermits());
                    TimeUnit.SECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(5);
                }
                System.out.println("T1 finished");
            }
        };

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    boolean success = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                    System.out.println(success ? "Successful" : "Failure");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println("T2 finished");
            }
        };

        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
        Collection<Thread> waitingThreads = semaphore.getWaitingThreads();
        for (Thread t : waitingThreads) {
            System.out.println(t);
        }
    }

    static class MySemaphore extends Semaphore {

        public MySemaphore(int permits) {
            super(permits);
        }

        public MySemaphore(int permits, boolean fair) {
            super(permits, fair);
        }

        public Collection<Thread> getWaitingThreads() {
            return super.getQueuedThreads();
        }
    }
}
