package com.t1.marselmkh.dto;

import com.t1.marselmkh.entity.DocumentType;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientCreateDto {
    @NotBlank(message = "Login cannot be empty")
    @Size(min = 4, max = 20, message = "Login must be 4-20 characters")
    String login;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    String email;

    @NotBlank(message = "First name cannot be empty")
    String firstName;

    String middleName;

    @NotBlank(message = "Last name cannot be empty")
    String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    LocalDate dateOfBirth;

    @NotNull(message = "Document type is required")
    DocumentType documentType;

    @NotBlank(message = "Document ID cannot be empty")
    String documentId;

    @NotBlank(message = "Client ID cannot be empty")
    String clientId;

    String documentPrefix;
    String documentSuffix;
}
