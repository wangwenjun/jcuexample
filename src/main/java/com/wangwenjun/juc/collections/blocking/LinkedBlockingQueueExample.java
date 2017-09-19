package com.wangwenjun.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/12
 * QQ交流群:601980517，463962286
 ***************************************/
public class LinkedBlockingQueueExample {

    public <T> LinkedBlockingQueue<T> create() {
        return new LinkedBlockingQueue<>();
    }

    public <T> LinkedBlockingQueue<T> create(int capacity) {
        return new LinkedBlockingQueue<>(capacity);
    }
}
