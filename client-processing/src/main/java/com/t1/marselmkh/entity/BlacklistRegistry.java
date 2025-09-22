package com.t1.marselmkh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "blacklist_registry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DocumentType documentType;

    @Column(nullable = false, unique = true, length = 50)
    private String documentId;

    @Column(nullable = false)
    private LocalDateTime blacklistedAt;

    private String reason;

    private LocalDateTime blacklistExpirationDate;
}
