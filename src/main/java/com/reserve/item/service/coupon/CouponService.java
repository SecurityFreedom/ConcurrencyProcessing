package com.reserve.item.service.coupon;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.User;

import java.util.List;

public interface CouponService {
    List<Coupon> getCouponInfo();
    boolean getCoupon(User user, Coupon coupon);
}
