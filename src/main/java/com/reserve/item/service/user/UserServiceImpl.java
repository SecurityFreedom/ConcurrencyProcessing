package com.reserve.item.service.user;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long join(User user) {
        if(isDuplicate(user))
            return 0L;

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
    public boolean isDuplicate(User user) {
        for (User find_User : userRepository.findAll())
            if(find_User.getId().equals(user.getId()))
                return true;

        return false;
    }
}
