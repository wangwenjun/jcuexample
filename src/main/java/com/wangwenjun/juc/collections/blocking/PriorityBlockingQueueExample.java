package com.wangwenjun.juc.collections.blocking;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/11
 * QQ交流群:601980517，463962286
 ***************************************/
public class PriorityBlockingQueueExample {
    public <T> PriorityBlockingQueue<T> create(int size) {
        return new PriorityBlockingQueue<>(size);
    }

    public <T> PriorityBlockingQueue<T> create(int size, Comparator<T> comparator) {
        return new PriorityBlockingQueue<>(size, comparator);
    }
}
