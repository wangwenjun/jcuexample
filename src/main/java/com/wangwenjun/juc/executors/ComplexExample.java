package com.wangwenjun.juc.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/2
 * QQ交流群:601980517，463962286
 ***************************************/
public class ComplexExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*final ExecutorService service = Executors.newSingleThreadExecutor();

        List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(toList());

        final CompletionService<Object> completionService = new ExecutorCompletionService<>(service);

        tasks.forEach(r -> completionService.submit(Executors.callable(r)));

        Future<?> future;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }*/


        //trap

        /*final ExecutorService service = Executors.newSingleThreadExecutor();
        List<Runnable> tasks = IntStream.range(0, 5).boxed().map(ComplexExample::toTask).collect(toList());
        final CompletionService<Object> completionService = new ExecutorCompletionService<>(service);

        tasks.forEach(r -> completionService.submit(Executors.callable(r)));

        TimeUnit.SECONDS.sleep(12);
        List<Runnable> runnables = service.shutdownNow();
        System.out.println(runnables);*/


        final ExecutorService service = Executors.newSingleThreadExecutor();
        List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed().map(MyTask::new).collect(toList());
        final CompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        tasks.forEach(completionService::submit);
        TimeUnit.SECONDS.sleep(12);
        service.shutdownNow();

        tasks.stream().filter(callable -> !((MyTask) callable).isSuccess()).forEach(System.out::println);
    }

    private static class MyTask implements Callable<Integer> {

        private final Integer value;

        private boolean success = false;

        MyTask(Integer value) {
            this.value = value;
        }


        @Override
        public Integer call() throws Exception {

            System.out.printf("The Task [%d] will be executed.\n", value);
            TimeUnit.SECONDS.sleep(value * 5 + 10);
            System.out.printf("The Task [%d] execute done.\n", value);
            success = true;
            return value;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    private static Runnable toTask(int i) {
        return () -> {
            try {
                System.out.printf("The Task [%d] will be executed.\n", i);
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.printf("The Task [%d] execute done.\n", i);
            } catch (InterruptedException e) {
                System.out.printf("The Task [%d]  be interrupted.\n", i);
            }
        };
    }
}
