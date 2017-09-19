package com.wangwenjun.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.junit.Assert.assertEquals;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/9
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicIntegerArrayTest {

    @Test
    public void testCreateAtomicIntegerArray() {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        assertEquals(10, array.length());
    }


    @Test
    public void testGet() {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        assertEquals(10, array.length());
        assertEquals(0, array.get(5));
    }

    @Test
    public void testSet() {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        array.set(5, 5);
        assertEquals(10, array.length());
        assertEquals(5, array.get(5));
    }

    @Test
    public void testGetAndSet() {
        int[] originalArray = new int[10];
        originalArray[5] = 5;
        AtomicIntegerArray array = new AtomicIntegerArray(originalArray);

        int v = array.getAndSet(5, 6);
        assertEquals(5, v);
        assertEquals(6, array.get(5));
    }
}
