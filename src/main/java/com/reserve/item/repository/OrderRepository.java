package com.reserve.item.repository;

import com.reserve.item.domain.Item;
import com.reserve.item.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
