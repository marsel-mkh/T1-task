package com.t1.marselmkh.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEventDto {
    @NotNull
    Long accountId;

    @NotNull
    @Size(min = 1, max = 32)
    String cardId;

    @NotNull
    @Size(min = 1, max = 16)
    String paymentSystem;

    @NotNull
    @Size(min = 1, max = 16)
    String status;
}