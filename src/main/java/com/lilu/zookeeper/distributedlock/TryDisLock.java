package com.lilu.zookeeper.distributedlock;

import com.lilu.zookeeper.ZUtil;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class TryDisLock {
    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
        ZooKeeper zk = ZUtil.getZooKeeper();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    DisLock disLock = new DisLock();
                    disLock.setZk(zk);
                    String threadName = Thread.currentThread().getName();
                    disLock.setThreadName(threadName);

                    try {
                        // 开始抢锁
                        disLock.tryLock();
                        // 开始干活
                        System.out.println("Working...");
                        Thread.sleep(1000);
                        // 释放锁
                        disLock.unlock();

                    } catch (InterruptedException | KeeperException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }

        while (true) {
        }
    }
}
