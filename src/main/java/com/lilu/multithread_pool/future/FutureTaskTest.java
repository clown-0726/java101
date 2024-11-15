package com.lilu.multithread_pool.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask 即是 Runnable 又是 Future<V>
        // 也就是说，FutureTask 即可以做线程任务的声明，又可以存储其未来返回结果
        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.MICROSECONDS.sleep(500);
            return "Hello FutureTask";
        });

        new Thread(task).start();
        System.out.println(task.get());
    }
}
