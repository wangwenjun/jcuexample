package com.wangwenjun.juc.collections.blocking;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/12
 * QQ交流群:601980517，463962286
 ***************************************/
public class SynchronousQueueExampleTest {

    @Test
    public void testAdd() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.remove(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        assertThat(queue.add("SynchronousQueue"), equalTo(true));
    }

    /**
     * Producer Consumer
     *
     * P------>queue<-----Consumer
     *
     * @throws InterruptedException
     */
    @Test
    public void testOffer() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.take(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        assertThat(queue.offer("SynchronousQueue"), equalTo(true));

    }


    /**
     * Producer Consumer
     *
     * P------>queue<-----Consumer
     *
     * @throws InterruptedException
     */
    @Test
    public void testPut() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();
/*
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                assertThat(queue.take(), equalTo("SynchronousQueue"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);*/
        queue.put("SynchronousQueue");
        fail("should not process to here.l");
    }

}