package com.lilu.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Q4BlockingQueue {
    // 无界队列
    //static BlockingQueue<String> bq = new LinkedBlockingQueue<>();
    // 有界队列
    static BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // bq.add("abc" + i); 如果是 add，满了则会报错
                    // bq.offer("abc" + i); 如果是 offer，返回值为布尔，表示是否增加成功，不会阻塞
                    bq.put("abc" + i); // 如果满了，就会阻塞
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        String res = bq.take(); // 如果没有，则会阻塞
                        System.out.println(Thread.currentThread().getName() + ": " + res);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
