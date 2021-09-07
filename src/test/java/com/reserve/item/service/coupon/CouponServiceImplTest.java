package com.reserve.item.service.coupon;

import com.reserve.item.domain.*;
import com.reserve.item.repository.CategoryRepository;
import com.reserve.item.repository.CouponRepository;
import com.reserve.item.repository.CouponStateRepository;
import com.reserve.item.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CouponServiceImplTest {

    @Autowired
    CouponService couponService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CouponStateRepository couponStateRepository;

    @Test
    @DisplayName("쿠폰의 전체 개수 확인")
    @Transactional
    void 전체쿠폰확인() {
        //GIVEN
        Category category = new Category();
        category.setName("테스트카테고리");
        categoryRepository.save(category);

        CouponFixed couponFixed = CouponFixed.createCoupon("FIX쿠폰", category, 1000, 5);
        couponRepository.save(couponFixed);

        CouponRate couponRate = CouponRate.createCoupon("RATE쿠폰", category, 15, 3);
        couponRepository.save(couponRate);

        //WHEN
        List<Coupon> list = couponService.getCouponInfo();

        //THEN
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자가 처음 쿠폰 발급")
    @Transactional
    void 쿠폰받기() {
        //GIVEN
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        CouponFixed coupon = CouponFixed.createCoupon("name", category, 1000, 5);
        couponRepository.save(coupon);

        //WHEN
        assertThat(couponRepository.getCouponstateByUserAndCoupon(user, coupon)).isEmpty();

        //THEN
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
    }

    @Test
    @DisplayName("사용자가 쿠폰을 보유하고, 추가 발급 요청")
    @Transactional
    void 쿠폰받기2() {
        //GIVEN
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        CouponFixed coupon = CouponFixed.createCoupon("name", category, 1000, 5);
        couponRepository.save(coupon);

        //WHEN & THEN
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
    }

    @Test
    @DisplayName("사용자가 한도 이상의 쿠폰을 요구")
    @Transactional
    void 쿠폰받기3() {
        //GIVEN
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        CouponFixed coupon = CouponFixed.createCoupon("name", category, 1000, 1);
        couponRepository.save(coupon);

        //WHEN & THEN
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(true);
        assertThat(couponService.getCoupon(user, coupon)).isEqualTo(false);
    }
}
