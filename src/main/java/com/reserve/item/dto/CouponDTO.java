package com.reserve.item.dto;

import lombok.Getter;

/**
 * Created by hehesi2007@gmail.com on 2021-09-07
 * Blog : http://mvmvm.tistory.com
 * Github : http://github.com/lsh0902
 */
@Getter
public class CouponDTO {
    private Long id;
    private String name;
    private int count;
    public CouponDTO(Long id,String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
