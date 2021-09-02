package com.reserve.item.repository.impl;

import com.reserve.item.domain.Orders;
import com.reserve.item.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OrderJpaRepository implements OrderRepository {
    @Autowired
    EntityManager em;

    @Override
    public void create(Orders orders) {
        em.persist(orders);
    }
}
