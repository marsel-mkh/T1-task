package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);

    boolean existByProductId(String productId);
}
