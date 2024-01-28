package com.lilu.multithread_pool;

import java.util.concurrent.Executors;

public class Child2ThreadPoolExecutor {
    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(4);
        Executors.newScheduledThreadPool(4);
    }
}
