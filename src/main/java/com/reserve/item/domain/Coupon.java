package com.reserve.item.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PROTECTED)
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Coupon {
    @Id @GeneratedValue
    @Column(name="COUPON_ID")
    private long pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
    private String name;

    // 쿠폰의 갯수
    private int count;

    /**
     * 할인율, 할인금액 변경 시 사용
     */
    public abstract void changeDiscount(int amount);

    public abstract int getDiscountValue(int itemPrice);
}
