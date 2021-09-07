package com.reserve.item.service.order;

import com.reserve.item.domain.*;
import com.reserve.item.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
class OrderServiceImplTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    CouponStateRepository couponStateRepository;

    @Test
    @DisplayName("Orders 생성테스트")
    @Transactional
    void createOrder() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 19900,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);

        assertThat(orderService.createOrder(user, item, coupon)).isNotEmpty();
    }

    //검증테스트
    @Test
    @DisplayName("Orders 검증 - 정상")
    @Transactional
    void verifyOrder() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 19900, "item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(true),
                () -> fail("createOrder 가 Optional.empty()를 반환"));
    }


    @Test
    @DisplayName("Orders 검증 - 해당 쿠폰 미존재")
    @Transactional
    void verifyOrder2() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 19900, "item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(false),
                () -> fail("createOrder 가 Optional.empty()를 반환"));

        orderService.createOrder(user, item, new CouponFixed()).ifPresentOrElse(
                (orders) -> assertThrows(InvalidDataAccessApiUsageException.class, () -> orderService.verifyOrder(orders)),
                () -> fail("createOrder 가 Optional.empty()를 반환"));
    }

    @Test
    @DisplayName("Orders 검증 - 할인 받아도 부족")
    @Transactional
    void verifyOrder3() {
        User user = User.createUser("id", "name", "password", "email");
        user.setAccount(1000L);
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(false),
                () -> fail("createOrder 가 Optional.empty()를 반환"));
    }

    @Test
    @DisplayName("Orders 검증 - 할인 받아야 가능(Fix)")
    @Transactional
    void verifyOrder4() {
        User user = User.createUser("id", "name", "password", "email");
        user.setAccount(19500L);
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(true),
                () -> fail("createOrder 가 Optional.empty()를 반환"));

    }

    @Test
    @DisplayName("Orders 검증 - 할인 받아야 가능(Rate)")
    @Transactional
    void verifyOrder5() {
        User user = User.createUser("id", "name", "password", "email");
        user.setAccount(19500L);
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponRate.createCoupon("겨울맞이쿠폰", category, 10, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(true),
                () -> fail("createOrder 가 Optional.empty()를 반환"));
    }

    @Test
    @DisplayName("Orders 검증 - 할인 받고 경계 값(Fix)")
    @Transactional
    void verifyOrder6() {
        User user = User.createUser("id", "name", "password", "email");
        user.setAccount(19000L);
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(true),
                () -> fail("createOrder 가 Optional.empty()를 반환")
        );

    }

    @Test
    @DisplayName("Orders 검증 - 할인 받고 경계 값(Rate)")
    @Transactional
    void verifyOrder7() {
        User user = User.createUser("id", "name", "password", "email");
        user.setAccount(18000L);
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponRate.createCoupon("겨울맞이쿠폰", category, 10, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(true),
                () -> fail("createOrder 가 Optional.empty()를 반환")
        );
    }

    @Test
    @DisplayName("Orders 검증 - 아이템 수량 부족")
    @Transactional
    void verifyOrder9() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 0, 20000,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(false),
                () -> fail("createOrder 가 Optional.empty()를 반환")
        );
    }

    @Test
    @DisplayName("Orders 검증 - 카테고리 불일치")
    @Transactional
    void verifyOrder8() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Category category2 = new Category();
        category2.setName("필기구");
        categoryRepository.save(category2);

        Item item = Item.createItem(category, 100, 19900,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category2, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> assertThat(orderService.verifyOrder(orders)).isEqualTo(false),
                () -> fail("createOrder 가 Optional.empty()를 반환")
        );
    }

    //반영테스트
    @Test
    @DisplayName("Orders 반영테스트")
    @Transactional
    void acceptOrder() {
        User user = User.createUser("id", "name", "password", "email");
        userRepository.save(user);

        Category category = new Category();
        category.setName("음식");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 19900,"item");
        itemRepository.save(item);

        Coupon coupon = CouponFixed.createCoupon("겨울맞이쿠폰", category, 1000, 3);
        couponRepository.save(coupon);

        CouponState couponState = CouponState.issueNewCoupon(user, coupon);
        couponStateRepository.save(couponState);

        orderService.createOrder(user, item, coupon).ifPresentOrElse(
                (orders) -> {
                    assertThat(orderService.verifyOrder(orders)).isEqualTo(true);
                    orderService.acceptOrder(orders);
                    assertThat(orderRepository.getById(orders.getPk())).isNotNull();
                },
                () -> fail("createOrder 가 Optional.empty()를 반환")
        );
    }
}