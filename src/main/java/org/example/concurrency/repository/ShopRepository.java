package org.example.concurrency.repository;

import org.example.concurrency.dao.ShopTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopTable, Integer> {
    ShopTable findWithLockingById(Integer id);
}
