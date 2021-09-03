package com.reserve.item.service.user;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import com.reserve.item.service.user.register.RegisterPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RegisterPolicy registerPolicy;

    @Override
    @Transactional
    public Long join(User user) {
        // 중복 검사 등 verify 필요. -> 적절하지 않으면 -1 반환.
        userRepository.save(user);
        return user.getPk();
    }

    @Override
    public Long login(String id, String password) {
        return 1L;
    }

    @Override
    public boolean editUser(User preUser,Class<?> newName){
        ////////////////////////////
        return false;
    }

    @Override
    public boolean verifyJoinUserInfo(User user) {
        user.getId();
        return false;
    }
}
