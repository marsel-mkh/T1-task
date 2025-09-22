package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.ClientProductEventDto;
import com.t1.marselmkh.entity.ProductRegistry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRegistryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "openDate", expression = "java(java.time.LocalDate.now())")
    ProductRegistry toEntity(ClientProductEventDto dto);


}
