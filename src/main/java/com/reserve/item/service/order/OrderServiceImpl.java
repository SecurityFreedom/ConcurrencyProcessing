package com.reserve.item.service.order;

import com.reserve.item.domain.*;
import com.reserve.item.repository.CouponRepository;
import com.reserve.item.repository.CouponStateRepository;
import com.reserve.item.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*  createOrder()   -> orders 객체 생성 (주문표)
 *      추후 장바구니에 이용할 예정
 *  verifyOrder()   -> 생성된 orders 객체의 내용을 검증
 *
 *  acceptOrder()   -> 검증된 orders 객체를 실제 반영
 */

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;

    @Override
    public Optional<Orders> createOrder(User user, Item item, Coupon coupon) {
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setItem(item);
        orders.setCoupon(coupon);
        return Optional.of(orders);
    }

    @Override
    public boolean verifyOrder(Orders orders) {
        User user = orders.getUser();
        Coupon coupon = orders.getCoupon();
        Item item = orders.getItem();

        // 쿠폰 유효성 검사.   (Coupon state에 사용자의 쿠폰 개수가 유효한 지.)
        return couponRepository.getCouponstateByUserAndCoupon(user, coupon).map((couponState) -> {
            // 쿠폰이 하나라도 존재하는 경우. && 쿠폰을 적용했을 때 사용자의 잔고가 충분한 지. && 상품의 카테고리가 같은 지. && 수량이 충분한 지
            return couponState.getCurrentAmount
                    () > 0 && isAvailableMoney(user, coupon, item.getPrice())
                    && isSameCategory(item.getCategory(), coupon.getCategory()) && item.getQuantity() > 0;
        }).orElse(false);
    }

    @Override
    public boolean acceptOrder(Orders orders) {
        User user = orders.getUser();
        Coupon coupon = orders.getCoupon();
        Item item = orders.getItem();

        Optional<CouponState> couponStateOptional = couponRepository.getCouponstateByUserAndCoupon(user, coupon);
        return couponStateOptional.map(couponState -> {
            couponState.useCoupon();
            item.sell();
            orderRepository.save(orders);
            return true;
        }).orElse(false);
    }

    private boolean isSameCategory(Category itemCategory, Category couponCategory) {
        // 카테고리 이름이 같을 때, itemCategory >= couponCategory 에 속한다고 판단하고 return true.
        // 추후 Category 테이블을 따로 생성하여 확장하면, 바꾸어야 함.
        return itemCategory == couponCategory;
    }

    private boolean isAvailableMoney(User user, Coupon coupon, int price) {
        System.out.println("coupon.getDiscountValue() = " + coupon.getDiscountValue(price));
        System.out.println("user.getAccount() = " + user.getAccount());
        
        return coupon.getDiscountValue(price) <= user.getAccount();
    }
}
