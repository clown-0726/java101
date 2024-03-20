package com.lilu.misc;

public class CacheLinePadding {
    /**
     * 这个程序用来测试缓存行对齐问题
     * 如果 new long[2]，那么大概率下面两个线程在执行的时候每个线程的执行 CPU 都会将缓存行读取到缓存中
     * 因此会产生数据不一致，也就造成伪并发问题。
     *
     * 如果 new long[16]，那么大概率下面两个线程在执行的时候每个线程的执行 CPU 读取的缓存行一个读取前 8 个字节，第二 CPU 读取的缓存行是后面 8 个字节
     * 因此会不会产生数据不一致。
     */
    public static volatile long[] arr = new long[2];
    //public static volatile long[] arr = new long[16];

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr[0] = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_0000_0000L; i++) {
                arr[1] = i;
                //arr[8] = i;
            }
        });
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_000);
    }
}
