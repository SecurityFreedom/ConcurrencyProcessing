package com.reserve.item.service.user;

import com.reserve.item.domain.User;


public interface UserService {
    Long join(User user);
    Long login(String id, String password);
    boolean editUser(User preUser,Class<?> newName);
    boolean isDuplicate(User user);
}
