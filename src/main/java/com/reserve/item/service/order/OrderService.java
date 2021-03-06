package com.reserve.item.service.order;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.Item;
import com.reserve.item.domain.Orders;
import com.reserve.item.domain.User;

import java.util.Optional;

public interface OrderService {
    Optional<Orders> createOrder(User user, Item item, Coupon coupon);
    boolean verifyOrder(Orders orders);
    boolean acceptOrder(Orders orders);
}
