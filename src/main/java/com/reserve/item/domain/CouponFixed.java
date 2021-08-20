package com.reserve.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("F")
public class CouponFixed extends Coupon{
    private int discountAmount;
}
