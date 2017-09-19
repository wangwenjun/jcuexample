package com.wangwenjun.juc.collections.blocking;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/15
 * QQ交流群:601980517，463962286
 ***************************************/
public class LinkedTransferQueueExample {

    /**
     * An unbounded {@link TransferQueue} based on linked nodes.
     * <p>
     * Bounded
     * Producer: when the capacity is full. the producer will blocked,
     * else just only insert the new element into the queue, but the data is consume or not?
     * <p>
     * The TransferQueue is useful in scenario where message passing need to guaranteed
     *
     * @param <T>
     * @return
     */
    public static <T> LinkedTransferQueue<T> create() {
        return new LinkedTransferQueue<>();
    }
}
