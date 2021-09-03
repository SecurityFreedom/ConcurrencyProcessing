package com.reserve.item.service.user.register;

import com.reserve.item.domain.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterPolicyImpl implements RegisterPolicy {
    @Override
    public int verifyRegister(User user) {
        // 현재 작업 중.
        verifyUserId(user.getId());
        return 1;
    }

    private boolean verifyUserId(String id) {
        return false;
    }

    private boolean verifyUserPassword(String password) {
        return false;
    }

    private boolean verifyUserEmail(String email) {
        return false;
    }
}
