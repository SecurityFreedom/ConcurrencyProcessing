package com.reserve.item.service.user;

import com.reserve.item.domain.User;


public interface UserService {
    Long join(User user);
    boolean login(String id, String password);
    boolean editUser(User preUser,Class<?> newName);
}
