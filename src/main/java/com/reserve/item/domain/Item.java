package com.reserve.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private long pk;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    private int quantity;
    private int price;
}
