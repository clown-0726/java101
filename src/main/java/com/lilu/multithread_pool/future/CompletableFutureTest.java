package com.lilu.multithread_pool;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    static Double getDoubleNum() {
        return 23.0;
    }

    public static void main(String[] args) {
        // --------------------- 第一种写法 ---------------------
        // 声明三个异步任务
        CompletableFuture<Double> future1 = CompletableFuture.supplyAsync(() -> getDoubleNum());
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> {
            return 23.0;
        });
        CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(() -> getDoubleNum());

        // 阻塞，等待三个任务完成
        CompletableFuture.allOf(future1, future2, future3).join();
        System.out.println("End...");

        // --------------------- 第二种写法 ---------------------
        // 流式写法
        CompletableFuture.supplyAsync(() -> getDoubleNum())
                .thenApply(String::valueOf)
                .thenApply(str -> "RES:" + str)
                .thenAccept(System.out::println);
    }
}
