package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.ProductDto.ProductCreateDto;
import com.t1.marselmkh.dto.ProductDto.ProductViewDto;
import com.t1.marselmkh.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateDto dto);

    ProductViewDto toDto(Product product);
}
