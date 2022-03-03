package com.senai.market.model.repository;

import com.senai.market.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUuid(UUID id);

    Optional<Product> getByUuid(UUID id);
}
