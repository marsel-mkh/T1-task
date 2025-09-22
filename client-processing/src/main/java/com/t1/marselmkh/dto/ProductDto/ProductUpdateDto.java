package com.t1.marselmkh.dto.ProductDto;


import com.t1.marselmkh.entity.ProductKey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "ProductKey is mandatory")
    private ProductKey key;
}
