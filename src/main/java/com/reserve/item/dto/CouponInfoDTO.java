package com.reserve.item.dto;

import lombok.Getter;

/**
 * Created by hehesi2007@gmail.com on 2021-09-07
 * Blog : http://mvmvm.tistory.com
 * Github : http://github.com/lsh0902
 */
@Getter
public class CouponInfoDTO {
    private String couponname;
    private int currentAmount;
    private Long pk;

    public CouponInfoDTO(int currentAmount, String name, Long pk) {
        this.currentAmount = currentAmount;
        couponname = name;
        this.pk =pk;
    }
}
