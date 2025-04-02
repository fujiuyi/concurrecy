package org.example.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ZookeeperExample {

    private static final String connect = "192.168.91.50:2181";
    private static final int timeout = 30000;

    public static void main(String[] args) {
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                log.info("监听到的事件 {}", watchedEvent);
            }
        };

        try {
            ZooKeeper zooKeeper = new ZooKeeper(connect, timeout, watcher);

            List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
            String result = zooKeeper.create("/fujiuyi/test", "test".getBytes(StandardCharsets.UTF_8),
                    acl, CreateMode.PERSISTENT);
            log.info(result);

            byte[] bytes = zooKeeper.getData("/fujiuyi/test", watcher, null);

            zooKeeper.delete("/fujiuyi/test",-1);
            //log.info(new String(bytes));
        } catch (Exception e) {
            log.warn("exception", e);
        }

    }
}
