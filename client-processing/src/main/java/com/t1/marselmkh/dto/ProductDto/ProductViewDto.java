package com.t1.marselmkh.dto.ProductDto;

import com.t1.marselmkh.entity.ProductKey;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductViewDto {
    String name;
    ProductKey key;
    LocalDate createDate;
}
