package com.reserve.item.repository;

import com.reserve.item.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CouponRepositorySDJTest {
    @Autowired
    CouponRepositorySDJ couponRepo;

    @Autowired
    UserRepositorySDJ userRepo;

    @Autowired
    CouponStateRepositorySDJ csRepo;

    @Test
    @DisplayName("이름으로 쿠폰 가져오기")
    public void 쿠폰발급() {
        CouponRate c = couponRepo.getRatecouponByName("가을 쿠폰");
        assertThat(c.getCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("이름으로 가져온 쿠폰 클래스")
    public void 쿠폰조회() {
        CouponRate c = couponRepo.getRatecouponByName("가을 쿠폰");
        assertThat(c.getDiscountRate()).isEqualTo(10);
    }

    @Test
    @DisplayName("발급 최대 갯수 조회")
    public void 쿠폰갯수() {
        Integer couponNum = couponRepo.getCountByName("가을 쿠폰");
        assertThat(couponNum).isEqualTo(10);

        CouponFixed coupon = couponRepo.getFixedcouponByName("추석 쿠폰");
        Integer couponNum2 = couponRepo.getCountByPk(coupon.getPk());
        assertThat(couponNum2).isEqualTo(10);
    }

    @Test
    @DisplayName("추가 발급 가능 갯수 조회")
    public void 추가발급갯수() {
        User user = userRepo.getById(1L);
        CouponFixed cf = couponRepo.getFixedcouponByName("추석 쿠폰");
        Integer remainByUserAndCoupon = couponRepo.getRemainByUserAndCoupon(user, cf);
        System.out.println(remainByUserAndCoupon + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }

    @Test
    @DisplayName("회원에게 쿠폰 발급 후 발급 상태 확인")
    public void 회원에게쿠폰발급() {
        User user = userRepo.getById(2L);
        CouponRate c = couponRepo.getRatecouponByName("가을 쿠폰");

        //발급 가능인지 체크
        Optional<CouponState> couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        boolean empty = couponstateByUserAndCoupon.isEmpty();
        Assertions.assertThat(empty).isEqualTo(true);
    }

}