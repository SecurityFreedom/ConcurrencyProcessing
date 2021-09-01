//package com.reserve.item.service.coupon;
//
//import com.reserve.item.domain.Category;
//import com.reserve.item.domain.Coupon;
//import com.reserve.item.domain.User;
//import com.reserve.item.repository.CouponStateRepository;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Transactional
//@RequiredArgsConstructor
//class CouponServiceImplTest {
//    TestCouponRepository couponRepository = new TestCouponRepository();
//    CouponStateRepository couponStateRepository;
//
//    @Test
//    @DisplayName("쿠폰 모두 가져오기")
//    void getCouponInfo() {
//        Coupon coupon1 = new Coupon() {
//            @Override
//            public void changeDiscount(int amount) {
//            }
//
//            @Override
//            public int getDiscountValue(int itemPrice) {
//                return 0;
//            }
//        };
//
//        Coupon coupon2 = new Coupon() {
//            @Override
//            public void changeDiscount(int amount) {
//            }
//
//            @Override
//            public int getDiscountValue(int itemPrice) {
//                return 0;
//            }
//        };
//
//        couponRepository.addCoupon(coupon1);
//        couponRepository.addCoupon(coupon2);
//
//        assertThat(couponRepository.findAll().size()).isEqualTo(2);
//    }
//
//    @Test
//    @DisplayName("쿠폰 발급 받기 - 현재 발급 / 제한 발급 비교")
//    void getCoupon() {
//        TestCoupon testCoupon = new TestCoupon();
//        testCoupon.setCount(3);
//        assertThat(2).isLessThan(testCoupon.getCount());
//    }
//
//    @Test
//    @DisplayName("쿠폰 발급 받기 - 쿠폰을 받은 적이 없을 때")
//    void getCouponX() {
//        Coupon coupon = new Coupon() {
//            @Override
//            public void changeDiscount(int amount) {
//
//            }
//
//            @Override
//            public int getDiscountValue(int itemPrice) {
//                return 0;
//            }
//        };
//
//        User user = User.createUser("id","name","password","email@email.com");
//
//        /////////////////////////////////////////////
//        /// couponStateRepository 구현 후 주석 제거. ///
//        /////////////////////////////////////////////
//
//        //Optional<CouponState> couponStateOptional = couponStateRepository.findByUserAndCoupon(user,coupon);
//        //assertThat(couponStateOptional.isEmpty());
//    }
//
//    @Repository
//    @Primary
//    class TestCouponRepository implements CouponRepository{
//        List<Coupon> list = new ArrayList<>();
//
//        public void addCoupon(Coupon coupon){
//            list.add(coupon);
//        }
//
//        @Override
//        public List<Coupon> findAll() {
//            return list;
//        }
//    }
//
//
//    @Setter
//    @Getter
//    public class TestCoupon {
//        private long pk;
//
//        private Category category;
//        private String name;
//
//        // 쿠폰의 갯수
//        private int count;
//    }
//}