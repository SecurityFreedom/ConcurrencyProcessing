package com.reserve.item.service.user;

import com.reserve.item.domain.User;

import java.util.Optional;


public interface UserService {
    Long join(User user);
    Optional<User> findUserById(String id);
    //Optional<User> findPasswordByEmail(String email);
    //JWT login(String id, String password);
    //User editUser(User preUser, User newUser, JWT);
}
