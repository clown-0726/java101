package com.lilu.zookeeper;

import com.lilu.utils.PropertiesReader;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ZUtil {
    private static ZooKeeper zk;
    private static String address;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    static {
        Properties propsConfig = PropertiesReader.getProperties("zookeeper.properties");
        String connectString = propsConfig.getProperty("connectString");
        address = connectString + "/appconfig";
    }

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
