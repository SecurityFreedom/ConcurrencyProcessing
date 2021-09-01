package com.reserve.item.repository.impl;

import com.reserve.item.domain.Coupon;
import com.reserve.item.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponJpaRepository implements CouponRepository {

    @Override
    public List<Coupon> findAll() {
        return null;
    }
}
