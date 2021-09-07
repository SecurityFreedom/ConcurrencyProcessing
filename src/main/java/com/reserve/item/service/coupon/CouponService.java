package com.reserve.item.service.coupon;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import com.reserve.item.dto.CouponInfoDTO;

import java.util.List;

public interface CouponService {
    List<Coupon> getCouponInfo();
    boolean getCoupon(User user, Coupon coupon);
    List<CouponInfoDTO> getUserCoupon(User user);
    Coupon findCouponById(Long id);
}
