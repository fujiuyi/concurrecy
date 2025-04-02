package org.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.nio.charset.StandardCharsets;

public class CuratorExample {
    private static final String connect = "192.168.91.50:2181";
    private static final int timeout = 30000;

    private static final CuratorFramework curator = CuratorFrameworkFactory.builder()
            .connectString(connect)
            .connectionTimeoutMs(timeout)
            .sessionTimeoutMs(timeout)
            .retryPolicy(new RetryNTimes(3, timeout))
            .namespace("fujiuyi")
            .build();

    public static void main(String[] args) throws Exception {
        curator.start();


        curator.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/fujiuyi/a/a/a", "6666666".getBytes(StandardCharsets.UTF_8));

        byte[] bytes = curator.getData().forPath("/fujiuyi/a/a/a");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        curator.setData().forPath("/fujiuyi/a/a/a", "8888888".getBytes(StandardCharsets.UTF_8));

        bytes = curator.getData().forPath("/fujiuyi/a/a/a");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        curator.delete().deletingChildrenIfNeeded().forPath("/fujiuyi/a");
        curator.close();
    }
}
