package com.lilu.zookeeper.distributedlock;

import lombok.Data;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Data
public class DisLock implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {
    ZooKeeper zk;
    String threadName;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    String pathName;

    public void tryLock() throws InterruptedException {
        System.out.println(this.threadName + " -- tryLock");
        zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "ctx");
        countDownLatch.await();
    }

    public void unlock() throws InterruptedException, KeeperException {
        zk.delete(pathName, -1);
        System.out.println("unlock success");
    }

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {

    }

    // For zk.create(...
    @Override
    public void processResult(int i, String s, Object o, String name) {
        System.out.println("xxxx" + name);
        if (name != null) {
            pathName = name;
            zk.getChildren("/", false, this, "ctx");
        }
    }

    // getChildren cb
    @Override
    public void processResult(int rc, String s, Object o, List<String> children, Stat stat) {
        // 一定可以看到自己前面的
        Collections.sort(children);
        int i = children.indexOf(pathName.substring(1));
        if (i == 0) {
            System.out.println("I am the 1st");
            try {
                zk.setData("/", threadName.getBytes(), -1);
                countDownLatch.countDown();
            } catch (KeeperException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            zk.exists("/" + children.get(i - 1), this, this, "ctx");
        }
    }

    // Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/", false, this, "ctx");
                break;
            case NodeChildrenChanged:
                break;
            case NodeDataChanged:
                break;
        }
    }
}
