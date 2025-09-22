package com.t1.marselmkh.dto;

import com.t1.marselmkh.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardEventDto {

    @NotNull(message = "accountId is required")
    Long accountId;

    @NotBlank(message = "cardId cannot be blank")
    String cardId;

    @NotBlank(message = "paymentSystem cannot be blank")
    String paymentSystem;

    @NotBlank(message = "status cannot be blank")
    Status status;

}
