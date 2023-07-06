package com.cande.store.repository;

import com.cande.store.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomOrderRepository extends JpaRepository<CustomerOrder,Integer> {
}
