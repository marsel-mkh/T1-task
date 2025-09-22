package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card,Long> {
}
