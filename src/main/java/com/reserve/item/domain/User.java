package com.reserve.item.domain;

import lombok.*;

import javax.persistence.*;

//생성자 및 생성메소드 추가.

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SequenceGenerator(
        name="member_sequence_generator",
        sequenceName = "member_sequence",
        initialValue = 1, allocationSize = 30
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE
                        ,generator = "member_sequence_generator")
    @Column(name="USER_ID")
    private long pk;

    private String id;
    private String name;
    private String password;
    private String email;
    private Long account;

    public static User createUser(String id, String name, String password, String email) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setAccount(100000000L);
        return user;
    }

    public void setAccount(Long account){
        this.account = account;
    }
}
