package org.example.concurrency.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.example.concurrency.dao.ShopTable;
import org.example.concurrency.repository.ShopRepository;
import org.example.concurrency.zookeeper.DistributeLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    private final DistributeLock distributeLock;

    private final InterProcessMutex interProcessMutex;

    public ShopService(ShopRepository shopRepository, DistributeLock distributeLock, InterProcessMutex interProcessMutex) {
        this.shopRepository = shopRepository;
        this.distributeLock = distributeLock;
        this.interProcessMutex = interProcessMutex;
    }

    //
    public ShopTable findById(Integer id) {
        return shopRepository.findById(id).orElse(null);
    }

    public ShopTable saveOrUpdate(ShopTable shopTable) {
        return shopRepository.save(shopTable);
    }


    public ShopTable getBookAndSave(Integer id) {
        try {
            interProcessMutex.acquire();
            return save(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                interProcessMutex.release();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Transactional
    protected ShopTable save(Integer id){
        //我就要一次买6本
        int buyNum = 6;
        ShopTable shopTable = shopRepository.findWithLockingById(id);
        if (shopTable.getCount() < 6) {
            log.info("没有了");
            shopTable.setCount(-1);
            return shopTable;
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.info("异常了");
        }
        shopTable.setCount(shopTable.getCount() - buyNum);

        saveOrUpdate(shopTable);

        return shopTable;
    }

}
