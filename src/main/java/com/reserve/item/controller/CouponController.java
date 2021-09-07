package com.reserve.item.controller;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.User;
import com.reserve.item.dto.CouponDTO;
import com.reserve.item.dto.CouponInfoDTO;
import com.reserve.item.service.coupon.CouponService;
import com.reserve.item.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hehesi2007@gmail.com on 2021-09-07
 * Blog : http://mvmvm.tistory.com
 * Github : http://github.com/lsh0902
 */


@Controller
@RequiredArgsConstructor
public class CouponController {

    private UserService userService;
    private CouponService couponService;

    //쿠폰 조회
    @GetMapping("/coupons")
    @ResponseBody
    public List<CouponDTO> allCoupon() {
        List<Coupon> couponInfo = couponService.getCouponInfo();
        List<CouponDTO> res = couponInfo.stream().map(coupon -> new CouponDTO(coupon.getPk(), coupon.getName(), coupon.getCount())).collect(Collectors.toList());
        return res;
    }

    //발급
    @PostMapping("/coupons/{id}/{userId}")
    public boolean issue(@PathVariable Long id,@PathVariable String userId) {
        User currentUser = userService.findUserById(userId);
        Coupon currentCoupon = couponService.findCouponById(id);
        return couponService.getCoupon(currentUser, currentCoupon);
    }

    @GetMapping("coupons/{userId}")
    @ResponseBody
    public List<CouponInfoDTO> userCoupon(@PathVariable String userId) {
        User currentUser = userService.findUserById(userId);
        return couponService.getUserCoupon(currentUser);
    }


}
