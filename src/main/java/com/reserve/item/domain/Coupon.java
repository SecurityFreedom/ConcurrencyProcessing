package com.reserve.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Coupon {
    @Id @GeneratedValue
    @Column(name="COUPON_ID")
    private long pk;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
    private String name;
}
