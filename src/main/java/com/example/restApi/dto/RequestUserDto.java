package com.example.restApi.dto;

import com.example.restApi.enumclass.Gender;
import com.example.restApi.enumclass.UserType;
import com.example.restApi.model.Address;
import com.example.restApi.model.Credential;
import com.example.restApi.model.Website;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RequestUserDto {

   private Long id;

    @NotEmpty (message = " name should not be empty")
   private String name;
    private UUID uuid;
    @Email
    private String email;
    private Gender gender;
    private UserType userType;
    private List<RequestAddress> address;
    private List<Website> websites;
    private Credential credential;
}
