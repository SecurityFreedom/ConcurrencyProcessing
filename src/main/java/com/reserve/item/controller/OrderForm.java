package com.reserve.item.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    private String id;
    private Long item_pk;
    private Long coupon_pk;
}
