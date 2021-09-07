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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    // Integrity 보장 X -> JPA 에서 안된다고 하는 듯.
    @Test
    @Transactional
    @DisplayName("쿠폰에 동시 접근 테스트")
    public void 동시접근테스트() throws InterruptedException {
        User user = User.createUser("TESTid", "TESTname", "TESTpassword", "TESTemail");
        userRepository.save(user);

        Category category = new Category();
        categoryRepository.save(category);

        Coupon coupon = CouponFixed.createCoupon("음식쿠폰",category,1000,5);
        couponRepository.save(coupon);


        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                assertThrows(DataIntegrityViolationException.class, ()->{
                    couponService.getCoupon(user, coupon);
                });
//                couponService.getCoupon(user, coupon);
                latch.countDown();
            });
        }
        latch.await();

        //assertThat(couponService.getUserCoupon(user).get(0).getCurrentAmount()).isEqualTo(5);

    }



    //CONCURRENCY TEST CODE (REFERENCE)

//    @Test
//    public void testCounterWithConcurrency() throws InterruptedException {
//        int numberOfThreads = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//        MyCounter counter = new MyCounter();
//        for (int i = 0; i < numberOfThreads; i++) {
//            service.execute(() -> {
//                counter.increment();
//                latch.countDown();
//            });
//        }
//        latch.await();
//        assertThat(numberOfThreads).isEqualTo(counter.getCount());
//    }
//
//    @Getter
//    public class MyCounter {
//        private int count;
//        public void increment() {
//            int temp = count;
//            count = temp + 1;
//        }
//    }


}
