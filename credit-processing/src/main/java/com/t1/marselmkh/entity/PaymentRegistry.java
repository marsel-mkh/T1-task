package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment_registry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productRegistryId;

    private LocalDate paymendDate;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(precision = 19, scale = 2)
    private BigDecimal interestRateAmount;

    @Column(precision = 19, scale = 2)
    private BigDecimal debtAmount;

    private Boolean expired;

    private LocalDate paymentExpirationDate;
}
