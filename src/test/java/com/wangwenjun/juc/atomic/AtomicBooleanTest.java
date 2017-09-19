package com.wangwenjun.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/4
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicBooleanTest {

    @Test
    public void testCreateWithOutArguments() {
        AtomicBoolean bool = new AtomicBoolean();
        assertFalse(bool.get());
    }

    @Test
    public void testCreateWithArguments() {
        AtomicBoolean bool = new AtomicBoolean(true);
        assertTrue(bool.get());
    }

    @Test
    public void testGetAndSet() {
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.getAndSet(false);
        assertTrue(result);
        assertFalse(bool.get());
    }

    @Test
    public void testCompareAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(true, false);
        assertTrue(result);
        assertFalse(bool.get());
    }

    @Test
    public void testCompareAndSetFailed(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(false, true);
        assertFalse(result);
        assertTrue(bool.get());
    }

}