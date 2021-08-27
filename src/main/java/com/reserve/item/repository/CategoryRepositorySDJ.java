package com.reserve.item.repository;

import com.reserve.item.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorySDJ extends JpaRepository<Category, Long> {

}
