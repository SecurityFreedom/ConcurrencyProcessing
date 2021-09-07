package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import com.reserve.item.dto.CouponInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponStateRepository extends JpaRepository<CouponState,Long> {

    //유저가 발급받은 쿠폰 목록
    //테스트 필요
    @Query("select new com.reserve.item.dto.CouponInfoDTO(s.currentAmount, c.name) from CouponState s join s.coupon c where s.user = :user")
    List<CouponInfoDTO> getListByUser(@Param("user") User user);

    Optional<CouponState> findByUserAndCoupon(User user, Coupon coupon);

//    void create(User user, Coupon coupon);  // 레코드 생성. (user, coupon, 1 , 1) 쿠폰을 발급 받는 것.
//
//    void increaseAmount(User user, Coupon coupon);  // 보유 개수 하나 추가. (user, coupon, +1 , +1)
//
//    void decreaseAmount(User user, Coupon coupon);  // 보유 개수 하나 감소. (user, coupon, -1 , 그대로)

}
