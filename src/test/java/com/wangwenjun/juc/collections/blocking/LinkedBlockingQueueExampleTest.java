package com.wangwenjun.juc.collections.blocking;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/12
 * QQ交流群:601980517，463962286
 ***************************************/

/**
 * This class is used for unit test the class {@link LinkedBlockingQueueExample}
 *
 * @see {@link LinkedBlockingQueue}
 */
public class LinkedBlockingQueueExampleTest {

    private LinkedBlockingQueueExample example;

    @Before
    public void setUp() {
        this.example = new LinkedBlockingQueueExample();
    }

    @After
    public void tearDown() {
        this.example = null;
    }

    /**
     * Test the {@link LinkedBlockingQueue#add(Object)}
     * Test the {@link LinkedBlockingQueue#offer(Object)}
     * Test the {@link LinkedBlockingQueue#put(Object)}
     */
    @Test
    public void testInsertData() throws InterruptedException {
        LinkedBlockingQueue<String> queue = example.create(2);
        assertThat(queue.offer("data1"), equalTo(true));
        assertThat(queue.offer("data2"), equalTo(true));
        assertThat(queue.offer("data3"), equalTo(false));

        queue.clear();

        assertThat(queue.add("data1"), equalTo(true));
        assertThat(queue.add("data2"), equalTo(true));

        //enqueue
        //enter queue
        queue.put("data");

    }


    /**
     * Test the {@link LinkedBlockingQueue#add(Object)}
     * Test the {@link LinkedBlockingQueue#offer(Object)}
     * Test the {@link LinkedBlockingQueue#put(Object)}
     */
    @Test
    public void testRemoveData() throws InterruptedException {
        LinkedBlockingQueue<String> queue = example.create(2);
        assertThat(queue.offer("data1"), equalTo(true));
        assertThat(queue.offer("data2"), equalTo(true));
        assertThat(queue.offer("data3"), equalTo(false));

        assertThat(queue.element(), equalTo("data1"));
        assertThat(queue.element(), equalTo("data1"));


        assertThat(queue.peek(), equalTo("data1"));
        assertThat(queue.peek(), equalTo("data1"));

        assertThat(queue.remove(), equalTo("data1"));
        assertThat(queue.poll(), equalTo("data2"));
        assertThat(queue.size(), equalTo(0));
        assertThat(queue.remainingCapacity(), equalTo(2));


        assertThat(queue.take(), equalTo("xxx"));
        fail("should not process to here.");

    }

}