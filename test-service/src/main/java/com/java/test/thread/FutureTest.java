package com.java.test.thread;

import java.util.concurrent.ExecutionException;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020/11/25 下午8:16
 */
public class FutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(12);
//        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(10, 100, TimeUnit.SECONDS, new LinkedBlockingDeque(), Executors.defaultThreadFactory());
//        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("future.supplyAsync");
//            System.out.println("future.supplyAsync:" + Thread.currentThread().getName());
//            return 123L;
//        },executorService).thenApply(value -> {
//            System.out.println("future.thenApply:" + value);
//            System.out.println("future.thenApply:" + Thread.currentThread().getName());
//            return value*2;
//        });
//        future.whenCompleteAsync((aLong, throwable) -> {
//            System.out.println("future.whenCompleteAsync:" + aLong);
//            System.out.println("future.whenCompleteAsync:" + Thread.currentThread().getName());
//        }, executorService);
//
//        System.out.println("future.get" + future.get());
//
//        executorService.shutdown();

//        System.out.println("main end");
    }
}
