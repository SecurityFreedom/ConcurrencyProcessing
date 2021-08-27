package com.reserve.item.repository;

import com.reserve.item.domain.Coupon;

import java.util.List;

public interface CouponRepository {
    List<Coupon> findAll();
}
