package com.reserve.item.service.coupon;

import com.reserve.item.domain.Coupon;
import com.reserve.item.domain.CouponState;
import com.reserve.item.domain.User;
import com.reserve.item.repository.CouponRepositorySDJ;
import com.reserve.item.repository.CouponStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepositorySDJ couponRepository;
    private final CouponStateRepository couponStateRepository;

    @Override
    public List<Coupon> getCouponInfo() {
        return couponRepository.findAll();
    }

    @Override
    public boolean getCoupon(User user, Coupon coupon) {
        Optional<CouponState> couponStateOptional = couponStateRepository.findByUserAndCoupon(user,coupon);
        return couponStateOptional.map((couponState) -> {
            // 사용자가 발급 받은 쿠폰의 개수 상태 < 쿠폰의 발급 제한 개수
            if(couponState.getIssuedAmount() < coupon.getCount()){
                couponStateRepository.increaseAmount(user,coupon);
                return true;
            }else{
                // 초과해서 발급 받으려고 함.
                return false;
            }
        }).orElseGet(()->{  // 쿠폰 state에 레코드가 없을 때. -> 쿠폰을 발급 받은 적이 없음.
            couponStateRepository.create(user,coupon);
            return true;
        });
    }
}
