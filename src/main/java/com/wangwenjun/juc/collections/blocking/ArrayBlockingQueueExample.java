package com.wangwenjun.juc.collections.blocking;

import java.util.concurrent.ArrayBlockingQueue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/10
 * QQ交流群:601980517，463962286
 ***************************************/
public class ArrayBlockingQueueExample {

    /**
     * 1.FIFO(First in First out)
     * 2 Once created, the capacity cannot be changed.
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }
}
