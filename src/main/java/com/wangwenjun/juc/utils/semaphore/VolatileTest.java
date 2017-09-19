package com.wangwenjun.juc.utils.semaphore;

public class VolatileTest {

    private static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 10;

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                int localValue = INIT_VALUE;
                while (localValue < MAX_LIMIT) {
                    if (localValue != INIT_VALUE) {
                        System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                        localValue = INIT_VALUE;
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                int localValue = INIT_VALUE;
                while (INIT_VALUE < MAX_LIMIT) {
                    System.out.printf("Update the value to [%d]\n", ++localValue);
                    INIT_VALUE = localValue;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}