package com.reserve.item.service.user.register;

import com.reserve.item.domain.User;

public interface RegisterPolicy {
    int verifyRegister(User user);
}
