package com.lilu.multithread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * CAS(Compare and set) 无锁优化，自旋锁，乐观锁
 * <p>
 * cas(value, expected, newValue) {
 * if (value == expected) {
 * value = newValue
 * }
 * otherwise try again or fail
 * }
 * <p>
 * 是 CPU 原语（指令集）支持的读写，中间不能被打断
 * 关注一下 Unsafe 这个类（单例模式），直接 C++ 操作虚拟机内存
 */

public class AtomicIntegerEx {
    //AtomicInteger count = new AtomicInteger(0);
    // 当线程数非常多时候，LongAdder 内部使用了分段锁，先分段类加，再最后求和
    // 因此线程非常多的时候 LongAdder 有优势
    LongAdder count = new LongAdder();

    void m() {
        for (int i = 0; i < 1000; i++) {
            //count.incrementAndGet();
            count.add(1);
        }
    }

    public static void main(String[] args) {
        AtomicIntegerEx atomicIntegerEx = new AtomicIntegerEx();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(atomicIntegerEx::m, "thread_" + i));
        }

        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(atomicIntegerEx.count);
    }
}
