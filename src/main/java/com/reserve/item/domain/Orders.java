package com.reserve.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name="order_sequence_generator",
        sequenceName = "order_sequence",
        initialValue = 1, allocationSize = 30
)
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
                        generator = "order_sequence_generator")
    @Column(name="ORDERS_ID")
    private long pk;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name="COUPON_ID")
    private Coupon coupon;
}
