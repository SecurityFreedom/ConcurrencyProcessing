package com.reserve.item.repository;

import com.reserve.item.domain.*;
import com.reserve.item.domain.exceptions.NotEnoughCouponException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.      api.Assertions.*;

@SpringBootTest
@Transactional
class CouponRepositorySDJTest {
    @Autowired
    CouponRepositorySDJ couponRepo;

    @Autowired
    UserRepositorySDJ userRepo;

    @Autowired
    CouponStateRepositorySDJ csRepo;

    @Autowired
    EntityManager em;

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
        assertThat(remainByUserAndCoupon).isEqualTo(9);
    }

    @Test
    @DisplayName("발급 정보 조회")
    public void 정보조회(){
        User user = userRepo.getById(1L);
        CouponFixed c = couponRepo.getFixedcouponByName("추석 쿠폰");
        Optional<CouponState> couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        assertThat(couponstateByUserAndCoupon.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("회원에게 발급 상태 확인 후 추가 발급")
    public void 회원에게쿠폰발급() {
        User user = userRepo.findById(1L).get();
        CouponFixed c = couponRepo.getFixedcouponByName("추석 쿠폰");

        Optional<CouponState> couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        if(!couponstateByUserAndCoupon.isEmpty()){
            CouponState couponState = couponstateByUserAndCoupon.get();
            couponState.issueCoupon();
            assertThat(couponState.getUser()).isEqualTo(user);
        }
        em.flush();
        em.clear();

        couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        if(!couponstateByUserAndCoupon.isEmpty()){
            CouponState couponState = couponstateByUserAndCoupon.get();
            assertThat(couponState.getCurrentAmount()).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("쿠폰 다 사용시 사용 불가능")
    public void 쿠폰사용(){
        User user = userRepo.findById(1L).get();
        CouponFixed c = couponRepo.getFixedcouponByName("추석 쿠폰");

        CouponState couponstate = couponRepo.getCouponstateByUserAndCoupon(user, c).get();
        couponstate.useCoupon();
        //하나만 발급됐으니 예외 터짐.
        assertThrows(NotEnoughCouponException.class, ()->couponstate.useCoupon());
    }

    @Test
    @DisplayName("쿠폰 발급 목록")
    public void 쿠폰목록() {
        User user = userRepo.findById(1L).get();
        CouponFixed c = couponRepo.getFixedcouponByName("추석 쿠폰");
        List<Coupon> listByUser = couponRepo.getListByUser(user);
        for (Coupon coupon : listByUser) {
            System.out.println("[coupon : " + coupon.getName() + "]");
        }

        //현재 발급은 하나만 된 상태.
        assertThat(listByUser.size()).isEqualTo(1);
    }
}