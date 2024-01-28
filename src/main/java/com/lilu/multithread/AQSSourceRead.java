package com.lilu.multithread;

import java.util.concurrent.locks.ReentrantLock;

public class AQSSourceRead {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
    }
}
