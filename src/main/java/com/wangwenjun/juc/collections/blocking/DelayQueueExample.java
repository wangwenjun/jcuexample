package com.wangwenjun.juc.collections.blocking;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/13
 * QQ交流群:601980517，463962286
 ***************************************/


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 1.The delay queue will ordered by expired time? yes
 * 2.When poll the empty delay queue will return null? use take?
 * 3.When less the expire time will return quickly? yes
 * 4.Even though unexpired elements cannot be removed using {@code take} or {@code poll} (take will waiting,but poll not)
 * 5.This queue does not permit null elements.  yes
 * 6.Use iterator can return quickly? yes
 * <p>
 * NOTICE: The DelayQueue elements must implement the {@link Delayed}
 * The DelayQueue is a unbounded queue.
 */
public class DelayQueueExample {


    /**
     * private[this] def create():DelayQueue[T:< Delayed]={
     * <p>
     * <p>
     * }
     *
     * @param <T>
     * @return
     */
    public static <T extends Delayed> DelayQueue<T> create() {
        return new DelayQueue<>();
        //new DelayQueue[T]
    }

}
