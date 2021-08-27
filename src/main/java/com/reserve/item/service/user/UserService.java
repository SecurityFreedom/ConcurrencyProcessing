package com.reserve.item.service;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserService {
    Long join(User user);
    Optional<User> findUserById(String id);
    Optional<User> findUserByEmail(String email);
}
