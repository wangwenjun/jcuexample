package com.wangwenjun.juc.collections.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/17
 * QQ交流群:601980517，463962286
 ***************************************/
public class ConcurrentSkipListMapVsConcurrentHashMap {

    static class Entry {
        int threshold;
        long ms;

        public Entry(int threshold, long ms) {
            this.threshold = threshold;
            this.ms = ms;
        }

        @Override
        public String toString() {
            return "Count:" + threshold + ",ms:" + ms;
        }
    }


    private final static Map<Class<?>, List<Entry>> summary = new HashMap<Class<?>, List<Entry>>() {
        {
            put(ConcurrentHashMap.class, new ArrayList<>());
            put(ConcurrentSkipListMap.class, new ArrayList<>());
        }
    };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 10; i <= 100; ) {
            pressureTest(new ConcurrentHashMap<>(), i);
            pressureTest(new ConcurrentSkipListMap<>(), i);
            i += 10;
        }
        summary.forEach((k, v) -> {
            System.out.println(k.getSimpleName());
            v.forEach(System.out::println);
            System.out.println("============================");
        });
    }

    private static void pressureTest(final Map<String, Integer> map, int threshold) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + map.getClass() + "] use the threshold [" + threshold + "]");
        long totalTime = 0L;
        final int MAX_THRESHOLD = 500000;
        for (int i = 0; i < 5; i++) {
            final AtomicInteger counter = new AtomicInteger(0);
            map.clear();
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(() -> {
                    for (int x = 0; x < MAX_THRESHOLD && counter.getAndIncrement() < MAX_THRESHOLD; x++) {
                        Integer randomNumber = (int) Math.ceil(Math.random() * 600000);
                        map.get(String.valueOf(randomNumber));
                        map.put(String.valueOf(randomNumber), randomNumber);
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(MAX_THRESHOLD + " element inserted/retrieved in " + period + " ms");
            totalTime += period;
        }

        summary.get(map.getClass()).add(new Entry(threshold, (totalTime / 5)));
        System.out.println("For the map [" + map.getClass() + "] the average time is " + (totalTime / 5) + " ms");
    }
}
