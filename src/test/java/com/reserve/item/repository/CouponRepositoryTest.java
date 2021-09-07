package com.reserve.item.repository;

import com.reserve.item.domain.*;
import com.reserve.item.domain.exceptions.NotEnoughCouponException;
import com.reserve.item.dto.CouponInfoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CouponRepositoryTest {
    @Autowired
    CouponRepository couponRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CouponStateRepository csRepo;

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

    /*
     *  TEST DATABASE VALUES
     *
     *  USER
     *      pk      id          name            password        email
     *      1L      'Testid'    'name'          'password'      'email'
     *
     *  CATEGORY
     *      pk      name
     *      1L      '카테고리이름'
     *
     *  COUPON
     *      pk      name        CATEGORY(fk)    amount          count
     *      1L      'FIX쿠폰'    1L(category)    1000            10
     *      2L      'RATE쿠폰'   1L(category)    10(%)           10
     *
     *  COUPON_STATE
     *      pk      USER(fk)    COUPON(fk)
     *      1L      1L(user)    1L(coupon)
     *      2L      1L(user)    2L(coupon)
     */
    @BeforeEach
    void 데이터베이스_테스트_추가() {
        // 유저 생성/저장
        User user = User.createUser("Testid", "name", "password", "email");
        userRepo.save(user);

        // 카테고리 생성/저장
        Category category = new Category();
        category.setName("카테고리이름");
        categoryRepository.save(category);

        // 쿠폰 생성/저장
        Coupon coupon = CouponFixed.createCoupon("FIX쿠폰", category, 1000, 10);
        couponRepo.save(coupon);

        Coupon coupon2 = CouponRate.createCoupon("RATE쿠폰", category, 10, 10);
        couponRepo.save(coupon2);

        // 쿠폰 State 생성/저장
        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        csRepo.save(couponState);

        CouponState couponState2 = CouponState.issueNewCoupon(user, coupon2);
        csRepo.save(couponState2);
    }

    @Test
    @DisplayName("이름으로 쿠폰 가져오기")
    @Transactional
    public void 쿠폰발급() {
        CouponRate c = couponRepo.getRatecouponByName("RATE쿠폰");
        assertThat(c.getCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("이름으로 가져온 쿠폰 클래스")
    @Transactional
    public void 쿠폰조회() {
        CouponRate c = couponRepo.getRatecouponByName("RATE쿠폰");
        assertThat(c.getDiscountRate()).isEqualTo(10);
    }

    @Test
    @DisplayName("발급 최대 갯수 조회")
    @Transactional
    public void 쿠폰갯수() {
        Integer couponNum = couponRepo.getCountByName("RATE쿠폰");
        assertThat(couponNum).isEqualTo(10);

        CouponFixed coupon = couponRepo.getFixedcouponByName("FIX쿠폰");
        Integer couponNum2 = couponRepo.getCountByPk(coupon.getPk());
        assertThat(couponNum2).isEqualTo(10);
    }

    @Test
    @DisplayName("추가 발급 가능 갯수 조회")
    @Transactional
    public void 추가발급갯수() {
        Optional<User> optionalUser = userRepo.findByName("name");
        assertThat(optionalUser.isPresent()).isEqualTo(true);


        CouponFixed cf = couponRepo.getFixedcouponByName("FIX쿠폰");
        optionalUser.ifPresentOrElse((user) -> {
                    assertThat(couponRepo.getRemainByUserAndCoupon(user, cf)).isEqualTo(9);
                },
                () -> Assertions.fail("findByName return empty."));
    }

    @Test
    @DisplayName("발급 정보 조회")
    @Transactional
    public void 정보조회() {
        User user = userRepo.findByName("name").get();
        CouponFixed c = couponRepo.getFixedcouponByName("FIX쿠폰");
        Optional<CouponState> couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        assertThat(couponstateByUserAndCoupon.isPresent());
    }
    @Test
    @DisplayName("발급 정보 조회2")
    @Transactional
    public void 조회2() {
        User user = userRepo.findByName("name").get();
        List<CouponInfoDTO> listByUser = csRepo.getListByUser(user);
        assertThat(listByUser.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원에게 발급 상태 확인 후 추가 발급")
    @Transactional
    public void 회원에게쿠폰발급() {
        User user = userRepo.findByName("name").get();
        CouponFixed c = couponRepo.getFixedcouponByName("FIX쿠폰");

        Optional<CouponState> couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        if (couponstateByUserAndCoupon.isPresent()) {
            CouponState couponState = couponstateByUserAndCoupon.get();
            couponState.issueCoupon();
            assertThat(couponState.getUser()).isEqualTo(user);
        }
        em.flush();
        em.clear();

        couponstateByUserAndCoupon = couponRepo.getCouponstateByUserAndCoupon(user, c);
        if (!couponstateByUserAndCoupon.isEmpty()) {
            CouponState couponState = couponstateByUserAndCoupon.get();
            assertThat(couponState.getCurrentAmount()).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("쿠폰 다 사용시 사용 불가능")
    @Transactional
    public void 쿠폰사용() {
        User user = userRepo.findByName("name").get();
        CouponFixed c = couponRepo.getFixedcouponByName("FIX쿠폰");

        CouponState couponstate = couponRepo.getCouponstateByUserAndCoupon(user, c).get();
        couponstate.useCoupon();
        //하나만 발급됐으니 예외 터짐.
        assertThrows(NotEnoughCouponException.class, () -> couponstate.useCoupon());
    }

    @Test
    @DisplayName("쿠폰 발급 목록")
    @Transactional
    public void 쿠폰목록() {
        User user = userRepo.findByName("name").get();
        List<Coupon> listByUser = couponRepo.getListByUser(user);
        for (Coupon coupon : listByUser) {
            System.out.println("[coupon : " + coupon.getName() + "]");
        }

        //현재 발급은 하나만 된 상태.
        assertThat(listByUser.size()).isEqualTo(2);
    }

}