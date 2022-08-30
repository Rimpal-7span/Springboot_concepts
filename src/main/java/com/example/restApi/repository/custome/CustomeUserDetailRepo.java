package com.example.restApi.repository.custome;

import com.example.restApi.custome.CustomeUserDetail;
import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.enumclass.UserType;

import java.util.List;
import java.util.UUID;

public interface CustomeUserDetailRepo {
    public CustomeUserDetail userDetail(UUID uuid);
    public List<ResponseUserDto> getNameAndUserType(String name, UserType userType);
}
