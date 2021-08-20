package com.reserve.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id @GeneratedValue
    @Column(name="CATEGORY_ID")
    private long pk;

    private String name;
}
