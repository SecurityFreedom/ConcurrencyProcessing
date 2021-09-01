package com.reserve.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CouponState {
    @Id @GeneratedValue
    @Column(name="USER_COUPON_ID")
    private long pk;

    @ManyToOne
    @JoinColumn(name="COUPON_ID")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    private int amount;
}
