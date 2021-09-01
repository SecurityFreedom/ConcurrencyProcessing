package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponStateRepository extends JpaRepository<CouponState,Long> {
    Optional<CouponState> findByUserAndCoupon(User user, Coupon coupon);
}
