package com.reserve.item.repository;

import com.reserve.item.domain.Orders;

public interface OrderRepository {
    void create(Orders orders);    // Order 테이블에 하나 생성.
}
