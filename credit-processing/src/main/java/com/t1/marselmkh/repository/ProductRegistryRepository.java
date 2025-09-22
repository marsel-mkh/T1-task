package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.ProductRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRegistryRepository  extends JpaRepository<ProductRegistry, Long> {
    List<ProductRegistry> findAllByClientId(String clientId);
}
