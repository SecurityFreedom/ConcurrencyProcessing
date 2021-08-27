package com.reserve.item.service.order;

import com.reserve.item.domain.*;
import com.reserve.item.repository.CouponStateRepository;
import com.reserve.item.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CouponStateRepository couponStateRepository;

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
        User user = orders.getUser();
        Coupon coupon = orders.getCoupon();
        Item item = orders.getItem();

        // 쿠폰 유효성 검사.   (Coupon state에 사용자의 쿠폰 개수가 유효한 지.)
        return couponStateRepository.findByUserAndCoupon(user,coupon).map((couponState) -> {
            // 쿠폰이 하나라도 존재하는 경우. && 쿠폰을 적용했을 때 사용자의 잔고가 충분한 지. && 상품의 카테고리가 같은 지.
            if(couponState.getCurrentAmount() > 0 && isAvailableMoney(user,coupon,item.getPrice())
                    && isSameCategory(item.getCategory(),coupon.getCategory())){
                return true;
            }
            return false;
        }).orElse(false);
    }

    private boolean isSameCategory(Category itemCategory, Category couponCategory) {
        // BODY 작성.
        //
        //
        return true;
    }

    private boolean isAvailableMoney(User user, Coupon coupon, int price) {
        // BODY 작성.
        //
        //
        return true;
    }

    @Override
    public boolean acceptOrder(Orders orders) {
        User user = orders.getUser();
        Coupon coupon = orders.getCoupon();
        Optional<CouponState> couponStateOptional = couponStateRepository.findByUserAndCoupon(user, coupon);
        return couponStateOptional.map(couponState -> {
            couponStateRepository.decreaseAmount(user,coupon);
            orderRepository.create(orders);
            return true;
        }).orElse(false);
    }
}
