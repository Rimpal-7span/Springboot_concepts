package com.example.restApi.custome;

import com.example.restApi.model.Address;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class CustomeUserDetail {
    private String name;
    private UUID uuid;
    private List<Address> address;
}
