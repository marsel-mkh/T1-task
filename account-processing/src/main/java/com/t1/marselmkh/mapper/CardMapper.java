package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.CardEventDto;
import com.t1.marselmkh.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "status", target = "status")
    Card toEntity(CardEventDto dto);
}
