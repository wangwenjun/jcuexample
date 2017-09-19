package com.wangwenjun.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/6
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExchangerExample1 {
    /**
     * V r=exchange(V v)
     * &nbsp;&nbsp;v：indicate the object the current thread wanted transfer.
     * &nbsp;&nbsp;r：indicate the other thread(pair) return object.
     * <p>
     * <p>
     * <pre>
     *      NOTE:
     *          1.if the pair thread not reached exchange point, the thread will WAITING.
     *          2.use the {@link Exchanger} must be paired.
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<String>();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start.");
                try {
                    String result = exchanger.exchange("I am come from T-A");
                    System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end.");
            }
        }, "==A==").start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start.");
                try {
                    String result = exchanger.exchange("I am come from T-A1");
                    System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end.");
            }
        }, "==A=1=").start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start.");
                try {
                    TimeUnit.SECONDS.sleep(20);
                    String result = exchanger.exchange("I am come from T-B");
                    System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end.");
            }
        }, "==B==").start();
    }
}
