package com.lilu.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class HelloReactor {
    public static void main(String[] args) {
        run2();
    }

    // With Scheduler
    private static void run3() throws InterruptedException {
        Flux.just("Hello", "World")
                .publishOn(Schedulers.elastic()) // 线程池切换
                .map(val -> val.toUpperCase())
                .subscribe(
                        HelloReactor::printThread,        // 数据消费
                        HelloReactor::printThread,        // 消费异常
                        () -> printThread("Finished!") // Finally
                );

        Thread.sleep(1000L);
    }

    // With Scheduler
    private static void run2() {
        Flux.just("Hello", "World")
                .publishOn(Schedulers.elastic()) // 线程池切换
                .subscribe(HelloReactor::printThread);
    }

    // In the same thread
    private static void run1() {
        Flux.just("Hello", "World")
                .subscribe(HelloReactor::printThread);
    }

    // Tool method
    private static void printThread(Object o) {
        String name = Thread.currentThread().getName();
        System.out.println("Thread name: " + name + " | Object: " + o);
    }
}
