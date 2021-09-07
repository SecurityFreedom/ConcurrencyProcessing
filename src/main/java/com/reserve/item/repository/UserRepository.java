package com.reserve.item.repository;

import com.reserve.item.domain.Category;
import com.reserve.item.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id=:id")
    Optional<User> findByUserid(@Param("id")String id);

    @Query("select u from User u where u.name=:name")
    Optional<User> findByName(@Param("name")String name);
}
