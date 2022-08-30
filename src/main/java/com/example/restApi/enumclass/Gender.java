package com.example.restApi.enumclass;

import lombok.Getter;
@Getter
public enum Gender {
    MALE(1, "MALE","Male"),
    FEMALE(2,"FEMALE","Female"),
    TRANSGENDER(3,"TRANSGENDER","Transgender");
    private long id;
    private String name;
    private  String value;

    Gender(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
