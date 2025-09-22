package com.t1.marselmkh.repository;

import com.t1.marselmkh.entity.PaymentRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRegistryRepository extends JpaRepository<PaymentRegistry, Long> {

    @Query("SELECT SUM(p.debtAmount + p.interestRateAmount) FROM PaymentRegistry p " +
            "WHERE p.productRegistryId IN :registryIds AND p.expired = false")
    BigDecimal sumOutstandingByProductIds(@Param("registryIds") List<Long> registryIds);


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PaymentRegistry p " +
            "WHERE p.productRegistryId IN :registryIds " +
            "AND p.expired = false " +
            "AND p.paymentExpirationDate < :today")
    boolean existsOverduePayments(@Param("registryIds") List<Long> registryIds,
                                  @Param("today") LocalDate today);
}
