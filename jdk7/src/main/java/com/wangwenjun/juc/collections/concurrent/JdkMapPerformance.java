package com.wangwenjun.juc.collections.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/16
 * QQ交流群:601980517，463962286
 ***************************************/
public class JdkMapPerformance {


    /**
     * Concurrency Testing use five threads
     * <p>
     * <pre>
     *     Start pressure testing the map [class java.util.Hashtable] use the threshold [5],retrieve=false
     * 2500000 element inserted/retrieved in 2691 ms
     * 2500000 element inserted/retrieved in 2470 ms
     * 2500000 element inserted/retrieved in 2281 ms
     * 2500000 element inserted/retrieved in 2070 ms
     * 2500000 element inserted/retrieved in 2183 ms
     * For the map [class java.util.Hashtable] thr average time is 2339 ms
     * Start pressure testing the map [class java.util.Hashtable] use the threshold [5],retrieve=true
     * 2500000 element inserted/retrieved in 4755 ms
     * 2500000 element inserted/retrieved in 2755 ms
     * 2500000 element inserted/retrieved in 3027 ms
     * 2500000 element inserted/retrieved in 2697 ms
     * 2500000 element inserted/retrieved in 2794 ms
     * For the map [class java.util.Hashtable] thr average time is 3205 ms
     * Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [5],retrieve=false
     * 2500000 element inserted/retrieved in 2035 ms
     * 2500000 element inserted/retrieved in 1989 ms
     * 2500000 element inserted/retrieved in 1983 ms
     * 2500000 element inserted/retrieved in 2269 ms
     * 2500000 element inserted/retrieved in 1808 ms
     * For the map [class java.util.Collections$SynchronizedMap] thr average time is 2016 ms
     * Start pressure testing the map [class java.util.Collections$SynchronizedMap] use the threshold [5],retrieve=true
     * 2500000 element inserted/retrieved in 2985 ms
     * 2500000 element inserted/retrieved in 3141 ms
     * 2500000 element inserted/retrieved in 2783 ms
     * 2500000 element inserted/retrieved in 4543 ms
     * 2500000 element inserted/retrieved in 2655 ms
     * For the map [class java.util.Collections$SynchronizedMap] thr average time is 3221 ms
     * Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [5],retrieve=false
     * 2500000 element inserted/retrieved in 1523 ms
     * 2500000 element inserted/retrieved in 1054 ms
     * 2500000 element inserted/retrieved in 957 ms
     * 2500000 element inserted/retrieved in 937 ms
     * 2500000 element inserted/retrieved in 1270 ms
     * For the map [class java.util.concurrent.ConcurrentHashMap] thr average time is 1148 ms
     * Start pressure testing the map [class java.util.concurrent.ConcurrentHashMap] use the threshold [5],retrieve=true
     * 2500000 element inserted/retrieved in 1439 ms
     * 2500000 element inserted/retrieved in 1493 ms
     * 2500000 element inserted/retrieved in 1136 ms
     * 2500000 element inserted/retrieved in 1391 ms
     * 2500000 element inserted/retrieved in 1282 ms
     * For the map [class java.util.concurrent.ConcurrentHashMap] thr average time is 1348 ms
     * ===============================================================
     *
     * </pre>
     * <p>
     * <p>
     * Single thread testing report
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

     /*   pressureTest(new Hashtable<String, Integer>(), 5, false);
        pressureTest(new Hashtable<String, Integer>(), 5, true);


        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()), 5, false);
        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()), 5, true);

        pressureTest(new ConcurrentHashMap<String, Integer>(), 5, false);
        pressureTest(new ConcurrentHashMap<String, Integer>(), 5, true);*/
        System.out.println("===============================================================");

        pressureTest(new Hashtable<String, Integer>(), 1, false);
        pressureTest(new Hashtable<String, Integer>(), 1, true);


        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()), 1, false);
        pressureTest(Collections.synchronizedMap(new HashMap<String, Integer>()), 1, true);

        pressureTest(new ConcurrentHashMap<String, Integer>(), 1, false);
        pressureTest(new ConcurrentHashMap<String, Integer>(), 1, true);

        pressureTest(new HashMap<String, Integer>(), 1, false);
        pressureTest(new HashMap<String, Integer>(), 1, true);

    }

    private static void pressureTest(final Map<String, Integer> map, int threshold, final boolean retrieve) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + map.getClass() + "] use the threshold [" + threshold + "],retrieve=" + retrieve);
        long totalTime = 0L;
        final int MAX_THRESHOLD = 2500000;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < MAX_THRESHOLD; i++) {
                            Integer randomNumber = (int) Math.ceil(Math.random() * 600000);
                            if (retrieve) map.get(String.valueOf(randomNumber));
                            map.put(String.valueOf(randomNumber), randomNumber);
                        }
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(threshold * MAX_THRESHOLD + " element inserted/retrieved in " + period + " ms");
            totalTime += period;
        }

        System.out.println("For the map [" + map.getClass() + "] the average time is " + (totalTime / 5) + " ms");

    }
}
