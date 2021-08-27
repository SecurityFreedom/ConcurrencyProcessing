package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;

import java.util.Optional;

public interface CouponStateRepository {
    Optional<CouponState> findByUserAndCoupon(User user, Coupon coupon);
    void create(User user, Coupon coupon);  // 레코드 생성. (user, coupon, 1 , 1) 쿠폰을 발급 받는 것.
    void increaseAmount(User user, Coupon coupon);  // 보유 개수 하나 추가. (user, coupon, +1 , +1)
}
