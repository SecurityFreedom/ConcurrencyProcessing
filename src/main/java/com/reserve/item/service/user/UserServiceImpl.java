package com.reserve.item.service.user;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getPk();
    }

    @Override
    public Object login(String id, String password) {
        return null;
    }

    @Override
    public boolean editUser(User preUser,Class<?> newName){
        ////////////////////////////
        return false;
    }
}
