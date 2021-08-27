package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponRate;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CouponRepositorySDJ extends JpaRepository<Coupon, Long> {

    CouponRate getRatecouponByName(@Param("name")String name);

    @Query("select s from CouponState s where s.user = :user and s.coupon = :coupon")
    Optional<CouponState> getCouponstateByUserAndCoupon(@Param("user")User user,@Param("coupon")Coupon coupon);
}
