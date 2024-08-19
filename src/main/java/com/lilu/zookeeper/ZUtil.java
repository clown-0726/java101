package com.lilu.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZUtil {
    private static ZooKeeper zk;
    private static String address = "127.0.0.1:2181/appconfig";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper getZooKeeper() {
        try {
            zk = new ZooKeeper(address, 3000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    switch (watchedEvent.getState()) {
                        case Unknown:
                            break;
                        case Disconnected:
                            break;
                        case NoSyncConnected:
                            break;
                        case SyncConnected:
                            System.out.println("SyncConnected");
                            countDownLatch.countDown();
                            break;
                        case AuthFailed:
                            break;
                        case ConnectedReadOnly:
                            break;
                        case SaslAuthenticated:
                            break;
                        case Expired:
                            break;
                        case Closed:
                            break;
                    }
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }

}
