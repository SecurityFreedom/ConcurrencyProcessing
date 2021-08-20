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
        name="member_sequence_generator",
        sequenceName = "member_sequence",
        initialValue = 1, allocationSize = 30
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
                        generator = "member_sequence_generator")
    @Column(name="MEMBER_ID")
    private long pk;

    private String id;
    private String name;
    private String password;
    private String email;
}
