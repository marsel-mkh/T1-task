package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "client_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String productId;

    private LocalDate openDate;
    private LocalDate closeDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
