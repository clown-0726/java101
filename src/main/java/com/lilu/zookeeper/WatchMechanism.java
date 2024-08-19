package com.lilu.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class WatchMechanism {
    public static void main(String[] args) {
        ZooKeeper zk = ZUtil.getZooKeeper();
        zk.getData("/data", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType());
            }
        }, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object ctx, byte[] bytes, Stat stat) {
                System.out.println(ctx.toString());
            }
        }, "ctx");

        System.out.println("Now blocking here...");
        while (true) {
        }

        /*
          有 watch 的方法：
          getData
          getChildren
          exists

          没有 watch 的方法：
          create
          delete
          setData
         */
    }
}
