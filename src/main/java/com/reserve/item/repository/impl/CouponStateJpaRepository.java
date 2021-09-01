package com.reserve.item.repository.impl;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import com.reserve.item.repository.CouponStateRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CouponStateJpaRepository implements CouponStateRepository {
    @Override
    public Optional<CouponState> findByUserAndCoupon(User user, Coupon coupon) {
        return Optional.empty();
    }

    @Override
    public void create(User user, Coupon coupon) {

    }

    @Override
    public void increaseAmount(User user, Coupon coupon) {

    }

    @Override
    public void decreaseAmount(User user, Coupon coupon) {

    }
}
