package com.reserve.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private long pk;

    private String id;
    private String name;
    private String password;
    private String email;
}
