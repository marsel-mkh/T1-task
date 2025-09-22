package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String clientId;

    private Long userId;

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentId;
    private String documentPrefix;
    private String documentSuffix;

}
