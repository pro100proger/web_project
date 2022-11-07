package com.microservice.eurekaclient.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.microservice.eurekaclient.dto.UserDTO;
import com.microservice.eurekaclient.entity.User;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    User dtoToEntity(UserDTO userDTO);
    UserDTO entityToDto(User user);
}