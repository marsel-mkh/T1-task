package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.ClientProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {
}
