package com.lilu.multithread_pool;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Callable 和 Runnable 一样，都是对对线程任务的声明
        // 可以通过线程池执行器 Executor 或者线程类 Thread 进行执行
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        // 创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c); // 异步提交
        String res = future.get(); // 阻塞等待结果
        System.out.println(res);
        service.shutdown();
    }
}
