package com.t1.marselmkh.dto;

import com.t1.marselmkh.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProductEventDto {

    @NotNull(message = "clientId is required")
    Long clientId;

    @NotNull(message = "productId is required")
    Long productId;

    @NotBlank(message = "status cannot be blank")
    Status status;
}
