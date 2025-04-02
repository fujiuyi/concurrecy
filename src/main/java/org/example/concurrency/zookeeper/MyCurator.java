package org.example.concurrency.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyCurator {

    private final String connectString;

    private final static String namespace = "ZK_distribute_lock";

    public MyCurator(@Value("${spring.data.zookeeper.host}") String connectString) {
        this.connectString = connectString;
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new RetryNTimes(3, 10000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        return client;
    }

    @Bean
    public InterProcessMutex interProcessMutex(CuratorFramework curatorFramework) {
        // 分布式锁的根路径
        return new InterProcessMutex(curatorFramework, "/fujiyi-lock");
    }

}
