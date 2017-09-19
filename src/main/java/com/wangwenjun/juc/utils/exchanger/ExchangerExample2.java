package com.wangwenjun.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/6
 * QQ交流群:601980517，463962286
 ***************************************/
public class ExchangerExample2 {

    /**
     * Actor
     *
     * @param args
     */
    public static void main(String[] args) {

        final Exchanger<Object> exchanger = new Exchanger<Object>();

        new Thread() {
            @Override
            public void run() {
                Object aobj = new Object();
                System.out.println("A will send  the object " + aobj);

                try {
                    Object rObj = exchanger.exchange(aobj);
                    System.out.println("A recieved the object " + rObj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Object bobj = new Object();
                System.out.println("B will send  the object " + bobj);

                try {
                    Object rObj = exchanger.exchange(bobj);
                    System.out.println("B recieved the object " + rObj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
