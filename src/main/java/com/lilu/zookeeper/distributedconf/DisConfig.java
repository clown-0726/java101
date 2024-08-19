package com.lilu.zookeeper.distributedconf;

import lombok.Data;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

@Data
public class DisConfig implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
    ZooKeeper zk;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    Conf conf;

    public void await() {
        zk.exists("/appconf", this, this, "ctx");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // AsyncCallback.DataCallback
    @Override
    public void processResult(int rc, String path, Object o, byte[] data, Stat stat) {
        if (data != null) {
            String s = new String(data);
            conf.setConf(s);
            countDownLatch.countDown();
        }
    }

    // AsyncCallback.StatCallback
    @Override
    public void processResult(int rc, String path, Object o, Stat stat) {
        if (stat != null) {
            zk.getData("/appconf", this, this, "ctx");
        }
    }

    // Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/appconf", this, this, "ctx");
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                zk.getData("/appconf", this, this, "ctx");
                break;
            case NodeChildrenChanged:
                break;
        }

    }
}
