package com.wangwenjun.juc.atomic;

/***************************************
 * @author:Alex Wang
 * @Date:2017/7/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread() {
            @Override
            public void run() {
                while (!init) {
//                    System.out.println(".");
                }
                /**
                 * while(true){}
                 *
                 * while (!init) {
                     System.out.println(".");
                 }
                 */
            }
        }.start();

        Thread.sleep(1000);

        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("Set init to true.");
            }
        }.start();
    }
}
