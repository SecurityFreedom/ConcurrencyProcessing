package com.reserve.item.repository;

import com.reserve.item.domain.Item;
import com.reserve.item.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositorySDJ extends JpaRepository<Orders, Long> {

}
