package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "product_registry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private Long accountId;

    private String productId;

    private Double interestRate;

    private LocalDate openDate;

    private Integer monthCount;
}