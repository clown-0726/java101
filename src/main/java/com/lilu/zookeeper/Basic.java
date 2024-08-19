package com.lilu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Basic {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // zk 没有连接池概念，有连接 session 概念，一个连接就是一个 session
        // watch 是 session 级别的
        // 可以写多个 zk 节点的连接，ZooKeeper 类内部会自动做负载均衡，如果这时候断开连接，zk 会自动连接到别的节点，并且 session id 不会变
        // 连接断开之后会保持 3 秒
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String path = watchedEvent.getPath();
                System.out.println(path);

                // 得到 zk 的所有连接状态的 watch 对象
                Event.KeeperState state = watchedEvent.getState();
                switch (state) {
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

                // 得到 zk 的所有连接类型的 watch 对象
                Event.EventType type = watchedEvent.getType();
                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                    case DataWatchRemoved:
                        break;
                    case ChildWatchRemoved:
                        break;
                    case PersistentWatchRemoved:
                        break;
                }
            }
        });

        countDownLatch.await();
        // .......
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("...ing");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("...ed");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        // ---------------------------------------
        // 节点类型：
        // CreateMode.EPHEMERAL             临时节点
        // CreateMode.EPHEMERAL_SEQUENTIAL  临时节点 + 序列化
        // CreateMode.PERSISTENT            持久节点
        // CreateMode.PERSISTENT_SEQUENTIAL 持久节点 + 序列化

        // 创建节点操作没有回调，是产生事件的
        String s = zk.create("/abc", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        // 获取节点数据的时候可以注册回调机制
        Stat stat = new Stat();
        byte[] data = zk.getData("/abc", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);

                // 打破一次回调，继续监听事件的发生
                try {
                    // true：是 Default 的 watch，也就是一开始 new ZooKeeper 后面跟的 watch
                    // this：是当前的 watch
                    zk.getData("/abc", this, stat);
                } catch (KeeperException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, stat);

        System.out.println(new String(data));

        // 再次设置数据，会触发节点注册的事件
        Stat stat1 = zk.setData("/abc", "new data".getBytes(), 0);

        // 用于测试 watch 是一次性的，这次不会发生，除非在监听的时候再次注册
        Stat stat2 = zk.setData("/abc", "new data 123".getBytes(), stat1.getVersion());
    }
}
