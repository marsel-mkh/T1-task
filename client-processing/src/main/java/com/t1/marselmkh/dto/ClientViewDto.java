package com.t1.marselmkh.dto;

import com.t1.marselmkh.entity.DocumentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientViewDto {
    String firstName;
    String middleName;
    String lastName;
    LocalDate dateOfBirth;
    DocumentType documentType;
    String documentId;
    String documentPrefix;
    String documentSuffix;
}