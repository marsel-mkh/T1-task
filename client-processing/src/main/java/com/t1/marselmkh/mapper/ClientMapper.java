package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.ClientCreateDto;
import com.t1.marselmkh.entity.Client;
import com.t1.marselmkh.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Client toClientEntity(ClientCreateDto clientCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toUserEntity(ClientCreateDto request);
}
