package com.lilu.container;

import java.util.PriorityQueue;

public class BQ4PriorityQueue {
    public static void main(String[] args) {
        // 其内部实现就是有序二叉树结构
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.add("a");
        pq.add("d");
        pq.add("c");
        pq.add("z");
        pq.add("v");

        for (int i = 0; i < 5; i++) {
            System.out.println(pq.poll());
        }
    }
}
