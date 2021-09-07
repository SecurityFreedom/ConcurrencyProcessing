package com.reserve.item.controller;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.Item;
import com.reserve.item.domain.User;
import com.reserve.item.service.coupon.CouponService;
import com.reserve.item.service.item.ItemService;
import com.reserve.item.service.order.OrderService;
import com.reserve.item.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CouponService couponService;
    private final ItemService itemService;

    @PostMapping("/orders")
    @ResponseBody
    public String make_order(@RequestBody OrderForm orderForm){
        String userId = orderForm.getId();
        Long item_pk = orderForm.getItem_pk();
        Long coupon_pk = orderForm.getCoupon_pk();

        User user = userService.findByUserId(userId);
        Item item = itemService.findItemByPk(item_pk);
        Coupon coupon = couponService.findCouponByPk(coupon_pk);


        orderService.createOrder(user, item, coupon);
    }

}
