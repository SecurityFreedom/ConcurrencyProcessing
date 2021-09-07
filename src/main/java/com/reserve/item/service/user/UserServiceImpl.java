package com.reserve.item.service.user;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public boolean login(String id, String password) {
        Optional<User> byUserid = userRepository.findByUserid(id);
        if(byUserid.isPresent()){
            User user = byUserid.get();
            if (user.getPassword() == password)
                return true;
            else
                return false;
        } else {
            return false;
        }
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
