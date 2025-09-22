package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private Long productId;

    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    private Double interestRate;

    private Boolean isRecalc;

    private Boolean cardExist;

    @Enumerated(EnumType.STRING)
    private Status status;
}
