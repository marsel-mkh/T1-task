package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.BlacklistRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRegistryRepository extends JpaRepository<BlacklistRegistry, Long> {
    boolean existsByDocumentId(String documentId);
}
