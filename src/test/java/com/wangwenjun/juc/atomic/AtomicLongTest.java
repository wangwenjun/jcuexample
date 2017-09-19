package com.wangwenjun.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/4
 * QQ交流群:601980517，463962286
 ***************************************/
public class AtomicLongTest {

    @Test
    public void testCreate() {
        AtomicLong l = new AtomicLong(100L);
        /**
         * 32
         * long 64
         *
         * high 32
         * low  32
         *
         *
         */
        assertEquals(100L, l.get());
    }
}
