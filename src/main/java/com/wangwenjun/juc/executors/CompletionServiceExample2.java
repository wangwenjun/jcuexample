package com.wangwenjun.juc.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26
 * QQ交流群:601980517，463962286
 ***************************************/
public class CompletionServiceExample2 {
    /**
     * 100
     * 10
     *
     * get
     * 100
     * 10
     *
     * take
     * 10
     *
     * 100
     *
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(10);
                    System.out.println("The 10 finished");
                    return 10;
                },
                () -> {
                    sleep(20);
                    System.out.println("The 20 finished");
                    return 20;
                }
        );
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        List<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(callable -> futures.add(completionService.submit(callable)));

        Future<Integer> future;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }*/
        /*Future<Integer> future = completionService.poll();
        System.out.println(future);*/

//        System.out.println(completionService.poll(11, TimeUnit.SECONDS).get());


        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(service);
        final Event event = new Event(1);
        completionService.submit(new MyTask(event), event);

        System.out.println(completionService.take().get().result);
    }

    private static class MyTask implements Runnable {

        private final Event event;

        private MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("I AM SUCCESSFULLY.");

        }
    }

    private static class Event {
        final private int eventId;
        private String result;

        private Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    /**
     * sleep the specify seconds
     *
     * @param seconds
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
