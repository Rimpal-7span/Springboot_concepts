package com.example.restApi.mapper;

import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
 //   UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestDtoToUser(RequestUserDto requestUserDto);

    RequestUserDto requestUserToDto(User user);

    User responseDtoToUser(ResponseUserDto responseUserDto);

    List<ResponseUserDto> responseUserToDtoList(List<User> user);
    ResponseUserDto responseUserToDto(User user);
}
