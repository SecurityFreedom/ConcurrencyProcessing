package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponRate;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
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