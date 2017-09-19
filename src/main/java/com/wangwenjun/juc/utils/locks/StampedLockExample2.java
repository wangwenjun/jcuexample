package com.wangwenjun.juc.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/12
 * QQ交流群:601980517，463962286
 ***************************************/
public class StampedLockExample2 {
    private final static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {

        final ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };

        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
    }


    private static void read() {

        long stamp = lock.tryOptimisticRead();
        if (lock.validate(stamp)) {
            try {
                stamp = lock.readLock();
                System.err.println(stamp);
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }

        /*long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(
                    DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
            ).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }*/
    }

    private static void write() {
        long stamp = -1;
        try {
            stamp = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
