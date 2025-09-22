package com.t1.marselmkh.mapper;

import com.t1.marselmkh.dto.UserViewDto;
import com.t1.marselmkh.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserViewDto toUserViewDto(User user);

}
