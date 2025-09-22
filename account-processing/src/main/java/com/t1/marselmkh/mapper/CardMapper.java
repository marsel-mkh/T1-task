package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.CardEventDto;
import com.t1.marselmkh.entity.Card;
import com.t1.marselmkh.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(target = "id", ignore = true)
    Card toEntity(CardEventDto dto);

    default Status mapStatus(String status) {
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (Exception e) {
            return Status.BLOCKED;
        }
    }
}
