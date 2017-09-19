package com.wangwenjun.juc.collections.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/17
 * QQ交流群:601980517，463962286
 ***************************************/
public class ConcurrentSkipListMapExample {

    public static <K, V> ConcurrentSkipListMap<K, V> create() {
        return new ConcurrentSkipListMap<>();
    }
}
