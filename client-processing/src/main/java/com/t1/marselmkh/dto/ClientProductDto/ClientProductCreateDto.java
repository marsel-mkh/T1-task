package com.t1.marselmkh.dto.ClientProductDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientProductCreateDto {

    @NotBlank(message = "ClientId is mandatory")
    String clientId;
    @NotBlank(message = "ProductId is mandatory")
    String productId;
}
