package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.ClientProductDto.ClientProductCreateDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductEventDto;
import com.t1.marselmkh.dto.ClientProductDto.ClientProductViewDto;
import com.t1.marselmkh.entity.ClientProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientProductMapper {

    ClientProduct toEntity(ClientProductCreateDto clientProduct);

    ClientProductViewDto toDto(ClientProduct clientProduct);

    ClientProductEventDto toEventDto(ClientProduct clientProduct);
}
