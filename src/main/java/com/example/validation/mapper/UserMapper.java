package com.example.validation.mapper;

import com.example.validation.dto.UserDto;
import com.example.validation.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);

    User map(UserDto user);

    List<UserDto> map(List<User> users);

}
