package com.wangwenjun.juc.executors;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/23
 * QQ交流群:601980517，463962286
 ***************************************/
public class QuartzSchedule {
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("Job1", "Group1").build();

        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}
