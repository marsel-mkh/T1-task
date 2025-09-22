package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.ClientProductEventDto;
import com.t1.marselmkh.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", constant = "0.00")
    @Mapping(target = "interestRate", ignore = true)
    @Mapping(target = "isRecalc", ignore = true)
    @Mapping(target = "cardExist", constant = "false")
    @Mapping(source = "status", target = "status")
    Account toEntity(ClientProductEventDto dto);
}
