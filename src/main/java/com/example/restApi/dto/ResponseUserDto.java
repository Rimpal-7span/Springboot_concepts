package com.example.restApi.dto;

import com.example.restApi.model.Website;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResponseUserDto {

    private UUID uuid;
    private String name;
}
