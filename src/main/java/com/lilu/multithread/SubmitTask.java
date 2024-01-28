package com.lilu.multithread;

import java.util.concurrent.*;

public class SubmitTask {
    //public static void main(String[] args) throws ExecutionException, InterruptedException {
    //    ExecutorService threadPool = Executors.newCachedThreadPool();
    //    Future<Integer> future = threadPool.submit(() -> {
    //        System.out.println(1000L * 10);
    //        return 9;
    //    });
    //
    //    // 阻塞方法，只到当前线程池有结果之后再往下执行
    //    Integer num = future.get();
    //    System.out.println(num);
    //}

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 利用 execute 提交任务，没有返回结果
        threadPool.execute(() -> {
            System.out.println(1000L * 10);
        });
    }
}
