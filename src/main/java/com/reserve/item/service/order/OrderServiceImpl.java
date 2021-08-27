package com.reserve.item.service.order;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.Item;
import com.reserve.item.domain.Orders;
import com.reserve.item.domain.User;
import com.reserve.item.repository.OrderRepository;
import com.reserve.item.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    @Override
    public Optional<Orders> createOrder(User user, Item item, Coupon coupon) {
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setItem(item);
        orders.setCoupon(coupon);
        return Optional.ofNullable(orders);
    }

    @Override
    public boolean verifyOrder(Orders orders) {
        // 쿠폰 유효성 검사.   (Coupon state에 사용자의 쿠폰 개수가 유효한 지.)
        //                  (Coupon 이름으로 해당 쿠폰이 존재하는지)

        // 쿠폰을 적용했을 때 사용자의 잔고가 충분한 지.



        return false;
    }

    @Override
    public void acceptOrder(Orders orders) {

    }
}
