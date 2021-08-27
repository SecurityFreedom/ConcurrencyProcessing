package com.reserve.item.repository;

import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositorySDJ extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}