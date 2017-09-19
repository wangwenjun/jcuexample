package com.wangwenjun.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingDeque;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/15
 * QQ交流群:601980517，463962286
 ***************************************/
public class LinkedBlockingDequeExample {

    public static <T> LinkedBlockingDeque<T> create() {
        return new LinkedBlockingDeque<>();
    }
}
