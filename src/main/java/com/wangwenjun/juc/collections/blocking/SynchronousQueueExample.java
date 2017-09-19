package com.wangwenjun.juc.collections.blocking;

import java.util.concurrent.SynchronousQueue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/12
 * QQ交流群:601980517，463962286
 ***************************************/
public class SynchronousQueueExample {

    public static <T> SynchronousQueue<T> create() {
        return new SynchronousQueue<>();
    }

    public static void main(String[] args) {
        create().offer(new Object());
    }
}
