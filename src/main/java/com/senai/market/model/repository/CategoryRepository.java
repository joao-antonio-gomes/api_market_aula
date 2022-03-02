package com.senai.market.model.repository;

import com.senai.market.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c FROM Category c WHERE UPPER(c.name) LIKE UPPER(?1)", nativeQuery = false)
    Optional<Category> findCategoryByName(String name);
}
