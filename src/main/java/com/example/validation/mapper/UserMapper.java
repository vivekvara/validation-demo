package com.example.validation.mapper;

import com.example.validation.dto.UserDto;
import com.example.validation.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);

}
