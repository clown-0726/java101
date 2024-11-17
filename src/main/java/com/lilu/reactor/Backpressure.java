package com.lilu.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Backpressure {
    public static void main(String[] args) throws InterruptedException {
        run2();
    }

    /**
     * 在消费者一订阅的时候就通过背压通知发布者可以接受的数据流的数量
     * @throws InterruptedException
     */
    private static void run1() throws InterruptedException {
        Scheduler elastic = Schedulers.elastic();
        Flux<String> just = Flux.just("Hello", "World", "Nihao", "Shijie");

        just.publishOn(elastic)
                .map(val -> val.toUpperCase())
                .subscribe(
                        Backpressure::printThread,         // onNext(String s)
                        Backpressure::printThread,         // onError(Throwable throwable)
                        () -> printThread("Finished!"), // onComplete()
                        subscription -> {       // onSubscribe(Subscription s)
                            subscription.request(1);
                        }
                );

        Thread.sleep(1000L);
    }

    /**
     * 通过背压控制消费的速率
     * @throws InterruptedException
     */
    private static void run2() throws InterruptedException {
        Scheduler elastic = Schedulers.elastic();
        Flux<String> just = Flux.just("Hello", "World", "Nihao", "Shijie");

        just.publishOn(elastic)
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        printThread("onNext: " + s);
                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        Thread.sleep(3000L);
    }

    // Tool method
    private static void printThread(Object o) {
        String name = Thread.currentThread().getName();
        System.out.println("Thread name: " + name + " | Object: " + o);
    }
}
