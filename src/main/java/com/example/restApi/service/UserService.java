package com.example.restApi.service;

import com.example.restApi.custome.CustomeUserDetail;
import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.enumclass.UserType;
import com.example.restApi.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public  interface UserService {
    public ResponseUserDto addUser(RequestUserDto requestUserDto) ;
    public List<ResponseUserDto> getUser();

    public ResponseUserDto findByUuid(UUID uuid);
    public Optional<User> findById(Long id);
    public ResponseUserDto updateUser(RequestUserDto requestUserDto);
    public  void deleteUser(Long id);
    public ResponseUserDto patchUser(RequestUserDto requestUserDto);

    public Page<User> getPagingData();

    public List<ResponseUserDto> getSortedData();
    public List<ResponseUserDto> findAllUser();
    public List<ResponseUserDto> getByNameAndUserType(String name, UserType userType);
    public List<ResponseUserDto> findUserByName(String name);
    //public CustomeUserDetail customeUserDetail(UUID uuid);

    public Optional<User> findByIdLazy(Long id);
    public User findByIdLazyusingBuilder(Long id);
     public void deleteAddress(Long id);

}
