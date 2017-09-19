package com.wangwenjun.juc.executors;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/23
 * QQ交流群:601980517，463962286
 ***************************************/
public class TimerScheduler {

    /**
     * scheduler solution
     * Timer/TimerTask
     * SchedulerExecutorService
     * crontab
     * cron4j
     * quartz
     * <p>
     * <p>
     * Timer:
     * Question
     * when the timertask process  more than 1 seconds what happen?
     * <p>
     * <p>
     * crontab
     * <p>
     * interval correct
     * <p>
     * quartz
     * interval correct
     * <p>
     * Control-M
     *
     * @param args
     */
    public static void main(String[] args) {

        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("=====" + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 1000, 1000);
    }
}
