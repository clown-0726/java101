package com.lilu.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BQ4SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        // 无长度 Queue（容量为 0 的队列）
        BlockingQueue<String> bq = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                bq.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        bq.put("abc"); // 如果没有消费，则一直阻塞状态
        System.out.println(bq.size());
    }
}
