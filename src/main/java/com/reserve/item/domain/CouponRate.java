package com.reserve.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("R")
public class CouponRate extends Coupon{
    private int discountRate;

    public static CouponRate createCoupon(String name, Category category, int amount, int count){
        CouponRate c = new CouponRate();
        c.setName(name);
        c.setCategory(category);
        c.discountRate = amount;
        c.setCount(count);
        return c;
    }

    @Override
    public void changeDiscount(int amount) {
        this.discountRate = amount;
    }

    @Override
    public int getDiscountValue(int itemPrice) {
        return itemPrice * (100 - discountRate) / 100;
    }
}
