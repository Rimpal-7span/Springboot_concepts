package com.example.restApi.mapper;

import com.example.restApi.dto.RequestAddress;
import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseAddress;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.model.Address;
import com.example.restApi.model.User;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address requestDtoToUser(RequestAddress requestAddress);

    RequestAddress requestAddressToDto(Address address);

    Address responseDtoToUser(ResponseAddress responseAddress);

    ResponseAddress responseAddressToDto(Address address);

}
