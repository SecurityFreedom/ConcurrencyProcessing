package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponStateRepositorySDJ extends JpaRepository<CouponState,Long> {

    //유저가 발급받은 쿠폰 목록
    //테스트 필요
    @Query("select s.coupon from CouponState s where s.user = :user")
    List<Coupon> getListByUser(@Param("user") User user);

//    void create(User user, Coupon coupon);  // 레코드 생성. (user, coupon, 1 , 1) 쿠폰을 발급 받는 것.
//
//    void increaseAmount(User user, Coupon coupon);  // 보유 개수 하나 추가. (user, coupon, +1 , +1)
//
//    void decreaseAmount(User user, Coupon coupon);  // 보유 개수 하나 감소. (user, coupon, -1 , 그대로)

}
