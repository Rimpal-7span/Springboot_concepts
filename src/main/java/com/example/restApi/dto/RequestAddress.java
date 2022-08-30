package com.example.restApi.dto;

import com.example.restApi.model.User;
import lombok.Data;

import java.util.UUID;
@Data
public class RequestAddress {
    private int id;
    private UUID uuid;
    private int houseNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private int pincode;
    private RequestUserDto user;


}
