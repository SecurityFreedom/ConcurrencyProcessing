package com.reserve.item.repository;

import com.reserve.item.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    CouponRate getRatecouponByName(@Param("name")String name);
    CouponFixed getFixedcouponByName(@Param("name")String name);

    //인당 몇 장 발급인지
    @Query("select c.count from Coupon c where c.name = :name")
    Integer getCountByName(@Param("name") String name);
    @Query("select c.count from Coupon c where c.pk = :id")
    Integer getCountByPk(@Param("id") long id);

    //발급 가능한 갯수
    @Query("select c.count - s.currentAmount from CouponState s join s.coupon c where s.user = :user and s.coupon = :coupon")
    Integer getRemainByUserAndCoupon(@Param("user")User user, @Param("coupon")Coupon coupon);

    //유저가 발급받은 쿠폰 목록
    @Query("select s.coupon from CouponState s where s.user = :user")
    List<Coupon> getListByUser(@Param("user")User user);

    //대상 유저의 특정 쿠폰 발급정보
    @Query("select s from CouponState s where s.user = :user and s.coupon = :coupon")
    Optional<CouponState> getCouponstateByUserAndCoupon(@Param("user")User user,@Param("coupon")Coupon coupon);

}
