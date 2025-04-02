package org.example.concurrency.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_REMOVED;

@Slf4j
@Service
public class DistributeLock {

    private static final String LOCK_PATH = "/distributed-locks";

    private static final String LOCK_NAME = "/pay-lock";

    private static CountDownLatch zkCountDown = new CountDownLatch(1);

    private final CuratorFramework curatorFramework;

    private CuratorCache cache;

    public DistributeLock(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
        initLockPath();
    }

    private void initLockPath() {
        try {
            if (curatorFramework.checkExists().forPath(LOCK_PATH) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(LOCK_PATH);

            }
            addWatcherToLock();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize lock path", e);
        }
    }

    private void addWatcherToLock() {
        cache = CuratorCache.build(curatorFramework, LOCK_PATH);
        log.info("监听事件1");
        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forPathChildrenCache(LOCK_PATH, curatorFramework, (client, event) -> {
                    log.info("监听事件");
                    if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                        String path = event.getData().getPath();
                        log.info("指定的节点已经释放：{}", path);
                        if (path.contains(LOCK_PATH)) {
                            zkCountDown.countDown();
                            log.info("释放计数器，让当前的线程来获得锁");
                        }
                    }

                }).build();
        cache.listenable().addListener(listener);
        log.info("监听事件2");
        cache.start();

//      这部分的实现和上边的效果是一样的，只不过现在不推荐
//        try {
//            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, LOCK_PATH, true);
//            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
//            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
//                @Override
//                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
//                    if (pathChildrenCacheEvent.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
//
//                    }
//                }
//            });
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to initialize lock path", e);
//        }
    }

    public void getLock() {
        while (true) {
            try {
                //先直接创建一个临时的节点，如果创建成功就认为获取到了锁，否则认为没有获取到
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(LOCK_PATH + LOCK_NAME);
                log.info("获取临时节点的分布式锁成功");
                return;
            } catch (Exception e) {
                log.info("获取临时节点的分布式锁失败");
                if (zkCountDown.getCount() <= 0) {
                    zkCountDown = new CountDownLatch(1);
                }
                try {
                    zkCountDown.await();
                } catch (Exception e2) {
                    log.info("zkCountDown e", e2);
                }
            }

        }
    }

    public boolean releaseLock() {
        try {
            if (curatorFramework.checkExists().forPath(LOCK_PATH + LOCK_NAME) != null) {
                curatorFramework.delete().forPath(LOCK_PATH + LOCK_NAME);
            }
            log.info("释放临时节点的分布式锁成功");
            return true;
        } catch (Exception e) {
            log.info("释放临时节点的分布式锁失败");
            return false;
        }

    }


}
