package com.t1.marselmkh.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardCreateDto {
    Long accountId;
    String cardId;
    String paymentSystem;
    String status;

}
