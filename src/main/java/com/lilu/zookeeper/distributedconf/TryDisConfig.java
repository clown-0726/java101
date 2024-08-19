package com.lilu.zookeeper.distributedconf;

import com.lilu.zookeeper.ZUtil;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class TryDisConfig {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zk = ZUtil.getZooKeeper();
        Conf conf = new Conf();

        DisConfig watchCallBack = new DisConfig();
        watchCallBack.setZk(zk);
        watchCallBack.setConf(conf);

        // 1. 当配置不存在的时候
        // 2. 当配置已存在，配置被更改的时候
        watchCallBack.await();

        while (true) {
            Thread.sleep(1000);
            System.out.println(conf.getConf());
        }
    }
}
