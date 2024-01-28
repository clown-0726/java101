package com.lilu.container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Q4QueueOperation {
    public static void main(String[] args) {
        Queue<String> q = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            q.offer("abc" + i);
        }

        System.out.println(q);
        System.out.println(q.size());
        System.out.println(q.poll()); // 取出一个元素，同时删除
        System.out.println(q.peek()); // 取出一个元素，但是不删除
    }
}
