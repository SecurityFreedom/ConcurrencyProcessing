package com.reserve.item.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@DiscriminatorValue("F")
public class CouponFixed extends Coupon{
    private int discountAmount;

    public static CouponFixed CreateCoupon(String name, Category category, int amount, int count){
        CouponFixed c = new CouponFixed();
        c.setName(name);
        c.setCategory(category);
        c.discountAmount = amount;
        c.setCount(count);
        return c;
    }


    public void changeDiscount(int amount) {
        this.discountAmount = amount;
    }

}
