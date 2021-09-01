package com.reserve.item.repository.impl;

import com.reserve.item.domain.Orders;
import com.reserve.item.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaRepository implements OrderRepository {
    @Override
    public void create(Orders orders) {

    }
}
