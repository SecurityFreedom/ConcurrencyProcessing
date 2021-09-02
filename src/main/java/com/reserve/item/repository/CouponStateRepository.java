package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponStateRepository extends JpaRepository<CouponState,Long> {
    //유저가 발급받은 쿠폰 목록
    //테스트 필요
    @Query("select s.coupon from CouponState s where s.user = :user")
    List<Coupon> getListByUser(@Param("user") User user);
}
